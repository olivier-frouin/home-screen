package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class ForecastClouds {
	@XmlAttribute(name="value")
	String _value;
	
	@XmlAttribute(name="all")
	Double _all;
	
	@XmlAttribute(name="unit")
	String _unit;

}
