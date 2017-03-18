/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Materiales_Sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialesSucursal.findAll", query = "SELECT m FROM MaterialesSucursal m"),
    @NamedQuery(name = "MaterialesSucursal.findById", query = "SELECT m FROM MaterialesSucursal m WHERE m.id = :id"),
    @NamedQuery(name = "MaterialesSucursal.findByAdministrar", query = "SELECT m FROM MaterialesSucursal m WHERE m.administrar = :administrar"),
    @NamedQuery(name = "MaterialesSucursal.findByFecha", query = "SELECT m FROM MaterialesSucursal m WHERE m.fecha = :fecha")})
public class MaterialesSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Administrar")
    private boolean administrar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Id_Material", referencedColumnName = "Id_Material")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaterialesCli idMaterial;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;

    public MaterialesSucursal() {
    }

    public MaterialesSucursal(Long id) {
        this.id = id;
    }

    public MaterialesSucursal(Long id, boolean administrar, Date fecha) {
        this.id = id;
        this.administrar = administrar;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAdministrar() {
        return administrar;
    }

    public void setAdministrar(boolean administrar) {
        this.administrar = administrar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MaterialesCli getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(MaterialesCli idMaterial) {
        this.idMaterial = idMaterial;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialesSucursal)) {
            return false;
        }
        MaterialesSucursal other = (MaterialesSucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MaterialesSucursal[ id=" + id + " ]";
    }
    
}
