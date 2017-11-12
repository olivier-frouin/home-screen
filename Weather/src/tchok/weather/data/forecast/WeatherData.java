package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tchok.weather.data.Sun;

@XmlRootElement(name="weatherdata")
@XmlAccessorType(value=XmlAccessType.NONE)
public class WeatherData {
	@XmlElement(name="location")
	Location _location;
	
	@XmlElement(name="sun")
	Sun _sun;
	
	@XmlElement(name="forecast")
	Forecast _forecasts;

	public Location getLocation() {
		return _location;
	}

	public Sun getSun() {
		return _sun;
	}

	public Forecast getForecasts() {
		return _forecasts;
	}
}
