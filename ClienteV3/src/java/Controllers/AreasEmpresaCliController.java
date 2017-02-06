package Controllers;

import Entities.AreasEmpresaCli;

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

@Named("areasEmpresaCliController")
@SessionScoped
public class AreasEmpresaCliController implements Serializable {

    @EJB
    private Facade.AreasEmpresaCliFacade ejbFacade;
    private List<AreasEmpresaCli> items = null;//TO SHOW IN SELECT ONE MENU

    public AreasEmpresaCliController() {
    }

    public List<AreasEmpresaCli> getItems() {
        if (items == null) {
            items = (List<AreasEmpresaCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = AreasEmpresaCli.class)
    public static class AreasEmpresaCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasEmpresaCliController controller = (AreasEmpresaCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasEmpresaCliController");
            return controller.ejbFacade.find(getKey(value));
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

    }
    //</editor-fold>
}
