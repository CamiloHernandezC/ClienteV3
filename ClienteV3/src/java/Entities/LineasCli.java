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
@Table(name = "Lineas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineasCli.findAll", query = "SELECT l FROM LineasCli l"),
    @NamedQuery(name = "LineasCli.findByIdLinea", query = "SELECT l FROM LineasCli l WHERE l.idLinea = :idLinea"),
    @NamedQuery(name = "LineasCli.findByDescripcion", query = "SELECT l FROM LineasCli l WHERE l.descripcion = :descripcion")})
public class LineasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Id_Linea")
    private String idLinea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idLinea", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(mappedBy = "idLinea", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;
    @JoinColumn(name = "Id_Marca", referencedColumnName = "Id_Marca")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MarcasCli idMarca;

    public LineasCli() {
    }

    public LineasCli(String idLinea) {
        this.idLinea = idLinea;
    }

    public LineasCli(String idLinea, String descripcion) {
        this.idLinea = idLinea;
        this.descripcion = descripcion;
    }

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
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

    public MarcasCli getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(MarcasCli idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinea != null ? idLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineasCli)) {
            return false;
        }
        LineasCli other = (LineasCli) object;
        if ((this.idLinea == null && other.idLinea != null) || (this.idLinea != null && !this.idLinea.equals(other.idLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.LineasCli[ idLinea=" + idLinea + " ]";
    }
    
}
