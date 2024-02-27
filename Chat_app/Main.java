// Main.java
package Chat_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private ChartManager chartManager;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chart Application");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);

        chartManager = new ChartManager();

        // UI components and template
        ComboBox<String> chartTypeComboBox = new ComboBox<>();
        chartTypeComboBox.getItems().addAll("Bar Chart", "Line Chart", "Pie Chart");
        chartTypeComboBox.setValue("Bar Chart");

        Button updateButton = new Button("Update Chart");
        updateButton.setOnAction(e -> updateChart(chartTypeComboBox.getValue()));

        HBox uiControls = new HBox(10, chartTypeComboBox, updateButton);
        uiControls.setStyle("-fx-padding: 10px;");

        root.getChildren().addAll(uiControls, chartManager.getChartNode());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateChart(String chartType) {
        chartManager.updateChart(chartType);
    }

    public static void main(String[] args) {
        //user input
        launch(args);
        
    }
    // public static void main(String[] args) {
    //     System.out.println("Hello, Java!");
    // }
}
