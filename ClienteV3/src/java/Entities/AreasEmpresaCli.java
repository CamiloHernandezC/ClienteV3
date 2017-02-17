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
@Table(name = "Areas_Empresa_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasEmpresaCli.findAll", query = "SELECT a FROM AreasEmpresaCli a"),
    @NamedQuery(name = "AreasEmpresaCli.findByIdareaemp", query = "SELECT a FROM AreasEmpresaCli a WHERE a.idareaemp = :idareaemp"),
    @NamedQuery(name = "AreasEmpresaCli.findByDescripcion", query = "SELECT a FROM AreasEmpresaCli a WHERE a.descripcion = :descripcion")})
public class AreasEmpresaCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Id_areaemp")
    private String idareaemp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "areaempresa", fetch = FetchType.LAZY)
    private List<NovedadesCli> novedadesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private List<MovPersonasCli> movPersonasCliList;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;

    public AreasEmpresaCli() {
    }

    public AreasEmpresaCli(String idareaemp) {
        this.idareaemp = idareaemp;
    }

    public AreasEmpresaCli(String idareaemp, String descripcion) {
        this.idareaemp = idareaemp;
        this.descripcion = descripcion;
    }

    public String getIdareaemp() {
        return idareaemp;
    }

    public void setIdareaemp(String idareaemp) {
        this.idareaemp = idareaemp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<NovedadesCli> getNovedadesCliList() {
        return novedadesCliList;
    }

    public void setNovedadesCliList(List<NovedadesCli> novedadesCliList) {
        this.novedadesCliList = novedadesCliList;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList() {
        return personasSucursalCliList;
    }

    public void setPersonasSucursalCliList(List<PersonasSucursalCli> personasSucursalCliList) {
        this.personasSucursalCliList = personasSucursalCliList;
    }

    @XmlTransient
    public List<MovPersonasCli> getMovPersonasCliList() {
        return movPersonasCliList;
    }

    public void setMovPersonasCliList(List<MovPersonasCli> movPersonasCliList) {
        this.movPersonasCliList = movPersonasCliList;
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
        hash += (idareaemp != null ? idareaemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreasEmpresaCli)) {
            return false;
        }
        AreasEmpresaCli other = (AreasEmpresaCli) object;
        if ((this.idareaemp == null && other.idareaemp != null) || (this.idareaemp != null && !this.idareaemp.equals(other.idareaemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AreasEmpresaCli[ idareaemp=" + idareaemp + " ]";
    }
    
}
