package Controllers;

import Entities.MenuCliente;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MenuClienteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("menuClienteController")
@SessionScoped
public class MenuClienteController implements Serializable {

    @EJB
    private Facade.MenuClienteFacade ejbFacade;
    private List<MenuCliente> items = null;
    private MenuCliente selected;

    public MenuClienteController() {
    }

    public MenuCliente getSelected() {
        return selected;
    }

    public void setSelected(MenuCliente selected) {
        this.selected = selected;
    }

    private MenuClienteFacade getFacade() {
        return ejbFacade;
    }

    public List<MenuCliente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MenuCliente getMenuCliente(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MenuCliente.class)
    public static class MenuClienteControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MenuClienteController controller = (MenuClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "menuClienteController");
            return controller.getMenuCliente(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MenuCliente) {
                MenuCliente o = (MenuCliente) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MenuCliente.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
