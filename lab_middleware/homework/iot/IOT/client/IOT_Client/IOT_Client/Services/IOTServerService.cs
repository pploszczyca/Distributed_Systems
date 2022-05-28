using config;
using device.fridge;
using device.lamp;
using device.weather;
using Ice;

namespace IOT_Client.Services;

public class IotServerService
{
    private const string ObjectInfoServiceName = "objectList";
    private string[] _programArgs;

    public IotServerService(string[] programArgs)
    {
        _programArgs = programArgs;
    }

    public ObjectInfoServicePrx GetObjectInfoServicePrxHelper(int port)
    {
        return ObjectInfoServicePrxHelper.checkedCast(GetObjectPrx(ObjectInfoServiceName, ObjectInfoServiceName, port));
    }

    public WeatherStationPrx GetWeatherStationPrx(string categoryAndName, int port)
    {
        return WeatherStationPrxHelper.checkedCast(GetObjectPrx(categoryAndName, port));
    }

    public ClassicLampPrx GetClassicLampPrx(string categoryAndName, int port)
    {
        return ClassicLampPrxHelper.checkedCast(GetObjectPrx(categoryAndName, port));
    }

    public RgbLampPrx GetRgbLampPrx(string categoryAndName, int port)
    {
        return RgbLampPrxHelper.checkedCast(GetObjectPrx(categoryAndName, port));
    }

    public LightTemperatureLampPrx GetLightTemperatureLampPrx(string categoryAndName, int port)
    {
        return LightTemperatureLampPrxHelper.checkedCast(GetObjectPrx(categoryAndName, port));
    }

    public BasicFridgePrx GetBasicFridgePrx(string categoryAndName, int port)
    {
        return BasicFridgePrxHelper.checkedCast(GetObjectPrx(categoryAndName, port));
    }

    private ObjectPrx GetObjectPrx(string categoryAndName, int port)
    {
        var communicator = Util.initialize(ref _programArgs);
        return communicator.stringToProxy($"{categoryAndName}:tcp -h 127.0.0.2 -p {port} -z : udp -h 127.0.0.2 -p {port} -z");
    }

    private ObjectPrx GetObjectPrx(string category, string name, int port)
    {
        return GetObjectPrx($"{category}/{name}", port);
    }
}