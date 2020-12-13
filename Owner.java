import java.util.ArrayList;

/**
 * Class to represent an owner of a property
 */
public class Owner {
    /**
     * Variables declared
     */
    private ArrayList<Property> properties;
    private String name;

    /**
     * constructor method takes one parameter
     * @param name
     */
    public Owner(String name) {
        properties = new ArrayList<>();
        this.name = name;
    }

    /**
     * get the properties from the arrayList and return the result
     * @return
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * gets the name of the owner and returns it
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * adds a property to to the arraylist properties
     * @param p
     */
    public void addProperty(Property p){
        properties.add(p);
    }

    /**
     * name setter method
     * @param ownerName
     */
    public void setName(String ownerName) {
        this.name = ownerName;
    }
}
