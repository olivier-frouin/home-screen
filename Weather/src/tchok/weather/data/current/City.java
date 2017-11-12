package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import tchok.weather.data.Coordinates;
import tchok.weather.data.Sun;

@XmlAccessorType(value=XmlAccessType.NONE)
public class City {
	@XmlElement(name="coord")
	Coordinates _coordinates;
	
	@XmlElement(name="country")
	String _country;
	
	@XmlElement(name="sun")
	Sun _sun;

	public Coordinates getCoordinates() {
		return _coordinates;
	}

	public String getCountry() {
		return _country;
	}

	public Sun getSun() {
		return _sun;
	}
}
