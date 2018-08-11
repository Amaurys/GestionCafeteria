/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author asanchez
 */
@Entity
@Table(name = "TIPOSDEUSUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposdeusuario.findAll", query = "SELECT t FROM Tiposdeusuario t")
    , @NamedQuery(name = "Tiposdeusuario.findById", query = "SELECT t FROM Tiposdeusuario t WHERE t.id = :id")
    , @NamedQuery(name = "Tiposdeusuario.findByNombre", query = "SELECT t FROM Tiposdeusuario t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tiposdeusuario.findByDescripcion", query = "SELECT t FROM Tiposdeusuario t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tiposdeusuario.findByEstado", query = "SELECT t FROM Tiposdeusuario t WHERE t.estado = :estado")})
public class Tiposdeusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDeUsuario")
    private Collection<Users> usersCollection;

    public Tiposdeusuario() {
    }

    public Tiposdeusuario(Integer id) {
        this.id = id;
    }

    public Tiposdeusuario(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof Tiposdeusuario)) {
            return false;
        }
        Tiposdeusuario other = (Tiposdeusuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tiposdeusuario[ id=" + id + " ]";
    }
    
}
