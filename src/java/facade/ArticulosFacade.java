/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Articulos;
import model.Articulos_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Ventas;
import model.Facturacionarticulos;
import model.Marcas;
import model.Proveedores;

/**
 *
 * @author asanchez
 */
@Stateless
public class ArticulosFacade extends AbstractFacade<Articulos> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticulosFacade() {
        super(Articulos.class);
    }

    public boolean isVentasCollectionEmpty(Articulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Articulos> articulos = cq.from(Articulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(articulos, entity), cb.isNotEmpty(articulos.get(Articulos_.ventasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Ventas> findVentasCollection(Articulos entity) {
        Articulos mergedEntity = this.getMergedEntity(entity);
        Collection<Ventas> ventasCollection = mergedEntity.getVentasCollection();
        ventasCollection.size();
        return ventasCollection;
    }

    public boolean isFacturacionarticulosCollectionEmpty(Articulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Articulos> articulos = cq.from(Articulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(articulos, entity), cb.isNotEmpty(articulos.get(Articulos_.facturacionarticulosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Facturacionarticulos> findFacturacionarticulosCollection(Articulos entity) {
        Articulos mergedEntity = this.getMergedEntity(entity);
        Collection<Facturacionarticulos> facturacionarticulosCollection = mergedEntity.getFacturacionarticulosCollection();
        facturacionarticulosCollection.size();
        return facturacionarticulosCollection;
    }

    public boolean isIdMarcaEmpty(Articulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Articulos> articulos = cq.from(Articulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(articulos, entity), cb.isNotNull(articulos.get(Articulos_.idMarca)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Marcas findIdMarca(Articulos entity) {
        return this.getMergedEntity(entity).getIdMarca();
    }

    public boolean isIdProveedorEmpty(Articulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Articulos> articulos = cq.from(Articulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(articulos, entity), cb.isNotNull(articulos.get(Articulos_.idProveedor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Proveedores findIdProveedor(Articulos entity) {
        return this.getMergedEntity(entity).getIdProveedor();
    }
    
}
