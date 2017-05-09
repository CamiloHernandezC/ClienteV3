package Converters;


import Entities.Horarios;
import Facade.AbstractFacade;
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

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
//@Named("horariosController")
//@SessionScoped
public class HorariosController extends AbstractPersistenceController<Horarios>{

    @EJB
    protected Facade.HorariosFacade ejbFacade;
    protected Horarios selected;
    protected List<Horarios> items;
    
    public HorariosController() {
    }
    
    public Horarios getHorarios(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected Horarios getSelected() {
        if(selected== null){
            selected = new Horarios();
        }
        return selected;
    }

    @Override
    protected void setSelected(Horarios selected) {
        this.selected = selected;
    }

    @Override
    protected void setItems(List<Horarios> items) {
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
        calculatePrimaryKey(Querys.HORARIOS_PRIMARY_KEY);
        prepareUpdate();
    }

    @Override
    protected void prepareUpdate() {
        assignParametersToUpdate();
    }

    @Override
    public void clean() {
        selected = null;
        items = null;
    }

    @FacesConverter(forClass = Horarios.class)
    public static class HorariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HorariosController controller = (HorariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "horariosController");
            return controller.getHorarios(getKey(value));
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
            if (object instanceof Horarios) {
                Horarios o = (Horarios) object;
                return getStringKey(o.getIdHorario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Horarios.class.getName()});
                return null;
            }
        }

    }

}
