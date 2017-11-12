package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Weather {
	@XmlAttribute(name="number")
	double _number;

	@XmlAttribute(name="value")
	String _value;
	
	@XmlAttribute(name="icon")
	String _icon;

	public double getNumber() {
		return _number;
	}

	public String getValue() {
		return _value;
	}

	public String getIcon() {
		return _icon;
	}
}
