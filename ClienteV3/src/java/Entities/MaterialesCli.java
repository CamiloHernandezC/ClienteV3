/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Materiales_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialesCli.findAll", query = "SELECT m FROM MaterialesCli m"),
    @NamedQuery(name = "MaterialesCli.findByIdMaterial", query = "SELECT m FROM MaterialesCli m WHERE m.idMaterial = :idMaterial"),
    @NamedQuery(name = "MaterialesCli.findByIdExterno", query = "SELECT m FROM MaterialesCli m WHERE m.idExterno = :idExterno"),
    @NamedQuery(name = "MaterialesCli.findByDescripcion", query = "SELECT m FROM MaterialesCli m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaterialesCli.findByAdministrar", query = "SELECT m FROM MaterialesCli m WHERE m.administrar = :administrar"),
    @NamedQuery(name = "MaterialesCli.findByFecha", query = "SELECT m FROM MaterialesCli m WHERE m.fecha = :fecha")})
public class MaterialesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Material")
    private String idMaterial;
    @Size(max = 40)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Administrar")
    private boolean administrar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;
    @JoinColumn(name = "Unidad", referencedColumnName = "Id_Unidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadesCli unidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterial", fetch = FetchType.LAZY)
    private List<MovMaterialesCli> movMaterialesCliList;

    public MaterialesCli() {
    }

    public MaterialesCli(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public MaterialesCli(String idMaterial, String descripcion, boolean administrar, Date fecha) {
        this.idMaterial = idMaterial;
        this.descripcion = descripcion;
        this.administrar = administrar;
        this.fecha = fecha;
    }

    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public UnidadesCli getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadesCli unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public List<MovMaterialesCli> getMovMaterialesCliList() {
        return movMaterialesCliList;
    }

    public void setMovMaterialesCliList(List<MovMaterialesCli> movMaterialesCliList) {
        this.movMaterialesCliList = movMaterialesCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialesCli)) {
            return false;
        }
        MaterialesCli other = (MaterialesCli) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MaterialesCli[ idMaterial=" + idMaterial + " ]";
    }
    
}
