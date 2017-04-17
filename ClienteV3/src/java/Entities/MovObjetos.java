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
@Table(name = "mov_objetos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovObjetos.findAll", query = "SELECT m FROM MovObjetos m"),
    @NamedQuery(name = "MovObjetos.findByIdMovimiento", query = "SELECT m FROM MovObjetos m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovObjetos.findByIdObjeto", query = "SELECT m FROM MovObjetos m WHERE m.idObjeto = :idObjeto"),
    @NamedQuery(name = "MovObjetos.findByFechaEntrada", query = "SELECT m FROM MovObjetos m WHERE m.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "MovObjetos.findByHoraEntrada", query = "SELECT m FROM MovObjetos m WHERE m.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "MovObjetos.findByFechaSalida", query = "SELECT m FROM MovObjetos m WHERE m.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "MovObjetos.findByHoraSalida", query = "SELECT m FROM MovObjetos m WHERE m.horaSalida = :horaSalida"),
    @NamedQuery(name = "MovObjetos.findByObservacionEntrada", query = "SELECT m FROM MovObjetos m WHERE m.observacionEntrada = :observacionEntrada"),
    @NamedQuery(name = "MovObjetos.findByObservacionSalida", query = "SELECT m FROM MovObjetos m WHERE m.observacionSalida = :observacionSalida"),
    @NamedQuery(name = "MovObjetos.findBySalidaForzada", query = "SELECT m FROM MovObjetos m WHERE m.salidaForzada = :salidaForzada"),
    @NamedQuery(name = "MovObjetos.findByIngresoForzado", query = "SELECT m FROM MovObjetos m WHERE m.ingresoForzado = :ingresoForzado"),
    @NamedQuery(name = "MovObjetos.findByFecha", query = "SELECT m FROM MovObjetos m WHERE m.fecha = :fecha")})
public class MovObjetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Objeto")
    private int idObjeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Entrada")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Column(name = "Fecha_Salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "Hora_Salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Size(max = 140)
    @Column(name = "Observacion_Entrada")
    private String observacionEntrada;
    @Size(max = 140)
    @Column(name = "Observacion_Salida")
    private String observacionSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_Forzada")
    private boolean salidaForzada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ingreso_Forzado")
    private boolean ingresoForzado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Mov_Persona_Entrada", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonas movPersonaEntrada;
    @JoinColumn(name = "Mov_Persona_Salida", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovPersonas movPersonaSalida;

    public MovObjetos() {
    }

    public MovObjetos(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovObjetos(Integer idMovimiento, int idObjeto, Date fechaEntrada, Date horaEntrada, boolean salidaForzada, boolean ingresoForzado, Date fecha) {
        this.idMovimiento = idMovimiento;
        this.idObjeto = idObjeto;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.salidaForzada = salidaForzada;
        this.ingresoForzado = ingresoForzado;
        this.fecha = fecha;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservacionEntrada() {
        return observacionEntrada;
    }

    public void setObservacionEntrada(String observacionEntrada) {
        this.observacionEntrada = observacionEntrada;
    }

    public String getObservacionSalida() {
        return observacionSalida;
    }

    public void setObservacionSalida(String observacionSalida) {
        this.observacionSalida = observacionSalida;
    }

    public boolean getSalidaForzada() {
        return salidaForzada;
    }

    public void setSalidaForzada(boolean salidaForzada) {
        this.salidaForzada = salidaForzada;
    }

    public boolean getIngresoForzado() {
        return ingresoForzado;
    }

    public void setIngresoForzado(boolean ingresoForzado) {
        this.ingresoForzado = ingresoForzado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public MovPersonas getMovPersonaEntrada() {
        return movPersonaEntrada;
    }

    public void setMovPersonaEntrada(MovPersonas movPersonaEntrada) {
        this.movPersonaEntrada = movPersonaEntrada;
    }

    public MovPersonas getMovPersonaSalida() {
        return movPersonaSalida;
    }

    public void setMovPersonaSalida(MovPersonas movPersonaSalida) {
        this.movPersonaSalida = movPersonaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovObjetos)) {
            return false;
        }
        MovObjetos other = (MovObjetos) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovObjetos[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
