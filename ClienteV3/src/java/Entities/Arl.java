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
 * @author Kmilo
 */
@Entity
@Table(name = "arl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arl.findAll", query = "SELECT a FROM Arl a"),
    @NamedQuery(name = "Arl.findByArl", query = "SELECT a FROM Arl a WHERE a.arl = :arl"),
    @NamedQuery(name = "Arl.findByCodigo", query = "SELECT a FROM Arl a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Arl.findByNombre", query = "SELECT a FROM Arl a WHERE a.nombre = :nombre")})
public class Arl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARL")
    private Integer arl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "arl", fetch = FetchType.LAZY)
    private List<Personas> personasList;

    public Arl() {
    }

    public Arl(Integer arl) {
        this.arl = arl;
    }

    public Arl(Integer arl, String codigo, String nombre) {
        this.arl = arl;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getArl() {
        return arl;
    }

    public void setArl(Integer arl) {
        this.arl = arl;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
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
        if (!(object instanceof Arl)) {
            return false;
        }
        Arl other = (Arl) object;
        if ((this.arl == null && other.arl != null) || (this.arl != null && !this.arl.equals(other.arl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Arl[ arl=" + arl + " ]";
    }
    
}
