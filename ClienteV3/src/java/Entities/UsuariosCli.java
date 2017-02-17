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
@Table(name = "Usuarios_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosCli.findAll", query = "SELECT u FROM UsuariosCli u"),
    @NamedQuery(name = "UsuariosCli.findByIdUsuario", query = "SELECT u FROM UsuariosCli u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuariosCli.findByPrivilegios", query = "SELECT u FROM UsuariosCli u WHERE u.privilegios = :privilegios"),
    @NamedQuery(name = "UsuariosCli.findByPassword", query = "SELECT u FROM UsuariosCli u WHERE u.password = :password"),
    @NamedQuery(name = "UsuariosCli.findByFechaDesde", query = "SELECT u FROM UsuariosCli u WHERE u.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "UsuariosCli.findByFechaHasta", query = "SELECT u FROM UsuariosCli u WHERE u.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "UsuariosCli.findBySesion", query = "SELECT u FROM UsuariosCli u WHERE u.sesion = :sesion"),
    @NamedQuery(name = "UsuariosCli.findByFecha", query = "SELECT u FROM UsuariosCli u WHERE u.fecha = :fecha")})
public class UsuariosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Id_Usuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Privilegios")
    private short privilegios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sesion")
    private boolean sesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PrivilegiosCliente> privilegiosClienteList;
    @JoinColumn(name = "Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli idEstado;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli idPersona;

    public UsuariosCli() {
    }

    public UsuariosCli(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuariosCli(String idUsuario, short privilegios, String password, Date fechaDesde, Date fechaHasta, boolean sesion, Date fecha) {
        this.idUsuario = idUsuario;
        this.privilegios = privilegios;
        this.password = password;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.sesion = sesion;
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public short getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(short privilegios) {
        this.privilegios = privilegios;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public boolean getSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<PrivilegiosCliente> getPrivilegiosClienteList() {
        return privilegiosClienteList;
    }

    public void setPrivilegiosClienteList(List<PrivilegiosCliente> privilegiosClienteList) {
        this.privilegiosClienteList = privilegiosClienteList;
    }

    public EstadosCli getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosCli idEstado) {
        this.idEstado = idEstado;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public PersonasCli getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonasCli idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosCli)) {
            return false;
        }
        UsuariosCli other = (UsuariosCli) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.UsuariosCli[ idUsuario=" + idUsuario + " ]";
    }
    
}
