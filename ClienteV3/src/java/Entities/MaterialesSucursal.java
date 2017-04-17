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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Kmilo
 */
@Entity
@Table(name = "materiales_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialesSucursal.findAll", query = "SELECT m FROM MaterialesSucursal m"),
    @NamedQuery(name = "MaterialesSucursal.findByIdMaterial", query = "SELECT m FROM MaterialesSucursal m WHERE m.materialesSucursalPK.idMaterial = :idMaterial"),
    @NamedQuery(name = "MaterialesSucursal.findByIdSucursal", query = "SELECT m FROM MaterialesSucursal m WHERE m.materialesSucursalPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "MaterialesSucursal.findByAdministrar", query = "SELECT m FROM MaterialesSucursal m WHERE m.administrar = :administrar"),
    @NamedQuery(name = "MaterialesSucursal.findByFecha", query = "SELECT m FROM MaterialesSucursal m WHERE m.fecha = :fecha")})
public class MaterialesSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaterialesSucursalPK materialesSucursalPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Administrar")
    private boolean administrar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Id_Material", referencedColumnName = "Id_Material", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materiales materiales;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursales;

    public MaterialesSucursal() {
    }

    public MaterialesSucursal(MaterialesSucursalPK materialesSucursalPK) {
        this.materialesSucursalPK = materialesSucursalPK;
    }

    public MaterialesSucursal(MaterialesSucursalPK materialesSucursalPK, boolean administrar, Date fecha) {
        this.materialesSucursalPK = materialesSucursalPK;
        this.administrar = administrar;
        this.fecha = fecha;
    }

    public MaterialesSucursal(int idMaterial, int idSucursal) {
        this.materialesSucursalPK = new MaterialesSucursalPK(idMaterial, idSucursal);
    }

    public MaterialesSucursalPK getMaterialesSucursalPK() {
        return materialesSucursalPK;
    }

    public void setMaterialesSucursalPK(MaterialesSucursalPK materialesSucursalPK) {
        this.materialesSucursalPK = materialesSucursalPK;
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

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Materiales getMateriales() {
        return materiales;
    }

    public void setMateriales(Materiales materiales) {
        this.materiales = materiales;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialesSucursalPK != null ? materialesSucursalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialesSucursal)) {
            return false;
        }
        MaterialesSucursal other = (MaterialesSucursal) object;
        if ((this.materialesSucursalPK == null && other.materialesSucursalPK != null) || (this.materialesSucursalPK != null && !this.materialesSucursalPK.equals(other.materialesSucursalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MaterialesSucursal[ materialesSucursalPK=" + materialesSucursalPK + " ]";
    }
    
}
