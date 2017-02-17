/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Config_Form_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigFormCli.findAll", query = "SELECT c FROM ConfigFormCli c"),
    @NamedQuery(name = "ConfigFormCli.findById", query = "SELECT c FROM ConfigFormCli c WHERE c.id = :id"),
    @NamedQuery(name = "ConfigFormCli.findByFormulario", query = "SELECT c FROM ConfigFormCli c WHERE c.formulario = :formulario"),
    @NamedQuery(name = "ConfigFormCli.findByCampo", query = "SELECT c FROM ConfigFormCli c WHERE c.campo = :campo"),
    @NamedQuery(name = "ConfigFormCli.findByMostrar", query = "SELECT c FROM ConfigFormCli c WHERE c.mostrar = :mostrar")})
public class ConfigFormCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "Formulario")
    private String formulario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Campo")
    private String campo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar")
    private boolean mostrar;
    @JoinColumn(name = "Porteria", referencedColumnName = "Id_Porteria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Porterias porteria;

    public ConfigFormCli() {
    }

    public ConfigFormCli(Long id) {
        this.id = id;
    }

    public ConfigFormCli(Long id, String formulario, String campo, boolean mostrar) {
        this.id = id;
        this.formulario = formulario;
        this.campo = campo;
        this.mostrar = mostrar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Porterias getPorteria() {
        return porteria;
    }

    public void setPorteria(Porterias porteria) {
        this.porteria = porteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigFormCli)) {
            return false;
        }
        ConfigFormCli other = (ConfigFormCli) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ConfigFormCli[ id=" + id + " ]";
    }
    
}
