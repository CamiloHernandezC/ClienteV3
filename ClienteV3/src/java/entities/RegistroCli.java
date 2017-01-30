/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chernandez
 */
@Entity
@Table(name = "Registro_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroCli.findAll", query = "SELECT r FROM RegistroCli r"),
    @NamedQuery(name = "RegistroCli.findByIdPorteria", query = "SELECT r FROM RegistroCli r WHERE r.idPorteria = :idPorteria"),
    @NamedQuery(name = "RegistroCli.findByServidor", query = "SELECT r FROM RegistroCli r WHERE r.servidor = :servidor"),
    @NamedQuery(name = "RegistroCli.findByPuerto", query = "SELECT r FROM RegistroCli r WHERE r.puerto = :puerto"),
    @NamedQuery(name = "RegistroCli.findByFechainstalacion", query = "SELECT r FROM RegistroCli r WHERE r.fechainstalacion = :fechainstalacion"),
    @NamedQuery(name = "RegistroCli.findByHash", query = "SELECT r FROM RegistroCli r WHERE r.hash = :hash"),
    @NamedQuery(name = "RegistroCli.findByUsuario", query = "SELECT r FROM RegistroCli r WHERE r.usuario = :usuario")})
public class RegistroCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Id_Porteria")
    private String idPorteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Servidor")
    private String servidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "Puerto")
    private String puerto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_instalacion")
    @Temporal(TemporalType.DATE)
    private Date fechainstalacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Hash")
    private String hash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Usuario")
    private String usuario;

    public RegistroCli() {
    }

    public RegistroCli(String idPorteria) {
        this.idPorteria = idPorteria;
    }

    public RegistroCli(String idPorteria, String servidor, String puerto, Date fechainstalacion, String hash, String usuario) {
        this.idPorteria = idPorteria;
        this.servidor = servidor;
        this.puerto = puerto;
        this.fechainstalacion = fechainstalacion;
        this.hash = hash;
        this.usuario = usuario;
    }

    public String getIdPorteria() {
        return idPorteria;
    }

    public void setIdPorteria(String idPorteria) {
        this.idPorteria = idPorteria;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public Date getFechainstalacion() {
        return fechainstalacion;
    }

    public void setFechainstalacion(Date fechainstalacion) {
        this.fechainstalacion = fechainstalacion;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof RegistroCli)) {
            return false;
        }
        RegistroCli other = (RegistroCli) object;
        if ((this.idPorteria == null && other.idPorteria != null) || (this.idPorteria != null && !this.idPorteria.equals(other.idPorteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegistroCli[ idPorteria=" + idPorteria + " ]";
    }
    
}
