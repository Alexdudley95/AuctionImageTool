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
import javax.swing.JOptionPane;

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

        Scene scene = new Scene(pane, 400, 150);


        stage.setTitle("Lot Numbering Tool");
        stage.setScene(scene);
        stage.show();


        //event handler
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String folderPath  = filePath.getText();
                if (folderPath.equalsIgnoreCase("") || folderPath.equalsIgnoreCase("Please paste your filepath here.")){
                    JOptionPane.showMessageDialog(null, "No folder path inputted.");
                    return;
                }

                int amountOfPictures = Integer.parseInt(pictureEntry.getText());
                String lotNum = lotEntry.getText();

                renameFiles(folderPath, amountOfPictures, lotNum);

                lotEntry.setText(String.valueOf(updateLotNum(lotNum)));
                pictureEntry.setText("");
            }
        };

        btn.setOnAction(event);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void renameFiles(String folderPath, int amountOfPictures, String lotNum){

        int count = 0;
        File myFolder = new File(folderPath);

        File[] file_array = myFolder.listFiles();

            for(int i = 0; i < file_array.length; i++){

                if(count != amountOfPictures){
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

    public static int updateLotNum(String currentLotNum){
        StringBuilder num = new StringBuilder();
        String numChars = "0123456789";

        for(int i = 0; i < currentLotNum.length(); i++){
            for(int j = 0; j < 9; j++){
                if(currentLotNum.charAt(i) == numChars.charAt(j)){
                    num.append(currentLotNum.charAt(i));
                    System.out.println(num);
                }
            }
        }
        int lotNum = Integer.parseInt(String.valueOf(num));

        return  lotNum + 1;
    }
}