package com.thoughtworks.sun.dao;

import com.thoughtworks.sun.Person;

import javax.persistence.EntityManager;

public interface PersonDao {
    void save(Person person);

    void setEntityManager(EntityManager entityManager);
}
