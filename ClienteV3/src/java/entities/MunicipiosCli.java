/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chernandez
 */
@Entity
@Table(name = "Municipios_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MunicipiosCli.findAll", query = "SELECT m FROM MunicipiosCli m"),
    @NamedQuery(name = "MunicipiosCli.findByIdDepartamento", query = "SELECT m FROM MunicipiosCli m WHERE m.municipiosCliPK.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "MunicipiosCli.findByIdMunicipio", query = "SELECT m FROM MunicipiosCli m WHERE m.municipiosCliPK.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "MunicipiosCli.findByDescripcion", query = "SELECT m FROM MunicipiosCli m WHERE m.descripcion = :descripcion")})
public class MunicipiosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MunicipiosCliPK municipiosCliPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "municipiosCli")
    private List<VehiculosCli> vehiculosCliList;
    @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DepartamentosCli departamentosCli;
    @OneToMany(mappedBy = "municipiosCli")
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(mappedBy = "municipiosCli")
    private List<SucursalesCli> sucursalesCliList;
    @OneToMany(mappedBy = "municipiosCli")
    private List<PersonasCli> personasCliList;
    @OneToMany(mappedBy = "municipiosCli")
    private List<ObjetosCli> objetosCliList;

    public MunicipiosCli() {
    }

    public MunicipiosCli(MunicipiosCliPK municipiosCliPK) {
        this.municipiosCliPK = municipiosCliPK;
    }

    public MunicipiosCli(MunicipiosCliPK municipiosCliPK, String descripcion) {
        this.municipiosCliPK = municipiosCliPK;
        this.descripcion = descripcion;
    }

    public MunicipiosCli(String idDepartamento, String idMunicipio) {
        this.municipiosCliPK = new MunicipiosCliPK(idDepartamento, idMunicipio);
    }

    public MunicipiosCliPK getMunicipiosCliPK() {
        return municipiosCliPK;
    }

    public void setMunicipiosCliPK(MunicipiosCliPK municipiosCliPK) {
        this.municipiosCliPK = municipiosCliPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList() {
        return vehiculosCliList;
    }

    public void setVehiculosCliList(List<VehiculosCli> vehiculosCliList) {
        this.vehiculosCliList = vehiculosCliList;
    }

    public DepartamentosCli getDepartamentosCli() {
        return departamentosCli;
    }

    public void setDepartamentosCli(DepartamentosCli departamentosCli) {
        this.departamentosCli = departamentosCli;
    }

    @XmlTransient
    public List<EmpresaOrigenCli> getEmpresaOrigenCliList() {
        return empresaOrigenCliList;
    }

    public void setEmpresaOrigenCliList(List<EmpresaOrigenCli> empresaOrigenCliList) {
        this.empresaOrigenCliList = empresaOrigenCliList;
    }

    @XmlTransient
    public List<SucursalesCli> getSucursalesCliList() {
        return sucursalesCliList;
    }

    public void setSucursalesCliList(List<SucursalesCli> sucursalesCliList) {
        this.sucursalesCliList = sucursalesCliList;
    }

    @XmlTransient
    public List<PersonasCli> getPersonasCliList() {
        return personasCliList;
    }

    public void setPersonasCliList(List<PersonasCli> personasCliList) {
        this.personasCliList = personasCliList;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipiosCliPK != null ? municipiosCliPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipiosCli)) {
            return false;
        }
        MunicipiosCli other = (MunicipiosCli) object;
        if ((this.municipiosCliPK == null && other.municipiosCliPK != null) || (this.municipiosCliPK != null && !this.municipiosCliPK.equals(other.municipiosCliPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MunicipiosCli[ municipiosCliPK=" + municipiosCliPK + " ]";
    }
    
}
