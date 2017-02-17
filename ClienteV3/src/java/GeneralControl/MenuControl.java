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
        DefaultSubMenu firstSubmenu = new DefaultSubMenu(rb.getString("Datos maestros"));
        
        
        DefaultMenuItem item = new DefaultMenuItem(rb.getString("Personas"));
        item.setUrl(Navigation.PAGE_MASTER_DATA_PERSON);
        firstSubmenu.addElement(item);
         
        menu.addElement(firstSubmenu);
         
        //</editor-fold>
    }
 
    public MenuModel getMenu() {
        return menu;
    }   

}
