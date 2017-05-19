/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author MAURICIO
 */
public abstract class AbstractEntity implements Serializable{
    /*Primary key used to calculate autoincremental primary key*/
    public abstract int getPrimaryKey();
    public abstract void setPrimaryKey(int primaryKey);
    public abstract void setUser(Personas user);
    public abstract void setDate(Date date);
    public abstract void setStatus(Integer STATUS_INACTIVE);
    
}
