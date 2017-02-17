/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralControl;

import Utils.Navigation;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author MAURICIO
 */
@Named(value = "menuControl")
@ApplicationScoped
public class MenuControl {

    
    private MenuModel menu;
    /**
     * Creates a new instance of MenuController
     */
    public MenuControl() {
    }
    
    @PostConstruct//This method is called just one time by JSF
    public void init() {
        
        //<editor-fold desc="Menu" defaultstate="collapsed">
        menu = new DefaultMenuModel();
         
        ResourceBundle rb = ResourceBundle.getBundle("Utils/Bundle");
        
        //ENTRY SUBMENU
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Datos Maestros");
        
        
        DefaultMenuItem item = new DefaultMenuItem("Personas");
        item.setUrl(Navigation.PAGE_MASTER_DATA_PERSON);
        firstSubmenu.addElement(item);
         
        menu.addElement(firstSubmenu);
        
        //ENTRY SUBMENU
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Reportes");
        
        
        item = new DefaultMenuItem("Personas");
        item.setUrl(Navigation.PAGE_REPORT_PERSON);
        secondSubmenu.addElement(item);
         
        menu.addElement(secondSubmenu);
         
        //</editor-fold>
    }
 
    public MenuModel getMenu() {
        return menu;
    }   

}
