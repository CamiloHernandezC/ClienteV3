package Controllers;

import Controllers.util.JsfUtil;
import Entities.Cardex;
import Entities.Inventario;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Result;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("cardexController")
@SessionScoped
public class CardexController extends Converters.CardexController {
    
    public void seeMovements(Inventario selected){
        String squery = Querys.CARDEX_ALL + " WHERE" +Querys.CARDEX_SUCURSAL+selected.getMaterialesSucursal().getSucursales().getIdSucursal()
                +"' AND"+Querys.CARDEX_ALMACEN+selected.getAlmacen().getIdAlmacen()+"' AND"+Querys.CARDEX_MATERIAL+selected.getMaterialesSucursal().getMateriales().getIdMaterial()+"'";
        Result result = ejbFacade.findByQueryArray(squery);
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("NoResult"));
            return;
        }
        if(result.errorCode!=Constants.OK){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
            return;
        }
        items = (List<Cardex>) result.result;
        JsfUtil.showModal("dialogmaterialMovements");
        
    }
    
}
