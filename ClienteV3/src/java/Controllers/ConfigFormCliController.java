package Controllers;

import Entities.ConfigFormCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.ConfigFormCliFacade;
import Utils.Constants;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("configFormCliController")
@SessionScoped
public class ConfigFormCliController implements Serializable {

    @EJB
    private Facade.ConfigFormCliFacade ejbFacade;
    private List<ConfigFormCli> items = null;
    private ConfigFormCli selected;
    
    //Booleanos de control Vista
    // <editor-fold desc="Booleanos" defaultstate="collapsed">
    private boolean mostrarARL;
    private boolean mostrarCelular;
    private boolean mostrarEmail;
    private boolean mostrarDireccion;
    private boolean mostrarEPS;
    private boolean mostrarnacimiento;
    private boolean mostrarFecha_vigencia_ARL;
    private boolean mostrarFecha_vigencia_EPS;
    private boolean mostrarDepartamento;
    private boolean mostrarIdExterno;
    private boolean mostrarPais;
    private boolean mostrarMunicipio;
    private boolean mostrarSucursal;
    private boolean mostrarSexo;
    private boolean mostrarTelefono;
    private boolean mostrarTipo_sanguineo;
    private boolean mostrarFuncionario;
    private boolean mostrarEmpresa;
    private boolean mostrarPersonaContacto;

    public boolean isMostrarPersonaContacto() {
        return mostrarPersonaContacto;
    }
    
    public ConfigFormCliFacade getEjbFacade() {
        return ejbFacade;
    }

    public boolean isMostrarARL() {
        return mostrarARL;
    }

    public boolean isMostrarCelular() {
        return mostrarCelular;
    }

    public boolean isMostrarEmail() {
        return mostrarEmail;
    }

    public boolean isMostrarDireccion() {
        return mostrarDireccion;
    }

    public boolean isMostrarEPS() {
        return mostrarEPS;
    }

    public boolean isMostrarnacimiento() {
        return mostrarnacimiento;
    }

    public boolean isMostrarFecha_vigencia_ARL() {
        return mostrarFecha_vigencia_ARL;
    }

    public boolean isMostrarFecha_vigencia_EPS() {
        return mostrarFecha_vigencia_EPS;
    }

    public boolean isMostrarDepartamento() {
        return mostrarDepartamento;
    }

    public boolean isMostrarIdExterno() {
        return mostrarIdExterno;
    }

    public boolean isMostrarPais() {
        return mostrarPais;
    }

    public boolean isMostrarMunicipio() {
        return mostrarMunicipio;
    }

    public boolean isMostrarSucursal() {
        return mostrarSucursal;
    }

    public boolean isMostrarSexo() {
        return mostrarSexo;
    }

    public boolean isMostrarTelefono() {
        return mostrarTelefono;
    }

    public boolean isMostrarTipo_sanguineo() {
        return mostrarTipo_sanguineo;
    }

    public boolean isMostrarFuncionario() {
        return mostrarFuncionario;
    }

    public boolean isMostrarEmpresa() {
        return mostrarEmpresa;
    }

    //</editor-fold>
    
    public ConfigFormCliController() {
    }

    public ConfigFormCli getSelected() {
        return selected;
    }

    public void setSelected(ConfigFormCli selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ConfigFormCliFacade getFacade() {
        return ejbFacade;
    }

    public ConfigFormCli prepareCreate() {
        selected = new ConfigFormCli();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ConfigFormCliCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ConfigFormCliUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ConfigFormCliDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ConfigFormCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ConfigFormCli getConfigFormCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ConfigFormCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ConfigFormCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void showFieldsPerson() {
        List<ConfigFormCli> array;
        String squery = "SELECT c FROM ConfigFormCli c WHERE c.formulario ='" + Constants.CONFIGPERSONSFORM + "'";
        array = (List<ConfigFormCli>) ejbFacade.findByQueryArray(squery).result;
        for (int i = 0; i < array.size(); i++) {
            ConfigFormCli modelo = array.get(i);
            String tmp = modelo.getCampo();
            tmp = JsfUtil.quitaEspacios(tmp);
            switch (tmp) {

                case "Arl":
                    mostrarARL = modelo.getMostrar();
                    break;
                case "Celular":
                    mostrarCelular = modelo.getMostrar();
                    break;
                case "Email":
                    mostrarEmail = modelo.getMostrar();
                    break;
                case "Direccion":
                    mostrarDireccion = modelo.getMostrar();
                    break;
                case "Eps":
                    mostrarEPS = modelo.getMostrar();
                    break;
                case "Fechanacimiento":
                    mostrarnacimiento = modelo.getMostrar();
                    break;
                case "Fechaarl":
                    mostrarFecha_vigencia_ARL = modelo.getMostrar();
                    break;
                case "Fechaeps":
                    mostrarFecha_vigencia_EPS = modelo.getMostrar();
                    break;
                case "Departamento":
                    mostrarDepartamento = modelo.getMostrar();
                    break;
                case "Id_externo":
                    mostrarIdExterno = modelo.getMostrar();
                    //limpiarIdExterno = mostrarIdExterno;
                    break;
                case "Municipio":
                    mostrarMunicipio = modelo.getMostrar();
                    break;
                case "Pais":
                    mostrarPais = modelo.getMostrar();
                    break;
                case "Sucursal":
                    mostrarSucursal = modelo.getMostrar();
                    break;
                case "Sexo":
                    mostrarSexo = modelo.getMostrar();
                    break;
                case "Telefono":
                    mostrarTelefono = modelo.getMostrar();
                    break;
                case "Rh":
                    mostrarTipo_sanguineo = modelo.getMostrar();
                    break;
                case "Funcionario":
                    mostrarFuncionario = modelo.getMostrar();
                    break;
                case "Empresa":
                    mostrarEmpresa = modelo.getMostrar();
                    break;
                case "Personacontacto":
                    mostrarPersonaContacto= modelo.getMostrar();
                    break;   
                 
            }
        }
    }

    @FacesConverter(forClass = ConfigFormCli.class)
    public static class ConfigFormCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConfigFormCliController controller = (ConfigFormCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "configFormCliController");
            return controller.getConfigFormCli(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ConfigFormCli) {
                ConfigFormCli o = (ConfigFormCli) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ConfigFormCli.class.getName()});
                return null;
            }
        }

    }

}
