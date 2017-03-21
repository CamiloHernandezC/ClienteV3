package Controllers;

import Entities.MovPersonasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.PersonasCli;
import Entities.PersonasSucursalCli;
import Facade.MovPersonasCliFacade;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.Constants;
import Utils.Result;

import java.io.Serializable;
import java.util.Date;
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

@Named("movPersonasCliController")
@SessionScoped
public class MovPersonasCliController implements Serializable{

    @EJB
    private Facade.MovPersonasCliFacade ejbFacade;
    private List<MovPersonasCli> items = null;
    private MovPersonasCli selected;

    public MovPersonasCliController() {
    }

    public MovPersonasCli getSelected() {
        return selected;
    }

    public void setSelected(MovPersonasCli selected) {
        this.selected = selected;
    }

    protected MovPersonasCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MovPersonasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<MovPersonasCli> getItemsByBranchOffice() {//TODO DATES FILTER HERE
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<MovPersonasCli>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public MovPersonasCli getMovPersonasCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovPersonasCli.class)
    public static class MovPersonasCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovPersonasCliController controller = (MovPersonasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movPersonasCliController");
            return controller.getMovPersonasCli(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovPersonasCli) {
                MovPersonasCli o = (MovPersonasCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovPersonasCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
