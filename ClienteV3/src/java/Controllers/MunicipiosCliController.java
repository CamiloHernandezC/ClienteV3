package Controllers;

import Entities.MunicipiosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.DepartamentosCli;
import Facade.MunicipiosCliFacade;
import Querys.Querys;

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

@Named("municipiosCliController")
@SessionScoped
public class MunicipiosCliController implements Serializable {

    @EJB
    private Facade.MunicipiosCliFacade ejbFacade;
    private List<MunicipiosCli> items = null;
    private MunicipiosCli selected;

    public MunicipiosCliController() {
    }

    public MunicipiosCli getSelected() {
        return selected;
    }

    public void setSelected(MunicipiosCli selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MunicipiosCliFacade getFacade() {
        return ejbFacade;
    }

    public MunicipiosCli prepareCreate() {
        selected = new MunicipiosCli();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MunicipiosCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MunicipiosCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MunicipiosCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MunicipiosCli> getItems() {
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

    public MunicipiosCli getMunicipiosCli(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<MunicipiosCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MunicipiosCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public List<MunicipiosCli> getItemsOfDepartment(DepartamentosCli department) {
        if(department==null){
            items = null;
            return items;
        }
        String squery = Querys.MUNICIPIOS_CLI_DEPARTAMENTO+department.getIdDepartamento()+"'";
        items = (List<MunicipiosCli>) getFacade().findByQueryArray(squery).result;
        return items;
    }

    @FacesConverter(forClass = MunicipiosCli.class)
    public static class MunicipiosCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipiosCliController controller = (MunicipiosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipiosCliController");
            return controller.getMunicipiosCli(getKey(value));
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
            if (object instanceof MunicipiosCli) {
                MunicipiosCli o = (MunicipiosCli) object;
                return getStringKey(o.getIdMunicipio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MunicipiosCli.class.getName()});
                return null;
            }
        }

    }

}
