package Converters;


import Entities.Estados;
import Entities.Personas;
import Facade.PersonasFacade;
import Querys.Querys;
import Utils.Constants;
import Utils.Result;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
//@Named("personasController")
//@SessionScoped
public class PersonasController extends AbstractPersistenceController<Personas>{

     @EJB
    protected Facade.PersonasFacade ejbFacade;
    protected List<Personas> items = null;
    protected Personas selected;
    
    public PersonasController() {
    }
    
    //<editor-fold desc="INHERITED METHODS" defaultstate="collapsed">
    @Override
    public Personas getSelected() {
        if (selected == null) {
            selected = new Personas();
        }
        return selected;
    }

    @Override
    public void setSelected(Personas selected) {
        this.selected = selected;
    }

    @Override
    protected PersonasFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected void setItems(List<Personas> items) {
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
    public void prepareCreate() {
        calculatePrimaryKey(Querys.PERSONA_CLI_LAST_PRIMARY_KEY);
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
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
    //</editor-fold>
    
    public Personas getPersonas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public Result findPersonByDocument() {
        String squery = Querys.PERSONA_CLI_ALL + "WHERE" + Querys.PERSONA_CLI_DOC_TYPE + selected.getTipoDocumento().getTipoDocumento() + "' AND"
                + Querys.PERSONA_CLI_DOC_NUMBER + selected.getNumeroDocumento() + "'";
        return ejbFacade.findByQuery(squery, false);//Only one person should have document type, an document number (It is unique in database)
    }
    
    /**
     * Update person's properties from file
     * @param existingPerson 
     * @return  
     */
    public Result updateFromFile(Personas existingPerson) {
        selected.setIdPersona(existingPerson.getIdPersona());
        //<editor-fold desc="update properties if added" defaultstate="collapsed">
        if(selected.getEmpresaOrigen()==null ){
            selected.setEmpresaOrigen(existingPerson.getEmpresaOrigen());
        }
        if(selected.getNombre2()==null && existingPerson.getNombre2()!=null){
            selected.setNombre2(existingPerson.getNombre2());
        }
        if(selected.getApellido2()==null && existingPerson.getApellido2()!=null){
            selected.setApellido2(existingPerson.getApellido2());
        }
        if(selected.getDireccion()==null && existingPerson.getDireccion()!=null){
            selected.setDireccion(existingPerson.getDireccion());
        }
        if(selected.getTelefono()==null && existingPerson.getTelefono()!=null){
            selected.setTelefono(existingPerson.getTelefono());
        }
        if(selected.getCelular()==null && existingPerson.getCelular()!=null){
            selected.setCelular(existingPerson.getCelular());
        }
        if(selected.getMail()==null && existingPerson.getMail()!=null){
            selected.setMail(existingPerson.getMail());
        }
        if(selected.getPersonaContacto()==null && existingPerson.getPersonaContacto()!=null){
            selected.setPersonaContacto(existingPerson.getPersonaContacto());
        }
        if(selected.getTelPersonaContacto()==null && existingPerson.getTelPersonaContacto()!=null){
            selected.setTelPersonaContacto(existingPerson.getPersonaContacto());
        }
        if(selected.getPais()==null && existingPerson.getPais()!=null){
            selected.setPais(existingPerson.getPais());
        }
        if(selected.getDepartamento()==null && existingPerson.getDepartamento()!=null){
            selected.setDepartamento(existingPerson.getDepartamento());
        }
        if(selected.getMunicipio()==null && existingPerson.getMunicipio()!=null){
            selected.setMunicipio(existingPerson.getMunicipio());
        }
        if(selected.getEps()==null && existingPerson.getEps()!=null){
            selected.setEps(existingPerson.getEps());
        }
        if(selected.getFechaVigenciaSS()==null && existingPerson.getFechaVigenciaSS()!=null){
            selected.setFechaVigenciaSS(existingPerson.getFechaVigenciaSS());
        }
        if(selected.getArl()==null && existingPerson.getArl()!=null){
            selected.setArl(existingPerson.getArl());
        }
        if(selected.getSexo()==null && existingPerson.getSexo()!=null){
            selected.setSexo(existingPerson.getSexo());
        }
        if(selected.getRh()==null && existingPerson.getRh()!=null){
            selected.setRh(existingPerson.getRh());
        }
        if(selected.getFechaNacimiento()==null && existingPerson.getFechaNacimiento()!=null){
            selected.setFechaNacimiento(existingPerson.getFechaNacimiento());
        }
        if(selected.getEstado()==null && existingPerson.getEstado()!=null){
            selected.setEstado(existingPerson.getEstado());
        }
        return update();
        //</editor-fold>
        
    }

    @FacesConverter(forClass = Personas.class)
    public static class PersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasController controller = (PersonasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasController");
            return controller.getPersonas(getKey(value));
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
            if (object instanceof Personas) {
                Personas o = (Personas) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Personas.class.getName()});
                return null;
            }
        }

    }

}
