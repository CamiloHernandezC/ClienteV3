package Themes;
 
public class Theme {
 
    private Integer id;
    private String displayName;
    private String name;
     
    public Theme() {}
 
    public Theme(Integer id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getDisplayName() {
        return displayName;
    }
 
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    
}
