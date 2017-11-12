package tchok.weather.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Humidity {
	@XmlAttribute(name="value")
	double _value;
	
	@XmlAttribute(name="unit")
	String _metric;

	public double getValue() {
		return _value;
	}

	public String getMetric() {
		return _metric;
	}
}
