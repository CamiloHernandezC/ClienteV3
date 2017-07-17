package Converters;


import Entities.Categorias;
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

@Named("categoriasController")
@SessionScoped
public class CategoriasController implements Serializable {

    @EJB
    private Facade.CategoriasFacade ejbFacade;
    
    public CategoriasController() {
    }
    
    private List<Categorias> items = null;
   
    public List<Categorias> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
        }
        return items;
    }

    public Categorias getCategorias(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Categorias.class)
    public static class CategoriasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriasController controller = (CategoriasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriasController");
            return controller.getCategorias(getKey(value));
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
            if (object instanceof Categorias) {
                Categorias o = (Categorias) object;
                return getStringKey(o.getIdCategoria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categorias.class.getName()});
                return null;
            }
        }

    }

}
