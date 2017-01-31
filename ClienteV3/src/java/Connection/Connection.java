/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chernandez
 */
public class Connection {
    
    private static final Connection connection = new Connection();//Just 1 Connection must be created for all sessions
    protected static EntityManagerFactory emf;//Just 1 entity manager factory must be created for all sessions

    public static Connection getInstance() {
        return connection;
    }

    public EntityManagerFactory getEntityManagerFactory() {//Singleton pattern
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClienteV3PU");
        }
        return emf;
    }

    public void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    
}
