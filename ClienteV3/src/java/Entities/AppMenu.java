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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chernandez
 */
@Entity
@Table(name = "App_Menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMenu.findAll", query = "SELECT a FROM AppMenu a"),
    @NamedQuery(name = "AppMenu.findByMenCodigo", query = "SELECT a FROM AppMenu a WHERE a.menCodigo = :menCodigo"),
    @NamedQuery(name = "AppMenu.findByMenNombre", query = "SELECT a FROM AppMenu a WHERE a.menNombre = :menNombre"),
    @NamedQuery(name = "AppMenu.findByMenUrl", query = "SELECT a FROM AppMenu a WHERE a.menUrl = :menUrl"),
    @NamedQuery(name = "AppMenu.findByMenTipo", query = "SELECT a FROM AppMenu a WHERE a.menTipo = :menTipo"),
    @NamedQuery(name = "AppMenu.findByMenTipoUser", query = "SELECT a FROM AppMenu a WHERE a.menTipoUser = :menTipoUser"),
    @NamedQuery(name = "AppMenu.findByMenCodSub", query = "SELECT a FROM AppMenu a WHERE a.menCodSub = :menCodSub"),
    @NamedQuery(name = "AppMenu.findByMenEstado", query = "SELECT a FROM AppMenu a WHERE a.menEstado = :menEstado")})
public class AppMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEN_CODIGO")
    private Integer menCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "MEN_NOMBRE")
    private String menNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "MEN_URL")
    private String menUrl;
    @Size(max = 5)
    @Column(name = "MEN_TIPO")
    private String menTipo;
    @Size(max = 5)
    @Column(name = "MEN_TIPO_USER")
    private String menTipoUser;
    @Size(max = 10)
    @Column(name = "MEN_COD_SUB")
    private String menCodSub;
    @Size(max = 1)
    @Column(name = "MEN_ESTADO")
    private String menEstado;

    public AppMenu() {
    }

    public AppMenu(Integer menCodigo) {
        this.menCodigo = menCodigo;
    }

    public AppMenu(Integer menCodigo, String menNombre, String menUrl) {
        this.menCodigo = menCodigo;
        this.menNombre = menNombre;
        this.menUrl = menUrl;
    }

    public Integer getMenCodigo() {
        return menCodigo;
    }

    public void setMenCodigo(Integer menCodigo) {
        this.menCodigo = menCodigo;
    }

    public String getMenNombre() {
        return menNombre;
    }

    public void setMenNombre(String menNombre) {
        this.menNombre = menNombre;
    }

    public String getMenUrl() {
        return menUrl;
    }

    public void setMenUrl(String menUrl) {
        this.menUrl = menUrl;
    }

    public String getMenTipo() {
        return menTipo;
    }

    public void setMenTipo(String menTipo) {
        this.menTipo = menTipo;
    }

    public String getMenTipoUser() {
        return menTipoUser;
    }

    public void setMenTipoUser(String menTipoUser) {
        this.menTipoUser = menTipoUser;
    }

    public String getMenCodSub() {
        return menCodSub;
    }

    public void setMenCodSub(String menCodSub) {
        this.menCodSub = menCodSub;
    }

    public String getMenEstado() {
        return menEstado;
    }

    public void setMenEstado(String menEstado) {
        this.menEstado = menEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menCodigo != null ? menCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMenu)) {
            return false;
        }
        AppMenu other = (AppMenu) object;
        if ((this.menCodigo == null && other.menCodigo != null) || (this.menCodigo != null && !this.menCodigo.equals(other.menCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AppMenu[ menCodigo=" + menCodigo + " ]";
    }
    
}
