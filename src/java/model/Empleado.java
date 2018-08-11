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
@Table(name = "EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findById", query = "SELECT e FROM Empleado e WHERE e.id = :id")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula")
    , @NamedQuery(name = "Empleado.findByPorcientoComision", query = "SELECT e FROM Empleado e WHERE e.porcientoComision = :porcientoComision")
    , @NamedQuery(name = "Empleado.findByFechaIngreso", query = "SELECT e FROM Empleado e WHERE e.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Empleado.findByEstado", query = "SELECT e FROM Empleado e WHERE e.estado = :estado")})
public class Empleado implements Serializable {

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
    @Column(name = "porcientoComision")
    private Integer porcientoComision;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private Collection<Cafeteria> cafeteriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private Collection<Ventas> ventasCollection;
    @JoinColumn(name = "IdTandaLaboral", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Tandalaboral idTandaLaboral;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado")
    private Collection<Facturacionarticulos> facturacionarticulosCollection;

    public Empleado() {
    }

    public Empleado(Integer id) {
        this.id = id;
    }

    public Empleado(Integer id, String nombre, String cedula) {
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

    public Integer getPorcientoComision() {
        return porcientoComision;
    }

    public void setPorcientoComision(Integer porcientoComision) {
        this.porcientoComision = porcientoComision;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Cafeteria> getCafeteriaCollection() {
        return cafeteriaCollection;
    }

    public void setCafeteriaCollection(Collection<Cafeteria> cafeteriaCollection) {
        this.cafeteriaCollection = cafeteriaCollection;
    }

    @XmlTransient
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    public Tandalaboral getIdTandaLaboral() {
        return idTandaLaboral;
    }

    public void setIdTandaLaboral(Tandalaboral idTandaLaboral) {
        this.idTandaLaboral = idTandaLaboral;
    }

    @XmlTransient
    public Collection<Facturacionarticulos> getFacturacionarticulosCollection() {
        return facturacionarticulosCollection;
    }

    public void setFacturacionarticulosCollection(Collection<Facturacionarticulos> facturacionarticulosCollection) {
        this.facturacionarticulosCollection = facturacionarticulosCollection;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Empleado[ id=" + id + " ]";
    }
    
}
