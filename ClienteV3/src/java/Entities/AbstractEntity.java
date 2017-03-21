/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author MAURICIO
 */
public abstract class AbstractEntity {
    
    public abstract Long getPrimaryKey();
    public abstract void setPrimaryKey(Long primaryKey);
    public abstract void setUser(PersonasCli user);
    public abstract void setDate(Date date);
    
}
