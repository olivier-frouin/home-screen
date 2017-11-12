package tchok.weather;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageFactory {
	private static Map<String, Image> _images = new HashMap<>();
	
	public static Image getImage(String key) {
		// http://openweathermap.org/img/w/10d.png
		if ( ! _images.containsKey(key)) {
	
			Image image = new Image(ImageFactory.class.getResource("/tchok/weather/res/" + key + ".png").toExternalForm());
			_images.put(key, image);
		}
		return _images.get(key);
	}
}
