package com.thoughtworks.sun;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by xiaosun on 11/25/15.
 */
public final class JpaUtil {
    private static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("PersistenceUnit");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
