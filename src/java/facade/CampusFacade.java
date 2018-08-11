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
import model.Campus;
import model.Campus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Cafeteria;

/**
 *
 * @author asanchez
 */
@Stateless
public class CampusFacade extends AbstractFacade<Campus> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampusFacade() {
        super(Campus.class);
    }

    public boolean isCafeteriaCollectionEmpty(Campus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Campus> campus = cq.from(Campus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(campus, entity), cb.isNotEmpty(campus.get(Campus_.cafeteriaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Cafeteria> findCafeteriaCollection(Campus entity) {
        Campus mergedEntity = this.getMergedEntity(entity);
        Collection<Cafeteria> cafeteriaCollection = mergedEntity.getCafeteriaCollection();
        cafeteriaCollection.size();
        return cafeteriaCollection;
    }
    
}
