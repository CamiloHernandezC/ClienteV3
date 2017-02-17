/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "EPS_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EPSCli.findAll", query = "SELECT e FROM EPSCli e"),
    @NamedQuery(name = "EPSCli.findByEps", query = "SELECT e FROM EPSCli e WHERE e.eps = :eps"),
    @NamedQuery(name = "EPSCli.findByDescripcion", query = "SELECT e FROM EPSCli e WHERE e.descripcion = :descripcion")})
public class EPSCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "EPS")
    private String eps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "eps", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;

    public EPSCli() {
    }

    public EPSCli(String eps) {
        this.eps = eps;
    }

    public EPSCli(String eps, String descripcion) {
        this.eps = eps;
        this.descripcion = descripcion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PersonasCli> getPersonasCliList() {
        return personasCliList;
    }

    public void setPersonasCliList(List<PersonasCli> personasCliList) {
        this.personasCliList = personasCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eps != null ? eps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EPSCli)) {
            return false;
        }
        EPSCli other = (EPSCli) object;
        if ((this.eps == null && other.eps != null) || (this.eps != null && !this.eps.equals(other.eps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EPSCli[ eps=" + eps + " ]";
    }
    
}
