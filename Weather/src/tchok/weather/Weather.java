package tchok.weather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tchok.weather.data.forecast.TimedData;
import tchok.weather.data.forecast.TimedDataController;
import tchok.weather.data.forecast.WeatherData;

public class Weather extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Forecast zone
		VBox forecasts = new VBox();
		
		// Load
		WeatherData weatherData = DataFactory.getForecast();
		for (TimedData data : weatherData.getForecasts().getEntries()) {
			// Build a controller
			TimedDataController controller = TimedDataController.createFor(data);
			
			// Push
			forecasts.getChildren().add(controller.getRoot());
		}
		
		// Build scene
		Scene scene = new Scene(forecasts);
		scene.getStylesheets().setAll(Weather.class.getResource("timed_data.css").toExternalForm());
		forecasts.getStylesheets().setAll(scene.getStylesheets());
		
		primaryStage.setScene(scene);
		
		// Show
//		primaryStage.initStyle(StageStyle.UNDECORATED);
//		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
