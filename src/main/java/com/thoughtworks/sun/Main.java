package com.thoughtworks.sun;

import com.thoughtworks.sun.dao.PersonDao;
import com.thoughtworks.sun.dao.PersonDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.persistence.Persistence.createEntityManagerFactory;


public class Main {
    private static final Logger LOGGER = Logger.getLogger("JPA");
    private static PersonDao PERSON_DAO = new PersonDaoImpl();


    public static void main(String[] args) {
        run(createEntityManagerFactory("PersistenceUnit"), createPerson());
    }

    public static void run(EntityManagerFactory factory, Person person) {
        EntityManager entityManager = null;
        PERSON_DAO.setEntityManager(entityManager);
        try {
            entityManager = factory.createEntityManager();

            PERSON_DAO.save(person);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }


    private static Person createPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith");
        return person;
    }

}
