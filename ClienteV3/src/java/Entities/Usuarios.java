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
 * @author Kmilo
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByPrivilegios", query = "SELECT u FROM Usuarios u WHERE u.privilegios = :privilegios"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByFechaDesde", query = "SELECT u FROM Usuarios u WHERE u.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Usuarios.findByFechaHasta", query = "SELECT u FROM Usuarios u WHERE u.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Usuarios.findBySesion", query = "SELECT u FROM Usuarios u WHERE u.sesion = :sesion"),
    @NamedQuery(name = "Usuarios.findByIDSesion", query = "SELECT u FROM Usuarios u WHERE u.iDSesion = :iDSesion"),
    @NamedQuery(name = "Usuarios.findByMail", query = "SELECT u FROM Usuarios u WHERE u.mail = :mail"),
    @NamedQuery(name = "Usuarios.findByFecha", query = "SELECT u FROM Usuarios u WHERE u.fecha = :fecha")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Id_Usuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Privilegios")
    private int privilegios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
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
    @Size(min = 1, max = 140)
    @Column(name = "ID_Sesion")
    private String iDSesion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "Mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Persona", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas persona;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Usuario_Modifica", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuarioModifica;
    @JoinColumn(name = "Tema", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Theme tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PrivilegiosCliente> privilegiosClienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<AccesoUsuario> accesoUsuarioList;

    public Usuarios() {
    }

    public Usuarios(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(String idUsuario, int privilegios, String password, Date fechaDesde, Date fechaHasta, boolean sesion, String iDSesion, String mail, Date fecha) {
        this.idUsuario = idUsuario;
        this.privilegios = privilegios;
        this.password = password;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.sesion = sesion;
        this.iDSesion = iDSesion;
        this.mail = mail;
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(int privilegios) {
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

    public String getIDSesion() {
        return iDSesion;
    }

    public void setIDSesion(String iDSesion) {
        this.iDSesion = iDSesion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Personas getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(Personas usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Theme getTema() {
        return tema;
    }

    public void setTema(Theme tema) {
        this.tema = tema;
    }

    @XmlTransient
    public List<PrivilegiosCliente> getPrivilegiosClienteList() {
        return privilegiosClienteList;
    }

    public void setPrivilegiosClienteList(List<PrivilegiosCliente> privilegiosClienteList) {
        this.privilegiosClienteList = privilegiosClienteList;
    }

    @XmlTransient
    public List<AccesoUsuario> getAccesoUsuarioList() {
        return accesoUsuarioList;
    }

    public void setAccesoUsuarioList(List<AccesoUsuario> accesoUsuarioList) {
        this.accesoUsuarioList = accesoUsuarioList;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
