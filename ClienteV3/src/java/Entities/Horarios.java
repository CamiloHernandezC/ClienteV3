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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "horarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horarios.findAll", query = "SELECT h FROM Horarios h"),
    @NamedQuery(name = "Horarios.findByIdHorario", query = "SELECT h FROM Horarios h WHERE h.idHorario = :idHorario"),
    @NamedQuery(name = "Horarios.findByLunes", query = "SELECT h FROM Horarios h WHERE h.lunes = :lunes"),
    @NamedQuery(name = "Horarios.findByHoraIngresoL", query = "SELECT h FROM Horarios h WHERE h.horaIngresoL = :horaIngresoL"),
    @NamedQuery(name = "Horarios.findByHoraSalidaL", query = "SELECT h FROM Horarios h WHERE h.horaSalidaL = :horaSalidaL"),
    @NamedQuery(name = "Horarios.findByMartes", query = "SELECT h FROM Horarios h WHERE h.martes = :martes"),
    @NamedQuery(name = "Horarios.findByHoraIngresoM", query = "SELECT h FROM Horarios h WHERE h.horaIngresoM = :horaIngresoM"),
    @NamedQuery(name = "Horarios.findByHoraSalidaM", query = "SELECT h FROM Horarios h WHERE h.horaSalidaM = :horaSalidaM"),
    @NamedQuery(name = "Horarios.findByMiercoles", query = "SELECT h FROM Horarios h WHERE h.miercoles = :miercoles"),
    @NamedQuery(name = "Horarios.findByHoraIngresoW", query = "SELECT h FROM Horarios h WHERE h.horaIngresoW = :horaIngresoW"),
    @NamedQuery(name = "Horarios.findByHoraSalidaW", query = "SELECT h FROM Horarios h WHERE h.horaSalidaW = :horaSalidaW"),
    @NamedQuery(name = "Horarios.findByJueves", query = "SELECT h FROM Horarios h WHERE h.jueves = :jueves"),
    @NamedQuery(name = "Horarios.findByHoraIngresoJ", query = "SELECT h FROM Horarios h WHERE h.horaIngresoJ = :horaIngresoJ"),
    @NamedQuery(name = "Horarios.findByHoraSalidaJ", query = "SELECT h FROM Horarios h WHERE h.horaSalidaJ = :horaSalidaJ"),
    @NamedQuery(name = "Horarios.findByViernes", query = "SELECT h FROM Horarios h WHERE h.viernes = :viernes"),
    @NamedQuery(name = "Horarios.findByHoraIngresoV", query = "SELECT h FROM Horarios h WHERE h.horaIngresoV = :horaIngresoV"),
    @NamedQuery(name = "Horarios.findByHoraSalidaV", query = "SELECT h FROM Horarios h WHERE h.horaSalidaV = :horaSalidaV"),
    @NamedQuery(name = "Horarios.findBySabado", query = "SELECT h FROM Horarios h WHERE h.sabado = :sabado"),
    @NamedQuery(name = "Horarios.findByHoraIngresoS", query = "SELECT h FROM Horarios h WHERE h.horaIngresoS = :horaIngresoS"),
    @NamedQuery(name = "Horarios.findByHoraSalidaS", query = "SELECT h FROM Horarios h WHERE h.horaSalidaS = :horaSalidaS"),
    @NamedQuery(name = "Horarios.findByDomingo", query = "SELECT h FROM Horarios h WHERE h.domingo = :domingo"),
    @NamedQuery(name = "Horarios.findByHoraIngresoD", query = "SELECT h FROM Horarios h WHERE h.horaIngresoD = :horaIngresoD"),
    @NamedQuery(name = "Horarios.findByHoraSalidaD", query = "SELECT h FROM Horarios h WHERE h.horaSalidaD = :horaSalidaD"),
    @NamedQuery(name = "Horarios.findByFecha", query = "SELECT h FROM Horarios h WHERE h.fecha = :fecha")})
public class Horarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Horario")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lunes")
    private boolean lunes;
    @Column(name = "Hora_Ingreso_L")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoL;
    @Column(name = "Hora_Salida_L")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaL;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Martes")
    private boolean martes;
    @Column(name = "Hora_Ingreso_M")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoM;
    @Column(name = "Hora_Salida_M")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaM;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Miercoles")
    private boolean miercoles;
    @Column(name = "Hora_Ingreso_W")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoW;
    @Column(name = "Hora_Salida_W")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaW;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Jueves")
    private boolean jueves;
    @Column(name = "Hora_Ingreso_J")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoJ;
    @Column(name = "Hora_Salida_J")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaJ;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Viernes")
    private boolean viernes;
    @Column(name = "Hora_Ingreso_V")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoV;
    @Column(name = "Hora_Salida_V")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaV;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sabado")
    private boolean sabado;
    @Column(name = "Hora_Ingreso_S")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoS;
    @Column(name = "Hora_Salida_S")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Domingo")
    private boolean domingo;
    @Column(name = "Hora_Ingreso_D")
    @Temporal(TemporalType.TIME)
    private Date horaIngresoD;
    @Column(name = "Hora_Salida_D")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(mappedBy = "horario", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;

    public Horarios() {
    }

    public Horarios(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Horarios(Integer idHorario, boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes, boolean sabado, boolean domingo, Date fecha) {
        this.idHorario = idHorario;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.fecha = fecha;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public boolean getLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public Date getHoraIngresoL() {
        return horaIngresoL;
    }

    public void setHoraIngresoL(Date horaIngresoL) {
        this.horaIngresoL = horaIngresoL;
    }

    public Date getHoraSalidaL() {
        return horaSalidaL;
    }

    public void setHoraSalidaL(Date horaSalidaL) {
        this.horaSalidaL = horaSalidaL;
    }

    public boolean getMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public Date getHoraIngresoM() {
        return horaIngresoM;
    }

    public void setHoraIngresoM(Date horaIngresoM) {
        this.horaIngresoM = horaIngresoM;
    }

    public Date getHoraSalidaM() {
        return horaSalidaM;
    }

    public void setHoraSalidaM(Date horaSalidaM) {
        this.horaSalidaM = horaSalidaM;
    }

    public boolean getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public Date getHoraIngresoW() {
        return horaIngresoW;
    }

    public void setHoraIngresoW(Date horaIngresoW) {
        this.horaIngresoW = horaIngresoW;
    }

    public Date getHoraSalidaW() {
        return horaSalidaW;
    }

    public void setHoraSalidaW(Date horaSalidaW) {
        this.horaSalidaW = horaSalidaW;
    }

    public boolean getJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public Date getHoraIngresoJ() {
        return horaIngresoJ;
    }

    public void setHoraIngresoJ(Date horaIngresoJ) {
        this.horaIngresoJ = horaIngresoJ;
    }

    public Date getHoraSalidaJ() {
        return horaSalidaJ;
    }

    public void setHoraSalidaJ(Date horaSalidaJ) {
        this.horaSalidaJ = horaSalidaJ;
    }

    public boolean getViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public Date getHoraIngresoV() {
        return horaIngresoV;
    }

    public void setHoraIngresoV(Date horaIngresoV) {
        this.horaIngresoV = horaIngresoV;
    }

    public Date getHoraSalidaV() {
        return horaSalidaV;
    }

    public void setHoraSalidaV(Date horaSalidaV) {
        this.horaSalidaV = horaSalidaV;
    }

    public boolean getSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public Date getHoraIngresoS() {
        return horaIngresoS;
    }

    public void setHoraIngresoS(Date horaIngresoS) {
        this.horaIngresoS = horaIngresoS;
    }

    public Date getHoraSalidaS() {
        return horaSalidaS;
    }

    public void setHoraSalidaS(Date horaSalidaS) {
        this.horaSalidaS = horaSalidaS;
    }

    public boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public Date getHoraIngresoD() {
        return horaIngresoD;
    }

    public void setHoraIngresoD(Date horaIngresoD) {
        this.horaIngresoD = horaIngresoD;
    }

    public Date getHoraSalidaD() {
        return horaSalidaD;
    }

    public void setHoraSalidaD(Date horaSalidaD) {
        this.horaSalidaD = horaSalidaD;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList() {
        return personasSucursalList;
    }

    public void setPersonasSucursalList(List<PersonasSucursal> personasSucursalList) {
        this.personasSucursalList = personasSucursalList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horarios)) {
            return false;
        }
        Horarios other = (Horarios) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Horarios[ idHorario=" + idHorario + " ]";
    }
    
}
