/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralControl;

import Converters.util.JsfUtil;
import Entities.MenuCliente;
import Entities.Usuarios;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author MAURICIO
 */
@Named(value = "menuControl")
@SessionScoped
public class MenuControl implements Serializable{

    @EJB
    private Facade.MenuClienteFacade ejbFacade;
    
    private MenuModel menu;
    /**
     * Creates a new instance of MenuController
     */
    public MenuControl() {
    }
    
    @PostConstruct//This method is called just one time by JSF
    public void init() {
        //<editor-fold desc="Menu construction" defaultstate="collapsed">
        menu = new DefaultMenuModel();
        HashMap menuMap = new HashMap();
        
        Usuarios user = JsfUtil.getSessionUser();
        
        String menuQuery = Querys.MENU_CLIENTE_JOIN_PRIVILEGIOS+" WHERE "+Querys.MENU_CLIENTE_NIVEL_MORE_EQUAL+user.getPrivilegios()+"'"+Querys.MENU_CLIENTE_HAS_PRIVILEGE;//TODO ADD STATUS FILTER
        List<MenuCliente> menuItems = (List<MenuCliente>) ejbFacade.findByQueryArray(menuQuery).result;
        for(MenuCliente item: menuItems){
            if(item.getTipo()==Constants.MENU_TYPE_CHILDREN){
                DefaultMenuItem menuItem = new DefaultMenuItem(BundleUtils.getBundleProperty(item.getNombre()));
                menuItem.setUrl(item.getUrl());
                DefaultSubMenu subMenu = (DefaultSubMenu) menuMap.get(item.getPadre());
                if(subMenu==null){//If father isn't loaded yet
                    MenuCliente fatherMenu = ejbFacade.find(item.getPadre());
                    subMenu = new DefaultSubMenu(BundleUtils.getBundleProperty(fatherMenu.getNombre()));
                }
                subMenu.addElement(menuItem);
                menuMap.put(item.getPadre(), subMenu);
            }
        }
        menuMap.forEach((k,v)->menu.addElement((MenuElement) v));
        //</editor-fold>
    }
 
    public MenuModel getMenu() {
        return menu;
    }   

}
