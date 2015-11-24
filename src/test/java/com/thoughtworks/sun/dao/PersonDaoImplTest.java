package com.thoughtworks.sun.dao;

import com.thoughtworks.sun.Person;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;


public class PersonDaoImplTest {

    private EasyMockSupport easyMockSupport;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private PersonDaoImpl personDao;

    @Before
    public void setUp() throws Exception {
        easyMockSupport = new EasyMockSupport();
        entityManager = easyMockSupport.createMock(EntityManager.class);
        transaction = easyMockSupport.createMock(EntityTransaction.class);
        personDao = new PersonDaoImpl();
        personDao.setEntityManager(entityManager);
    }

    @Test
    public void shouldInsertDataPerson() throws Exception {
        Person person = createPerson();
        expect(entityManager.getTransaction()).andReturn(transaction);
        transaction.begin();
        entityManager.persist(person);
        transaction.commit();

        easyMockSupport.replayAll();
        personDao.save(person);
        easyMockSupport.verifyAll();
    }

    @Test
    public void shouldRollBackWhenHaveError() throws Exception {
        Person person = createPerson();
        expect(entityManager.getTransaction()).andReturn(transaction);
        transaction.begin();
        entityManager.persist(person);
        expectLastCall().andThrow(new RuntimeException());
        expect(transaction.isActive()).andReturn(true);
        transaction.rollback();

        easyMockSupport.replayAll();
        personDao.save(person);
        easyMockSupport.verifyAll();
    }

    private Person createPerson() {
        Person person = new Person();
        person.setFirstName("Xiao");
        person.setLastName("Sun");
        return person;
    }
}