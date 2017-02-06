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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chernandez
 */
@Entity
@Table(name = "Personas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasCli.findAll", query = "SELECT p FROM PersonasCli p"),
    @NamedQuery(name = "PersonasCli.findByIdPersona", query = "SELECT p FROM PersonasCli p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonasCli.findByIdExterno", query = "SELECT p FROM PersonasCli p WHERE p.idExterno = :idExterno"),
    @NamedQuery(name = "PersonasCli.findByNombre1", query = "SELECT p FROM PersonasCli p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "PersonasCli.findByNombre2", query = "SELECT p FROM PersonasCli p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "PersonasCli.findByApellido1", query = "SELECT p FROM PersonasCli p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "PersonasCli.findByApellido2", query = "SELECT p FROM PersonasCli p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "PersonasCli.findByNumDocumento", query = "SELECT p FROM PersonasCli p WHERE p.numDocumento = :numDocumento"),
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
    @Size(max = 18)
    @Column(name = "Id_Externo")
    private String idExterno;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Num_Documento")
    private String numDocumento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(mappedBy = "idPersona")
    private List<NovedadesCli> novedadesCliList;
    @OneToMany(mappedBy = "contratistainformado")
    private List<NovedadesCli> novedadesCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<NovedadesCli> novedadesCliList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MaterialesCli> materialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MovVehiculosCli> movVehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<VisitasEsperadasCli> visitasEsperadasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioVisitado")
    private List<VisitasEsperadasCli> visitasEsperadasCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MovDocumentosCli> movDocumentosCliList;
    @JoinColumn(name = "Area", referencedColumnName = "Id_areaemp")
    @ManyToOne(optional = false)
    private AreasEmpresaCli area;
    @JoinColumn(name = "ARL", referencedColumnName = "ARL")
    @ManyToOne
    private ARLCli arl;
    @PrimaryKeyJoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne
    private DepartamentosCli idDepartamento;
    @JoinColumn(name = "Id_Empresa_Origen", referencedColumnName = "Id_Emorigen")
    @ManyToOne
    private EmpresaOrigenCli idEmpresaOrigen;
    @JoinColumn(name = "Id_Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false)
    private EntidadesCli idEntidad;
    @JoinColumn(name = "EPS", referencedColumnName = "EPS")
    @ManyToOne
    private EPSCli eps;
    @JoinColumn(name = "Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false)
    private EstadosCli idEstado;
    @JoinColumns({
        @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento"),
        @JoinColumn(name = "Id_Municipio", referencedColumnName = "Id_Municipio")})
    @ManyToOne
    private MunicipiosCli municipiosCli;
    @JoinColumn(name = "Id_Pais", referencedColumnName = "Id_Pais")
    @ManyToOne
    private PaisesCli idPais;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false)
    private SucursalesCli idSucursal;
    @JoinColumn(name = "Tipo_Documento", referencedColumnName = "Tipo_documento")
    @ManyToOne(optional = false)
    private TiposDocumentoCli tipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<ObjetosCli> objetosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<UsuariosCli> usuariosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<UsuariosCli> usuariosCliList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<MovPersonasCli> movPersonasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MovPersonasCli> movPersonasCliList1;
    @OneToMany(mappedBy = "personaAutoriza")
    private List<MovPersonasCli> movPersonasCliList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MovMaterialesCli> movMaterialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<MovHerramientasCli> movHerramientasCliList;

    public PersonasCli() {
    }

    public PersonasCli(String idPersona) {
        this.idPersona = idPersona;
    }

    public PersonasCli(String idPersona, String nombre1, String apellido1, String numDocumento, String usuario, Date fecha) {
        this.idPersona = idPersona;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.numDocumento = numDocumento;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
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

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
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
    public List<NovedadesCli> getNovedadesCliList2() {
        return novedadesCliList2;
    }

    public void setNovedadesCliList2(List<NovedadesCli> novedadesCliList2) {
        this.novedadesCliList2 = novedadesCliList2;
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
    public List<MovDocumentosCli> getMovDocumentosCliList() {
        return movDocumentosCliList;
    }

    public void setMovDocumentosCliList(List<MovDocumentosCli> movDocumentosCliList) {
        this.movDocumentosCliList = movDocumentosCliList;
    }

    public AreasEmpresaCli getArea() {
        return area;
    }

    public void setArea(AreasEmpresaCli area) {
        this.area = area;
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

    public EntidadesCli getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(EntidadesCli idEntidad) {
        this.idEntidad = idEntidad;
    }

    public EPSCli getEps() {
        return eps;
    }

    public void setEps(EPSCli eps) {
        this.eps = eps;
    }

    public EstadosCli getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosCli idEstado) {
        this.idEstado = idEstado;
    }

    public MunicipiosCli getMunicipiosCli() {
        return municipiosCli;
    }

    public void setMunicipiosCli(MunicipiosCli municipiosCli) {
        this.municipiosCli = municipiosCli;
    }

    public PaisesCli getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisesCli idPais) {
        this.idPais = idPais;
    }

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
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
    public List<MovPersonasCli> getMovPersonasCliList2() {
        return movPersonasCliList2;
    }

    public void setMovPersonasCliList2(List<MovPersonasCli> movPersonasCliList2) {
        this.movPersonasCliList2 = movPersonasCliList2;
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
        return "entities.PersonasCli[ idPersona=" + idPersona + " ]";
    }
    
}
