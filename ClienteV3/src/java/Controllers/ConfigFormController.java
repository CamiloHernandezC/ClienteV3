/*
 * To change this license headerchoose License Headers in Project Properties.
 * To change this template filechoose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Converters.util.JsfUtil;
import Entities.ConfigForm;
import Facade.ConfigFormFacade;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Navigation;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Kmilo
 */
@Named("configFormController")
@SessionScoped
public class ConfigFormController extends Converters.ConfigFormController {

    private boolean showARL;
    private boolean showAddress;
    private boolean showBirthDay;
    private boolean showCellphone;
    private boolean showCountry;
    private boolean showDepartment;
    private boolean showEmergencyPhone;
    private boolean showEmergencyPerson;
    private boolean showEPS;
    private boolean showMail;
    private boolean showMunicipaly;
    private boolean showOriginEnterprise;
    private boolean showOtherID;
    private boolean showPhone;
    private boolean showRH;
    private boolean showSexo;
    private boolean showVigencySS;

    private boolean changeARL;
    private boolean changeAddress;
    private boolean changeBirthDay;
    private boolean changeCellphone;
    private boolean changeCountry;
    private boolean changeDepartment;
    private boolean changeEmergencyPhone;
    private boolean changeEmergencyPerson;
    private boolean changeEPS;
    private boolean changeMail;
    private boolean changeMunicipaly;
    private boolean changeOriginEnterprise;
    private boolean changeOtherID;
    private boolean changePhone;
    private boolean changeRH;
    private boolean changeSexo;
    private boolean changeVigencySS;
    
    private HashMap booleanMap;

    public boolean isShowARL() {
        return showARL;
    }

    public void setShowARL(boolean showARL) {
        this.showARL = showARL;
        changeARL = true;
    }

    public boolean isShowAddress() {
        return showAddress;
    }

    public void setShowAddress(boolean showAddress) {
        this.showAddress = showAddress;
        changeAddress = true;
    }

    public boolean isShowBirthDay() {
        return showBirthDay;
    }

    public void setShowBirthDay(boolean showBirthDay) {
        this.showBirthDay = showBirthDay;
        changeBirthDay = true;
    }

    public boolean isShowCellphone() {
        return showCellphone;
    }

    public void setShowCellphone(boolean showCellphone) {
        this.showCellphone = showCellphone;
        changeCellphone = true;
    }

    public boolean isShowCountry() {
        return showCountry;
    }

    public void setShowCountry(boolean showCountry) {
        this.showCountry = showCountry;
        changeCountry = true;
    }

    public boolean isShowDepartment() {
        return showDepartment;
    }

    public void setShowDepartment(boolean showDepartment) {
        this.showDepartment = showDepartment;
        changeDepartment = true;
    }

    public boolean isShowEmergencyPhone() {
        return showEmergencyPhone;
    }

    public void setShowEmergencyPhone(boolean showEmergencyPhone) {
        this.showEmergencyPhone = showEmergencyPhone;
        changeEmergencyPhone = true;
    }

    public boolean isShowEmergencyPerson() {
        return showEmergencyPerson;
    }

    public void setShowEmergencyPerson(boolean showEmergencyPerson) {
        this.showEmergencyPerson = showEmergencyPerson;
        changeEmergencyPerson = true;
    }

    public boolean isShowEPS() {
        return showEPS;
    }

    public void setShowEPS(boolean showEPS) {
        this.showEPS = showEPS;
        changeEPS = true;
    }

    public boolean isShowMail() {
        return showMail;
    }

    public void setShowMail(boolean showMail) {
        this.showMail = showMail;
        changeMail = true;
    }

    public boolean isShowMunicipaly() {
        return showMunicipaly;
    }

    public void setShowMunicipaly(boolean showMunicipaly) {
        this.showMunicipaly = showMunicipaly;
        changeMunicipaly = true;
    }

    public boolean isShowOriginEnterprise() {
        return showOriginEnterprise;
    }

    public void setShowOriginEnterprise(boolean showOriginEnterprise) {
        this.showOriginEnterprise = showOriginEnterprise;
        changeOriginEnterprise = true;
    }

    public boolean isShowOtherID() {
        return showOtherID;
    }

    public void setShowOtherID(boolean showOtherID) {
        this.showOtherID = showOtherID;
        changeOtherID = true;
    }

    public boolean isShowPhone() {
        return showPhone;
    }

    public void setShowPhone(boolean showPhone) {
        this.showPhone = showPhone;
        changePhone = true;
    }

    public boolean isShowRH() {
        return showRH;
    }

    public void setShowRH(boolean showRH) {
        this.showRH = showRH;
        changeRH = true;
    }

    public boolean isShowSexo() {
        return showSexo;
    }

    public void setShowSexo(boolean showSexo) {
        this.showSexo = showSexo;
        changeSexo = true;
    }

    public boolean isShowVigencySS() {
        return showVigencySS;
    }

    public void setShowVigencySS(boolean showVigencySS) {
        this.showVigencySS = showVigencySS;
        changeVigencySS = true;
    }

    public ConfigFormController() {
    }
    
    private void assingBooleans(){
        showARL = (boolean) booleanMap.get("Arl");
        showAddress = (boolean) booleanMap.get("Direccion");
        showBirthDay= (boolean) booleanMap.get("Fecha nacimiento");
        showCellphone= (boolean) booleanMap.get("Celular");
        showCountry= (boolean) booleanMap.get("Pais");
        showDepartment= (boolean) booleanMap.get("Departamento");
        showEPS= (boolean) booleanMap.get("Eps");
        showEmergencyPerson= (boolean) booleanMap.get("Persona contacto");
        showEmergencyPhone= (boolean) booleanMap.get("Tel persona contacto");
        showMail= (boolean) booleanMap.get("Email");
        showMunicipaly= (boolean) booleanMap.get("Municipio");
        showOriginEnterprise= (boolean) booleanMap.get("Empresa");
        changeOtherID= (boolean) booleanMap.get("Id externo");
        showPhone= (boolean) booleanMap.get("Telefono");
        showRH= (boolean) booleanMap.get("Rh");
        showSexo= (boolean) booleanMap.get("Sexo");
        showVigencySS= (boolean) booleanMap.get("Vigencia SS");
    }
            
    @PostConstruct
    private void initializeBooleans() {
        String squery = Querys.CONFIG_FORM_ALL + " WHERE"+Querys.CONFIG_FORM_PERSON;//TODO filte for only one entry
        List<ConfigForm> configItems = (List<ConfigForm>) ejbFacade.findByQueryArray(squery).result;
        booleanMap = new HashMap();
        for(ConfigForm configItem: configItems){
            booleanMap.put(configItem.getCampo(),configItem.getMostrar());
        }
        assingBooleans();
    }
    
    public String save() {
        ////TODO SEARC IN PORTERIAS SUCURSAL ALL ENTRYS THAT HAS ACTUAL BRANCH OFFICE ASSIGNED
        String updateQuery = "UPDATE ConfigForm a SET a.mostrar = CASE";
        if (changeARL) {
            String s = showARL ? "1":"0";
            updateQuery += " WHEN a.campo = 'Arl' THEN '"+s+"'";
        }
        if (changeAddress) {
            String s = showAddress ? "1":"0";
            updateQuery += " WHEN a.campo = 'Direccion' THEN '"+s+"'";
        }
        if (changeBirthDay) {
            String s = showBirthDay ? "1":"0";
            updateQuery += " WHEN a.campo = 'Fecha nacimiento' THEN '"+s+"'";
        }
        if (changeCellphone) {
            String s = showCellphone ? "1":"0";
            updateQuery += " WHEN a.campo = 'Celular' THEN '"+s+"'";
        }
        if (changeCountry) {
            String s = showCountry ? "1":"0";
            updateQuery += " WHEN a.campo = 'Pais' THEN '"+s+"'";
        }
        if (changeDepartment) {
            String s = showDepartment ? "1":"0";
            updateQuery += " WHEN a.campo = 'Departamento' THEN '"+s+"'";
        }
        if (changeEPS) {
            String s = showEPS ? "1":"0";
            updateQuery += " WHEN a.campo = 'Eps' THEN '"+s+"'";
        }
        if (changeEmergencyPerson) {
            String s = showEmergencyPerson ? "1":"0";
            updateQuery += " WHEN a.campo = 'Persona contacto' THEN '"+s+"'";
        }
        if (changeEmergencyPhone) {
            String s = showEmergencyPhone ? "1":"0";
            updateQuery += " WHEN a.campo = 'Tel persona contacto' THEN '"+s+"'";
        }
        if (changeMail) {
            String s = showMail ? "1":"0";
            updateQuery += " WHEN a.campo = 'Email' THEN '"+s+"'";
        }
        if (changeMunicipaly) {
            String s = showMunicipaly ? "1":"0";
            updateQuery += " WHEN a.campo = 'Municipio' THEN '"+s+"'";
        }
        if (changeOriginEnterprise) {
            String s = showOriginEnterprise ? "1":"0";
            updateQuery += " WHEN a.campo = 'Empresa' THEN '"+s+"'";
        }
        if (changeOtherID) {
            String s = showOtherID ? "1":"0";
            updateQuery += " WHEN a.campo = 'Id externo' THEN '"+s+"'";
        }
        if (changePhone) {
            String s = showPhone ? "1":"0";
            updateQuery += " WHEN a.campo = 'Telefono' THEN '"+s+"'";
        }
        if (changeRH) {
            String s = showRH ? "1":"0";
            updateQuery += " WHEN a.campo = 'Rh' THEN '"+s+"'";
        }
        if (changeSexo) {
            String s = showSexo ? "1":"0";
            updateQuery += " WHEN a.campo = 'Sexo' THEN '"+s+"'";
        }
        if (changeVigencySS) {
            String s = showVigencySS ? "1":"0";
            updateQuery += " WHEN a.campo = 'Vigencia SS' THEN '"+s+"'";
        }
        updateQuery += " ELSE a.mostrar END WHERE" + Querys.CONFIG_FORM_PERSON;//TODO ASSIGN ENTRYS LOADED BEFORE
        ejbFacade.executeQuery(updateQuery);
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        return Navigation.PAGE_INDEX;
    }

    public void showCancelDialog() {
        JsfUtil.showModal("dialogConfirmCancel");
    }
    
    public String cancel(){
        return Navigation.PAGE_INDEX;
    }
}
