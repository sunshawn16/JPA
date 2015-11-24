package com.thoughtworks.sun;

import com.thoughtworks.sun.dao.PersonDaoImpl;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class MainTest {

    private PersonDaoImpl personDao;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImpl();
        entityManager = null;
    }

    @Test
    public void shoudleInsertData() throws Exception {
        try{
            entityManager = JpaUtil.getEntityManager();
            personDao.setEntityManager(entityManager);
            Person person = getPerson();
            personDao.save(person);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }

    }

    @Test
    public void testJPLQuery() throws Exception {
        try{
            entityManager = JpaUtil.getEntityManager();
            String jpl = "select person from Person person";
            Query q = entityManager.createQuery(jpl);
            List<Person> all = q.getResultList();
            Iterator<Person> iterator = all .iterator();
            while(iterator.hasNext()){
                Person person = iterator.next();
                System.out.println(person.getFirstName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }

    }


    private Person getPerson() {
        Person person = new Person();
        person.setFirstName("Xiao");
        person.setLastName("Sun");
        return person;
    }
}