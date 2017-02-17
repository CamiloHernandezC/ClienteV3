/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Themes;

import Entities.PersonasCli;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.PrimeFacesContext;

/**
 *
 * @author amorales
 */
@Named("themeController")
@SessionScoped
public class ThemeController implements Serializable{
    private Theme theme;

    public Theme getTheme() {
        if(theme==null){
            theme = new Theme(14, "Excite-Bike", "excite-bike");
        }
        return theme;
    }
    
    public void reset(){
        theme = new Theme(14, "Excite-Bike", "excite-bike");
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
    
    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = Theme.class)
    public static class ThemeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ThemeService controller =  (ThemeService) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "themeService");
            return controller.getThemes().get(Integer.valueOf(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Theme) {
                Theme o = (Theme) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
    
    public void preview() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
