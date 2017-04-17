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
 * @author Kmilo
 */
@Entity
@Table(name = "config_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigForm.findAll", query = "SELECT c FROM ConfigForm c"),
    @NamedQuery(name = "ConfigForm.findByIdConfigForm", query = "SELECT c FROM ConfigForm c WHERE c.idConfigForm = :idConfigForm"),
    @NamedQuery(name = "ConfigForm.findByFormulario", query = "SELECT c FROM ConfigForm c WHERE c.formulario = :formulario"),
    @NamedQuery(name = "ConfigForm.findByCampo", query = "SELECT c FROM ConfigForm c WHERE c.campo = :campo"),
    @NamedQuery(name = "ConfigForm.findByMostrar", query = "SELECT c FROM ConfigForm c WHERE c.mostrar = :mostrar")})
public class ConfigForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Config_Form")
    private Integer idConfigForm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Formulario")
    private String formulario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Campo")
    private String campo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mostrar")
    private boolean mostrar;
    @JoinColumn(name = "Porteria", referencedColumnName = "Id_Porteria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Porterias porteria;

    public ConfigForm() {
    }

    public ConfigForm(Integer idConfigForm) {
        this.idConfigForm = idConfigForm;
    }

    public ConfigForm(Integer idConfigForm, String formulario, String campo, boolean mostrar) {
        this.idConfigForm = idConfigForm;
        this.formulario = formulario;
        this.campo = campo;
        this.mostrar = mostrar;
    }

    public Integer getIdConfigForm() {
        return idConfigForm;
    }

    public void setIdConfigForm(Integer idConfigForm) {
        this.idConfigForm = idConfigForm;
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
        hash += (idConfigForm != null ? idConfigForm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigForm)) {
            return false;
        }
        ConfigForm other = (ConfigForm) object;
        if ((this.idConfigForm == null && other.idConfigForm != null) || (this.idConfigForm != null && !this.idConfigForm.equals(other.idConfigForm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ConfigForm[ idConfigForm=" + idConfigForm + " ]";
    }
    
}
