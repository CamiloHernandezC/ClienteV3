package Controllers;

import Entities.EmpresaOrigenCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.AreasEmpresaCli;
import Facade.EmpresaOrigenCliFacade;
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

@Named("empresaOrigenCliController")
@SessionScoped
public class EmpresaOrigenCliController extends AbstractPersistenceController<EmpresaOrigenCli> {

    @EJB
    private Facade.EmpresaOrigenCliFacade ejbFacade;
    private List<EmpresaOrigenCli> items = null;
    private EmpresaOrigenCli selected;

    public EmpresaOrigenCliController() {
    }

    @Override
    public EmpresaOrigenCli getSelected() {
        if(selected==null){
            selected = new EmpresaOrigenCli();
        }
        return selected;
    }

    @Override
    public void setSelected(EmpresaOrigenCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {//Nothing to do here
    }

    @Override
    protected void initializeEmbeddableKey() {//Nothing to do here
    }

    @Override
    protected EmpresaOrigenCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        calculatePrimaryKey(Querys.EMPRESA_ORIGEN_LAST_PRIMARY_KEY);
    }

    public List<EmpresaOrigenCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public EmpresaOrigenCli getEmpresaOrigenCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @Override
    protected void setItems(List<EmpresaOrigenCli> items) {
        this.items = items;
    }

    @Override
    protected void prepareUpdate() {
        assignParametersToUpdate();
    }

    @Override
    protected void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = EmpresaOrigenCli.class)
    public static class EmpresaOrigenCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaOrigenCliController controller = (EmpresaOrigenCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaOrigenCliController");
            return controller.getEmpresaOrigenCli(getKey(value));
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
            if (object instanceof EmpresaOrigenCli) {
                EmpresaOrigenCli o = (EmpresaOrigenCli) object;
                return getStringKey(o.getIdEmorigen());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EmpresaOrigenCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
