package pl.agh.server.device.lamp;

import com.zeroc.Ice.Current;
import device.lamp.ColorTemperature;
import device.lamp.LightTemperatureLamp;

public class LightTemperatureLampImp extends ClassicLampImp implements
        LightTemperatureLamp {
    private ColorTemperature colorTemperature = ColorTemperature.NORMAL;

    @Override
    public void setColorTemperature(ColorTemperature colorTemperature,
                                    Current current) {
        this.colorTemperature = colorTemperature;
    }

    @Override
    public ColorTemperature getColorTemperature(Current current) {
        return colorTemperature;
    }
}
