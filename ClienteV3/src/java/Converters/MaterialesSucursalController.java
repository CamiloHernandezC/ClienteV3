package Converters;


import Entities.MaterialesSucursal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("materialesSucursalController")
@SessionScoped
public class MaterialesSucursalController implements Serializable {

    @EJB
    private Facade.MaterialesSucursalFacade ejbFacade;
    
    public MaterialesSucursalController() {
    }
    /*
    private List<MaterialesSucursal> items = null;
    private MaterialesSucursal selected;

    

    public MaterialesSucursal getSelected() {
        return selected;
    }

    public void setSelected(MaterialesSucursal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMaterialesSucursalPK().setIdSucursal(selected.getSucursales().getIdSucursal());
        selected.getMaterialesSucursalPK().setIdMaterial(selected.getMateriales().getIdMaterial());
    }

    protected void initializeEmbeddableKey() {
        selected.setMaterialesSucursalPK(new Entities.MaterialesSucursalPK());
    }

    private MaterialesSucursalFacade getFacade() {
        return ejbFacade;
    }

    public MaterialesSucursal prepareCreate() {
        selected = new MaterialesSucursal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaterialesSucursalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaterialesSucursalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaterialesSucursalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaterialesSucursal> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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
    }

    public List<MaterialesSucursal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaterialesSucursal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    */
    public MaterialesSucursal getMaterialesSucursal(Entities.MaterialesSucursalPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MaterialesSucursal.class)
    public static class MaterialesSucursalControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterialesSucursalController controller = (MaterialesSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materialesSucursalController");
            return controller.getMaterialesSucursal(getKey(value));
        }

        Entities.MaterialesSucursalPK getKey(String value) {
            Entities.MaterialesSucursalPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.MaterialesSucursalPK();
            key.setIdMaterial(Integer.parseInt(values[0]));
            key.setIdSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.MaterialesSucursalPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdMaterial());
            sb.append(SEPARATOR);
            sb.append(value.getIdSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaterialesSucursal) {
                MaterialesSucursal o = (MaterialesSucursal) object;
                return getStringKey(o.getMaterialesSucursalPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaterialesSucursal.class.getName()});
                return null;
            }
        }

    }

}
