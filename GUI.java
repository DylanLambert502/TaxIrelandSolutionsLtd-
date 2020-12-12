
import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{

Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8;
    
@Override
public void start(Stage primaryStage) throws IOException {
TextField tf = new TextField();
Owner owner = new Owner("");
        
primaryStage.setTitle("Property Charge Management System");

//Scene 1

Label label1= new Label("Choose which mode you would like to use today");
Button button1= new Button("Property Owner");
Button button2= new Button("Department of Environment Manager");
button1.setOnAction(e -> primaryStage.setScene(scene2));   
button2.setOnAction(e -> primaryStage.setScene(scene3));
VBox layout1 = new VBox(10);     
layout1.getChildren().addAll(label1, button1, button2);
scene1= new Scene(layout1, 550, 200);
               
//Scene 2
GridPane layout2 = new GridPane();
Label label2= new Label("Please enter your name:");
layout2.add(label2,0,0);
layout2.add(tf,1,0);
Button button3a= new Button("Go Back");
button3a.setOnAction(e -> {
    primaryStage.setScene(scene1);
    tf.setText("");});
Button button3b= new Button("Advance");
button3b.setOnAction(e -> {
    boolean validated = validate(tf.getText());
    if(validated){
    primaryStage.setScene(scene3);
    String ownerName = tf.getText();
    tf.setText("");}});
    WriteToFileMethods writeToFileMethods = new WriteToFileMethods();
    ReadFromFileMethods readFromFileMethods = new ReadFromFileMethods();
    readFromFileMethods.readPropertiesFromFileToOwnersArrayList( owner);

for ( Property p : owner.getProperties()){  //checks is it tax day.
                p.tax.taxDay();
            }
    
HBox layout2a= new HBox(10);
layout2a.getChildren().addAll(button3a, button3b);
layout2a.setAlignment(Pos.BOTTOM_RIGHT);
VBox layout2c= new VBox(10);
layout2c.getChildren().addAll(layout2, layout2a);
scene2= new Scene(layout2c,550,200);

//scene 3

GridPane layout3 = new GridPane();
Label label3= new Label("Welcome "+owner.getName()+". Choose what you would like to do.");
Button button4= new Button("Register a Property");
Button button5= new Button("View Properties and Tax Due");
Button button6= new Button("Pay Tax");
Button button7= new Button("View Balancing Statements");
Button button8= new Button("View Payment History");
Button button9= new Button("Quit");
Label msg= new Label("");
layout3.add(button4,0,0);
layout3.add(button5,1,0);
layout3.add(button6,0,1);
layout3.add(button7,1,1);
layout3.add(button8,0,2);
layout3.add(button9,1,2);
button4.setOnAction(e -> {
    primaryStage.setScene(scene4);
msg.setText("");});
button5.setOnAction(e -> {
    if(owner.getProperties().isEmpty())
                    msg.setText("No Properties to view.");
    else
    primaryStage.setScene(scene5);});
button6.setOnAction(e -> {
    if(owner.getProperties().isEmpty())
                    msg.setText("No Property Tax to Pay.");
    else
    primaryStage.setScene(scene6);});
button7.setOnAction(e -> {
    primaryStage.setScene(scene7);});
button8.setOnAction(e -> {
    primaryStage.setScene(scene8);});
button9.setOnAction(e -> {
    System.exit(0);});
VBox layout4= new VBox(10);
layout4.getChildren().addAll(label3, layout3,msg);
scene3= new Scene(layout4,550,250);

//scene 4

VBox layout5 = new VBox(10);
GridPane layout6 = new GridPane();
Label label4= new Label("Address:");
TextField tfad = new TextField();
Label label5= new Label("Postcode:");
TextField tfpc = new TextField();
Label label6= new Label("Estimated Market Value:");
TextField tfmk = new TextField();
layout6.add(label4,0,0);
layout6.add(tfad,1,0);
layout6.add(label5,0,1);
layout6.add(tfpc,1,1);
layout6.add(label6,0,2);
layout6.add(tfmk,1,2);
HBox layout7 = new HBox(10);
Label labelloc= new Label("Location:");
GridPane layout8 = new GridPane();
RadioButton city = new RadioButton("City");
RadioButton ltown = new RadioButton("Large Town");
RadioButton stown = new RadioButton("Small Town");
RadioButton vill = new RadioButton("Village");
RadioButton country = new RadioButton("Countryside");
city.setOnAction(e -> {
    ltown.setSelected(false);
    stown.setSelected(false);
    vill.setSelected(false);
    country.setSelected(false);
});
ltown.setOnAction(e -> {
    city.setSelected(false);
    stown.setSelected(false);
    vill.setSelected(false);
    country.setSelected(false);
});
stown.setOnAction(e -> {
    ltown.setSelected(false);
    city.setSelected(false);
    vill.setSelected(false);
    country.setSelected(false);
});
vill.setOnAction(e -> {
    ltown.setSelected(false);
    stown.setSelected(false);
    city.setSelected(false);
    country.setSelected(false);
});
country.setOnAction(e -> {
    ltown.setSelected(false);
    stown.setSelected(false);
    vill.setSelected(false);
    city.setSelected(false);
});
    
layout8.add(city,0,0);
layout8.add(ltown,1,0);
layout8.add(stown,2,0);
layout8.add(vill,0,1);
layout8.add(country,1,1);
layout7.getChildren().addAll(labelloc, layout8);
HBox layout9 = new HBox(10);
Label labelpr= new Label("Principal private residence:");
RadioButton ppYes = new RadioButton("Yes");
RadioButton ppNo = new RadioButton("No");
ppYes.setOnAction(e -> ppNo.setSelected(false));
ppNo.setOnAction(e -> ppYes.setSelected(false));
layout9.getChildren().addAll(labelpr,ppYes, ppNo);
HBox layout10 = new HBox(10);
Button buttonback= new Button("Go back");
Button buttonreg= new Button("Register");
Label errormsg= new Label("");
layout10.getChildren().addAll(buttonback,buttonreg,errormsg);
layout5.getChildren().addAll(layout6, layout7, layout9,layout10);
buttonback.setOnAction(e -> {
    tfad.setText("");
    tfpc.setText("");
    tfmk.setText("");
    errormsg.setText("");
    city.setSelected(false);
    ltown.setSelected(false);
    stown.setSelected(false);
    vill.setSelected(false);
    country.setSelected(false);
    ppYes.setSelected(false);
    ppNo.setSelected(false);
    primaryStage.setScene(scene3);});
buttonreg.setOnAction(e -> {
    boolean validatedad = validate(tfad.getText());
    boolean validatedpc = validate(tfpc.getText());
    boolean validatedmk = validate(tfmk.getText());
    boolean isCitySelected = city.isSelected();
    boolean isLTownSelected = ltown.isSelected();
    boolean isSTownSelected = stown.isSelected();
    boolean isVillSelected = vill.isSelected();
    boolean isCountrySelected = country.isSelected();
    boolean isppYesSelected = ppYes.isSelected();
    boolean isppNoSelected = ppNo.isSelected();
    
    String address = tfad.getText();
    String postCode = tfpc.getText();    
    String mkValue = tfmk.getText(); 
    int locationCategory=0;
    if(isLTownSelected)locationCategory=1;
    if(isSTownSelected)locationCategory=2;
    if(isVillSelected)locationCategory=3;
    if(isCountrySelected)locationCategory=4;
    boolean ppr=false;
    if(isppYesSelected)ppr=true;
        
    if( validatedad&&validatedpc&&validatedmk&&(isCitySelected||
            isLTownSelected||isSTownSelected||isVillSelected||isCountrySelected)
            &&(isppYesSelected||isppNoSelected)){
        if(isDouble(mkValue)==true){
            double mkValueD = Double.parseDouble(mkValue);
            //owner.addProperty( new Property(owner.getName(), address, postCode, mkValueD, locationCategory, ppr));
            //    writeToFileMethods.writePropertyToFile(new Property(owner.getName(), address, postCode, mkValueD, locationCategory, ppr));
            errormsg.setText("");
        tfad.setText("");
    tfpc.setText("");
    tfmk.setText("");
    city.setSelected(false);
    ltown.setSelected(false);
    stown.setSelected(false);
    vill.setSelected(false);
    country.setSelected(false);
    ppYes.setSelected(false);
    ppNo.setSelected(false);
    primaryStage.setScene(scene3);}
        else errormsg.setText("Market Value must be a number.");
}else errormsg.setText("All fields must be entered.");
});
scene4= new Scene(layout5,550,250);

VBox layoutViewProp = new VBox(10);
GridPane layoutProp1 = new GridPane();
Label labelPpost= new Label("           Postcode           ");
Label labelPtaxd= new Label("           Tax Due            ");
Label labelPtaxod= new Label("         Tax Overdue          ");
layoutProp1.add(labelPpost,0,0);
layoutProp1.add(labelPtaxd,1,0);
layoutProp1.add(labelPtaxod,2,0);
layoutViewProp.getChildren().addAll(layoutProp1);
for(Property p: owner.getProperties()){
                    GridPane layoutPropt = new GridPane();
                    Label poste= new Label(p.getPostCode());
Label taxde= new Label(Double.toString(p.tax.getTaxDue()));
Label taxode= new Label(Double.toString(p.tax.getTaxOverDue()));
layoutPropt.add(labelPpost,0,0);
layoutPropt.add(labelPtaxd,1,0);
layoutPropt.add(labelPtaxod,2,0);
layoutViewProp.getChildren().addAll(layoutPropt);}
Button buttonback2= new Button("Go back");
layoutViewProp.getChildren().addAll(buttonback2);
buttonback2.setOnAction(e -> {
    primaryStage.setScene(scene3);});

scene5= new Scene(layoutViewProp,550,250);


VBox layoutTax = new VBox(10);
Label labelTax= new Label("Tax Due on your properties.");
layoutTax.getChildren().addAll(labelTax);
for(Property p: owner.getProperties()){
                    GridPane layoutPropt = new GridPane();
                    Label poste= new Label(p.getPostCode());
Label taxde= new Label("Tax Due: "+Double.toString(p.tax.getTaxDue()));
layoutPropt.add(poste,0,0);
layoutPropt.add(taxde,1,0);
layoutTax.getChildren().addAll(layoutPropt);}
HBox layoutTaxpay = new HBox(10);
Label poste= new Label("Enter Postcode of Property Tax you want to pay:");
TextField tftax = new TextField();
layoutTaxpay.getChildren().addAll(poste,tftax);
HBox layoutTaxadgb = new HBox(10);
Button buttonback3= new Button("Go back");
Button buttonTax= new Button("Pay Tax");
layoutTaxadgb.getChildren().addAll(buttonback3,buttonTax);
layoutTax.getChildren().addAll(layoutTaxpay,layoutTaxadgb);
buttonback3.setOnAction(e -> {
    primaryStage.setScene(scene3);
tftax.setText("");});
buttonTax.setOnAction(e -> {
    String choice = tftax.getText();
                for (Property p: owner.getProperties()){
                    if(choice.equalsIgnoreCase( p.getPostCode() ) ){
                        //p.payTax();
                    }}
    primaryStage.setScene(scene3);
tftax.setText("");});
scene6= new Scene(layoutTax,550,250);





VBox layoutBal = new VBox(10);
Label labelBal= new Label("Enter Postcode and Year of Balancing Statement to View.");
layoutBal.getChildren().addAll(labelBal);
for(Property p: owner.getProperties()){
Label posteB= new Label(p.getPostCode());
layoutBal.getChildren().addAll(posteB);}
HBox layoutBalPOY = new HBox(10);
Label labelBalPO= new Label("Postcode:");
TextField tfbalPO = new TextField();
Label labelBalY= new Label("Year:");
TextField tfbalY = new TextField();
layoutBalPOY.getChildren().addAll(labelBalPO,tfbalPO,labelBalY,tfbalY);
HBox layoutBaladgb = new HBox(10);
Button buttonback4= new Button("Go back");
Button buttonBal= new Button("Enter");
Label errormsgBal= new Label("");
layoutBaladgb.getChildren().addAll(buttonback4,buttonBal,errormsgBal);
layoutBal.getChildren().addAll(layoutBalPOY,layoutBaladgb);
buttonback4.setOnAction(e -> {
    primaryStage.setScene(scene3);
    tfbalY.setText("");
    tfbalPO.setText("");});
buttonBal.setOnAction(e -> {
    boolean validatedPO = validate(tfbalPO.getText());
    boolean validatedY = validate(tfbalY.getText());
    String pChoice = tfbalPO.getText();
    if(validatedPO&&validatedY) {
    if(isDouble(tfbalY.getText())==true){
    int yChoice = Integer.parseInt(tfbalY.getText());
                for(Property p: owner.getProperties()){
                    if( pChoice.equalsIgnoreCase( p.getPostCode())){
                        for (BalancingStatement s : p.tax.getStatements()){
                            if (yChoice == s.getYear()){
                                Label balS= new Label(s.toString());
                                layoutBal.getChildren().addAll(balS);
                            }
                        }
                    }
                }
                tfbalY.setText("");
                tfbalPO.setText("");
                errormsgBal.setText("");
    } else errormsgBal.setText("Year must be a number.");
    } else errormsgBal.setText("All fields must be entered.");
});
scene7= new Scene(layoutBal,550,250);


VBox layoutPay = new VBox(10);
Label labelPay= new Label("All Payments");
layoutPay.getChildren().addAll(labelPay);
for (Property p: owner.getProperties() ){
                    for(BalancingStatement b: p.tax.getStatements()){
                        for( Payment p1: b.getPayments()){
                            Label pay= new Label(p.getPostCode() + ": " + p1.toString());
                            layoutPay.getChildren().addAll(pay);
                        }
                    }
                }
HBox layoutPayadgb = new HBox(10);
Button buttonback5= new Button("Go back");
layoutPay.getChildren().addAll(buttonback5);
buttonback5.setOnAction(e -> {
    primaryStage.setScene(scene3);});

scene8= new Scene(layoutPay,550,250);

        
primaryStage.setScene(scene1);
primaryStage.show();
}

private boolean validate(String text){
  return text != null && !text.isEmpty();
}

public static boolean isDouble(String s) {
    try { 
        Double.parseDouble(s); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    
    return true;
}

public static boolean isInt(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    
    return true;
}

public static void main(String[] args) {
launch(args);
}

    
    
}

