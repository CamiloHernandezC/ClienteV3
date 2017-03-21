package Controllers;

import Entities.PrivilegiosCliente;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.PrivilegiosClienteFacade;

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

@Named("privilegiosClienteController")
@SessionScoped
public class PrivilegiosClienteController implements Serializable {

    @EJB
    private Facade.PrivilegiosClienteFacade ejbFacade;
    private List<PrivilegiosCliente> items = null;
    private PrivilegiosCliente selected;

    public PrivilegiosClienteController() {
    }

    public PrivilegiosCliente getSelected() {
        return selected;
    }

    public void setSelected(PrivilegiosCliente selected) {
        this.selected = selected;
    }

    private PrivilegiosClienteFacade getFacade() {
        return ejbFacade;
    }

    public List<PrivilegiosCliente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public PrivilegiosCliente getPrivilegiosCliente(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = PrivilegiosCliente.class)
    public static class PrivilegiosClienteControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PrivilegiosClienteController controller = (PrivilegiosClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "privilegiosClienteController");
            return controller.getPrivilegiosCliente(getKey(value));
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
            if (object instanceof PrivilegiosCliente) {
                PrivilegiosCliente o = (PrivilegiosCliente) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PrivilegiosCliente.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
