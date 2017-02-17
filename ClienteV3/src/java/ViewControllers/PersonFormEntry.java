/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author MAURICIO
 */
@Named(value = "personFormEntry")
@SessionScoped
public class PersonFormEntry implements Serializable{

    //<editor-fold desc="VARIBLES DECLARATION" defaultstate="collapsed">
    boolean disableNoEditableField;
    boolean disableOtherEnterprise =true;//Starts in true until other origin enterprise is selected
    //</editor-fold>
    
    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
    public boolean isDisableNoEditableField() {
        return disableNoEditableField;
    }
    public void setDisableNoEditableField(boolean disableNoEditableField) {
        this.disableNoEditableField = disableNoEditableField;
    }

    public boolean isDisableOtherEnterprise() {
        return disableOtherEnterprise;
    }

    public void setDisableOtherEnterprise(boolean disableOtherEnterprise) {
        this.disableOtherEnterprise = disableOtherEnterprise;
    }
    //</editor-fold>
    /**
     * Creates a new instance of CompleteEntry
     */
    public PersonFormEntry() {
    }
    
   
}
