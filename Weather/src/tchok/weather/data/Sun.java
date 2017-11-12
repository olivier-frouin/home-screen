package tchok.weather.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Sun {
	@XmlAttribute(name="rise")
	Date _rise;
	
	@XmlAttribute(name="set")
	Date _set;

	public Date getRise() {
		return _rise;
	}

	public Date getSet() {
		return _set;
	}
}
