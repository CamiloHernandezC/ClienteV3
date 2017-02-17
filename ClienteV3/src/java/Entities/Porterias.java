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
@Table(name = "Porterias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porterias.findAll", query = "SELECT p FROM Porterias p"),
    @NamedQuery(name = "Porterias.findByIdPorteria", query = "SELECT p FROM Porterias p WHERE p.idPorteria = :idPorteria"),
    @NamedQuery(name = "Porterias.findByEquipoAsignado", query = "SELECT p FROM Porterias p WHERE p.equipoAsignado = :equipoAsignado"),
    @NamedQuery(name = "Porterias.findByDescripcion", query = "SELECT p FROM Porterias p WHERE p.descripcion = :descripcion")})
public class Porterias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Porteria")
    private Long idPorteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Equipo_Asignado")
    private String equipoAsignado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idPorteria", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porteria", fetch = FetchType.LAZY)
    private List<ConfigFormCli> configFormCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porterias", fetch = FetchType.LAZY)
    private List<PorteriaSucursalCli> porteriaSucursalCliList;

    public Porterias() {
    }

    public Porterias(Long idPorteria) {
        this.idPorteria = idPorteria;
    }

    public Porterias(Long idPorteria, String equipoAsignado, String descripcion) {
        this.idPorteria = idPorteria;
        this.equipoAsignado = equipoAsignado;
        this.descripcion = descripcion;
    }

    public Long getIdPorteria() {
        return idPorteria;
    }

    public void setIdPorteria(Long idPorteria) {
        this.idPorteria = idPorteria;
    }

    public String getEquipoAsignado() {
        return equipoAsignado;
    }

    public void setEquipoAsignado(String equipoAsignado) {
        this.equipoAsignado = equipoAsignado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
    }

    @XmlTransient
    public List<ConfigFormCli> getConfigFormCliList() {
        return configFormCliList;
    }

    public void setConfigFormCliList(List<ConfigFormCli> configFormCliList) {
        this.configFormCliList = configFormCliList;
    }

    @XmlTransient
    public List<PorteriaSucursalCli> getPorteriaSucursalCliList() {
        return porteriaSucursalCliList;
    }

    public void setPorteriaSucursalCliList(List<PorteriaSucursalCli> porteriaSucursalCliList) {
        this.porteriaSucursalCliList = porteriaSucursalCliList;
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
