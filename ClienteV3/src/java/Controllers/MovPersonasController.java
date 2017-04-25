package Controllers;

import Entities.MovPersonas;
import Controllers.util.JsfUtil;
import GeneralControl.GeneralControl;
import Querys.Querys;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("movPersonasController")
@SessionScoped
public class MovPersonasController extends Converters.MovPersonasController{   
    
    public MovPersonasController() {
    }
    
    public List<MovPersonas> getItemsByBranchOffice() {//TODO DATES FILTER HERE
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<MovPersonas>) ejbFacade.findByQueryArray(squery).result;
        }
        return items;
    }

}
