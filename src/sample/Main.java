package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Pane panel = new Pane();
        panel.setId("panel");

        Label mainLabel = new Label("pjatk karpo life\nsimulation 2018");
        Button start = new Button("start");
        mainLabel.setId("pjatk");
        //mainLabel.setLayoutY(30);
        start.setId("start");

        Label sig = new Label("nina dziedziurko, s17077");
        sig.setId("sig");

        //start.setLayoutY(50);
        panel.getChildren().add(mainLabel);
        panel.getChildren().add(start);
        panel.getChildren().add(sig);
        root.getChildren().add(panel);
        //root.getChildren().add(start);


        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(this.getClass().getResource("/sample/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("pjatk builder");
        primaryStage.show();



        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              Game game = new Game(primaryStage);
            }
        });
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        System.exit(0);
                    }
                });
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
