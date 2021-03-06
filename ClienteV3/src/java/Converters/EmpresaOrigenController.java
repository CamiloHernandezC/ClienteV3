package Converters;


import Entities.EmpresaOrigen;
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

@Named("empresaOrigenController")
@SessionScoped
public class EmpresaOrigenController implements Serializable {

    @EJB
    private Facade.EmpresaOrigenFacade ejbFacade;
    private List<EmpresaOrigen> items = null;
    
    public EmpresaOrigenController() {
    }
    /*
    private List<EmpresaOrigen> items = null;
    private EmpresaOrigen selected;

    

    public EmpresaOrigen getSelected() {
        return selected;
    }

    public void setSelected(EmpresaOrigen selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmpresaOrigenFacade getFacade() {
        return ejbFacade;
    }

    public EmpresaOrigen prepareCreate() {
        selected = new EmpresaOrigen();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmpresaOrigenCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmpresaOrigenUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmpresaOrigenDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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
    

    public List<EmpresaOrigen> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EmpresaOrigen> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    */
    public List<EmpresaOrigen> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
        }
        return items;
    }
    
    public EmpresaOrigen getEmpresaOrigen(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = EmpresaOrigen.class)
    public static class EmpresaOrigenControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaOrigenController controller = (EmpresaOrigenController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaOrigenController");
            return controller.getEmpresaOrigen(getKey(value));
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
            if (object instanceof EmpresaOrigen) {
                EmpresaOrigen o = (EmpresaOrigen) object;
                return getStringKey(o.getIdEmpresaOrigen());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EmpresaOrigen.class.getName()});
                return null;
            }
        }

    }

}
