/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asanchez
 */
@Entity
@Table(name = "PROVEEDORES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p")
    , @NamedQuery(name = "Proveedores.findById", query = "SELECT p FROM Proveedores p WHERE p.id = :id")
    , @NamedQuery(name = "Proveedores.findByNombreComercial", query = "SELECT p FROM Proveedores p WHERE p.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "Proveedores.findByRnc", query = "SELECT p FROM Proveedores p WHERE p.rnc = :rnc")
    , @NamedQuery(name = "Proveedores.findByFechaRegistro", query = "SELECT p FROM Proveedores p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Proveedores.findByEstado", query = "SELECT p FROM Proveedores p WHERE p.estado = :estado")})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreComercial")
    private String nombreComercial;
    @Size(max = 9)
    @Column(name = "RNC")
    private String rnc;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private Collection<Articulos> articulosCollection;

    public Proveedores() {
    }

    public Proveedores(Integer id) {
        this.id = id;
    }

    public Proveedores(Integer id, String nombreComercial) {
        this.id = id;
        this.nombreComercial = nombreComercial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
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
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Proveedores[ id=" + id + " ]";
    }
    
}
