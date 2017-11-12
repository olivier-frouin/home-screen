package tchok.weather.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Precipitation {
	@XmlAttribute(name="value")
	double _value;
	
	@XmlAttribute(name="mode")
	String _mode;
	
	@XmlAttribute(name="type")
	String _type;

	public double getValue() {
		return _value;
	}

	public String getMode() {
		return _mode;
	}
}
