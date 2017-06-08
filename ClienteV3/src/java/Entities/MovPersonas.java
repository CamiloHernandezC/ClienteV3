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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "mov_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovPersonas.findAll", query = "SELECT m FROM MovPersonas m"),
    @NamedQuery(name = "MovPersonas.findByIdMovPersona", query = "SELECT m FROM MovPersonas m WHERE m.idMovPersona = :idMovPersona"),
    @NamedQuery(name = "MovPersonas.findByFechaEntrada", query = "SELECT m FROM MovPersonas m WHERE m.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "MovPersonas.findByHoraEntrada", query = "SELECT m FROM MovPersonas m WHERE m.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "MovPersonas.findByFechaSalida", query = "SELECT m FROM MovPersonas m WHERE m.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "MovPersonas.findByHoraSalida", query = "SELECT m FROM MovPersonas m WHERE m.horaSalida = :horaSalida"),
    @NamedQuery(name = "MovPersonas.findBySalidaForzosa", query = "SELECT m FROM MovPersonas m WHERE m.salidaForzosa = :salidaForzosa"),
    @NamedQuery(name = "MovPersonas.findByIngresoForzado", query = "SELECT m FROM MovPersonas m WHERE m.ingresoForzado = :ingresoForzado"),
    @NamedQuery(name = "MovPersonas.findByFecha", query = "SELECT m FROM MovPersonas m WHERE m.fecha = :fecha")})
public class MovPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mov_Persona")
    private Integer idMovPersona;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_Forzosa")
    private boolean salidaForzosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ingreso_Forzado")
    private boolean ingresoForzado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movPersona", fetch = FetchType.LAZY)
    private List<MovRemisiones> movRemisionesList;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Area", referencedColumnName = "Id_Area_Empresa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreasEmpresa area;
    @JoinColumn(name = "Persona_Autoriza", referencedColumnName = "Id_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas personaAutoriza;
    @JoinColumns({
        @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona"),
        @JoinColumn(name = "Sucursal", referencedColumnName = "Sucursal")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasSucursal personasSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoPersona", fetch = FetchType.LAZY)
    private List<MovDocumentos> movDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movPersonaEntrada", fetch = FetchType.LAZY)
    private List<MovObjetos> movObjetosList;
    @OneToMany(mappedBy = "movPersonaSalida", fetch = FetchType.LAZY)
    private List<MovObjetos> movObjetosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movPersonaEntrada", fetch = FetchType.LAZY)
    private List<MovVehiculos> movVehiculosList;
    @OneToMany(mappedBy = "movPersonaSalida", fetch = FetchType.LAZY)
    private List<MovVehiculos> movVehiculosList1;

    public MovPersonas() {
    }

    public MovPersonas(Integer idMovPersona) {
        this.idMovPersona = idMovPersona;
    }

    public MovPersonas(Integer idMovPersona, Date fechaEntrada, Date horaEntrada, boolean salidaForzosa, boolean ingresoForzado, Date fecha) {
        this.idMovPersona = idMovPersona;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.salidaForzosa = salidaForzosa;
        this.ingresoForzado = ingresoForzado;
        this.fecha = fecha;
    }

    public Integer getIdMovPersona() {
        return idMovPersona;
    }

    public void setIdMovPersona(Integer idMovPersona) {
        this.idMovPersona = idMovPersona;
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

    public boolean getSalidaForzosa() {
        return salidaForzosa;
    }

    public void setSalidaForzosa(boolean salidaForzosa) {
        this.salidaForzosa = salidaForzosa;
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

    @XmlTransient
    public List<MovRemisiones> getMovRemisionesList() {
        return movRemisionesList;
    }

    public void setMovRemisionesList(List<MovRemisiones> movRemisionesList) {
        this.movRemisionesList = movRemisionesList;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public AreasEmpresa getArea() {
        return area;
    }

    public void setArea(AreasEmpresa area) {
        this.area = area;
    }

    public Personas getPersonaAutoriza() {
        return personaAutoriza;
    }

    public void setPersonaAutoriza(Personas personaAutoriza) {
        this.personaAutoriza = personaAutoriza;
    }

    public PersonasSucursal getPersonasSucursal() {
        return personasSucursal;
    }

    public void setPersonasSucursal(PersonasSucursal personasSucursal) {
        this.personasSucursal = personasSucursal;
    }

    @XmlTransient
    public List<MovDocumentos> getMovDocumentosList() {
        return movDocumentosList;
    }

    public void setMovDocumentosList(List<MovDocumentos> movDocumentosList) {
        this.movDocumentosList = movDocumentosList;
    }

    @XmlTransient
    public List<MovObjetos> getMovObjetosList() {
        return movObjetosList;
    }

    public void setMovObjetosList(List<MovObjetos> movObjetosList) {
        this.movObjetosList = movObjetosList;
    }

    @XmlTransient
    public List<MovObjetos> getMovObjetosList1() {
        return movObjetosList1;
    }

    public void setMovObjetosList1(List<MovObjetos> movObjetosList1) {
        this.movObjetosList1 = movObjetosList1;
    }

    @XmlTransient
    public List<MovVehiculos> getMovVehiculosList() {
        return movVehiculosList;
    }

    public void setMovVehiculosList(List<MovVehiculos> movVehiculosList) {
        this.movVehiculosList = movVehiculosList;
    }

    @XmlTransient
    public List<MovVehiculos> getMovVehiculosList1() {
        return movVehiculosList1;
    }

    public void setMovVehiculosList1(List<MovVehiculos> movVehiculosList1) {
        this.movVehiculosList1 = movVehiculosList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovPersona != null ? idMovPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovPersonas)) {
            return false;
        }
        MovPersonas other = (MovPersonas) object;
        if ((this.idMovPersona == null && other.idMovPersona != null) || (this.idMovPersona != null && !this.idMovPersona.equals(other.idMovPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovPersonas[ idMovPersona=" + idMovPersona + " ]";
    }
    
}
