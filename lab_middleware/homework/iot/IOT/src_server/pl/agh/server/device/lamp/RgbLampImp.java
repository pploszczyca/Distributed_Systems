package pl.agh.server.device.lamp;

import com.zeroc.Ice.Current;
import device.lamp.MaxColorNumber;
import device.lamp.MinColorNumber;
import device.lamp.RgbColor;
import device.lamp.RgbColorIsIncorect;
import device.lamp.RgbLamp;

public class RgbLampImp extends ClassicLampImp implements RgbLamp {
    private RgbColor rgbColor = new RgbColor();

    @Override
    public void setRgbColor(RgbColor rgbColor, Current current)
            throws RgbColorIsIncorect {
        if (!isRgbColorProper(rgbColor)) {
            throw new RgbColorIsIncorect();
        }
        this.rgbColor = rgbColor;
    }

    @Override
    public RgbColor getRgbColor(Current current) {
        return this.rgbColor;
    }

    private boolean isRgbColorProper(RgbColor rgbColor) {
        return isColorProper(rgbColor.red) && isColorProper(rgbColor.green) &&
                isColorProper(rgbColor.blue);
    }

    private boolean isColorProper(int colorValue) {
        return MinColorNumber.value <= colorValue && colorValue <=
                MaxColorNumber.value;
    }
}
