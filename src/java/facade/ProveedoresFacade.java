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
import model.Proveedores;
import model.Proveedores_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Articulos;

/**
 *
 * @author asanchez
 */
@Stateless
public class ProveedoresFacade extends AbstractFacade<Proveedores> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedoresFacade() {
        super(Proveedores.class);
    }

    public boolean isArticulosCollectionEmpty(Proveedores entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Proveedores> proveedores = cq.from(Proveedores.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(proveedores, entity), cb.isNotEmpty(proveedores.get(Proveedores_.articulosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Articulos> findArticulosCollection(Proveedores entity) {
        Proveedores mergedEntity = this.getMergedEntity(entity);
        Collection<Articulos> articulosCollection = mergedEntity.getArticulosCollection();
        articulosCollection.size();
        return articulosCollection;
    }
    
}
