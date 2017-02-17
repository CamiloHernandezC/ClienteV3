/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Municipios_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MunicipiosCli.findAll", query = "SELECT m FROM MunicipiosCli m"),
    @NamedQuery(name = "MunicipiosCli.findByIdMunicipio", query = "SELECT m FROM MunicipiosCli m WHERE m.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "MunicipiosCli.findByDescripcion", query = "SELECT m FROM MunicipiosCli m WHERE m.descripcion = :descripcion")})
public class MunicipiosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Id_Municipio")
    private String idMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idMunicipio", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DepartamentosCli idDepartamento;
    @OneToMany(mappedBy = "idMunicipio", fetch = FetchType.LAZY)
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<SucursalesCli> sucursalesCliList;
    @OneToMany(mappedBy = "idMunicipio", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;
    @OneToMany(mappedBy = "idMunicipio", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;

    public MunicipiosCli() {
    }

    public MunicipiosCli(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public MunicipiosCli(String idMunicipio, String descripcion) {
        this.idMunicipio = idMunicipio;
        this.descripcion = descripcion;
    }

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
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

    public DepartamentosCli getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(DepartamentosCli idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipiosCli)) {
            return false;
        }
        MunicipiosCli other = (MunicipiosCli) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MunicipiosCli[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
