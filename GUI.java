import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{

Scene scene1, scene2, scene3, scene4;
    
@Override
public void start(Stage primaryStage)  {
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
scene1= new Scene(layout1, 500, 200);
               
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
    owner.setName(ownerName);
    //WriteToFileMethods writeToFileMethods = new WriteToFileMethods();
    //ReadFromFileMethods readFromFileMethods = new ReadFromFileMethods();
    //readFromFileMethods.readPropertiesFromFileToOwnersArrayList( owner);
    tf.setText("");}});

for ( Property p : owner.getProperties()){  //checks is it tax day.
                p.tax.taxDay();
            }
    
HBox layout2a= new HBox(10);
layout2a.getChildren().addAll(button3a, button3b);
layout2a.setAlignment(Pos.BOTTOM_RIGHT);
VBox layout2c= new VBox(10);
layout2c.getChildren().addAll(layout2, layout2a);
scene2= new Scene(layout2c,500,200);



GridPane layout3 = new GridPane();
Label label3= new Label("Choose what you would like to do.");
Button button4= new Button("Register a Property");
Button button5= new Button("View Properties and Tax Due");
Button button6= new Button("Pay Tax");
Button button7= new Button("View Balancing Statements");
Button button8= new Button("View Payment History");
Button button9= new Button("Quit");
layout3.add(button4,0,0);
layout3.add(button5,1,0);
layout3.add(button6,0,1);
layout3.add(button7,1,1);
layout3.add(button8,0,2);
layout3.add(button9,1,2);
button4.setOnAction(e -> primaryStage.setScene(scene4));
VBox layout4= new VBox(10);
layout4.getChildren().addAll(label3, layout3);
scene3= new Scene(layout4,500,250);


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
layout9.getChildren().addAll(labelpr,ppYes, ppNo);
HBox layout10 = new HBox(10);
Button buttonback= new Button("Go back");
buttonback.setOnAction(e -> {
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
    primaryStage.setScene(scene3);});
Button buttonreg= new Button("Register");
buttonreg.setOnAction(e -> primaryStage.setScene(scene3));
layout10.getChildren().addAll(buttonback,buttonreg);
layout5.getChildren().addAll(layout6, layout7, layout9,layout10);
scene4= new Scene(layout5,500,250);
        
        
primaryStage.setScene(scene1);
primaryStage.show();
}

private boolean validate(String text){
  return text != null && !text.isEmpty();
}

public static void main(String[] args) {
launch(args);
}

    
    
}

