package com.thoughtworks.sun;

import com.thoughtworks.sun.dao.PersonDao;
import com.thoughtworks.sun.dao.PersonDaoImpl;
import org.junit.Test;

import javax.persistence.EntityManager;

public class MainTest {

    @Test
    public void shoudleInsertData() throws Exception {
        EntityManager entityManager = null;
        try{
            entityManager = JpaUtil.getEntityManager();
            PersonDao personDao = new PersonDaoImpl();
            personDao.setEntityManager(entityManager);
            Person person = new Person();
            person.setFirstName("Yu");
            person.setLastName("Zang");
            personDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!= null){
                entityManager.close();
            }
        }

    }
}