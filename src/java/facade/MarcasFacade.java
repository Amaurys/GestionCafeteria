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
import model.Marcas;
import model.Marcas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Articulos;

/**
 *
 * @author asanchez
 */
@Stateless
public class MarcasFacade extends AbstractFacade<Marcas> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcasFacade() {
        super(Marcas.class);
    }

    public boolean isArticulosCollectionEmpty(Marcas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Marcas> marcas = cq.from(Marcas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(marcas, entity), cb.isNotEmpty(marcas.get(Marcas_.articulosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Articulos> findArticulosCollection(Marcas entity) {
        Marcas mergedEntity = this.getMergedEntity(entity);
        Collection<Articulos> articulosCollection = mergedEntity.getArticulosCollection();
        articulosCollection.size();
        return articulosCollection;
    }
    
}
