package Controllers;

import Entities.MovMaterialesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovMaterialesCliFacade;

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

@Named("movMaterialesCliController")
@SessionScoped
public class MovMaterialesCliController implements Serializable {

    @EJB
    private Facade.MovMaterialesCliFacade ejbFacade;
    private List<MovMaterialesCli> items = null;
    private MovMaterialesCli selected;

    public MovMaterialesCliController() {
    }

    public MovMaterialesCli getSelected() {
        return selected;
    }

    public void setSelected(MovMaterialesCli selected) {
        this.selected = selected;
    }

    public void prepareCreate() {
        selected = new MovMaterialesCli();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MovMaterialesCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MovMaterialesCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MovMaterialesCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MovMaterialesCli> getItems() {
        if (items == null) {
            items = (List<MovMaterialesCli>) ejbFacade.findAll().result;
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
    @FacesConverter(forClass = MovMaterialesCli.class)
    public static class MovMaterialesCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovMaterialesCliController controller = (MovMaterialesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movMaterialesCliController");
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
            if (object instanceof MovMaterialesCli) {
                MovMaterialesCli o = (MovMaterialesCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovMaterialesCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
    
}
