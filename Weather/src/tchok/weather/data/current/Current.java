package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tchok.weather.data.Humidity;
import tchok.weather.data.Precipitation;
import tchok.weather.data.Pressure;

@XmlRootElement
@XmlAccessorType(value=XmlAccessType.NONE)
public class Current {
	@XmlElement(name="city")
	City _city;
	
	@XmlElement(name="temperature")
	Temperature _temperature;
	
	@XmlElement(name="humidity")
	Humidity _humidity;
	
	@XmlElement(name="pressure")
	Pressure _pressure;
	
	@XmlElement(name="wind")
	Wind _wind;
	
	@XmlElement(name="clounds")
	Clouds _clounds;
	
	@XmlElement(name="visibility")
	Visibility _visibility;
	
	@XmlElement(name="precipitation")
	Precipitation _precipitation;
	
	@XmlElement(name="weather")
	Weather _weather;
	
	@XmlElement(name="lastupdate")
	LastUpdate _lastUpdate;

	public City getCity() {
		return _city;
	}

	public Temperature getTemperature() {
		return _temperature;
	}

	public Humidity getHumidity() {
		return _humidity;
	}

	public Pressure getPressure() {
		return _pressure;
	}

	public Wind getWind() {
		return _wind;
	}

	public Clouds getClounds() {
		return _clounds;
	}

	public Visibility getVisibility() {
		return _visibility;
	}

	public Precipitation getPrecipitation() {
		return _precipitation;
	}

	public Weather getWeather() {
		return _weather;
	}

	public LastUpdate getLastUpdate() {
		return _lastUpdate;
	}
}
