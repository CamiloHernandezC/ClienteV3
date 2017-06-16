/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportsControl.Person;

import Converters.util.JsfUtil;
import Facade.PersonasSucursalFacade;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Result;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Kmilo
 */
@Named("assistanceReportControl")
@SessionScoped
public class AssistanceReportControl implements Serializable {
    @EJB
    private PersonasSucursalFacade ejbFacade;
    private List<Object> items = null;

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
    
    public void generateRepor(){
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() == null) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("EditPersonasCliRequiredMessage_idSucursal"));
            return;
        }
        Date actualDate = new Date();
        Calendar calendar = Calendar.getInstance();

        Time time = new Time(actualDate.getTime());
        calendar.setTime(actualDate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String day = "";
        String dayLetter = "";
        switch(dayOfWeek){
            case 1://Sunday
                day = "domingo";
                dayLetter = "D";
                break;
            case 2://Monday
                day = "lunes";
                dayLetter = "L";
                break;
            case 3://Tuesday
                day = "martes";
                dayLetter = "M";
                break;
            case 4://Wednesday
                day = "miercoles";
                dayLetter = "W";
                break;
            case 5://Thursday
                day = "jueves";
                dayLetter = "J";
                break;
            case 6://Friday
                day = "viernes";
                dayLetter = "V";
                break;
            case 7://Saturday
                day = "sabado";
                dayLetter = "S";
                break; 
        }
        String squery = Querys.ASSISTANCE_SELECT+dayLetter+", h.horaSalida"+dayLetter+Querys.ASSISTANCE_TABLES
                +day+"= '1' AND"+Querys.HORARIOS_HORA_INGRESO+dayLetter+" < '"+time+"' AND"+Querys.HORARIOS_HORA_SALIDA+dayLetter+" > '"+time+"' group by p.idPersona";
        Result result = ejbFacade.findByQueryArray(squery);
        items = (List<Object>) result.result;
        if(result.errorCode== Constants.NO_RESULT_EXCEPTION){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("NoResult"));
        }
    }
}
