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
        File DoEFile = new File("AllProperties.csv");
        //DoEfile created to store all properties in one file for Dept. of Enviorment.
        FileWriter fr = new FileWriter(nFile, true);
        FileWriter DoEfr = new FileWriter(DoEFile, true);
        
        for ( Property p: owner.getProperties() ){
            fr.append(owner.getName() + "," + p.toString() + "\n");
            DoEfr.append(owner.getName() + "," + p.toString() + "\n");
        }
        DoEfr.flush();
        DoEfr.close();
        fr.flush();
        fr.close();
    }

}
