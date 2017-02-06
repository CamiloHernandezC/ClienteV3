package Controllers;

import Entities.MaterialesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MaterialesCliFacade;

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

@Named("materialesCliController")
@SessionScoped
public class MaterialesCliController implements Serializable {

    @EJB
    private Facade.MaterialesCliFacade ejbFacade;
    private List<MaterialesCli> items = null;
    private MaterialesCli selected;

    public MaterialesCliController() {
    }

    public MaterialesCli getSelected() {
        return selected;
    }

    public void setSelected(MaterialesCli selected) {
        this.selected = selected;
    }

    public void prepareCreate() {
        selected = new MaterialesCli();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaterialesCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaterialesCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaterialesCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaterialesCli> getItems() {
        if (items == null) {
            items = (List<MaterialesCli>) ejbFacade.findAll().result;
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
    @FacesConverter(forClass = MaterialesCli.class)
    public static class MaterialesCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterialesCliController controller = (MaterialesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materialesCliController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaterialesCli) {
                MaterialesCli o = (MaterialesCli) object;
                return getStringKey(o.getIdMaterial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaterialesCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
    
}
