package Converters;

import Entities.PorteriasSucursal;
import Facade.PorteriasSucursalFacade;
import Querys.Querys;
import Utils.Result;

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

@Named("porteriasSucursalController")
@SessionScoped
public class PorteriasSucursalController implements Serializable {

    @EJB
    private Facade.PorteriasSucursalFacade ejbFacade;
    private List<PorteriasSucursal> items = null;
    private PorteriasSucursal selected;

    public PorteriasSucursalController() {
    }
/*
    public PorteriasSucursal getSelected() {
        return selected;
    }

    public void setSelected(PorteriasSucursal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPorteriasSucursalPK().setPorteria(selected.getPorterias().getIdPorteria());
        selected.getPorteriasSucursalPK().setSucursal(selected.getSucursales().getIdSucursal());
    }

    protected void initializeEmbeddableKey() {
        selected.setPorteriasSucursalPK(new Entities.PorteriasSucursalPK());
    }

    private PorteriasSucursalFacade getFacade() {
        return ejbFacade;
    }

    public PorteriasSucursal prepareCreate() {
        selected = new PorteriasSucursal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PorteriasSucursalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PorteriasSucursalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PorteriasSucursalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PorteriasSucursal> getItems() {
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

    public PorteriasSucursal getPorteriasSucursal(Entities.PorteriasSucursalPK id) {
        return getFacade().find(id);
    }

    public List<PorteriasSucursal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PorteriasSucursal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Result findBranchOfficeByEntry(String entry) {
        String squery = Querys.PORTERIA_SUCURSAL_CLI_PORTERIA + entry + "'";
        return ejbFacade.findByQueryArray(squery);
    }
    
    @FacesConverter(forClass = PorteriasSucursal.class)
    public static class PorteriasSucursalControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PorteriasSucursalController controller = (PorteriasSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "porteriasSucursalController");
            return controller.getPorteriasSucursal(getKey(value));
        }

        Entities.PorteriasSucursalPK getKey(String value) {
            Entities.PorteriasSucursalPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PorteriasSucursalPK();
            key.setPorteria(Integer.parseInt(values[0]));
            key.setSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.PorteriasSucursalPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPorteria());
            sb.append(SEPARATOR);
            sb.append(value.getSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PorteriasSucursal) {
                PorteriasSucursal o = (PorteriasSucursal) object;
                return getStringKey(o.getPorteriasSucursalPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PorteriasSucursal.class.getName()});
                return null;
            }
        }

    }
*/
}
