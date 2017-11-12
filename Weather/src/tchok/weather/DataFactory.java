package tchok.weather;

import java.io.IOException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import tchok.weather.data.current.Current;
import tchok.weather.data.forecast.WeatherData;

public class DataFactory {
	static final String BASE_URL="http://api.openweathermap.org/data/2.5/";
	
	static final String SHARED_ARGS="&mode=xml&units=metric&lang=en&APPID=cf95bf498cd3b134d79fd1b43f4aef0c";
	
	static final String SAVIGNY="2975525";
	
	static final String CURRENT_URL=BASE_URL+"weather?id="+SAVIGNY+SHARED_ARGS;
//	static final String CURRENT_URL=BASE_URL+"weather?q=Savigny sur Orge"+SHARED_ARGS;
	static final String DAILY_URL=BASE_URL+"forecast/daily?id="+SAVIGNY+SHARED_ARGS;
	
	static final Unmarshaller _unmarshaller;
	
	static {
		JAXBContext contex;
		try {
			contex = JAXBContext.newInstance(Current.class, WeatherData.class);
			_unmarshaller = contex.createUnmarshaller();
		} catch (JAXBException e) {
			throw new Error(e);
		}
	}
	
	public static WeatherData getForecast() throws IOException, JAXBException {
		URL url = new URL(DAILY_URL);
		WeatherData data = (WeatherData) _unmarshaller.unmarshal(url.openStream());
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		
		URL url = new URL(CURRENT_URL);
//		Current current = (Current) unmarshaller.unmarshal(url.openStream());
		
		url = new URL(DAILY_URL);
		WeatherData data = (WeatherData) _unmarshaller.unmarshal(url.openStream());
		System.out.println("yeh");
	}
}
