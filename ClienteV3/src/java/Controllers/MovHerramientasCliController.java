package Controllers;

import Entities.MovHerramientasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovHerramientasCliFacade;

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

@Named("movHerramientasCliController")
@SessionScoped
public class MovHerramientasCliController implements Serializable {

    @EJB
    private Facade.MovHerramientasCliFacade ejbFacade;
    private List<MovHerramientasCli> items = null;
    private MovHerramientasCli selected;

    public MovHerramientasCliController() {
    }

    public MovHerramientasCli getSelected() {
        return selected;
    }

    public void setSelected(MovHerramientasCli selected) {
        this.selected = selected;
    }

    public void prepareCreate() {
        selected = new MovHerramientasCli();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MovHerramientasCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MovHerramientasCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MovHerramientasCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MovHerramientasCli> getItems() {
        if (items == null) {
            items = (List<MovHerramientasCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbFacade.edit(selected);
                } else {
                    ejbFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = MovHerramientasCli.class)
    public static class MovHerramientasCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovHerramientasCliController controller = (MovHerramientasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movHerramientasCliController");
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
            if (object instanceof MovHerramientasCli) {
                MovHerramientasCli o = (MovHerramientasCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovHerramientasCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>

}
