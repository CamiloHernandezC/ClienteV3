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
@Table(name = "ARL_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ARLCli.findAll", query = "SELECT a FROM ARLCli a"),
    @NamedQuery(name = "ARLCli.findByArl", query = "SELECT a FROM ARLCli a WHERE a.arl = :arl"),
    @NamedQuery(name = "ARLCli.findByDescripcion", query = "SELECT a FROM ARLCli a WHERE a.descripcion = :descripcion")})
public class ARLCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "ARL")
    private String arl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "arl", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;

    public ARLCli() {
    }

    public ARLCli(String arl) {
        this.arl = arl;
    }

    public ARLCli(String arl, String descripcion) {
        this.arl = arl;
        this.descripcion = descripcion;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
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
        hash += (arl != null ? arl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ARLCli)) {
            return false;
        }
        ARLCli other = (ARLCli) object;
        if ((this.arl == null && other.arl != null) || (this.arl != null && !this.arl.equals(other.arl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ARLCli[ arl=" + arl + " ]";
    }
    
}
