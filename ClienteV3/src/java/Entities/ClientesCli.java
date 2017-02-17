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
@Table(name = "Clientes_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientesCli.findAll", query = "SELECT c FROM ClientesCli c"),
    @NamedQuery(name = "ClientesCli.findByIdCliente", query = "SELECT c FROM ClientesCli c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "ClientesCli.findByNombre", query = "SELECT c FROM ClientesCli c WHERE c.nombre = :nombre")})
public class ClientesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Cliente")
    private Long idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<SucursalesCli> sucursalesCliList;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli estado;

    public ClientesCli() {
    }

    public ClientesCli(Long idCliente) {
        this.idCliente = idCliente;
    }

    public ClientesCli(Long idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<SucursalesCli> getSucursalesCliList() {
        return sucursalesCliList;
    }

    public void setSucursalesCliList(List<SucursalesCli> sucursalesCliList) {
        this.sucursalesCliList = sucursalesCliList;
    }

    public EstadosCli getEstado() {
        return estado;
    }

    public void setEstado(EstadosCli estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesCli)) {
            return false;
        }
        ClientesCli other = (ClientesCli) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ClientesCli[ idCliente=" + idCliente + " ]";
    }
    
}
