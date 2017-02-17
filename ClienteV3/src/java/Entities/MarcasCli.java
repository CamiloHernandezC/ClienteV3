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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Marcas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarcasCli.findAll", query = "SELECT m FROM MarcasCli m"),
    @NamedQuery(name = "MarcasCli.findByIdMarca", query = "SELECT m FROM MarcasCli m WHERE m.idMarca = :idMarca"),
    @NamedQuery(name = "MarcasCli.findByDescripcion", query = "SELECT m FROM MarcasCli m WHERE m.descripcion = :descripcion")})
public class MarcasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "Id_Marca")
    private String idMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idMarca", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @JoinColumn(name = "Id_Categoria", referencedColumnName = "Id_Categoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriasCli idCategoria;
    @OneToMany(mappedBy = "idMarca", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarca", fetch = FetchType.LAZY)
    private List<LineasCli> lineasCliList;

    public MarcasCli() {
    }

    public MarcasCli(String idMarca) {
        this.idMarca = idMarca;
    }

    public MarcasCli(String idMarca, String descripcion) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
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

    public CategoriasCli getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriasCli idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @XmlTransient
    public List<LineasCli> getLineasCliList() {
        return lineasCliList;
    }

    public void setLineasCliList(List<LineasCli> lineasCliList) {
        this.lineasCliList = lineasCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcasCli)) {
            return false;
        }
        MarcasCli other = (MarcasCli) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MarcasCli[ idMarca=" + idMarca + " ]";
    }
    
}
