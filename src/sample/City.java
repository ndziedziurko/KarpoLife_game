package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class City extends RadioButton {
    private String name;
    private ArrayList<Floor> floors;
    private int floornumber = 1;


    public City(String name){
        this.name = name;
        this.floors = new ArrayList<>(){{
            add(new Floor(true, floornumber));
            add(new Floor(false, floornumber+1));
        }};
        this.floornumber++;
        this.setText(name);
    }


    public ArrayList<Floor> getFloors() {
        return floors;
    }


    public void initPanel(Pane label){
     int l =1;

    for(Floor i : this.floors){

        if(l==6){
            break;
        }

        String name = "buy"+l;
        Button floorb = new Button();
        floorb.setId("buy"+l);
        label.getChildren().add(floorb);

        floorb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Income.getSum() < i.getPrice()){
                    Stage errorDialog = new Stage();
                    errorDialog.initModality(Modality.WINDOW_MODAL);
                    VBox vbox = new VBox(new Text("Not enough money"));
                    Pane vboxp = new Pane(vbox);
                    errorDialog.setScene(new Scene(vboxp));
                    errorDialog.show();
                }
                else {
                    Income.setSum(Income.getSum()-i.getPrice());
                    i.setBought(true);
                    floorb.setDisable(true);
                    floors.add(new Floor(false, floornumber + 1));
                    floornumber++;
                    label.getChildren().clear();
                    initPanel(label);
                }
            }
        });

        if(i.isBought()){
            floorb.setVisible(false);
            floorb.setDisable(true);
            Pane floorl = new Pane();
            floorl.setId("floorl"+l);
            i.initEmployee(floorl);
            label.getChildren().add(floorl);
        }else{
            floorb.setText("buy new floor \nfor " + i.getPrice()+"$");
        }

        l++;
    }
    }
}



