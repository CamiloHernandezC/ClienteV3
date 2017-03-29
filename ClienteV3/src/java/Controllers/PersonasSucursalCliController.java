package Controllers;

import Entities.PersonasSucursalCli;
import Controllers.util.JsfUtil;
import Entities.EstadosCli;
import Facade.PersonasSucursalCliFacade;
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

@Named("personasSucursalCliController")
@SessionScoped
public class PersonasSucursalCliController extends AbstractPersistenceController<PersonasSucursalCli> {

    @EJB
    private Facade.PersonasSucursalCliFacade ejbFacade;
    private List<PersonasSucursalCli> items = null;
    private PersonasSucursalCli selected;

    public PersonasSucursalCliController() {
    }

    @Override
    public PersonasSucursalCli getSelected() {
        if(selected==null){
            selected = new PersonasSucursalCli();
        }
        return selected;
    }

    @Override
    public void setSelected(PersonasSucursalCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        selected.getPersonasSucursalCliPK().setSucursal(selected.getSucursalesCli().getIdSucursal());
        selected.getPersonasSucursalCliPK().setIdPersona(selected.getPersonasCli().getIdPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        selected.setPersonasSucursalCliPK(new Entities.PersonasSucursalCliPK());
    }

    @Override
    protected PersonasSucursalCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        assignPrimaryKey();
        selected.setEstado(new EstadosCli(Constants.STATUS_ACTIVE));
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        initializeEmbeddableKey();
        
        selected.setUsuario(JsfUtil.getSessionUser().getIdPersona());
        selected.setFecha(new Date());
    }
    
    @Override
    protected void setItems(List<PersonasSucursalCli> items) {
        this.items = items;
    }
    
    public List<PersonasSucursalCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    /**
     * Load person where branch office is equal to selected and status is different of inactive
     * @return List of people belonging to selected branch office
     * */
    public List<PersonasSucursalCli> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF LIKE VALID SESSION METHOD
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL+"WHERE"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+
                    "' AND"+Querys.PERSONAS_SUCURSAL_CLI_NO_ESTADO+Constants.STATUS_INACTIVE+"'";
            items = (List<PersonasSucursalCli>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public PersonasSucursalCli getPersonasSucursalCli(Entities.PersonasSucursalCliPK id) {
        return getFacade().find(id);
    }

    Result findSpecificPerson() {
        assignPrimaryKey();
        String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_PERSONA+ selected.getPersonasCli().getIdPersona()+
                "' AND"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+selected.getSucursalesCli().getIdSucursal()+"'";//Here we doesn't filter by status, because this method is used when search to create
        return ejbFacade.findByQuery(squery, false);//False because only one person should appear*/
    }

    public void assignPrimaryKey() {
        PersonasCliController personasCliController = JsfUtil.findBean("personasCliController");
        if(selected==null){
            selected = new PersonasSucursalCli();
        }
        selected.setPersonasCli(personasCliController.getSelected());
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        selected.setSucursalesCli(generalControl.getSelectedBranchOffice());
    }
        
    public void preEdit(PersonasSucursalCli person){
        setSelected(person);
        PersonasCliController personasCliController = JsfUtil.findBean("personasCliController");
        personasCliController.setSelected(person.getPersonasCli());
        JsfUtil.redirectTo(Navigation.PAGE_PERSONAS_EDIT);
    }
    
    public void blockPerson(PersonasSucursalCli person){
        person.setEstado(new EstadosCli(Constants.STATUS_BLOCKED));
        setSelected(person);
        update();
        //TODO WHEN BLOCK PERSON CHANGE BUTTON TO UNBLOCK PERSON
        //TODO SHOW DIALOG TO BLOCK PERSON FOR OTHER BRANCH OFICCES WHERE USER HAS ACCESS
    }
    
    public void unlockPerson(PersonasSucursalCli person){
        person.setEstado(new EstadosCli(Constants.STATUS_ACTIVE));
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
        selected.setEstado(new EstadosCli(Constants.STATUS_ACTIVE));
        update();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        JsfUtil.redirectTo(Navigation.PAGE_MASTER_DATA_PERSON);
    }
    
    @FacesConverter(forClass = PersonasSucursalCli.class)
    public static class PersonasSucursalCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasSucursalCliController controller = (PersonasSucursalCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasSucursalCliController");
            return controller.getPersonasSucursalCli(getKey(value));
        }

        Entities.PersonasSucursalCliPK getKey(String value) {
            Entities.PersonasSucursalCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PersonasSucursalCliPK();
            key.setIdPersona(values[0]);
            key.setSucursal(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(Entities.PersonasSucursalCliPK value) {
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
            if (object instanceof PersonasSucursalCli) {
                PersonasSucursalCli o = (PersonasSucursalCli) object;
                return getStringKey(o.getPersonasSucursalCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasSucursalCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
