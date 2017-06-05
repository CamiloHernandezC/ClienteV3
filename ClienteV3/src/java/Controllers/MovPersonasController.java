package Controllers;

import Entities.MovPersonas;
import Controllers.util.JsfUtil;
import Entities.TiposDocumento;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import java.util.Date;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@Named("movPersonasController")
@SessionScoped
public class MovPersonasController extends Converters.MovPersonasController {

    private TiposDocumento docType;
    private String docNumber;

    private Date startDate;
    private Date endDate;

    public MovPersonasController() {
    }

    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
    public TiposDocumento getDocType() {
        return docType;
    }

    public void setDocType(TiposDocumento docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //</editor-fold>
    public List<MovPersonas> getItemsByBranchOffice() {
        return items;
    }

    public void generateReport() {//TODO DATES FILTER HERE
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() == null) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("EditPersonasCliRequiredMessage_idSucursal"));
            return;
        }
        java.sql.Date initialDate = new java.sql.Date(startDate.getTime());
        java.sql.Date finalDate = new java.sql.Date(endDate.getTime());
        String squery = Querys.MOV_PERSONA_CLI_ALL + "WHERE" + Querys.MOV_PERSONA_CLI_SUCURSAL + generalControl.getSelectedBranchOffice().getIdSucursal() + "' AND" + Querys.MOV_PERSONA_CLI_FECHA_INGRESO_BETWEEN + initialDate + "' AND '" + finalDate + "'";
        if (docType != null && docNumber != null && !docNumber.equals("")) {
            squery += " AND" + Querys.MOV_PERSONA_CLI_TIPO_DOC + docType.getTipoDocumento() + "' AND" + Querys.MOV_PERSONA_CLI_NUM_DOC + docNumber + "'";
        }
        items = (List<MovPersonas>) ejbFacade.findByQueryArray(squery).result;
    }

}
