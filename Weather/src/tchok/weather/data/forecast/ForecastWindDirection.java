package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class ForecastWindDirection {
	@XmlAttribute(name="value")
	double _value;
	
	@XmlAttribute(name="code")
	String _code;
	
	@XmlAttribute(name="name")
	String _name;

	public double getValue() {
		return _value;
	}

	public String getCode() {
		return _code;
	}

	public String getName() {
		return _name;
	}
}
