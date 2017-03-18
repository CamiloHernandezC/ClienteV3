/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Unidades_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadesCli.findAll", query = "SELECT u FROM UnidadesCli u"),
    @NamedQuery(name = "UnidadesCli.findByIdUnidad", query = "SELECT u FROM UnidadesCli u WHERE u.idUnidad = :idUnidad"),
    @NamedQuery(name = "UnidadesCli.findByTipoUnidad", query = "SELECT u FROM UnidadesCli u WHERE u.tipoUnidad = :tipoUnidad"),
    @NamedQuery(name = "UnidadesCli.findByUnidadSIAB", query = "SELECT u FROM UnidadesCli u WHERE u.unidadSIAB = :unidadSIAB"),
    @NamedQuery(name = "UnidadesCli.findByDescripcion", query = "SELECT u FROM UnidadesCli u WHERE u.descripcion = :descripcion")})
public class UnidadesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Unidad")
    private Integer idUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Unidad")
    private long tipoUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Unidad_SI_AB")
    private String unidadSIAB;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "unidadPeso", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(mappedBy = "unidadVolumen", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad", fetch = FetchType.LAZY)
    private List<MaterialesCli> materialesCliList;

    public UnidadesCli() {
    }

    public UnidadesCli(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public UnidadesCli(Integer idUnidad, long tipoUnidad, String unidadSIAB, String descripcion) {
        this.idUnidad = idUnidad;
        this.tipoUnidad = tipoUnidad;
        this.unidadSIAB = unidadSIAB;
        this.descripcion = descripcion;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public long getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(long tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getUnidadSIAB() {
        return unidadSIAB;
    }

    public void setUnidadSIAB(String unidadSIAB) {
        this.unidadSIAB = unidadSIAB;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList() {
        return vehiculosCliList;
    }

    public void setVehiculosCliList(List<VehiculosCli> vehiculosCliList) {
        this.vehiculosCliList = vehiculosCliList;
    }

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList1() {
        return vehiculosCliList1;
    }

    public void setVehiculosCliList1(List<VehiculosCli> vehiculosCliList1) {
        this.vehiculosCliList1 = vehiculosCliList1;
    }

    @XmlTransient
    public List<MaterialesCli> getMaterialesCliList() {
        return materialesCliList;
    }

    public void setMaterialesCliList(List<MaterialesCli> materialesCliList) {
        this.materialesCliList = materialesCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadesCli)) {
            return false;
        }
        UnidadesCli other = (UnidadesCli) object;
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.UnidadesCli[ idUnidad=" + idUnidad + " ]";
    }
    
}
