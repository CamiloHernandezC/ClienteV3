/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author MAURICIO
 */
@Named(value = "generalViewControl")
@ViewScoped
public class GeneralViewControl implements Serializable{

    //<editor-fold desc="VARIBLES DECLARATION" defaultstate="collapsed">
    private Date actualDate;
    //</editor-fold>
       
    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
    public Date getActualDate() {
        actualDate = new Date();
        return actualDate;
    }
    //</editor-fold>
    
    /**
     * Creates a new instance of CompleteEntry
     */
    public GeneralViewControl() {
    }
    
}
