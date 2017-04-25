package Converters;


import Entities.VisitasEsperadas;
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

@Named("visitasEsperadasController")
@SessionScoped
public class VisitasEsperadasController implements Serializable {

    @EJB
    private Facade.VisitasEsperadasFacade ejbFacade;
    
    public VisitasEsperadasController() {
    }
    /*
    private List<VisitasEsperadas> items = null;
    private VisitasEsperadas selected;

    

    public VisitasEsperadas getSelected() {
        return selected;
    }

    public void setSelected(VisitasEsperadas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getVisitasEsperadasPK().setIdPersona(selected.getPersonas().getIdPersona());
        selected.getVisitasEsperadasPK().setSucursal(selected.getSucursales().getIdSucursal());
    }

    protected void initializeEmbeddableKey() {
        selected.setVisitasEsperadasPK(new Entities.VisitasEsperadasPK());
    }

    private VisitasEsperadasFacade getFacade() {
        return ejbFacade;
    }

    public VisitasEsperadas prepareCreate() {
        selected = new VisitasEsperadas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VisitasEsperadasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VisitasEsperadas> getItems() {
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

    public List<VisitasEsperadas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VisitasEsperadas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    */
    public VisitasEsperadas getVisitasEsperadas(Entities.VisitasEsperadasPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = VisitasEsperadas.class)
    public static class VisitasEsperadasControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VisitasEsperadasController controller = (VisitasEsperadasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "visitasEsperadasController");
            return controller.getVisitasEsperadas(getKey(value));
        }

        Entities.VisitasEsperadasPK getKey(String value) {
            Entities.VisitasEsperadasPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.VisitasEsperadasPK();
            key.setIdPersona(Integer.parseInt(values[0]));
            key.setSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.VisitasEsperadasPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VisitasEsperadas) {
                VisitasEsperadas o = (VisitasEsperadas) object;
                return getStringKey(o.getVisitasEsperadasPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VisitasEsperadas.class.getName()});
                return null;
            }
        }

    }

}
