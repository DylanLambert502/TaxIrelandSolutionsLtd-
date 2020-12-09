import java.util.ArrayList;

/**
 * Class to represent an owner of a property
 */
public class Owner {
    private ArrayList<Property> properties;
    private String name;

    public Owner(String name) {
        properties = new ArrayList<Property>();
        this.name = name;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }

    public void addProperty(Property p){
        properties.add(p);
    }

    public void setName(String ownerName) {
        this.name = ownerName;
    }
}
