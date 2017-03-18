/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralControl;

import Controllers.util.JsfUtil;
import Entities.AccesoUsuario;
import Entities.SucursalesCli;
import Entities.UsuariosCli;
import Facade.AccesoUsuarioFacade;
import Querys.Querys;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author MAURICIO
 */
@Named(value = "generalControl")
@SessionScoped
public class GeneralControl implements Serializable{
    
    @EJB
    private AccesoUsuarioFacade ejbFacade;
    private ArrayList<SucursalesCli> branchOffices = null;
    private SucursalesCli selectedBranchOffice = null;

    /**
     * Creates a new instance of MenuController
     */
    public GeneralControl() {
    }

    public boolean isShowBranchOffice() {
        loadBranchOffice();
        if(branchOffices==null || branchOffices.isEmpty()){
            selectedBranchOffice = new SucursalesCli(0L);//query whit brachOffice = 0 will return no result
            return false;
        }
        if(branchOffices.size()==1){
            selectedBranchOffice = branchOffices.get(0);
            return false;
        }
        return true;
    }

    public SucursalesCli getSelectedBranchOffice() {
        return selectedBranchOffice;
    }

    public void setSelectedBranchOffice(SucursalesCli selectedBranchOffice) {
        this.selectedBranchOffice = selectedBranchOffice;
    }

    public ArrayList<SucursalesCli> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(ArrayList<SucursalesCli> branchOffices) {
        this.branchOffices = branchOffices;
    }
    
    public void loadBranchOffice(){
        branchOffices = new ArrayList<>();
        UsuariosCli user = JsfUtil.getSessionAtribute(Constants.SESSION_USER);
        String squery = Querys.ACCESO_USUARIO_ALL+" WHERE"+Querys.ACCESO_USUARIO_USUARIO+user.getIdUsuario()+"'";
        Result result = ejbFacade.findByQueryArray(squery);
        if(result.errorCode==Constants.OK){
            List<AccesoUsuario> list = (List<AccesoUsuario>) result.result;
            for(AccesoUsuario a: list){
                branchOffices.add(a.getSucursal());
            }
        }
    }

    
}
