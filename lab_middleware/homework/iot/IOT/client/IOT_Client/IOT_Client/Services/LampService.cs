using device.lamp;

namespace IOT_Client.Services;

public class LampService
{
    private readonly IotServerService _iotServerService;
    private const int LampPort = 10001;

    public LampService(IotServerService iotServerService)
    {
        _iotServerService = iotServerService;
    }

    public void SetLampStatus(string lampName, LampStatus lampStatus)
    {
        GetClassicLamp(lampName).setLampStatus(lampStatus);
    }

    public LampStatus GetLampStatus(string lampName)
    {
        return GetClassicLamp(lampName).getLampStatus();
    }

    public void SetLampPower(string lampName, int powerLevel)
    {
        GetClassicLamp(lampName).setLampPower(powerLevel);
    }

    public int GetLampPower(string lampName)
    {
        return GetClassicLamp(lampName).getLampPower();
    }

    public void SetRgbColor(string lampName, RgbColor rgbColor)
    {
        GetRgbLamp(lampName).setRgbColor(rgbColor);
    }

    public RgbColor GetRgbColor(string lampName)
    {
        return GetRgbLamp(lampName).getRgbColor();
    }

    public void SetColorTemperature(string lampName, ColorTemperature colorTemperature)
    {
        GetLightTemperatureLamp(lampName).setColorTemperature(colorTemperature);
    }

    public ColorTemperature GetColorTemperature(string lampName)
    {
        return GetLightTemperatureLamp(lampName).getColorTemperature();
    }

    private ClassicLampPrx GetClassicLamp(string lampName)
    {
        return _iotServerService.GetClassicLampPrx(lampName, LampPort);
    }

    private RgbLampPrx GetRgbLamp(string lampName)
    {
        return _iotServerService.GetRgbLampPrx(lampName, LampPort);
    }

    private LightTemperatureLampPrx GetLightTemperatureLamp(string lampName)
    {
        return _iotServerService.GetLightTemperatureLampPrx(lampName, LampPort);
    }
}