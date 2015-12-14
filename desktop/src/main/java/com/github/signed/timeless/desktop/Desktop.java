package com.github.signed.timeless.desktop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Desktop extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FlowPane pane = new FlowPane();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPrefWidth(500);
        pane.getChildren().add(comboBox);
        comboBox.buttonCellProperty().set(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean isEmpty) {
                super.updateItem(item, isEmpty);
                if (isEmpty) {
                    setText("Prompt Text");
                    return;
                }
                setText(item);
            }
        });

        comboBox.setItems(FXCollections.observableArrayList("one", "two"));

        stage.setScene(new Scene(pane));
        stage.show();
    }
}
