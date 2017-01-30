/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author chernandez
 */
@Entity
@Table(name = "Lineas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineasCli.findAll", query = "SELECT l FROM LineasCli l"),
    @NamedQuery(name = "LineasCli.findByIdMarca", query = "SELECT l FROM LineasCli l WHERE l.lineasCliPK.idMarca = :idMarca"),
    @NamedQuery(name = "LineasCli.findByIdLinea", query = "SELECT l FROM LineasCli l WHERE l.lineasCliPK.idLinea = :idLinea"),
    @NamedQuery(name = "LineasCli.findByDescripcion", query = "SELECT l FROM LineasCli l WHERE l.descripcion = :descripcion")})
public class LineasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineasCliPK lineasCliPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "lineasCli")
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(mappedBy = "lineasCli")
    private List<ObjetosCli> objetosCliList;
    @JoinColumn(name = "Id_Marca", referencedColumnName = "Id_Marca", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MarcasCli marcasCli;

    public LineasCli() {
    }

    public LineasCli(LineasCliPK lineasCliPK) {
        this.lineasCliPK = lineasCliPK;
    }

    public LineasCli(LineasCliPK lineasCliPK, String descripcion) {
        this.lineasCliPK = lineasCliPK;
        this.descripcion = descripcion;
    }

    public LineasCli(String idMarca, String idLinea) {
        this.lineasCliPK = new LineasCliPK(idMarca, idLinea);
    }

    public LineasCliPK getLineasCliPK() {
        return lineasCliPK;
    }

    public void setLineasCliPK(LineasCliPK lineasCliPK) {
        this.lineasCliPK = lineasCliPK;
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
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    public MarcasCli getMarcasCli() {
        return marcasCli;
    }

    public void setMarcasCli(MarcasCli marcasCli) {
        this.marcasCli = marcasCli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineasCliPK != null ? lineasCliPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineasCli)) {
            return false;
        }
        LineasCli other = (LineasCli) object;
        if ((this.lineasCliPK == null && other.lineasCliPK != null) || (this.lineasCliPK != null && !this.lineasCliPK.equals(other.lineasCliPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LineasCli[ lineasCliPK=" + lineasCliPK + " ]";
    }
    
}
