package Themes;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

 
@ManagedBean(name="themeService", eager = true)
@ApplicationScoped
public class ThemeService {
     
    private List<Theme> themes;
     
    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
        themes.add(new Theme(0, "Afterdark", "afterdark"));
        themes.add(new Theme(1, "Afternoon", "afternoon"));
        themes.add(new Theme(2, "Afterwork", "afterwork"));
        themes.add(new Theme(3, "Aristo", "aristo"));
        themes.add(new Theme(4, "Black-Tie", "black-tie"));
        themes.add(new Theme(5, "Blitzer", "blitzer"));
        themes.add(new Theme(6, "Bluesky", "bluesky"));
        themes.add(new Theme(7, "Bootstrap", "bootstrap"));
        themes.add(new Theme(8, "Casablanca", "casablanca"));
        themes.add(new Theme(9, "Cupertino", "cupertino"));
        themes.add(new Theme(10, "Cruze", "cruze"));
        themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
        themes.add(new Theme(12, "Dot-Luv", "dot-luv"));
        themes.add(new Theme(13, "Eggplant", "eggplant"));
        themes.add(new Theme(14, "Excite-Bike", "excite-bike"));
        themes.add(new Theme(15, "Flick", "flick"));
        themes.add(new Theme(16, "Glass-X", "glass-x"));
        themes.add(new Theme(17, "Home", "home"));
        themes.add(new Theme(18, "Hot-Sneaks", "hot-sneaks"));
        themes.add(new Theme(19, "Humanity", "humanity"));
        themes.add(new Theme(20, "Le-Frog", "le-frog"));
        themes.add(new Theme(21, "Midnight", "midnight"));
        themes.add(new Theme(22, "Mint-Choc", "mint-choc"));
        themes.add(new Theme(23, "Overcast", "overcast"));
        themes.add(new Theme(24, "Pepper-Grinder", "pepper-grinder"));
        themes.add(new Theme(25, "Redmond", "redmond"));
        themes.add(new Theme(26, "Rocket", "rocket"));
        themes.add(new Theme(27, "Sam", "sam"));
        themes.add(new Theme(28, "Smoothness", "smoothness"));
        themes.add(new Theme(29, "South-Street", "south-street"));
        themes.add(new Theme(30, "Start", "start"));
        themes.add(new Theme(31, "Sunny", "sunny"));
        themes.add(new Theme(32, "Swanky-Purse", "swanky-purse"));
        themes.add(new Theme(33, "Trontastic", "trontastic"));
        themes.add(new Theme(34, "UI-Darkness", "ui-darkness"));
        themes.add(new Theme(35, "UI-Lightness", "ui-lightness"));
        themes.add(new Theme(36, "Vader", "vader"));
    }
     
    public List<Theme> getThemes() {
        return themes;
    } 
}