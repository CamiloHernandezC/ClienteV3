package Controllers;

import Entities.PersonasSucursal;
import Controllers.util.JsfUtil;
import Entities.Estados;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("personasSucursalController")
@SessionScoped
public class PersonasSucursalController extends Converters.GeneralPersonasSucursalController{

    public PersonasSucursalController() {
    }
    
    /**
     * Load person where branch office is equal to selected and status is different of inactive
     * @return List of people belonging to selected branch office
     * */
    public List<PersonasSucursal> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF WHEN PAGE IS REALOADED, MAKE C:IF ACTION SET BOOLEAN VALUE TO FALSE AND USE THAT BOOLEAN TO ASK IF RELOAD IS NEEDED LIKE VALID SESSION METHOD
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL+"WHERE"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+
                    "' AND"+Querys.PERSONAS_SUCURSAL_CLI_NO_ESTADO+Constants.STATUS_INACTIVE+"'";
            items = (List<PersonasSucursal>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    Result findSpecificPerson() {
        assignPrimaryKey();
        String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_PERSONA+ selected.getPersonas().getIdPersona()+
                "' AND"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+selected.getSucursales().getIdSucursal()+"'";//Here we doesn't filter by status, because this method is used when search to create
        return ejbFacade.findByQuery(squery, false);//False because only one person should appear*/
    }
        
    public void preEdit(PersonasSucursal person){
        setSelected(person);
        personasController.setSelected(person.getPersonas());
        JsfUtil.redirectTo(Navigation.PAGE_PERSONAS_EDIT);
    }
    
    public void blockPerson(PersonasSucursal person){
        person.setEstado(new Estados(Constants.STATUS_BLOCKED));
        setSelected(person);
        update();
        //TODO SHOW DIALOG TO BLOCK PERSON FOR OTHER BRANCH OFICCES WHERE USER HAS ACCESS
    }
    
    public void unlockPerson(PersonasSucursal person){
        person.setEstado(new Estados(Constants.STATUS_ACTIVE));
        setSelected(person);
        update();
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
}
