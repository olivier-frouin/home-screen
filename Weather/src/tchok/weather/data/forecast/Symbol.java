package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Symbol {
	@XmlAttribute(name="number")
	Integer _number;
	
	@XmlAttribute(name="name")
	String _name;
	
	@XmlAttribute(name="var")
	String _var;
}
