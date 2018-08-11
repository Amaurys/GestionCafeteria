/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asanchez
 */
@Entity
@Table(name = "USUARIOSSESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariossesion.findAll", query = "SELECT u FROM Usuariossesion u")
    , @NamedQuery(name = "Usuariossesion.findById", query = "SELECT u FROM Usuariossesion u WHERE u.id = :id")
    , @NamedQuery(name = "Usuariossesion.findByNombre", query = "SELECT u FROM Usuariossesion u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuariossesion.findByUsuario", query = "SELECT u FROM Usuariossesion u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuariossesion.findByClave", query = "SELECT u FROM Usuariossesion u WHERE u.clave = :clave")})
public class Usuariossesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "clave")
    private String clave;

    public Usuariossesion() {
    }

    public Usuariossesion(Integer id) {
        this.id = id;
    }

    public Usuariossesion(Integer id, String nombre, String usuario, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
        if (!(object instanceof Usuariossesion)) {
            return false;
        }
        Usuariossesion other = (Usuariossesion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuariossesion[ id=" + id + " ]";
    }
    
}
