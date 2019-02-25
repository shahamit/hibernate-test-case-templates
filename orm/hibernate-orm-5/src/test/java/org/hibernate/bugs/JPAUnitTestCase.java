package org.hibernate.bugs;

import org.hibernate.lists.Credentials;
import org.hibernate.lists.GenericConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void readObjectWithList() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        GenericConnection connection = new GenericConnection();
        connection.setName("Oracle Connection");
        connection.setUrl("<jdbc-url>");

        List<Credentials> credentials = new ArrayList<>();
        final Credentials c1 = new Credentials();
        c1.setCredId("user1");
        c1.setEncryptedPassword("oghjero");
        credentials.add(c1);
        connection.setCredentials(credentials);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("P1", "V1");

        connection.setParameters(parameters);

        saveConnection(entityManager, connection);

        Assert.assertNotNull(entityManager.find(GenericConnection.class, connection.getId()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void saveConnection(EntityManager entityManager, GenericConnection connection) {
        entityManager.persist(connection);
        entityManager.flush();
        entityManager.detach(connection);
    }
}