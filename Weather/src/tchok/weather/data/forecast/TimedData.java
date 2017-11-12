package tchok.weather.data.forecast;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import tchok.weather.data.Humidity;
import tchok.weather.data.Precipitation;
import tchok.weather.data.Pressure;
import tchok.weather.data.WindSpeed;

@XmlAccessorType(value=XmlAccessType.NONE)
public class TimedData {
	@XmlAttribute(name="day")
	Date _day;

	@XmlElement(name="clouds")
	ForecastClouds _clouds;

	@XmlElement(name="humidity")
	Humidity _humidity;

	@XmlElement(name="precipitation")
	Precipitation _precipitation;

	@XmlElement(name="pressure")
	Pressure _pressure;

	@XmlElement(name="symbol")
	Symbol _symbol;

	@XmlElement(name="temperature")
	ForecastTemperature _temperature;

	@XmlElement(name="windDirection")
	ForecastWindDirection _windDirection;

	@XmlElement(name="windSpeed")
	ForecastWindSpeed _windSpeed;
}
