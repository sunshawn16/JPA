package com.thoughtworks.sun.dao;

import com.thoughtworks.sun.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by xiaosun on 11/24/15.
 */
public class PersonDaoImpl implements PersonDao {
    private EntityManager entityManager;

    public void save(Person person) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(person);
            transaction.commit();
        }catch(Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
