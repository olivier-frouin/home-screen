package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import tchok.weather.data.Coordinates;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Location {
	@XmlElement(name="name")
	String _name;
	
	@XmlElement(name="country")
	String _country;
	
	// TODO Coordinates don't share attributes names
	@XmlElement(name="location")
	Coordinates _location;
}
