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
        DefaultSubMenu firstSubmenu = new DefaultSubMenu(rb.getString("Entry"));
        
        
        DefaultMenuItem item = new DefaultMenuItem(rb.getString("Complete_Entry"));
        item.setUrl(Navigation.PAGE_COMPLETE_ENTRY);
        firstSubmenu.addElement(item);
        
        item = new DefaultMenuItem(rb.getString("Express_Entry"));
        item.setUrl(Navigation.PAGE_EXPRESS_ENTRY);
        firstSubmenu.addElement(item);
        
        
         
        menu.addElement(firstSubmenu);
         
        //EXIT SUBMENU
        DefaultSubMenu secondSubmenu = new DefaultSubMenu(rb.getString("Exit"));
 
        item = new DefaultMenuItem(rb.getString("Complete_Exit"));
        item.setUrl(Navigation.PAGE_COMPLETE_EXIT);
        secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem(rb.getString("Express_Exit"));
        item.setUrl(Navigation.PAGE_EXPRESS_EXIT);
        secondSubmenu.addElement(item);
 
        menu.addElement(secondSubmenu);
        
        //CONFIG SUBMENU
        DefaultSubMenu thirdSubmenu = new DefaultSubMenu(rb.getString("Configuration"));

        item = new DefaultMenuItem(rb.getString("Configuration"));
        item.setUrl(Navigation.PAGE_CONFIGURATION);
        thirdSubmenu.addElement(item);
        
        menu.addElement(thirdSubmenu);
        //</editor-fold>
    }
 
    public MenuModel getMenu() {
        return menu;
    }   

}
