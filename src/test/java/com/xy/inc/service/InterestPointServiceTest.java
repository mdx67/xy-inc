package com.xy.inc.service;

import com.xy.inc.domain.InterestPoint;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Matheus Xavier
 */
public class InterestPointServiceTest {

    private static final List<InterestPoint> interestPoints = Arrays.asList(new InterestPoint(1, "Lanchonete", 27, 12), new InterestPoint(2, "Joalheria", 15, 12));

    @Mock
    private EntityManager manager;

    public InterestPointService interestPointService = new InterestPointService();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(interestPointService, "manager", manager);
    }

    @Test
    public void listAllInterestPoints() throws Exception {
        InterestPoint interestPoint = new InterestPoint(1, "Lanchonete", 27, 12);

        Query query = mock(Query.class);
        when(manager.createNamedQuery("InterestPoint.listAll")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(interestPoint));

        List<InterestPoint> response = interestPointService.listAll();

        assertTrue(!response.isEmpty());
        assertTrue(response.size() == 1);
    }

    @Test
    public void listInterestPointsNextToMe() throws Exception {
        String expectedQueryString = "select * from interest_point where sqrt(power(x - :x, 2) + power(y - :y, 2)) <= :maxDistance";

        Query query = mock(Query.class);
        when(manager.createNativeQuery(expectedQueryString)).thenReturn(query);
        when(query.getResultList()).thenReturn(interestPoints);

        List<InterestPoint> response = interestPointService.listUpcoming(20, 10, 10);

        assertTrue(!response.isEmpty());
        assertTrue(response.size() == 2);
    }

    @Test
    public void testSave() {
        InterestPoint interestPoint = new InterestPoint("Pub", 12, 8);

        interestPointService.save(interestPoint);

        verify(manager, times(1)).persist(interestPoint);
    }
}
