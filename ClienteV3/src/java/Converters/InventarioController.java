package Converters;

import Entities.Inventario;
import Facade.InventarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS SUCURSAL CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
/*@Named("inventarioController")
@SessionScoped*/
public class InventarioController implements Serializable {

    @EJB
    protected Facade.InventarioFacade ejbFacade;
    protected List<Inventario> items = null;
    protected Inventario selected;

    public InventarioController() {
    }

    public Inventario getSelected() {
        return selected;
    }

    public void setSelected(Inventario selected) {
        this.selected = selected;
    }

    protected InventarioFacade getFacade() {
        return ejbFacade;
    }

    public List<Inventario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Inventario getInventario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Inventario.class)
    public static class InventarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventarioController controller = (InventarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarioController");
            return controller.getInventario(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Inventario) {
                Inventario o = (Inventario) object;
                return getStringKey(o.getIdInventario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Inventario.class.getName()});
                return null;
            }
        }

    }

}
