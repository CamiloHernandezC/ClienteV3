package Controllers;

import Entities.PorteriaSucursalCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.PorteriaSucursalCliFacade;
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

@Named("porteriaSucursalCliController")
@SessionScoped
public class PorteriaSucursalCliController implements Serializable {

    @EJB
    private Facade.PorteriaSucursalCliFacade ejbFacade;
    private List<PorteriaSucursalCli> items = null;
    private PorteriaSucursalCli selected;

    public PorteriaSucursalCliController() {
    }

    public PorteriaSucursalCli getSelected() {
        return selected;
    }

    public void setSelected(PorteriaSucursalCli selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPorteriaSucursalCliPK().setSucursal(selected.getSucursalesCli().getIdSucursal());
        selected.getPorteriaSucursalCliPK().setPorteria(selected.getPorterias().getIdPorteria());
    }

    protected void initializeEmbeddableKey() {
        selected.setPorteriaSucursalCliPK(new Entities.PorteriaSucursalCliPK());
    }

    private PorteriaSucursalCliFacade getFacade() {
        return ejbFacade;
    }

    public PorteriaSucursalCli prepareCreate() {
        selected = new PorteriaSucursalCli();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PorteriaSucursalCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PorteriaSucursalCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PorteriaSucursalCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PorteriaSucursalCli> getItems() {
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

    public PorteriaSucursalCli getPorteriaSucursalCli(Entities.PorteriaSucursalCliPK id) {
        return getFacade().find(id);
    }

    public List<PorteriaSucursalCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PorteriaSucursalCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Result findBranchOfficeByEntry(String entry) {
        String squery = Querys.PORTERIA_SUCURSAL_CLI_PORTERIA + entry + "'";
        return ejbFacade.findByQueryArray(squery);
    }

    @FacesConverter(forClass = PorteriaSucursalCli.class)
    public static class PorteriaSucursalCliControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PorteriaSucursalCliController controller = (PorteriaSucursalCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "porteriaSucursalCliController");
            return controller.getPorteriaSucursalCli(getKey(value));
        }

        Entities.PorteriaSucursalCliPK getKey(String value) {
            Entities.PorteriaSucursalCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PorteriaSucursalCliPK();
            key.setPorteria(Long.parseLong(values[0]));
            key.setSucursal(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(Entities.PorteriaSucursalCliPK value) {
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
            if (object instanceof PorteriaSucursalCli) {
                PorteriaSucursalCli o = (PorteriaSucursalCli) object;
                return getStringKey(o.getPorteriaSucursalCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PorteriaSucursalCli.class.getName()});
                return null;
            }
        }

    }

}
