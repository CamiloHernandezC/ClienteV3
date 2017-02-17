/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
 * @author MAURICIO
 */
@Entity
@Table(name = "Departamentos_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartamentosCli.findAll", query = "SELECT d FROM DepartamentosCli d"),
    @NamedQuery(name = "DepartamentosCli.findByIdDepartamento", query = "SELECT d FROM DepartamentosCli d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "DepartamentosCli.findByDescripcion", query = "SELECT d FROM DepartamentosCli d WHERE d.descripcion = :descripcion")})
public class DepartamentosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Id_Departamento")
    private String idDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idDepartamento", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento", fetch = FetchType.LAZY)
    private List<MunicipiosCli> municipiosCliList;
    @OneToMany(mappedBy = "idDepartamento", fetch = FetchType.LAZY)
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<SucursalesCli> sucursalesCliList;
    @OneToMany(mappedBy = "idDepartamento", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;
    @OneToMany(mappedBy = "idDepartamento", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;

    public DepartamentosCli() {
    }

    public DepartamentosCli(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public DepartamentosCli(String idDepartamento, String descripcion) {
        this.idDepartamento = idDepartamento;
        this.descripcion = descripcion;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
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

    @XmlTransient
    public List<MunicipiosCli> getMunicipiosCliList() {
        return municipiosCliList;
    }

    public void setMunicipiosCliList(List<MunicipiosCli> municipiosCliList) {
        this.municipiosCliList = municipiosCliList;
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
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentosCli)) {
            return false;
        }
        DepartamentosCli other = (DepartamentosCli) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.DepartamentosCli[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
