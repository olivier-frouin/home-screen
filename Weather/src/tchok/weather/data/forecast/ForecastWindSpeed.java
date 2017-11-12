package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class ForecastWindSpeed {
	@XmlAttribute(name="mps")
	double _value;
	
	@XmlAttribute(name="name")
	String _name;

	public double getValue() {
		return _value;
	}

	public String getName() {
		return _name;
	}
}
