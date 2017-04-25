package Converters;

import Entities.Departamentos;
import Entities.Municipios;
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

@Named("municipiosController")
@SessionScoped
public class MunicipiosController implements Serializable {

    @EJB
    private Facade.MunicipiosFacade ejbFacade;
    private List<Municipios> items = null;
    
    public MunicipiosController() {
    }
    /*
    
    private Municipios selected;

    

    public Municipios getSelected() {
        return selected;
    }

    public void setSelected(Municipios selected) {
        this.selected = selected;
    }

    private MunicipiosFacade getFacade() {
        return ejbFacade;
    }
    */

    public List<Municipios> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
        }
        return items;
    }

    public Municipios getMunicipios(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public List<Municipios> getItemsOfDepartment(Departamentos department) {
        if(department==null){
            items = null;
            return items;
        }
        String squery = Querys.MUNICIPIOS_CLI_DEPARTAMENTO+department.getIdDepartamento()+"'";
        items = (List<Municipios>) ejbFacade.findByQueryArray(squery).result;
        return items;
    }


    @FacesConverter(forClass = Municipios.class)
    public static class MunicipiosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipiosController controller = (MunicipiosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipiosController");
            return controller.getMunicipios(getKey(value));
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
            if (object instanceof Municipios) {
                Municipios o = (Municipios) object;
                return getStringKey(o.getIdMunicipio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipios.class.getName()});
                return null;
            }
        }

    }

}
