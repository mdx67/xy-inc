package com.xy.inc.domain;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author Matheus Xavier
 */
public class InterestPointTest {

    private LocalValidatorFactoryBean localValidatorFactory;

    @Before
    public void setup() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    @Test
    public void validateComplete() {
        InterestPoint interestPoint = new InterestPoint();
        interestPoint.setName("Posto");
        interestPoint.setY(20);
        interestPoint.setX(20);

        Set<ConstraintViolation<InterestPoint>> constraintViolations = localValidatorFactory.validate(interestPoint);

        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void validateWithoutX() {
        InterestPoint interestPoint = new InterestPoint();
        interestPoint.setName("Supermercado");
        interestPoint.setY(10);

        Set<ConstraintViolation<InterestPoint>> constraintViolations = localValidatorFactory.validate(interestPoint);

        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Você deve informar a posição X."));
    }

    @Test
    public void validateWithoutY() {
        InterestPoint interestPoint = new InterestPoint();
        interestPoint.setName("Pub");
        interestPoint.setX(15);

        Set<ConstraintViolation<InterestPoint>> constraintViolations = localValidatorFactory.validate(interestPoint);

        assertTrue(constraintViolations.size() == 1);
        assertTrue(constraintViolations.iterator().next().getMessage().equals("Você deve informar a posição Y."));
    }
}
