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
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByIdPersona", query = "SELECT p FROM Personas p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Personas.findByNumeroDocumento", query = "SELECT p FROM Personas p WHERE p.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Personas.findByNombre1", query = "SELECT p FROM Personas p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Personas.findByNombre2", query = "SELECT p FROM Personas p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Personas.findByApellido1", query = "SELECT p FROM Personas p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Personas.findByApellido2", query = "SELECT p FROM Personas p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Personas.findByDireccion", query = "SELECT p FROM Personas p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Personas.findByTelefono", query = "SELECT p FROM Personas p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Personas.findByCelular", query = "SELECT p FROM Personas p WHERE p.celular = :celular"),
    @NamedQuery(name = "Personas.findByMail", query = "SELECT p FROM Personas p WHERE p.mail = :mail"),
    @NamedQuery(name = "Personas.findByPersonaContacto", query = "SELECT p FROM Personas p WHERE p.personaContacto = :personaContacto"),
    @NamedQuery(name = "Personas.findByTelPersonaContacto", query = "SELECT p FROM Personas p WHERE p.telPersonaContacto = :telPersonaContacto"),
    @NamedQuery(name = "Personas.findByFechaVigenciaSS", query = "SELECT p FROM Personas p WHERE p.fechaVigenciaSS = :fechaVigenciaSS"),
    @NamedQuery(name = "Personas.findBySexo", query = "SELECT p FROM Personas p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Personas.findByRh", query = "SELECT p FROM Personas p WHERE p.rh = :rh"),
    @NamedQuery(name = "Personas.findByFechaNacimiento", query = "SELECT p FROM Personas p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Personas.findByUsuario", query = "SELECT p FROM Personas p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "Personas.findByFecha", query = "SELECT p FROM Personas p WHERE p.fecha = :fecha")})
public class Personas extends AbstractEntity{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Persona")
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Numero_Documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nombre_1")
    private String nombre1;
    @Size(max = 20)
    @Column(name = "Nombre_2")
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Apellido_1")
    private String apellido1;
    @Size(max = 20)
    @Column(name = "Apellido_2")
    private String apellido2;
    @Size(max = 140)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "Celular")
    private String celular;
    @Size(max = 140)
    @Column(name = "Mail")
    private String mail;
    @Size(max = 140)
    @Column(name = "Persona_Contacto")
    private String personaContacto;
    @Size(max = 20)
    @Column(name = "Tel_Persona_Contacto")
    private String telPersonaContacto;
    @Column(name = "Fecha_Vigencia_SS")
    @Temporal(TemporalType.DATE)
    private Date fechaVigenciaSS;
    @Column(name = "Sexo")
    private Boolean sexo;
    @Size(max = 2)
    @Column(name = "RH")
    private String rh;
    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Usuario")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<VisitasEsperadas> visitasEsperadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas", fetch = FetchType.LAZY)
    private List<VisitasEsperadas> visitasEsperadasList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioVisitado", fetch = FetchType.LAZY)
    private List<VisitasEsperadas> visitasEsperadasList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MaterialesSucursal> materialesSucursalList;
    @OneToMany(mappedBy = "vistoBueno", fetch = FetchType.LAZY)
    private List<MovRemisiones> movRemisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacenista", fetch = FetchType.LAZY)
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioModifica", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<EmpresaOrigen> empresaOrigenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Materiales> materialesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Objetos> objetosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personas", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovPersonas> movPersonasList;
    @OneToMany(mappedBy = "personaAutoriza", fetch = FetchType.LAZY)
    private List<MovPersonas> movPersonasList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovDocumentos> movDocumentosList;
    @JoinColumn(name = "Tipo_Documento", referencedColumnName = "Tipo_Documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposDocumento tipoDocumento;
    @JoinColumn(name = "Empresa_Origen", referencedColumnName = "Id_Empresa_Origen")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaOrigen empresaOrigen;
    @JoinColumn(name = "Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paises pais;
    @JoinColumn(name = "Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamentos departamento;
    @JoinColumn(name = "Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipios municipio;
    @JoinColumn(name = "EPS", referencedColumnName = "EPS")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eps eps;
    @JoinColumn(name = "ARL", referencedColumnName = "ARL")
    @ManyToOne(fetch = FetchType.LAZY)
    private Arl arl;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Vehiculos> vehiculosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<VehiculosSucursal> vehiculosSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Horarios> horariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovObjetos> movObjetosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovVehiculos> movVehiculosList;

    public Personas() {
    }

    public Personas(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Personas(Integer idPersona, String numeroDocumento, String nombre1, String apellido1, int usuario, Date fecha) {
        this.idPersona = idPersona;
        this.numeroDocumento = numeroDocumento;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelPersonaContacto() {
        return telPersonaContacto;
    }

    public void setTelPersonaContacto(String telPersonaContacto) {
        this.telPersonaContacto = telPersonaContacto;
    }

    public Date getFechaVigenciaSS() {
        return fechaVigenciaSS;
    }

    public void setFechaVigenciaSS(Date fechaVigenciaSS) {
        this.fechaVigenciaSS = fechaVigenciaSS;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<VisitasEsperadas> getVisitasEsperadasList() {
        return visitasEsperadasList;
    }

    public void setVisitasEsperadasList(List<VisitasEsperadas> visitasEsperadasList) {
        this.visitasEsperadasList = visitasEsperadasList;
    }

    @XmlTransient
    public List<VisitasEsperadas> getVisitasEsperadasList1() {
        return visitasEsperadasList1;
    }

    public void setVisitasEsperadasList1(List<VisitasEsperadas> visitasEsperadasList1) {
        this.visitasEsperadasList1 = visitasEsperadasList1;
    }

    @XmlTransient
    public List<VisitasEsperadas> getVisitasEsperadasList2() {
        return visitasEsperadasList2;
    }

    public void setVisitasEsperadasList2(List<VisitasEsperadas> visitasEsperadasList2) {
        this.visitasEsperadasList2 = visitasEsperadasList2;
    }

    @XmlTransient
    public List<MaterialesSucursal> getMaterialesSucursalList() {
        return materialesSucursalList;
    }

    public void setMaterialesSucursalList(List<MaterialesSucursal> materialesSucursalList) {
        this.materialesSucursalList = materialesSucursalList;
    }

    @XmlTransient
    public List<MovRemisiones> getMovRemisionesList() {
        return movRemisionesList;
    }

    public void setMovRemisionesList(List<MovRemisiones> movRemisionesList) {
        this.movRemisionesList = movRemisionesList;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList1() {
        return usuariosList1;
    }

    public void setUsuariosList1(List<Usuarios> usuariosList1) {
        this.usuariosList1 = usuariosList1;
    }

    @XmlTransient
    public List<EmpresaOrigen> getEmpresaOrigenList() {
        return empresaOrigenList;
    }

    public void setEmpresaOrigenList(List<EmpresaOrigen> empresaOrigenList) {
        this.empresaOrigenList = empresaOrigenList;
    }

    @XmlTransient
    public List<Materiales> getMaterialesList() {
        return materialesList;
    }

    public void setMaterialesList(List<Materiales> materialesList) {
        this.materialesList = materialesList;
    }

    @XmlTransient
    public List<Objetos> getObjetosList() {
        return objetosList;
    }

    public void setObjetosList(List<Objetos> objetosList) {
        this.objetosList = objetosList;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList() {
        return personasSucursalList;
    }

    public void setPersonasSucursalList(List<PersonasSucursal> personasSucursalList) {
        this.personasSucursalList = personasSucursalList;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList1() {
        return personasSucursalList1;
    }

    public void setPersonasSucursalList1(List<PersonasSucursal> personasSucursalList1) {
        this.personasSucursalList1 = personasSucursalList1;
    }

    @XmlTransient
    public List<MovPersonas> getMovPersonasList() {
        return movPersonasList;
    }

    public void setMovPersonasList(List<MovPersonas> movPersonasList) {
        this.movPersonasList = movPersonasList;
    }

    @XmlTransient
    public List<MovPersonas> getMovPersonasList1() {
        return movPersonasList1;
    }

    public void setMovPersonasList1(List<MovPersonas> movPersonasList1) {
        this.movPersonasList1 = movPersonasList1;
    }

    @XmlTransient
    public List<MovDocumentos> getMovDocumentosList() {
        return movDocumentosList;
    }

    public void setMovDocumentosList(List<MovDocumentos> movDocumentosList) {
        this.movDocumentosList = movDocumentosList;
    }

    public TiposDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TiposDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public EmpresaOrigen getEmpresaOrigen() {
        return empresaOrigen;
    }

    public void setEmpresaOrigen(EmpresaOrigen empresaOrigen) {
        this.empresaOrigen = empresaOrigen;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public Arl getArl() {
        return arl;
    }

    public void setArl(Arl arl) {
        this.arl = arl;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Vehiculos> getVehiculosList() {
        return vehiculosList;
    }

    public void setVehiculosList(List<Vehiculos> vehiculosList) {
        this.vehiculosList = vehiculosList;
    }

    @XmlTransient
    public List<VehiculosSucursal> getVehiculosSucursalList() {
        return vehiculosSucursalList;
    }

    public void setVehiculosSucursalList(List<VehiculosSucursal> vehiculosSucursalList) {
        this.vehiculosSucursalList = vehiculosSucursalList;
    }

    @XmlTransient
    public List<Horarios> getHorariosList() {
        return horariosList;
    }

    public void setHorariosList(List<Horarios> horariosList) {
        this.horariosList = horariosList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList1() {
        return notificacionesList1;
    }

    public void setNotificacionesList1(List<Notificaciones> notificacionesList1) {
        this.notificacionesList1 = notificacionesList1;
    }

    @XmlTransient
    public List<MovObjetos> getMovObjetosList() {
        return movObjetosList;
    }

    public void setMovObjetosList(List<MovObjetos> movObjetosList) {
        this.movObjetosList = movObjetosList;
    }

    @XmlTransient
    public List<MovVehiculos> getMovVehiculosList() {
        return movVehiculosList;
    }

    public void setMovVehiculosList(List<MovVehiculos> movVehiculosList) {
        this.movVehiculosList = movVehiculosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Personas[ idPersona=" + idPersona + " ]";
    }
    
    public String getSexoString() {
        if (sexo == null) {
            return "-";
}
        if (sexo) {
            return "M";
        }
        return "F";
    }

    @Override
    public int getPrimaryKey() {
        return idPersona;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        idPersona = primaryKey;
    }

    @Override
    public void setUser(Personas user) {
        usuario = user.getIdPersona();
    }

    @Override
    public void setDate(Date date) {
        fecha = date;
    }

    @Override
    public void setStatus(Integer STATUS_INACTIVE) {
        estado = new Estados(STATUS_INACTIVE);
    }

}
