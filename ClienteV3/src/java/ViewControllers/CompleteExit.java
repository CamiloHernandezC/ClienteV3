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
@Named(value = "completeExit")
@SessionScoped
public class CompleteExit implements Serializable{

    //<editor-fold desc="VARIBLES DECLARATION" defaultstate="collapsed">
    boolean selectExitMethod =true;
    //</editor-fold>
    
    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
    public boolean isSelectExitMethod() {
        return selectExitMethod;
    }

    public void setSelectExitMethod(boolean selectEntryMethod) {
        this.selectExitMethod = selectEntryMethod;
    }
    //</editor-fold>

    /**
     * Creates a new instance of CompleteEntry
     */
    public CompleteExit() {
    }
    
}
