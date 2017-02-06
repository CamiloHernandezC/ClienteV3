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
@Table(name = "Notificaciones_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionesCli.findAll", query = "SELECT n FROM NotificacionesCli n"),
    @NamedQuery(name = "NotificacionesCli.findByIdNotificacion", query = "SELECT n FROM NotificacionesCli n WHERE n.idNotificacion = :idNotificacion"),
    @NamedQuery(name = "NotificacionesCli.findByTipoEvento", query = "SELECT n FROM NotificacionesCli n WHERE n.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "NotificacionesCli.findByIdSucursal", query = "SELECT n FROM NotificacionesCli n WHERE n.idSucursal = :idSucursal"),
    @NamedQuery(name = "NotificacionesCli.findByIdPorteria", query = "SELECT n FROM NotificacionesCli n WHERE n.idPorteria = :idPorteria"),
    @NamedQuery(name = "NotificacionesCli.findByIdEmpresaOrigen", query = "SELECT n FROM NotificacionesCli n WHERE n.idEmpresaOrigen = :idEmpresaOrigen"),
    @NamedQuery(name = "NotificacionesCli.findByIdCategoria", query = "SELECT n FROM NotificacionesCli n WHERE n.idCategoria = :idCategoria"),
    @NamedQuery(name = "NotificacionesCli.findByIdEntidad", query = "SELECT n FROM NotificacionesCli n WHERE n.idEntidad = :idEntidad"),
    @NamedQuery(name = "NotificacionesCli.findByIdEnte", query = "SELECT n FROM NotificacionesCli n WHERE n.idEnte = :idEnte"),
    @NamedQuery(name = "NotificacionesCli.findByFechaDesde", query = "SELECT n FROM NotificacionesCli n WHERE n.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "NotificacionesCli.findByFechaHasta", query = "SELECT n FROM NotificacionesCli n WHERE n.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "NotificacionesCli.findByHoraDesde", query = "SELECT n FROM NotificacionesCli n WHERE n.horaDesde = :horaDesde"),
    @NamedQuery(name = "NotificacionesCli.findByHoraHasta", query = "SELECT n FROM NotificacionesCli n WHERE n.horaHasta = :horaHasta"),
    @NamedQuery(name = "NotificacionesCli.findByMail", query = "SELECT n FROM NotificacionesCli n WHERE n.mail = :mail"),
    @NamedQuery(name = "NotificacionesCli.findByAsunto", query = "SELECT n FROM NotificacionesCli n WHERE n.asunto = :asunto"),
    @NamedQuery(name = "NotificacionesCli.findByMensaje", query = "SELECT n FROM NotificacionesCli n WHERE n.mensaje = :mensaje"),
    @NamedQuery(name = "NotificacionesCli.findByUsuario", query = "SELECT n FROM NotificacionesCli n WHERE n.usuario = :usuario"),
    @NamedQuery(name = "NotificacionesCli.findByFecha", query = "SELECT n FROM NotificacionesCli n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "NotificacionesCli.findByBSucursal", query = "SELECT n FROM NotificacionesCli n WHERE n.bSucursal = :bSucursal"),
    @NamedQuery(name = "NotificacionesCli.findByBPorteria", query = "SELECT n FROM NotificacionesCli n WHERE n.bPorteria = :bPorteria"),
    @NamedQuery(name = "NotificacionesCli.findByBEmpresaOrigen", query = "SELECT n FROM NotificacionesCli n WHERE n.bEmpresaOrigen = :bEmpresaOrigen"),
    @NamedQuery(name = "NotificacionesCli.findByBEntidad", query = "SELECT n FROM NotificacionesCli n WHERE n.bEntidad = :bEntidad"),
    @NamedQuery(name = "NotificacionesCli.findByBEnte", query = "SELECT n FROM NotificacionesCli n WHERE n.bEnte = :bEnte")})
public class NotificacionesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Notificacion")
    private Long idNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Evento")
    private Character tipoEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Porteria")
    private long idPorteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Id_Empresa_Origen")
    private String idEmpresaOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Id_Categoria")
    private String idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Id_Entidad")
    private String idEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Ente")
    private String idEnte;
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
    @Size(min = 1, max = 200)
    @Column(name = "Mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_Sucursal")
    private boolean bSucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_Porteria")
    private boolean bPorteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_Empresa_Origen")
    private boolean bEmpresaOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_Entidad")
    private boolean bEntidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_Ente")
    private boolean bEnte;

    public NotificacionesCli() {
    }

    public NotificacionesCli(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public NotificacionesCli(Long idNotificacion, Character tipoEvento, long idSucursal, long idPorteria, String idEmpresaOrigen, String idCategoria, String idEntidad, String idEnte, Date fechaDesde, Date fechaHasta, Date horaDesde, Date horaHasta, String mail, String asunto, String mensaje, String usuario, Date fecha, boolean bSucursal, boolean bPorteria, boolean bEmpresaOrigen, boolean bEntidad, boolean bEnte) {
        this.idNotificacion = idNotificacion;
        this.tipoEvento = tipoEvento;
        this.idSucursal = idSucursal;
        this.idPorteria = idPorteria;
        this.idEmpresaOrigen = idEmpresaOrigen;
        this.idCategoria = idCategoria;
        this.idEntidad = idEntidad;
        this.idEnte = idEnte;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.mail = mail;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.fecha = fecha;
        this.bSucursal = bSucursal;
        this.bPorteria = bPorteria;
        this.bEmpresaOrigen = bEmpresaOrigen;
        this.bEntidad = bEntidad;
        this.bEnte = bEnte;
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Character getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Character tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public long getIdPorteria() {
        return idPorteria;
    }

    public void setIdPorteria(long idPorteria) {
        this.idPorteria = idPorteria;
    }

    public String getIdEmpresaOrigen() {
        return idEmpresaOrigen;
    }

    public void setIdEmpresaOrigen(String idEmpresaOrigen) {
        this.idEmpresaOrigen = idEmpresaOrigen;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getBSucursal() {
        return bSucursal;
    }

    public void setBSucursal(boolean bSucursal) {
        this.bSucursal = bSucursal;
    }

    public boolean getBPorteria() {
        return bPorteria;
    }

    public void setBPorteria(boolean bPorteria) {
        this.bPorteria = bPorteria;
    }

    public boolean getBEmpresaOrigen() {
        return bEmpresaOrigen;
    }

    public void setBEmpresaOrigen(boolean bEmpresaOrigen) {
        this.bEmpresaOrigen = bEmpresaOrigen;
    }

    public boolean getBEntidad() {
        return bEntidad;
    }

    public void setBEntidad(boolean bEntidad) {
        this.bEntidad = bEntidad;
    }

    public boolean getBEnte() {
        return bEnte;
    }

    public void setBEnte(boolean bEnte) {
        this.bEnte = bEnte;
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
        if (!(object instanceof NotificacionesCli)) {
            return false;
        }
        NotificacionesCli other = (NotificacionesCli) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotificacionesCli[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
