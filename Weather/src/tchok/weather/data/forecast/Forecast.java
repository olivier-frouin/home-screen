package tchok.weather.data.forecast;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(value=XmlAccessType.NONE)
public class Forecast {
	@XmlElement(name="time")
	List<TimedData> _entries;

	public List<TimedData> getEntries() {
		return _entries;
	}
}
