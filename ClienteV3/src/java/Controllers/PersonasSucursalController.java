package Controllers;

import Entities.PersonasSucursal;
import Controllers.util.JsfUtil;
import Entities.Estados;
import Facade.PersonasSucursalFacade;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;

import java.util.Date;
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

@Named("personasSucursalController")
@SessionScoped
public class PersonasSucursalController extends AbstractPersistenceController<PersonasSucursal>{

    @EJB
    private Facade.PersonasSucursalFacade ejbFacade;
    private List<PersonasSucursal> items = null;
    private PersonasSucursal selected;

    public PersonasSucursalController() {
    }

     @Override
    public PersonasSucursal getSelected() {
        if(selected==null){
            selected = new PersonasSucursal();
        }
        return selected;
    }

    @Override
    public void setSelected(PersonasSucursal selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        selected.getPersonasSucursalPK().setSucursal(selected.getSucursales().getIdSucursal());
        selected.getPersonasSucursalPK().setIdPersona(selected.getPersonas().getIdPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        selected.setPersonasSucursalPK(new Entities.PersonasSucursalPK());
    }

    @Override
    protected PersonasSucursalFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        assignPrimaryKey();
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        initializeEmbeddableKey();
        
        selected.setUsuario(JsfUtil.getSessionUser().getPersona());
        selected.setFecha(new Date());
    }
    
    @Override
    protected void setItems(List<PersonasSucursal> items) {
        this.items = items;
    }
    
    public List<PersonasSucursal> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    /**
     * Load person where branch office is equal to selected and status is different of inactive
     * @return List of people belonging to selected branch office
     * */
    public List<PersonasSucursal> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF LIKE VALID SESSION METHOD
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL+"WHERE"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+
                    "' AND"+Querys.PERSONAS_SUCURSAL_CLI_NO_ESTADO+Constants.STATUS_INACTIVE+"'";
            items = (List<PersonasSucursal>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public PersonasSucursal getPersonasSucursal(Entities.PersonasSucursalPK id) {
        return getFacade().find(id);
    }

    Result findSpecificPerson() {
        assignPrimaryKey();
        String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_PERSONA+ selected.getPersonas().getIdPersona()+
                "' AND"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+selected.getSucursales().getIdSucursal()+"'";//Here we doesn't filter by status, because this method is used when search to create
        return ejbFacade.findByQuery(squery, false);//False because only one person should appear*/
    }

    public void assignPrimaryKey() {
        PersonasController personasController = JsfUtil.findBean("personasController");
        if(selected==null){
            selected = new PersonasSucursal();
        }
        selected.setPersonas(personasController.getSelected());
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        selected.setSucursales(generalControl.getSelectedBranchOffice());
    }
        
    public void preEdit(PersonasSucursal person){
        setSelected(person);
        PersonasController personasController = JsfUtil.findBean("personasController");
        personasController.setSelected(person.getPersonas());
        JsfUtil.redirectTo(Navigation.PAGE_PERSONAS_EDIT);
    }
    
    public void blockPerson(PersonasSucursal person){
        person.setEstado(new Estados(Constants.STATUS_BLOCKED));
        setSelected(person);
        update();
        //TODO WHEN BLOCK PERSON CHANGE BUTTON TO UNBLOCK PERSON
        //TODO SHOW DIALOG TO BLOCK PERSON FOR OTHER BRANCH OFICCES WHERE USER HAS ACCESS
    }
    
    public void unlockPerson(PersonasSucursal person){
        person.setEstado(new Estados(Constants.STATUS_ACTIVE));
        setSelected(person);
        update();
        //TODO WHEN UNLOCK PERSON CHANGE BUTTON TO BLOCK PERSON
        //TODO SHOW DIALOG TO BLOCK PERSON FOR OTHER BRANCH OFICCES WHERE USER HAS ACCESS
    }

    @Override
    protected void clean() {
        selected = null;
        items = null;
    }
    
    public void activePerson(){
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
        update();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        JsfUtil.redirectTo(Navigation.PAGE_MASTER_DATA_PERSON);
    }

    @FacesConverter(forClass = PersonasSucursal.class)
    public static class PersonasSucursalControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasSucursalController controller = (PersonasSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasSucursalController");
            return controller.getPersonasSucursal(getKey(value));
        }

        Entities.PersonasSucursalPK getKey(String value) {
            Entities.PersonasSucursalPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PersonasSucursalPK();
            key.setIdPersona(Integer.parseInt(values[0]));
            key.setSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.PersonasSucursalPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PersonasSucursal) {
                PersonasSucursal o = (PersonasSucursal) object;
                return getStringKey(o.getPersonasSucursalPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasSucursal.class.getName()});
                return null;
            }
        }

    }

}
