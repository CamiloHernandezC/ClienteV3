/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Kmilo
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n"),
    @NamedQuery(name = "Notificaciones.findByIdNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.idNotificacion = :idNotificacion"),
    @NamedQuery(name = "Notificaciones.findByTipoEvento", query = "SELECT n FROM Notificaciones n WHERE n.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "Notificaciones.findByFechaDesde", query = "SELECT n FROM Notificaciones n WHERE n.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Notificaciones.findByFechaHasta", query = "SELECT n FROM Notificaciones n WHERE n.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Notificaciones.findByHoraDesde", query = "SELECT n FROM Notificaciones n WHERE n.horaDesde = :horaDesde"),
    @NamedQuery(name = "Notificaciones.findByHoraHasta", query = "SELECT n FROM Notificaciones n WHERE n.horaHasta = :horaHasta"),
    @NamedQuery(name = "Notificaciones.findByMail", query = "SELECT n FROM Notificaciones n WHERE n.mail = :mail"),
    @NamedQuery(name = "Notificaciones.findByAsunto", query = "SELECT n FROM Notificaciones n WHERE n.asunto = :asunto"),
    @NamedQuery(name = "Notificaciones.findByMensaje", query = "SELECT n FROM Notificaciones n WHERE n.mensaje = :mensaje"),
    @NamedQuery(name = "Notificaciones.findByFecha", query = "SELECT n FROM Notificaciones n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Notificaciones.findByMostrarSucursal", query = "SELECT n FROM Notificaciones n WHERE n.mostrarSucursal = :mostrarSucursal"),
    @NamedQuery(name = "Notificaciones.findByMostrarPorteria", query = "SELECT n FROM Notificaciones n WHERE n.mostrarPorteria = :mostrarPorteria"),
    @NamedQuery(name = "Notificaciones.findByMostrarEmpresaOrigen", query = "SELECT n FROM Notificaciones n WHERE n.mostrarEmpresaOrigen = :mostrarEmpresaOrigen"),
    @NamedQuery(name = "Notificaciones.findByMostrarEntidad", query = "SELECT n FROM Notificaciones n WHERE n.mostrarEntidad = :mostrarEntidad"),
    @NamedQuery(name = "Notificaciones.findByMostrarEnte", query = "SELECT n FROM Notificaciones n WHERE n.mostrarEnte = :mostrarEnte")})
public class Notificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Notificacion")
    private Integer idNotificacion;
    @Column(name = "Tipo_Evento")
    private Character tipoEvento;
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
    @Column(name = "Hora_Desde")
    @Temporal(TemporalType.TIME)
    private Date horaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Hasta")
    @Temporal(TemporalType.TIME)
    private Date horaHasta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "Mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Asunto")
    private String asunto;
    @Size(max = 140)
    @Column(name = "Mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar_Sucursal")
    private boolean mostrarSucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar_Porteria")
    private boolean mostrarPorteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar_Empresa_Origen")
    private boolean mostrarEmpresaOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar_Entidad")
    private boolean mostrarEntidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar_Ente")
    private boolean mostrarEnte;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Objeto", referencedColumnName = "Id_Objeto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Objetos objeto;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Porteria", referencedColumnName = "Id_Porteria")
    @ManyToOne(fetch = FetchType.LAZY)
    private Porterias porteria;
    @JoinColumn(name = "Empresa_Origen", referencedColumnName = "Id_Empresa_Origen")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaOrigen empresaOrigen;
    @JoinColumn(name = "Categoria", referencedColumnName = "Id_Categoria")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorias categoria;
    @JoinColumn(name = "Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Entidades entidad;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Persona", referencedColumnName = "Id_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas persona;
    @JoinColumn(name = "Vehiculo", referencedColumnName = "Placa")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehiculos vehiculo;

    public Notificaciones() {
    }

    public Notificaciones(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificaciones(Integer idNotificacion, Date fechaDesde, Date fechaHasta, Date horaDesde, Date horaHasta, String mail, String asunto, Date fecha, boolean mostrarSucursal, boolean mostrarPorteria, boolean mostrarEmpresaOrigen, boolean mostrarEntidad, boolean mostrarEnte) {
        this.idNotificacion = idNotificacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.mail = mail;
        this.asunto = asunto;
        this.fecha = fecha;
        this.mostrarSucursal = mostrarSucursal;
        this.mostrarPorteria = mostrarPorteria;
        this.mostrarEmpresaOrigen = mostrarEmpresaOrigen;
        this.mostrarEntidad = mostrarEntidad;
        this.mostrarEnte = mostrarEnte;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Character getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Character tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getMostrarSucursal() {
        return mostrarSucursal;
    }

    public void setMostrarSucursal(boolean mostrarSucursal) {
        this.mostrarSucursal = mostrarSucursal;
    }

    public boolean getMostrarPorteria() {
        return mostrarPorteria;
    }

    public void setMostrarPorteria(boolean mostrarPorteria) {
        this.mostrarPorteria = mostrarPorteria;
    }

    public boolean getMostrarEmpresaOrigen() {
        return mostrarEmpresaOrigen;
    }

    public void setMostrarEmpresaOrigen(boolean mostrarEmpresaOrigen) {
        this.mostrarEmpresaOrigen = mostrarEmpresaOrigen;
    }

    public boolean getMostrarEntidad() {
        return mostrarEntidad;
    }

    public void setMostrarEntidad(boolean mostrarEntidad) {
        this.mostrarEntidad = mostrarEntidad;
    }

    public boolean getMostrarEnte() {
        return mostrarEnte;
    }

    public void setMostrarEnte(boolean mostrarEnte) {
        this.mostrarEnte = mostrarEnte;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Objetos getObjeto() {
        return objeto;
    }

    public void setObjeto(Objetos objeto) {
        this.objeto = objeto;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Porterias getPorteria() {
        return porteria;
    }

    public void setPorteria(Porterias porteria) {
        this.porteria = porteria;
    }

    public EmpresaOrigen getEmpresaOrigen() {
        return empresaOrigen;
    }

    public void setEmpresaOrigen(EmpresaOrigen empresaOrigen) {
        this.empresaOrigen = empresaOrigen;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Entidades getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidades entidad) {
        this.entidad = entidad;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Notificaciones[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
