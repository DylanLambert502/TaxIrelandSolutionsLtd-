import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A neat little way to store any methods necessary for the creation of and reading of files
 */
public class FileMethods {

    public FileMethods() { }

    public void writeAnOwnersPropertiesToFile(Owner owner) throws IOException {
        File nFile = new File(owner.getName() + ".csv");

        FileWriter fr = new FileWriter(nFile, true);
        fr.append(owner.getName() + "'s Properties\n-------------\n");
        for ( Property p: owner.getProperties() ){
            fr.append(p.toString() + "\n");
        }
        fr.flush();
        fr.close();
    }

}
