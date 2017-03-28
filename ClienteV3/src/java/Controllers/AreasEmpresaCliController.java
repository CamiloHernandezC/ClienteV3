package Controllers;

import Entities.AreasEmpresaCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.AbstractFacade;
import Facade.AreasEmpresaCliFacade;
import GeneralControl.GeneralControl;
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
import org.primefaces.util.BeanUtils;

@Named("areasEmpresaCliController")
@SessionScoped
public class AreasEmpresaCliController extends AbstractPersistenceController<AreasEmpresaCli>{

    @EJB
    private Facade.AreasEmpresaCliFacade ejbFacade;
    private List<AreasEmpresaCli> items = null;
    private List<AreasEmpresaCli> itemsByBranchOffice = null;
    private AreasEmpresaCli selected;

    public AreasEmpresaCliController() {
    }

    public List<AreasEmpresaCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<AreasEmpresaCli> getItemsByBranchOffice() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.AREAS_EMPRESA_ALL+" WHERE"+Querys.AREAS_EMPRESA_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            return (List<AreasEmpresaCli>) ejbFacade.findByQueryArray(squery).result;
        }
        return null;
    }

    public AreasEmpresaCli getAreasEmpresaCli(java.lang.String id) {
        return (AreasEmpresaCli) getFacade().find(id);
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected AreasEmpresaCli getSelected() {
        if(selected==null){
            selected = new AreasEmpresaCli();
        }
        return selected;
    }

    @Override
    protected void setSelected(AreasEmpresaCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setItems(List<AreasEmpresaCli> items) {
        this.items = items;
    }

    @Override
    protected void setEmbeddableKeys() {
        //Nothing to do here
    }

    @Override
    protected void initializeEmbeddableKey() {
        //Nothing to do here
    }

    @Override
    protected void prepareCreate() {
        calculatePrimaryKey(Querys.AREAS_EMPRESA_LAST_PRIMARY_KEY);
    }

    @Override
    protected void prepareUpdate() {
        //assignParametersToUpdate();TODO create user and date field in entity and add this line
    }

    @Override
    protected void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @FacesConverter(forClass = AreasEmpresaCli.class)
    public static class AreasEmpresaCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasEmpresaCliController controller = (AreasEmpresaCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasEmpresaCliController");
            return controller.getAreasEmpresaCli(getKey(value));
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
            if (object instanceof AreasEmpresaCli) {
                AreasEmpresaCli o = (AreasEmpresaCli) object;
                return getStringKey(o.getIdareaemp());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AreasEmpresaCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>

    }

}
