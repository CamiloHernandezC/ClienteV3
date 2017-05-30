package Controllers;

import Entities.Personas;
import Controllers.util.JsfUtil;
import Entities.PersonasSucursal;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("personasController")
@SessionScoped
public class PersonasController extends Converters.PersonasController{

    private String otherOriginEnterpriseName;
    private boolean showError;//Variable to show error in load from file option
    private PersonasSucursalController personasSucursalController = JsfUtil.findBean("personasSucursalController");;

    public PersonasController() {
    }

    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">

    public boolean isShowError() {
        return showError;
    }

    public String getOtherOriginEnterpriseName() {
        return otherOriginEnterpriseName;
    }

    public void setOtherOriginEnterpriseName(String otherOriginEnterpriseName) {
        this.otherOriginEnterpriseName = otherOriginEnterpriseName;
    }
    //</editor-fold>
    
    @Override
    public Result create() {
        Result generalResult = super.create();
        if(generalResult.errorCode!=Constants.OK){
            return generalResult;
        }
        return personasSucursalController.create();
    }

    
    public void showCancelDialog(){
        JsfUtil.showModal("dialogConfirmCancel");
    }
    
    public void cancel(){
        clean();
        personasSucursalController.clean();
        JsfUtil.redirectTo(Navigation.PAGE_MASTER_DATA_PERSON);
    }
    
    public String searchToCreate(){
        Result result = findPersonByDocument();
        if(result.errorCode==Constants.OK){//Person already exist, update persona, and create persona sucursal
            selected = (Personas) result.result;
            Result specificResult = personasSucursalController.findSpecificPerson();
            if(specificResult.errorCode==Constants.OK){
                PersonasSucursal specificPerson = (PersonasSucursal) specificResult.result;
                if(specificPerson.getEstado().getIdEstado().equals(Constants.STATUS_INACTIVE)){
                    personasSucursalController.setSelected(specificPerson);
                    JsfUtil.showModal("inactiveDialog");
                    return null;
                }
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
                return null;
            }
            JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
        }
        return Navigation.PAGE_PERSONAS_CREATE;
    }
    
    public String createByForm(){
        Result result = null;
        if(selected.getIdPersona()==null){//person doesn't exist
            result = create();
        }else{//Person exist, we need to update persona and create persona sucursal
            update();
            result = personasSucursalController.create();
        }
        switch(result.errorCode){
            case Constants.OK:
                clean();
                personasSucursalController.clean();
                JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyCreatedRegistry"));
                return Navigation.PAGE_MASTER_DATA_PERSON;
            case Constants.VALIDATION_ERROR:
                JsfUtil.addErrorMessage(validationErrorObservation);//VALIDATION ERROR MESSAGE
                return null;
            default://This should never happend
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
                return null;
        }
    }
    
    public String updateFromForm(){
        update();
        personasSucursalController.update();
        clean();
        personasSucursalController.clean();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        return Navigation.PAGE_MASTER_DATA_PERSON;
    }
    
    @Override
    public void clean(){
        super.clean();
        otherOriginEnterpriseName = null;
        validationErrorObservation = null;
        showError = false;
    }
    
    /*public String changeViewToCreate(){
         return Navigation.PAGE_SEARCH_PERSON_TO_CREATE;
    }*/

}
