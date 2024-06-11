package com.example.auctionimagerenamer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Label label = new Label();
        label.setText("Input lot num:");

        Label label1 = new Label();
        label1.setText("Input amount of pictures:");

        TextField lotEntry = new TextField();
        TextField pictureEntry = new TextField();

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

        BorderPane pane = new BorderPane();
        pane.setCenter(hbox);



        Scene scene = new Scene(pane, 500, 600);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        String folderPath = "C:\\Users\\alex_\\Desktop\\AUCTION PICS 2";

        File myFolder = new File(folderPath);
        int amountOfPictures = 4;
        int fileName = 651;

        File[] file_array = myFolder.listFiles();
        for(int i = 0; i < file_array.length; i++){
                if (file_array[i].isFile()) {
                    File myfile = new File(folderPath + "\\" + file_array[i].getName());


                    myfile.renameTo(new File(folderPath + "\\" + fileName + "-"  + ".png"));


                }
        }


        launch();


    }
}