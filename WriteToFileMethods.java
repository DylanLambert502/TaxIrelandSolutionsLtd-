import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 ** A neat little way to store any methods necessary for the creation of and writing to files.
 **/
public class WriteToFileMethods {

    File PropertiesFile = new File("Properties.csv");
    FileWriter PropertiesFR = new FileWriter(PropertiesFile, true);
    File PaymentsFile = new File("Payments.csv");
    FileWriter paymentsFR = new FileWriter(PaymentsFile, true);

    /**
     *
     * @throws IOException
     */
    public WriteToFileMethods() throws IOException {
    }

    /**
     *
     * @param property
     * @throws IOException
     */
    public void writePropertyToFile(Property property) throws IOException {
        PropertiesFR.append( property.toStringCSV() + "\n");
        PropertiesFR.flush();
    }

    /**
     *
     * @param addition
     * @throws IOException
     */
    public void writeToPaymentFile( String addition) throws IOException{
        paymentsFR.append( addition );
        paymentsFR.flush();
    }

}
