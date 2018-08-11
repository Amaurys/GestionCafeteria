/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asanchez
 */
@Entity
@Table(name = "VENTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByNoFactura", query = "SELECT v FROM Ventas v WHERE v.noFactura = :noFactura")
    , @NamedQuery(name = "Ventas.findByFechaVenta", query = "SELECT v FROM Ventas v WHERE v.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "Ventas.findByMontoArticulo", query = "SELECT v FROM Ventas v WHERE v.montoArticulo = :montoArticulo")
    , @NamedQuery(name = "Ventas.findByUnidadesVendidas", query = "SELECT v FROM Ventas v WHERE v.unidadesVendidas = :unidadesVendidas")
    , @NamedQuery(name = "Ventas.findByComentario", query = "SELECT v FROM Ventas v WHERE v.comentario = :comentario")
    , @NamedQuery(name = "Ventas.findByEstado", query = "SELECT v FROM Ventas v WHERE v.estado = :estado")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NoFactura")
    private Integer noFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaVenta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montoArticulo")
    private double montoArticulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidadesVendidas")
    private int unidadesVendidas;
    @Size(max = 150)
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;
    @JoinColumn(name = "idArticulo", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Articulos idArticulo;
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users idUsuario;

    public Ventas() {
    }

    public Ventas(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public Ventas(Integer noFactura, Date fechaVenta, double montoArticulo, int unidadesVendidas) {
        this.noFactura = noFactura;
        this.fechaVenta = fechaVenta;
        this.montoArticulo = montoArticulo;
        this.unidadesVendidas = unidadesVendidas;
    }

    public Integer getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getMontoArticulo() {
        return montoArticulo;
    }

    public void setMontoArticulo(double montoArticulo) {
        this.montoArticulo = montoArticulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Articulos getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulos idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Users getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Users idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noFactura != null ? noFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.noFactura == null && other.noFactura != null) || (this.noFactura != null && !this.noFactura.equals(other.noFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ventas[ noFactura=" + noFactura + " ]";
    }
    
}
