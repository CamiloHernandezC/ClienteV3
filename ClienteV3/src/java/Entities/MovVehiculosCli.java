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
 * @author MAURICIO
 */
@Entity
@Table(name = "Mov_Vehiculos_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovVehiculosCli.findAll", query = "SELECT m FROM MovVehiculosCli m"),
    @NamedQuery(name = "MovVehiculosCli.findByIdMovimiento", query = "SELECT m FROM MovVehiculosCli m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovVehiculosCli.findByFechaEntrada", query = "SELECT m FROM MovVehiculosCli m WHERE m.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "MovVehiculosCli.findByHoraEntrada", query = "SELECT m FROM MovVehiculosCli m WHERE m.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "MovVehiculosCli.findByFechaSalida", query = "SELECT m FROM MovVehiculosCli m WHERE m.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "MovVehiculosCli.findByHoraSalida", query = "SELECT m FROM MovVehiculosCli m WHERE m.horaSalida = :horaSalida"),
    @NamedQuery(name = "MovVehiculosCli.findByObservacinEntrada", query = "SELECT m FROM MovVehiculosCli m WHERE m.observacinEntrada = :observacinEntrada"),
    @NamedQuery(name = "MovVehiculosCli.findByObservacionSalida", query = "SELECT m FROM MovVehiculosCli m WHERE m.observacionSalida = :observacionSalida"),
    @NamedQuery(name = "MovVehiculosCli.findByFecha", query = "SELECT m FROM MovVehiculosCli m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MovVehiculosCli.findBySalidaForzosa", query = "SELECT m FROM MovVehiculosCli m WHERE m.salidaForzosa = :salidaForzosa")})
public class MovVehiculosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento")
    private Long idMovimiento;
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
    @Size(max = 2147483647)
    @Column(name = "Observacin_Entrada")
    private String observacinEntrada;
    @Size(max = 2147483647)
    @Column(name = "Observacion_Salida")
    private String observacionSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_Forzosa")
    private boolean salidaForzosa;
    @JoinColumn(name = "Id_Mov_Entrada", referencedColumnName = "Id_Movimiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonasCli idMovEntrada;
    @JoinColumn(name = "Id_Mov_Salida", referencedColumnName = "Id_Movimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovPersonasCli idMovSalida;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;
    @JoinColumn(name = "Id_Vehiculo", referencedColumnName = "Id_Vehiculo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VehiculosCli idVehiculo;

    public MovVehiculosCli() {
    }

    public MovVehiculosCli(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovVehiculosCli(Long idMovimiento, Date fechaEntrada, Date horaEntrada, Date fecha, boolean salidaForzosa) {
        this.idMovimiento = idMovimiento;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fecha = fecha;
        this.salidaForzosa = salidaForzosa;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
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

    public String getObservacinEntrada() {
        return observacinEntrada;
    }

    public void setObservacinEntrada(String observacinEntrada) {
        this.observacinEntrada = observacinEntrada;
    }

    public String getObservacionSalida() {
        return observacionSalida;
    }

    public void setObservacionSalida(String observacionSalida) {
        this.observacionSalida = observacionSalida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getSalidaForzosa() {
        return salidaForzosa;
    }

    public void setSalidaForzosa(boolean salidaForzosa) {
        this.salidaForzosa = salidaForzosa;
    }

    public MovPersonasCli getIdMovEntrada() {
        return idMovEntrada;
    }

    public void setIdMovEntrada(MovPersonasCli idMovEntrada) {
        this.idMovEntrada = idMovEntrada;
    }

    public MovPersonasCli getIdMovSalida() {
        return idMovSalida;
    }

    public void setIdMovSalida(MovPersonasCli idMovSalida) {
        this.idMovSalida = idMovSalida;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
    }

    public VehiculosCli getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(VehiculosCli idVehiculo) {
        this.idVehiculo = idVehiculo;
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
        if (!(object instanceof MovVehiculosCli)) {
            return false;
        }
        MovVehiculosCli other = (MovVehiculosCli) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovVehiculosCli[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
