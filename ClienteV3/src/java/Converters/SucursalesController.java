package Converters;


import Entities.Sucursales;
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

@Named("sucursalesController")
@SessionScoped
public class SucursalesController implements Serializable {

    @EJB
    private Facade.SucursalesFacade ejbFacade;
    
    public SucursalesController() {
    }
    
    public Sucursales getSucursales(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Sucursales.class)
    public static class SucursalesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SucursalesController controller = (SucursalesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sucursalesController");
            return controller.getSucursales(getKey(value));
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
            if (object instanceof Sucursales) {
                Sucursales o = (Sucursales) object;
                return getStringKey(o.getIdSucursal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Sucursales.class.getName()});
                return null;
            }
        }

    }

}
