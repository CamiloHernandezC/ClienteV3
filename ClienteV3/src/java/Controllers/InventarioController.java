package Controllers;

import Converters.util.JsfUtil;
import Entities.Inventario;
import GeneralControl.GeneralControl;
import Querys.Querys;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("inventarioController")
@SessionScoped
public class InventarioController extends Converters.InventarioController {
    
    public List<Inventario> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF WHEN PAGE IS REALOADED, MAKE C:IF ACTION SET BOOLEAN VALUE TO FALSE AND USE THAT BOOLEAN TO ASK IF RELOAD IS NEEDED LIKE VALID SESSION METHOD
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() != null) {
            String squery = Querys.INVENTARIO_ALL + " WHERE" + Querys.INVENTARIO_SUCURSAL + generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<Inventario>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }
}
