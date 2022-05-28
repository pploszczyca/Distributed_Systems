using device.weather;

namespace IOT_Client.Services;

public class WeatherStationService
{
    private readonly IotServerService _iotServerService;
    private const int WeatherStationPort = 11001;

    public WeatherStationService(IotServerService iotServerService)
    {
        _iotServerService = iotServerService;
    }

    public WeatherInfo GetWeatherInfo(string weatherStationName)
    {
        return GetWeatherStation(weatherStationName).getWeatherInfo();
    }

    public string GetCurrentTime(string weatherStationName)
    {
        return GetWeatherStation(weatherStationName).getCurrentTime();
    }

    private WeatherStationPrx GetWeatherStation(string weatherStationName)
    {
        return _iotServerService.GetWeatherStationPrx(weatherStationName, WeatherStationPort);
    }
}