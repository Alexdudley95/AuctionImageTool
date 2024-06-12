package com.example.auctionimagerenamer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));




        Label label = new Label();
        label.setText("Input lot num:");

        Label label1 = new Label();
        label1.setText("Input amount of pictures:");

        Label listLabel = new Label();
        listLabel.setText("");


        Button btn = new Button("Send");

        TextField lotEntry = new TextField();
        TextField pictureEntry = new TextField();
        TextField filePath = new TextField("Please paste your filepath here.");

        VBox vbox = new VBox();
        VBox vbox1 = new VBox();

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10));
        vbox1.setSpacing(5);
        vbox1.setPadding(new Insets(10));

        HBox hbox = new HBox(vbox, vbox1);

        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10));

        vbox.getChildren().add(label);
        vbox.getChildren().add(label1);

        vbox1.getChildren().add(lotEntry);
        vbox1.getChildren().add(pictureEntry);
        vbox1.getChildren().add(btn);

        BorderPane pane = new BorderPane();
        pane.setCenter(hbox);
        pane.setTop(filePath);
        pane.setBottom(listLabel);



        Scene scene = new Scene(pane, 500, 600);


        stage.setTitle("Alex made this");
        stage.setScene(scene);
        stage.show();


        //event handler
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                String folderPath  = filePath.getText();
                int amountOfPictures = Integer.parseInt(pictureEntry.getText());
                int lotNum = Integer.parseInt(lotEntry.getText());



                renameFiles(folderPath, amountOfPictures, lotNum);


                lotEntry.setText(String.valueOf(lotNum + 1));
                pictureEntry.setText("");
            }
        };

        btn.setOnAction(event);
    }

    public static void main(String[] args) {
        launch();

    }



    public static void renameFiles(String folderPath, int amountOfPictures, int lotNum){

        int count = 0;
        File myFolder = new File(folderPath);

        File[] file_array = myFolder.listFiles();

        for(int i = 0; i < file_array.length; i++){

            if(count == amountOfPictures){
                System.out.println("finished");
            }else{
                if (file_array[i].isFile()) {
                    File myfile = new File(folderPath + "\\" + file_array[i].getName());

                    if(count < 1){
                        myfile.renameTo(new File(folderPath + "\\" + lotNum + ".png"));
                    }else{
                        myfile.renameTo(new File(folderPath + "\\" + lotNum + "-" + (count+1) + ".png"));
                    }
                }
                count++;
            }



        }

    }
}
