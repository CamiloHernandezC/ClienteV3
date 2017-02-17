package Controllers;

import Entities.VisitasEsperadasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.VisitasEsperadasCliFacade;

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

@Named("visitasEsperadasCliController")
@SessionScoped
public class VisitasEsperadasCliController implements Serializable {

    @EJB
    private Facade.VisitasEsperadasCliFacade ejbFacade;
    private List<VisitasEsperadasCli> items = null;
    private VisitasEsperadasCli selected;

    public VisitasEsperadasCliController() {
    }

    public VisitasEsperadasCli getSelected() {
        return selected;
    }

    public void setSelected(VisitasEsperadasCli selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getVisitasEsperadasCliPK().setIdPersona(selected.getPersonasCli().getIdPersona());
        selected.getVisitasEsperadasCliPK().setIdSucursal(selected.getSucursalesCli().getIdSucursal());
    }

    protected void initializeEmbeddableKey() {
        selected.setVisitasEsperadasCliPK(new Entities.VisitasEsperadasCliPK());
    }

    private VisitasEsperadasCliFacade getFacade() {
        return ejbFacade;
    }

    public VisitasEsperadasCli prepareCreate() {
        selected = new VisitasEsperadasCli();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VisitasEsperadasCli> getItems() {
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

    public VisitasEsperadasCli getVisitasEsperadasCli(Entities.VisitasEsperadasCliPK id) {
        return getFacade().find(id);
    }

    public List<VisitasEsperadasCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VisitasEsperadasCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VisitasEsperadasCli.class)
    public static class VisitasEsperadasCliControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VisitasEsperadasCliController controller = (VisitasEsperadasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "visitasEsperadasCliController");
            return controller.getVisitasEsperadasCli(getKey(value));
        }

        Entities.VisitasEsperadasCliPK getKey(String value) {
            Entities.VisitasEsperadasCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.VisitasEsperadasCliPK();
            key.setIdPersona(values[0]);
            key.setIdSucursal(Long.parseLong(values[1]));
            key.setFechaVisita(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(Entities.VisitasEsperadasCliPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getIdSucursal());
            sb.append(SEPARATOR);
            sb.append(value.getFechaVisita());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VisitasEsperadasCli) {
                VisitasEsperadasCli o = (VisitasEsperadasCli) object;
                return getStringKey(o.getVisitasEsperadasCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VisitasEsperadasCli.class.getName()});
                return null;
            }
        }

    }

}
