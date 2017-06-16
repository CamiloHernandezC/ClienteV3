package Converters;

import Converters.util.JsfUtil;
import Entities.AreasEmpresa;
import GeneralControl.GeneralControl;
import Querys.Querys;

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

@Named("areasEmpresaController")
@SessionScoped
public class AreasEmpresaController implements Serializable{

    @EJB
    private Facade.AreasEmpresaFacade ejbFacade;
    /*private List<AreasEmpresa> items = null;
    private AreasEmpresa selected;

    public AreasEmpresaController() {
    }

    public List<AreasEmpresa> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    

    public AreasEmpresa getAreasEmpresa(java.lang.Integer id) {
        return (AreasEmpresa) getFacade().find(id);
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected AreasEmpresa getSelected() {
        if(selected==null){
            selected = new AreasEmpresa();
        }
        return selected;
    }

    @Override
    protected void setSelected(AreasEmpresa selected) {
        this.selected = selected;
    }

    @Override
    protected void setItems(List<AreasEmpresa> items) {
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
    */
    
    public List<AreasEmpresa> getItemsByBranchOffice() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.AREAS_EMPRESA_ALL+" WHERE"+Querys.AREAS_EMPRESA_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            return (List<AreasEmpresa>) ejbFacade.findByQueryArray(squery).result;
        }
        return null;
    }
    
    public AreasEmpresa getAreasEmpresa(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AreasEmpresa.class)
    public static class AreasEmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasEmpresaController controller = (AreasEmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasEmpresaController");
            return controller.getAreasEmpresa(getKey(value));
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
            if (object instanceof AreasEmpresa) {
                AreasEmpresa o = (AreasEmpresa) object;
                return getStringKey(o.getIdAreaEmpresa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AreasEmpresa.class.getName()});
                return null;
            }
        }

    }

}
