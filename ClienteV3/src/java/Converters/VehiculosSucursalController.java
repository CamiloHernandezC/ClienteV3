package Converters;


import Entities.VehiculosSucursal;
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

@Named("vehiculosSucursalController")
@SessionScoped
public class VehiculosSucursalController implements Serializable {

    @EJB
    private Facade.VehiculosSucursalFacade ejbFacade;

    public VehiculosSucursalController() {
    }
    /*
    private List<VehiculosSucursal> items = null;
    private VehiculosSucursal selected;

    

    public VehiculosSucursal getSelected() {
        return selected;
    }

    public void setSelected(VehiculosSucursal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getVehiculosSucursalPK().setPlaca(selected.getVehiculos().getPlaca());
        selected.getVehiculosSucursalPK().setSucursal(selected.getSucursales().getIdSucursal());
    }

    protected void initializeEmbeddableKey() {
        selected.setVehiculosSucursalPK(new Entities.VehiculosSucursalPK());
    }

    private VehiculosSucursalFacade getFacade() {
        return ejbFacade;
    }

    public VehiculosSucursal prepareCreate() {
        selected = new VehiculosSucursal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VehiculosSucursalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VehiculosSucursalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VehiculosSucursalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VehiculosSucursal> getItems() {
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

    

    public List<VehiculosSucursal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VehiculosSucursal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    */
    public VehiculosSucursal getVehiculosSucursal(Entities.VehiculosSucursalPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = VehiculosSucursal.class)
    public static class VehiculosSucursalControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiculosSucursalController controller = (VehiculosSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculosSucursalController");
            return controller.getVehiculosSucursal(getKey(value));
        }

        Entities.VehiculosSucursalPK getKey(String value) {
            Entities.VehiculosSucursalPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.VehiculosSucursalPK();
            key.setPlaca(values[0]);
            key.setSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.VehiculosSucursalPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPlaca());
            sb.append(SEPARATOR);
            sb.append(value.getSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VehiculosSucursal) {
                VehiculosSucursal o = (VehiculosSucursal) object;
                return getStringKey(o.getVehiculosSucursalPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VehiculosSucursal.class.getName()});
                return null;
            }
        }

    }

}
