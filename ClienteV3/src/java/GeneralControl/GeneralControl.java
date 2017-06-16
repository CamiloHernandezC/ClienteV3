/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralControl;

import Converters.util.JsfUtil;
import Entities.AccesoUsuario;
import Entities.Sucursales;
import Entities.Usuarios;
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
    private ArrayList<Sucursales> branchOffices = null;
    private Sucursales selectedBranchOffice = null;

    /**
     * Creates a new instance of MenuController
     */
    public GeneralControl() {
    }

    public boolean isShowBranchOffice() {
        loadBranchOffices();
        if(branchOffices==null || branchOffices.isEmpty()){
            selectedBranchOffice = new Sucursales(0);//query whit brachOffice = 0 will return no result
            return false;
        }
        if(branchOffices.size()==1){
            selectedBranchOffice = branchOffices.get(0);
            return false;
        }
        return true;
    }

    public Sucursales getSelectedBranchOffice() {
        return selectedBranchOffice;
    }

    public void setSelectedBranchOffice(Sucursales selectedBranchOffice) {
        this.selectedBranchOffice = selectedBranchOffice;
    }

    public ArrayList<Sucursales> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(ArrayList<Sucursales> branchOffices) {
        this.branchOffices = branchOffices;
    }
    
    public void loadBranchOffices(){
        branchOffices = new ArrayList<>();
        Usuarios user = JsfUtil.getSessionUser();
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
