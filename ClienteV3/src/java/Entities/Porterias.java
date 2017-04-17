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
 * @author Kmilo
 */
@Entity
@Table(name = "porterias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porterias.findAll", query = "SELECT p FROM Porterias p"),
    @NamedQuery(name = "Porterias.findByIdPorteria", query = "SELECT p FROM Porterias p WHERE p.idPorteria = :idPorteria"),
    @NamedQuery(name = "Porterias.findByDescripcion", query = "SELECT p FROM Porterias p WHERE p.descripcion = :descripcion")})
public class Porterias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Porteria")
    private Integer idPorteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porterias", fetch = FetchType.LAZY)
    private List<PorteriasSucursal> porteriasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porteria", fetch = FetchType.LAZY)
    private List<ConfigForm> configFormList;
    @OneToMany(mappedBy = "porteria", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;

    public Porterias() {
    }

    public Porterias(Integer idPorteria) {
        this.idPorteria = idPorteria;
    }

    public Porterias(Integer idPorteria, String descripcion) {
        this.idPorteria = idPorteria;
        this.descripcion = descripcion;
    }

    public Integer getIdPorteria() {
        return idPorteria;
    }

    public void setIdPorteria(Integer idPorteria) {
        this.idPorteria = idPorteria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PorteriasSucursal> getPorteriasSucursalList() {
        return porteriasSucursalList;
    }

    public void setPorteriasSucursalList(List<PorteriasSucursal> porteriasSucursalList) {
        this.porteriasSucursalList = porteriasSucursalList;
    }

    @XmlTransient
    public List<ConfigForm> getConfigFormList() {
        return configFormList;
    }

    public void setConfigFormList(List<ConfigForm> configFormList) {
        this.configFormList = configFormList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorteria != null ? idPorteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porterias)) {
            return false;
        }
        Porterias other = (Porterias) object;
        if ((this.idPorteria == null && other.idPorteria != null) || (this.idPorteria != null && !this.idPorteria.equals(other.idPorteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Porterias[ idPorteria=" + idPorteria + " ]";
    }
    
}
