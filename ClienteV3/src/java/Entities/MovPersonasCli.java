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
@Table(name = "Mov_Personas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovPersonasCli.findAll", query = "SELECT m FROM MovPersonasCli m"),
    @NamedQuery(name = "MovPersonasCli.findByIdMovimiento", query = "SELECT m FROM MovPersonasCli m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovPersonasCli.findByFechaEntrada", query = "SELECT m FROM MovPersonasCli m WHERE m.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "MovPersonasCli.findByHoraEntrada", query = "SELECT m FROM MovPersonasCli m WHERE m.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "MovPersonasCli.findByFechaSalida", query = "SELECT m FROM MovPersonasCli m WHERE m.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "MovPersonasCli.findByHoraSalida", query = "SELECT m FROM MovPersonasCli m WHERE m.horaSalida = :horaSalida"),
    @NamedQuery(name = "MovPersonasCli.findByPersonaAutoriza", query = "SELECT m FROM MovPersonasCli m WHERE m.personaAutoriza = :personaAutoriza"),
    @NamedQuery(name = "MovPersonasCli.findByFecha", query = "SELECT m FROM MovPersonasCli m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MovPersonasCli.findBySalidaForzosa", query = "SELECT m FROM MovPersonasCli m WHERE m.salidaForzosa = :salidaForzosa")})
public class MovPersonasCli implements Serializable {

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
    @Size(max = 14)
    @Column(name = "Persona_Autoriza")
    private String personaAutoriza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_Forzosa")
    private boolean salidaForzosa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovEntrada", fetch = FetchType.LAZY)
    private List<MovVehiculosCli> movVehiculosCliList;
    @OneToMany(mappedBy = "idMovSalida", fetch = FetchType.LAZY)
    private List<MovVehiculosCli> movVehiculosCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovPersona", fetch = FetchType.LAZY)
    private List<MovDocumentosCli> movDocumentosCliList;
    @JoinColumn(name = "Id_Area", referencedColumnName = "Id_areaemp")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreasEmpresaCli idArea;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli idPersona;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovPersona", fetch = FetchType.LAZY)
    private List<MovMaterialesCli> movMaterialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovEntrada", fetch = FetchType.LAZY)
    private List<MovHerramientasCli> movHerramientasCliList;
    @OneToMany(mappedBy = "idMovSalida", fetch = FetchType.LAZY)
    private List<MovHerramientasCli> movHerramientasCliList1;

    public MovPersonasCli() {
    }

    public MovPersonasCli(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovPersonasCli(Long idMovimiento, Date fechaEntrada, Date horaEntrada, Date fecha, boolean salidaForzosa) {
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

    public String getPersonaAutoriza() {
        return personaAutoriza;
    }

    public void setPersonaAutoriza(String personaAutoriza) {
        this.personaAutoriza = personaAutoriza;
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

    @XmlTransient
    public List<MovVehiculosCli> getMovVehiculosCliList() {
        return movVehiculosCliList;
    }

    public void setMovVehiculosCliList(List<MovVehiculosCli> movVehiculosCliList) {
        this.movVehiculosCliList = movVehiculosCliList;
    }

    @XmlTransient
    public List<MovVehiculosCli> getMovVehiculosCliList1() {
        return movVehiculosCliList1;
    }

    public void setMovVehiculosCliList1(List<MovVehiculosCli> movVehiculosCliList1) {
        this.movVehiculosCliList1 = movVehiculosCliList1;
    }

    @XmlTransient
    public List<MovDocumentosCli> getMovDocumentosCliList() {
        return movDocumentosCliList;
    }

    public void setMovDocumentosCliList(List<MovDocumentosCli> movDocumentosCliList) {
        this.movDocumentosCliList = movDocumentosCliList;
    }

    public AreasEmpresaCli getIdArea() {
        return idArea;
    }

    public void setIdArea(AreasEmpresaCli idArea) {
        this.idArea = idArea;
    }

    public PersonasCli getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonasCli idPersona) {
        this.idPersona = idPersona;
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

    @XmlTransient
    public List<MovMaterialesCli> getMovMaterialesCliList() {
        return movMaterialesCliList;
    }

    public void setMovMaterialesCliList(List<MovMaterialesCli> movMaterialesCliList) {
        this.movMaterialesCliList = movMaterialesCliList;
    }

    @XmlTransient
    public List<MovHerramientasCli> getMovHerramientasCliList() {
        return movHerramientasCliList;
    }

    public void setMovHerramientasCliList(List<MovHerramientasCli> movHerramientasCliList) {
        this.movHerramientasCliList = movHerramientasCliList;
    }

    @XmlTransient
    public List<MovHerramientasCli> getMovHerramientasCliList1() {
        return movHerramientasCliList1;
    }

    public void setMovHerramientasCliList1(List<MovHerramientasCli> movHerramientasCliList1) {
        this.movHerramientasCliList1 = movHerramientasCliList1;
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
        if (!(object instanceof MovPersonasCli)) {
            return false;
        }
        MovPersonasCli other = (MovPersonasCli) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovPersonasCli[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
