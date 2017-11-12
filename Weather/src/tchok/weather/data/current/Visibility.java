package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Visibility {
	@XmlAttribute(name="value")
	double _value;

	public double getValue() {
		return _value;
	}
}
