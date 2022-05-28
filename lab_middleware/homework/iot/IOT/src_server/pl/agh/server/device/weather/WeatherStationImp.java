package pl.agh.server.device.weather;

import com.zeroc.Ice.Current;
import device.weather.WeatherInfo;
import device.weather.WeatherStation;
import java.util.Date;
import java.util.Random;

public class WeatherStationImp implements WeatherStation {
    private final Random random = new Random();

    private final static double TEMPERATURE_MIN_RAND = 18;

    private final static double TEMPERATURE_MAX_RAND = 25;

    private final static double HUMIDITY_MIN_RAND = 0.4;

    private final static double HUMIDITY_MAX_RAND = 0.7;

    private final static double PRESSURE_MIN_RAND = 1010;

    private final static double PRESSURE_MAX_RAND = 1020;

    @Override
    public WeatherInfo getWeatherInfo(Current current) {
        return new WeatherInfo(getTemperature(), getHumidity(), getPressure());
    }

    @Override
    public String getCurrentTime(Current current) {
        return new Date().toString();
    }

    private double getTemperature() {
        return randValue(TEMPERATURE_MIN_RAND, TEMPERATURE_MAX_RAND);
    }

    private double getHumidity() {
        return randValue(HUMIDITY_MIN_RAND, HUMIDITY_MAX_RAND);
    }

    private double getPressure() {
        return randValue(PRESSURE_MIN_RAND, PRESSURE_MAX_RAND);
    }

    private double randValue(double minValue, double maxValue) {
        return this.random.nextDouble((maxValue - minValue)) + minValue;
    }
}
