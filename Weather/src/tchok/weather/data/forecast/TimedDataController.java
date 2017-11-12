package tchok.weather.data.forecast;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tchok.weather.ImageFactory;

public class TimedDataController {
	private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("EEEE dd");
	
	@FXML 
	private Parent _root;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="_dateLabel"
    private Label _dateLabel; // Value injected by FXMLLoader

    @FXML // fx:id="_iconLabel"
    private Label _iconLabel; // Value injected by FXMLLoader

    @FXML // fx:id="_windNameLabel"
    private Label _windNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="_tempLabelEvening"
    private Label _tempLabelEvening; // Value injected by FXMLLoader

    @FXML // fx:id="_pressureValueLabel"
    private Label _pressureValueLabel; // Value injected by FXMLLoader

    @FXML // fx:id="_tempLabelMorning"
    private Label _tempLabelMorning; // Value injected by FXMLLoader

    @FXML // fx:id="_symbolName"
    private Label _symbolName; // Value injected by FXMLLoader

    @FXML // fx:id="_tempLabelDay"
    private Label _tempLabelDay; // Value injected by FXMLLoader

    @FXML // fx:id="_humidityLabel"
    private Label _humidityLabel; // Value injected by FXMLLoader

    @FXML // fx:id="_tempLabelNight"
    private Label _tempLabelNight; // Value injected by FXMLLoader

    @FXML // fx:id="_windDetailsLabel"
    private Label _windDetailsLabel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert _dateLabel != null : "fx:id=\"_dateLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _iconLabel != null : "fx:id=\"_iconLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _windNameLabel != null : "fx:id=\"_windNameLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _tempLabelEvening != null : "fx:id=\"_tempLabelEvening\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _pressureValueLabel != null : "fx:id=\"_pressureValueLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _tempLabelMorning != null : "fx:id=\"_tempLabelMorning\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _symbolName != null : "fx:id=\"_symbolName\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _tempLabelDay != null : "fx:id=\"_tempLabelDay\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _humidityLabel != null : "fx:id=\"_humidityLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _tempLabelNight != null : "fx:id=\"_tempLabelNight\" was not injected: check your FXML file 'TimedData.fxml'.";
        assert _windDetailsLabel != null : "fx:id=\"_windDetailsLabel\" was not injected: check your FXML file 'TimedData.fxml'.";
    }
    
    public Parent getRoot() {
    	return _root;
    }
    
    public static TimedDataController createFor(TimedData data) throws IOException {
    	// Load
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(TimedDataController.class.getResourceAsStream("TimedData.fxml"));
		TimedDataController controller =  loader.getController();
		controller._root = root;
			
		// Load data
		controller._dateLabel.setText(DAY_FORMAT.format(data._day));
		controller._symbolName.setText(data._symbol._name);
		
		controller._windNameLabel.setText(data._windSpeed.getName());
		controller._windDetailsLabel.setText(String.format("%2.0f mph %s", data._windSpeed.getValue(), data._windDirection._code));
		
		controller._humidityLabel.setText(		String.format("Humidité: %2.0f %%", data._humidity.getValue()));
		controller._pressureValueLabel.setText(	String.format("Pression: %4.0f hPa", data._pressure.getValue()));
		
		controller._tempLabelDay.setText(		String.format("%2.1f °", data._temperature._day));
		controller._tempLabelEvening.setText(	String.format("%2.1f °", data._temperature._evening));
		controller._tempLabelMorning.setText(	String.format("%2.1f °", data._temperature._morning));
		controller._tempLabelNight.setText(		String.format("%2.1f °", data._temperature._night));
		
		controller._iconLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		controller._iconLabel.setGraphic(new ImageView(ImageFactory.getImage(data._symbol._var)));
		// Done
		return controller;
    }
}
