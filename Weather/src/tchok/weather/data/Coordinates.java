package tchok.weather.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Coordinates {
	@XmlAttribute(name="lat")
	double _latitude;
	
	@XmlAttribute(name="lon")
	double _longitude;

	public double getLatitude() {
		return _latitude;
	}

	public double getLongitude() {
		return _longitude;
	}
}
