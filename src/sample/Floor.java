package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Floor {
    private int price;
    private ArrayList<Employee> employees;
    private boolean bought;
    private Employee manager;
    private int income =0;

    public Floor(boolean bought, int number){
        this.price = number*1000;
        this.bought = bought;
        this.manager = new Employee(false, number, true);
        employees = new ArrayList<>(){{
            add(new Employee(false, number));
            add(new Employee(false, number));
            add(new Employee(false, number));
            add(new Employee(false, number));
            add(new Employee(false, number));
        }};
        for(Employee e: employees){
            if(e.isHired()){
                income+= e.getSalary();

            }
        }
        if(manager.isHired()){
            income += manager.getSalary();
        }
    }



    public boolean isBought() {
        return bought;
    }




    public int getPrice() {
        return price;
    }




    public void initEmployee(Pane label1) {
        int l = 1;

        for(Employee i: this.employees){
            String id = "empId"+l;
            Button emp = new Button(i.getPrice()+"$");
            emp.setId(id);
           // emp.setLayoutX(10+50*l);


            emp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(Income.getSum()< i.getPrice()){
                        Stage errorDialog = new Stage();
                        errorDialog.initModality(Modality.WINDOW_MODAL);
                        VBox vbox = new VBox(new Text("Not enough money"));
                        errorDialog.setScene(new Scene(vbox));
                        errorDialog.show();
                    }else{
                    Income.setSum(Income.getSum() - i.getPrice());
                 i.setHired(true);
                 income += i.getSalary();
                 label1.getChildren().clear();
                 initEmployee(label1);
                }}
            });



            if(i.isHired()){
              emp.setDisable(true);
            }
            l++;
            label1.getChildren().add(emp);
        }




        Button managerb = new Button(this.manager.getPrice() + "$");
        managerb.setId("manager");

        if(this.manager.isHired()){
            managerb.setDisable(true);
        }



        managerb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Income.getSum() < manager.getPrice()){
                    Stage errorDialog = new Stage();
                    errorDialog.initModality(Modality.WINDOW_MODAL);
                    VBox vbox = new VBox(new Text("Not enough money"));
                    errorDialog.setScene(new Scene(vbox));
                    errorDialog.show();
                }else{
                Income.setSum(Income.getSum()-manager.getPrice());
                manager.setHired(true);
                income += manager.getSalary();
                label1.getChildren().clear();
                initEmployee(label1);
            }}
        });



        //managerb.setLayoutX(10+50*l);
        label1.getChildren().add(managerb);
        Label inc = new Label(this.getIncome()+"$/s");
        inc.setId("income");
        //inc.setLayoutX(60+50*l);
        label1.getChildren().add(inc);
    }

    public void setBought(boolean b) {
        this.bought = b;
    }



    public int getIncome() {
        return income;
    }
}
