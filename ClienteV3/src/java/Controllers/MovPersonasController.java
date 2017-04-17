package Controllers;

import Entities.MovPersonas;
import Controllers.util.JsfUtil;
import Facade.MovPersonasFacade;
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

@Named("movPersonasController")
@SessionScoped
public class MovPersonasController implements Serializable {

    @EJB
    private Facade.MovPersonasFacade ejbFacade;
    private List<MovPersonas> items = null;
    private MovPersonas selected;

    public MovPersonasController() {
    }

   public MovPersonas getSelected() {
        return selected;
    }

    public void setSelected(MovPersonas selected) {
        this.selected = selected;
    }

    protected MovPersonasFacade getFacade() {
        return ejbFacade;
    }

    public List<MovPersonas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<MovPersonas> getItemsByBranchOffice() {//TODO DATES FILTER HERE
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<MovPersonas>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public MovPersonas getMovPersonas(java.lang.Integer id) {
        return getFacade().find(id);
    }


    @FacesConverter(forClass = MovPersonas.class)
    public static class MovPersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovPersonasController controller = (MovPersonasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movPersonasController");
            return controller.getMovPersonas(getKey(value));
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
            if (object instanceof MovPersonas) {
                MovPersonas o = (MovPersonas) object;
                return getStringKey(o.getIdMovPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovPersonas.class.getName()});
                return null;
            }
        }

    }

}
