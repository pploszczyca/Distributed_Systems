module device {
  module weather {
    struct WeatherInfo {
        double temperature;
        double humidity;
        double pressure;
    };

    interface WeatherStation {
        WeatherInfo getWeatherInfo();
        string getCurrentTime();
    };
  };
};