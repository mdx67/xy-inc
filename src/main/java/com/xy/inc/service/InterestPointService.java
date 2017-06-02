package com.xy.inc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.xy.inc.domain.InterestPoint;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matheus Xavier
 */
@Service
public class InterestPointService {

    @PersistenceContext
    private EntityManager manager;

    public void save(InterestPoint interestPoint) {
        manager.persist(interestPoint);
    }

    public List<InterestPoint> listAll() {
        return manager.createNamedQuery("InterestPoint.listAll").getResultList();
    }

    public List<InterestPoint> listUpcoming(Integer x, Integer y, Integer maxDistance) {
        Query query = manager.createNativeQuery("select * from interest_point where sqrt(power(x - :x, 2) + power(y - :y, 2)) <= :maxDistance");
        query.setParameter("x", x);
        query.setParameter("y", y);
        query.setParameter("maxDistance", maxDistance);

        return query.getResultList();
    }
}
