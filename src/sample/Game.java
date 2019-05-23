package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private ArrayList<City> cities = new ArrayList<>();
    public Game(Stage primaryStage) {
        Group root = new Group();
        Pane label = new Pane();
        Pane mainLabel = new Pane();
        label.setId("cities");

        ToggleGroup group = new ToggleGroup();
        City warsaw = new City("Warsaw");
        City newyork = new City("New York");
        City london = new City("London");
        cities.add(warsaw);
        cities.add(newyork);
        cities.add(london);
        warsaw.setId("warsaw");
        newyork.setId("newyork");
        london.setId("london");
        warsaw.setToggleGroup(group);
        newyork.setToggleGroup(group);
        london.setToggleGroup(group);

        mainLabel.setId("mainLabel");
        mainLabel.getChildren().addAll(warsaw,newyork,london);
        mainLabel.getChildren().add(label);
        root.getChildren().add(mainLabel);

        warsaw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.getChildren().clear();
                warsaw.initPanel(label);
            }
        });



        newyork.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.getChildren().clear();
                newyork.initPanel(label);

            }
        });




        london.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.getChildren().clear();
                london.initPanel(label);
            }
        });



        Label budget = new Label(Income.getSum()+"");
        budget.setId("budget");
        Label total = new Label("total capital: ");
        total.setId("total");

        root.getChildren().add(total);
        root.getChildren().add(budget);



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                increaseIncome();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        budget.setText(Income.getSum()+"$");
                    }
                });
                }
        }, 0, 1000);


        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(this.getClass().getResource("/sample/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public void increaseIncome(){
        for(City c: cities){
            for(Floor f: c.getFloors()){
                Income.setSum(Income.getSum()+f.getIncome());
            }
        }
    }
}
