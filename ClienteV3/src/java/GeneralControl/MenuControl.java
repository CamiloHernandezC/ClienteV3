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
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author MAURICIO
 */
@Named(value = "menuControl")
@SessionScoped
public class MenuControl implements Serializable {

    @EJB
    private Facade.MenuClienteFacade ejbFacade;

    private MenuModel menu;

    /**
     * Creates a new instance of MenuController
     */
    public MenuControl() {
    }

    //<editor-fold desc="Menu construction" defaultstate="collapsed">
    @PostConstruct//This method is called just one time by JSF
    public void init() {
        menu = new DefaultMenuModel();
        Usuarios user = JsfUtil.getSessionUser();
        String menuQuery = Querys.MENU_CLIENTE_JOIN_PRIVILEGIOS + " WHERE (" + Querys.MENU_CLIENTE_NIVEL_MORE_EQUAL + user.getPrivilegios() + "' AND " + Querys.MENU_CLIENTE_TIPO + Constants.MENU_TYPE_SUPER_FATHER + "'" + Querys.MENU_CLIENTE_HAS_PRIVILEGE + Querys.MENU_CLIENTE_ACTIVE;//TODO ADD STATUS FILTER
        List<MenuCliente> menuItems = (List<MenuCliente>) ejbFacade.findByQueryArray(menuQuery).result;
        for (MenuCliente item : menuItems) {
            DefaultSubMenu subMenu = new DefaultSubMenu(BundleUtils.getBundleProperty(item.getNombre()));
            subMenu = bajar(item.getMenuClienteList(), subMenu);
            menu.addElement(subMenu);
        }
    }
    //</editor-fold>

    private DefaultSubMenu bajar(List<MenuCliente> childrens, DefaultSubMenu subMenu) {
        for (MenuCliente children : childrens) {
            if (!children.getEstado()) {
                continue;
            }
            if (children.getTipo() == Constants.MENU_TYPE_FATHER) {
                DefaultSubMenu subSubMenu = new DefaultSubMenu(BundleUtils.getBundleProperty(children.getNombre()));
                subSubMenu = bajar(children.getMenuClienteList(), subSubMenu);
                subMenu.addElement(subSubMenu);
                continue;
            }
            DefaultMenuItem menuItem = new DefaultMenuItem(BundleUtils.getBundleProperty(children.getNombre()));
            menuItem.setUrl(children.getUrl());
            subMenu.addElement(menuItem);
        }
        return subMenu;
    }

    public MenuModel getMenu() {
        return menu;
    }

}
