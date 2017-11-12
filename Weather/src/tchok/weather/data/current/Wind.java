package tchok.weather.data.current;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import tchok.weather.data.WindSpeed;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Wind {
	@XmlElement(name="speed")
	WindSpeed _speed;
	
	@XmlElement(name="direction")
	WindDirection _direction;

	public WindSpeed getSpeed() {
		return _speed;
	}

	public WindDirection getDirection() {
		return _direction;
	}
}
