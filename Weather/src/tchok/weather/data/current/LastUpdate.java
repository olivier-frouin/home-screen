package tchok.weather.data.current;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class LastUpdate {
	@XmlAttribute(name="value")
	Date _value;

	public Date getValue() {
		return _value;
	}
}
