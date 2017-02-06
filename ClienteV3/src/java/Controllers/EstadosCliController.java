package Controllers;

import Entities.EstadosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.EstadosCliFacade;

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

@Named("estadosCliController")
@SessionScoped
public class EstadosCliController implements Serializable {

    @EJB
    private Facade.EstadosCliFacade ejbFacade;
    private List<EstadosCli> items = null;

    public EstadosCliController() {
    }

    public List<EstadosCli> getItems() {
        if (items == null) {
            items = (List<EstadosCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = EstadosCli.class)
    public static class EstadosCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadosCliController controller = (EstadosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadosCliController");
            return controller.ejbFacade.find(getKey(value));
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
            if (object instanceof EstadosCli) {
                EstadosCli o = (EstadosCli) object;
                return getStringKey(o.getIdEstado());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstadosCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>

}
