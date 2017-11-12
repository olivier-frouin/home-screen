package tchok.weather.data.forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value=XmlAccessType.NONE)
public class ForecastTemperature {
	@XmlAttribute(name="day")
	double _day;
	
	@XmlAttribute(name="min")
	double _min;
	
	@XmlAttribute(name="max")
	double _max;
	
	@XmlAttribute(name="eve")
	double _evening;
	
	@XmlAttribute(name="night")
	double _night;
	
	@XmlAttribute(name="morn")
	double _morning;

	public double getDay() {
		return _day;
	}

	public double getEvening() {
		return _evening;
	}

	public double getNight() {
		return _night;
	}

	public double getMorning() {
		return _morning;
	}

	public double getMin() {
		return _min;
	}

	public double getMax() {
		return _max;
	}
}
