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
@Table(name = "Novedades_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadesCli.findAll", query = "SELECT n FROM NovedadesCli n"),
    @NamedQuery(name = "NovedadesCli.findByIdnovedadporteria", query = "SELECT n FROM NovedadesCli n WHERE n.idnovedadporteria = :idnovedadporteria"),
    @NamedQuery(name = "NovedadesCli.findByIdcaso", query = "SELECT n FROM NovedadesCli n WHERE n.idcaso = :idcaso"),
    @NamedQuery(name = "NovedadesCli.findByIdnovedad", query = "SELECT n FROM NovedadesCli n WHERE n.idnovedad = :idnovedad"),
    @NamedQuery(name = "NovedadesCli.findByIdcategoria", query = "SELECT n FROM NovedadesCli n WHERE n.idcategoria = :idcategoria"),
    @NamedQuery(name = "NovedadesCli.findByIdentidad", query = "SELECT n FROM NovedadesCli n WHERE n.identidad = :identidad"),
    @NamedQuery(name = "NovedadesCli.findByIdObjeto", query = "SELECT n FROM NovedadesCli n WHERE n.idObjeto = :idObjeto"),
    @NamedQuery(name = "NovedadesCli.findByFechanov", query = "SELECT n FROM NovedadesCli n WHERE n.fechanov = :fechanov"),
    @NamedQuery(name = "NovedadesCli.findByHoranov", query = "SELECT n FROM NovedadesCli n WHERE n.horanov = :horanov"),
    @NamedQuery(name = "NovedadesCli.findByAsunto", query = "SELECT n FROM NovedadesCli n WHERE n.asunto = :asunto"),
    @NamedQuery(name = "NovedadesCli.findByDescripnov", query = "SELECT n FROM NovedadesCli n WHERE n.descripnov = :descripnov"),
    @NamedQuery(name = "NovedadesCli.findByAccion1", query = "SELECT n FROM NovedadesCli n WHERE n.accion1 = :accion1"),
    @NamedQuery(name = "NovedadesCli.findByAccion2", query = "SELECT n FROM NovedadesCli n WHERE n.accion2 = :accion2"),
    @NamedQuery(name = "NovedadesCli.findByAccion3", query = "SELECT n FROM NovedadesCli n WHERE n.accion3 = :accion3"),
    @NamedQuery(name = "NovedadesCli.findByAccion4", query = "SELECT n FROM NovedadesCli n WHERE n.accion4 = :accion4"),
    @NamedQuery(name = "NovedadesCli.findByAccion5", query = "SELECT n FROM NovedadesCli n WHERE n.accion5 = :accion5"),
    @NamedQuery(name = "NovedadesCli.findByOtraaccion", query = "SELECT n FROM NovedadesCli n WHERE n.otraaccion = :otraaccion"),
    @NamedQuery(name = "NovedadesCli.findByFuncionarioseracis", query = "SELECT n FROM NovedadesCli n WHERE n.funcionarioseracis = :funcionarioseracis"),
    @NamedQuery(name = "NovedadesCli.findByContratistainformado", query = "SELECT n FROM NovedadesCli n WHERE n.contratistainformado = :contratistainformado"),
    @NamedQuery(name = "NovedadesCli.findByFecha", query = "SELECT n FROM NovedadesCli n WHERE n.fecha = :fecha")})
public class NovedadesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_novedadporteria")
    private String idnovedadporteria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Id_caso")
    private String idcaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "Id_novedad")
    private String idnovedad;
    @Size(max = 3)
    @Column(name = "Id_categoria")
    private String idcategoria;
    @Size(max = 3)
    @Column(name = "Id_entidad")
    private String identidad;
    @Size(max = 14)
    @Column(name = "Id_Objeto")
    private String idObjeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_nov")
    @Temporal(TemporalType.DATE)
    private Date fechanov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_nov")
    @Temporal(TemporalType.TIME)
    private Date horanov;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Descrip_nov")
    private String descripnov;
    @Size(max = 5)
    @Column(name = "Accion1")
    private String accion1;
    @Size(max = 5)
    @Column(name = "Accion2")
    private String accion2;
    @Size(max = 5)
    @Column(name = "Accion3")
    private String accion3;
    @Size(max = 5)
    @Column(name = "Accion4")
    private String accion4;
    @Size(max = 5)
    @Column(name = "Accion5")
    private String accion5;
    @Size(max = 2147483647)
    @Column(name = "Otra_accion")
    private String otraaccion;
    @Size(max = 10)
    @Column(name = "Funcionario_seracis")
    private String funcionarioseracis;
    @Size(max = 14)
    @Column(name = "Contratista_informado")
    private String contratistainformado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Area_empresa", referencedColumnName = "Id_areaemp")
    @ManyToOne(fetch = FetchType.LAZY)
    private AreasEmpresaCli areaempresa;
    @JoinColumn(name = "Id_emorigen", referencedColumnName = "Id_Emorigen")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaOrigenCli idemorigen;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonasCli idPersona;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;

    public NovedadesCli() {
    }

    public NovedadesCli(String idnovedadporteria) {
        this.idnovedadporteria = idnovedadporteria;
    }

    public NovedadesCli(String idnovedadporteria, String idcaso, String idnovedad, Date fechanov, Date horanov, String asunto, String descripnov, Date fecha) {
        this.idnovedadporteria = idnovedadporteria;
        this.idcaso = idcaso;
        this.idnovedad = idnovedad;
        this.fechanov = fechanov;
        this.horanov = horanov;
        this.asunto = asunto;
        this.descripnov = descripnov;
        this.fecha = fecha;
    }

    public String getIdnovedadporteria() {
        return idnovedadporteria;
    }

    public void setIdnovedadporteria(String idnovedadporteria) {
        this.idnovedadporteria = idnovedadporteria;
    }

    public String getIdcaso() {
        return idcaso;
    }

    public void setIdcaso(String idcaso) {
        this.idcaso = idcaso;
    }

    public String getIdnovedad() {
        return idnovedad;
    }

    public void setIdnovedad(String idnovedad) {
        this.idnovedad = idnovedad;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Date getFechanov() {
        return fechanov;
    }

    public void setFechanov(Date fechanov) {
        this.fechanov = fechanov;
    }

    public Date getHoranov() {
        return horanov;
    }

    public void setHoranov(Date horanov) {
        this.horanov = horanov;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripnov() {
        return descripnov;
    }

    public void setDescripnov(String descripnov) {
        this.descripnov = descripnov;
    }

    public String getAccion1() {
        return accion1;
    }

    public void setAccion1(String accion1) {
        this.accion1 = accion1;
    }

    public String getAccion2() {
        return accion2;
    }

    public void setAccion2(String accion2) {
        this.accion2 = accion2;
    }

    public String getAccion3() {
        return accion3;
    }

    public void setAccion3(String accion3) {
        this.accion3 = accion3;
    }

    public String getAccion4() {
        return accion4;
    }

    public void setAccion4(String accion4) {
        this.accion4 = accion4;
    }

    public String getAccion5() {
        return accion5;
    }

    public void setAccion5(String accion5) {
        this.accion5 = accion5;
    }

    public String getOtraaccion() {
        return otraaccion;
    }

    public void setOtraaccion(String otraaccion) {
        this.otraaccion = otraaccion;
    }

    public String getFuncionarioseracis() {
        return funcionarioseracis;
    }

    public void setFuncionarioseracis(String funcionarioseracis) {
        this.funcionarioseracis = funcionarioseracis;
    }

    public String getContratistainformado() {
        return contratistainformado;
    }

    public void setContratistainformado(String contratistainformado) {
        this.contratistainformado = contratistainformado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public AreasEmpresaCli getAreaempresa() {
        return areaempresa;
    }

    public void setAreaempresa(AreasEmpresaCli areaempresa) {
        this.areaempresa = areaempresa;
    }

    public EmpresaOrigenCli getIdemorigen() {
        return idemorigen;
    }

    public void setIdemorigen(EmpresaOrigenCli idemorigen) {
        this.idemorigen = idemorigen;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public PersonasCli getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonasCli idPersona) {
        this.idPersona = idPersona;
    }

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnovedadporteria != null ? idnovedadporteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadesCli)) {
            return false;
        }
        NovedadesCli other = (NovedadesCli) object;
        if ((this.idnovedadporteria == null && other.idnovedadporteria != null) || (this.idnovedadporteria != null && !this.idnovedadporteria.equals(other.idnovedadporteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.NovedadesCli[ idnovedadporteria=" + idnovedadporteria + " ]";
    }
    
}
