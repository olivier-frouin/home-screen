package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Temperature {
	@XmlAttribute(name="value")
	double _value;
	
	@XmlAttribute(name="min")
	double _min;
	
	@XmlAttribute(name="max")
	double _max;
	
	@XmlAttribute(name="unit")
	String _metric;

	public double getValue() {
		return _value;
	}

	public double getMin() {
		return _min;
	}

	public double getMax() {
		return _max;
	}

	public String getMetric() {
		return _metric;
	}
}
