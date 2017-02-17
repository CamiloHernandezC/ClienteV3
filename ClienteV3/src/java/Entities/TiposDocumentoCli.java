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
@Table(name = "Tipos_Documento_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposDocumentoCli.findAll", query = "SELECT t FROM TiposDocumentoCli t"),
    @NamedQuery(name = "TiposDocumentoCli.findByTipodocumento", query = "SELECT t FROM TiposDocumentoCli t WHERE t.tipodocumento = :tipodocumento"),
    @NamedQuery(name = "TiposDocumentoCli.findByDescripcion", query = "SELECT t FROM TiposDocumentoCli t WHERE t.descripcion = :descripcion")})
public class TiposDocumentoCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Tipo_documento")
    private String tipodocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDocumento", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;

    public TiposDocumentoCli() {
    }

    public TiposDocumentoCli(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public TiposDocumentoCli(String tipodocumento, String descripcion) {
        this.tipodocumento = tipodocumento;
        this.descripcion = descripcion;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PersonasCli> getPersonasCliList() {
        return personasCliList;
    }

    public void setPersonasCliList(List<PersonasCli> personasCliList) {
        this.personasCliList = personasCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipodocumento != null ? tipodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposDocumentoCli)) {
            return false;
        }
        TiposDocumentoCli other = (TiposDocumentoCli) object;
        if ((this.tipodocumento == null && other.tipodocumento != null) || (this.tipodocumento != null && !this.tipodocumento.equals(other.tipodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TiposDocumentoCli[ tipodocumento=" + tipodocumento + " ]";
    }
    
}
