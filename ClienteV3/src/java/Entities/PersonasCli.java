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
@Table(name = "Personas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasCli.findAll", query = "SELECT p FROM PersonasCli p"),
    @NamedQuery(name = "PersonasCli.findByIdPersona", query = "SELECT p FROM PersonasCli p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonasCli.findByNumDocumento", query = "SELECT p FROM PersonasCli p WHERE p.numDocumento = :numDocumento"),
    @NamedQuery(name = "PersonasCli.findByNombre1", query = "SELECT p FROM PersonasCli p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "PersonasCli.findByNombre2", query = "SELECT p FROM PersonasCli p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "PersonasCli.findByApellido1", query = "SELECT p FROM PersonasCli p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "PersonasCli.findByApellido2", query = "SELECT p FROM PersonasCli p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "PersonasCli.findByDireccion", query = "SELECT p FROM PersonasCli p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonasCli.findByTelefono", query = "SELECT p FROM PersonasCli p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PersonasCli.findByCelular", query = "SELECT p FROM PersonasCli p WHERE p.celular = :celular"),
    @NamedQuery(name = "PersonasCli.findByMail", query = "SELECT p FROM PersonasCli p WHERE p.mail = :mail"),
    @NamedQuery(name = "PersonasCli.findByPersonaContacto", query = "SELECT p FROM PersonasCli p WHERE p.personaContacto = :personaContacto"),
    @NamedQuery(name = "PersonasCli.findByTelPersonaContacto", query = "SELECT p FROM PersonasCli p WHERE p.telPersonaContacto = :telPersonaContacto"),
    @NamedQuery(name = "PersonasCli.findByFechavigenciaEPS", query = "SELECT p FROM PersonasCli p WHERE p.fechavigenciaEPS = :fechavigenciaEPS"),
    @NamedQuery(name = "PersonasCli.findByFechavigenciaARL", query = "SELECT p FROM PersonasCli p WHERE p.fechavigenciaARL = :fechavigenciaARL"),
    @NamedQuery(name = "PersonasCli.findBySexo", query = "SELECT p FROM PersonasCli p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PersonasCli.findByRh", query = "SELECT p FROM PersonasCli p WHERE p.rh = :rh"),
    @NamedQuery(name = "PersonasCli.findByFechaNacimiento", query = "SELECT p FROM PersonasCli p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PersonasCli.findByUsuario", query = "SELECT p FROM PersonasCli p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "PersonasCli.findByFecha", query = "SELECT p FROM PersonasCli p WHERE p.fecha = :fecha")})
public class PersonasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Persona")
    private String idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Num_Documento")
    private String numDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre_1")
    private String nombre1;
    @Size(max = 30)
    @Column(name = "Nombre_2")
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Apellido_1")
    private String apellido1;
    @Size(max = 30)
    @Column(name = "Apellido_2")
    private String apellido2;
    @Size(max = 120)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 15)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 25)
    @Column(name = "Celular")
    private String celular;
    @Size(max = 60)
    @Column(name = "Mail")
    private String mail;
    @Size(max = 100)
    @Column(name = "Persona_Contacto")
    private String personaContacto;
    @Size(max = 15)
    @Column(name = "Tel_Persona_Contacto")
    private String telPersonaContacto;
    @Column(name = "Fecha_vigencia_EPS")
    @Temporal(TemporalType.DATE)
    private Date fechavigenciaEPS;
    @Column(name = "Fecha_vigencia_ARL")
    @Temporal(TemporalType.DATE)
    private Date fechavigenciaARL;
    @Column(name = "Sexo")
    private Boolean sexo;
    @Size(max = 3)
    @Column(name = "RH")
    private String rh;
    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<NovedadesCli> novedadesCliList;
    @OneToMany(mappedBy = "idPersona", fetch = FetchType.LAZY)
    private List<NovedadesCli> novedadesCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personasCli", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MaterialesCli> materialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovVehiculosCli> movVehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MaterialesSucursal> materialesSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;
    @OneToMany(mappedBy = "idPersona", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personasCli", fetch = FetchType.LAZY)
    private List<VisitasEsperadasCli> visitasEsperadasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<VisitasEsperadasCli> visitasEsperadasCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioVisitado", fetch = FetchType.LAZY)
    private List<VisitasEsperadasCli> visitasEsperadasCliList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovDocumentosCli> movDocumentosCliList;
    @JoinColumn(name = "ARL", referencedColumnName = "ARL")
    @ManyToOne(fetch = FetchType.LAZY)
    private ARLCli arl;
    @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartamentosCli idDepartamento;
    @JoinColumn(name = "Id_Empresa_Origen", referencedColumnName = "Id_Emorigen")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaOrigenCli idEmpresaOrigen;
    @JoinColumn(name = "EPS", referencedColumnName = "EPS")
    @ManyToOne(fetch = FetchType.LAZY)
    private EPSCli eps;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli estado;
    @JoinColumn(name = "Id_Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private MunicipiosCli idMunicipio;
    @JoinColumn(name = "Id_Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaisesCli idPais;
    @JoinColumn(name = "Tipo_Documento", referencedColumnName = "Tipo_documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposDocumentoCli tipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<UsuariosCli> usuariosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona", fetch = FetchType.LAZY)
    private List<UsuariosCli> usuariosCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona", fetch = FetchType.LAZY)
    private List<MovPersonasCli> movPersonasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovPersonasCli> movPersonasCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovMaterialesCli> movMaterialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<MovHerramientasCli> movHerramientasCliList;

    public PersonasCli() {
    }

    public PersonasCli(String idPersona) {
        this.idPersona = idPersona;
    }

    public PersonasCli(String idPersona, String numDocumento, String nombre1, String apellido1, String usuario, Date fecha) {
        this.idPersona = idPersona;
        this.numDocumento = numDocumento;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
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

    public Date getFechavigenciaEPS() {
        return fechavigenciaEPS;
    }

    public void setFechavigenciaEPS(Date fechavigenciaEPS) {
        this.fechavigenciaEPS = fechavigenciaEPS;
    }

    public Date getFechavigenciaARL() {
        return fechavigenciaARL;
    }

    public void setFechavigenciaARL(Date fechavigenciaARL) {
        this.fechavigenciaARL = fechavigenciaARL;
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

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList() {
        return vehiculosCliList;
    }

    public void setVehiculosCliList(List<VehiculosCli> vehiculosCliList) {
        this.vehiculosCliList = vehiculosCliList;
    }

    @XmlTransient
    public List<EmpresaOrigenCli> getEmpresaOrigenCliList() {
        return empresaOrigenCliList;
    }

    public void setEmpresaOrigenCliList(List<EmpresaOrigenCli> empresaOrigenCliList) {
        this.empresaOrigenCliList = empresaOrigenCliList;
    }

    @XmlTransient
    public List<NovedadesCli> getNovedadesCliList() {
        return novedadesCliList;
    }

    public void setNovedadesCliList(List<NovedadesCli> novedadesCliList) {
        this.novedadesCliList = novedadesCliList;
    }

    @XmlTransient
    public List<NovedadesCli> getNovedadesCliList1() {
        return novedadesCliList1;
    }

    public void setNovedadesCliList1(List<NovedadesCli> novedadesCliList1) {
        this.novedadesCliList1 = novedadesCliList1;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList() {
        return personasSucursalCliList;
    }

    public void setPersonasSucursalCliList(List<PersonasSucursalCli> personasSucursalCliList) {
        this.personasSucursalCliList = personasSucursalCliList;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList1() {
        return personasSucursalCliList1;
    }

    public void setPersonasSucursalCliList1(List<PersonasSucursalCli> personasSucursalCliList1) {
        this.personasSucursalCliList1 = personasSucursalCliList1;
    }

    @XmlTransient
    public List<MaterialesCli> getMaterialesCliList() {
        return materialesCliList;
    }

    public void setMaterialesCliList(List<MaterialesCli> materialesCliList) {
        this.materialesCliList = materialesCliList;
    }

    @XmlTransient
    public List<MovVehiculosCli> getMovVehiculosCliList() {
        return movVehiculosCliList;
    }

    public void setMovVehiculosCliList(List<MovVehiculosCli> movVehiculosCliList) {
        this.movVehiculosCliList = movVehiculosCliList;
    }

    @XmlTransient
    public List<MaterialesSucursal> getMaterialesSucursalList() {
        return materialesSucursalList;
    }

    public void setMaterialesSucursalList(List<MaterialesSucursal> materialesSucursalList) {
        this.materialesSucursalList = materialesSucursalList;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList1() {
        return notificacionesCliList1;
    }

    public void setNotificacionesCliList1(List<NotificacionesCli> notificacionesCliList1) {
        this.notificacionesCliList1 = notificacionesCliList1;
    }

    @XmlTransient
    public List<VisitasEsperadasCli> getVisitasEsperadasCliList() {
        return visitasEsperadasCliList;
    }

    public void setVisitasEsperadasCliList(List<VisitasEsperadasCli> visitasEsperadasCliList) {
        this.visitasEsperadasCliList = visitasEsperadasCliList;
    }

    @XmlTransient
    public List<VisitasEsperadasCli> getVisitasEsperadasCliList1() {
        return visitasEsperadasCliList1;
    }

    public void setVisitasEsperadasCliList1(List<VisitasEsperadasCli> visitasEsperadasCliList1) {
        this.visitasEsperadasCliList1 = visitasEsperadasCliList1;
    }

    @XmlTransient
    public List<VisitasEsperadasCli> getVisitasEsperadasCliList2() {
        return visitasEsperadasCliList2;
    }

    public void setVisitasEsperadasCliList2(List<VisitasEsperadasCli> visitasEsperadasCliList2) {
        this.visitasEsperadasCliList2 = visitasEsperadasCliList2;
    }

    @XmlTransient
    public List<MovDocumentosCli> getMovDocumentosCliList() {
        return movDocumentosCliList;
    }

    public void setMovDocumentosCliList(List<MovDocumentosCli> movDocumentosCliList) {
        this.movDocumentosCliList = movDocumentosCliList;
    }

    public ARLCli getArl() {
        return arl;
    }

    public void setArl(ARLCli arl) {
        this.arl = arl;
    }

    public DepartamentosCli getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(DepartamentosCli idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public EmpresaOrigenCli getIdEmpresaOrigen() {
        return idEmpresaOrigen;
    }

    public void setIdEmpresaOrigen(EmpresaOrigenCli idEmpresaOrigen) {
        this.idEmpresaOrigen = idEmpresaOrigen;
    }

    public EPSCli getEps() {
        return eps;
    }

    public void setEps(EPSCli eps) {
        this.eps = eps;
    }

    public EstadosCli getEstado() {
        return estado;
    }

    public void setEstado(EstadosCli estado) {
        this.estado = estado;
    }

    public MunicipiosCli getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipiosCli idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public PaisesCli getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisesCli idPais) {
        this.idPais = idPais;
    }

    public TiposDocumentoCli getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TiposDocumentoCli tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @XmlTransient
    public List<UsuariosCli> getUsuariosCliList() {
        return usuariosCliList;
    }

    public void setUsuariosCliList(List<UsuariosCli> usuariosCliList) {
        this.usuariosCliList = usuariosCliList;
    }

    @XmlTransient
    public List<UsuariosCli> getUsuariosCliList1() {
        return usuariosCliList1;
    }

    public void setUsuariosCliList1(List<UsuariosCli> usuariosCliList1) {
        this.usuariosCliList1 = usuariosCliList1;
    }

    @XmlTransient
    public List<MovPersonasCli> getMovPersonasCliList() {
        return movPersonasCliList;
    }

    public void setMovPersonasCliList(List<MovPersonasCli> movPersonasCliList) {
        this.movPersonasCliList = movPersonasCliList;
    }

    @XmlTransient
    public List<MovPersonasCli> getMovPersonasCliList1() {
        return movPersonasCliList1;
    }

    public void setMovPersonasCliList1(List<MovPersonasCli> movPersonasCliList1) {
        this.movPersonasCliList1 = movPersonasCliList1;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasCli)) {
            return false;
        }
        PersonasCli other = (PersonasCli) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PersonasCli[ idPersona=" + idPersona + " ]";
    }
    
}
