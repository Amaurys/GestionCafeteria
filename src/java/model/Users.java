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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
    , @NamedQuery(name = "Users.findByNombre", query = "SELECT u FROM Users u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Users.findByCedula", query = "SELECT u FROM Users u WHERE u.cedula = :cedula")
    , @NamedQuery(name = "Users.findByLimiteDeCredito", query = "SELECT u FROM Users u WHERE u.limiteDeCredito = :limiteDeCredito")
    , @NamedQuery(name = "Users.findByFechaRegistro", query = "SELECT u FROM Users u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Users.findByEstado", query = "SELECT u FROM Users u WHERE u.estado = :estado")})
public class Users implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "cedula")
    private String cedula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LimiteDeCredito")
    private Double limiteDeCredito;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Ventas> ventasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Facturacionarticulos> facturacionarticulosCollection;
    @JoinColumn(name = "idTipoDeUsuario", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Tiposdeusuario idTipoDeUsuario;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String nombre, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(Double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
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
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    @XmlTransient
    public Collection<Facturacionarticulos> getFacturacionarticulosCollection() {
        return facturacionarticulosCollection;
    }

    public void setFacturacionarticulosCollection(Collection<Facturacionarticulos> facturacionarticulosCollection) {
        this.facturacionarticulosCollection = facturacionarticulosCollection;
    }

    public Tiposdeusuario getIdTipoDeUsuario() {
        return idTipoDeUsuario;
    }

    public void setIdTipoDeUsuario(Tiposdeusuario idTipoDeUsuario) {
        this.idTipoDeUsuario = idTipoDeUsuario;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ id=" + id + " ]";
    }
    
}
