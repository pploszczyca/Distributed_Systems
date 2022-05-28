package pl.agh.server.device.lamp;

import com.zeroc.Ice.Current;
import device.lamp.ClassicLamp;
import device.lamp.LampStatus;
import device.lamp.MaxPowerLevel;
import device.lamp.MinPowerLevel;
import device.lamp.PowerLevelOutOfRange;

public class ClassicLampImp implements ClassicLamp {
    private LampStatus lampStatus = LampStatus.OFF;

    private int powerLevel = 100;

    @Override
    public void setLampStatus(LampStatus lampStatus, Current current) {
        this.lampStatus = lampStatus;
    }

    @Override
    public LampStatus getLampStatus(Current current) {
        return lampStatus;
    }

    @Override
    public void setLampPower(int powerLevel, Current current)
            throws PowerLevelOutOfRange {
        if (!isPowerLevelProper(powerLevel)) {
            throw new PowerLevelOutOfRange();
        }
        this.powerLevel = powerLevel;
    }

    @Override
    public int getLampPower(Current current) {
        return Math.min(powerLevel, lampStatus.value());
    }

    private boolean isPowerLevelProper(int powerLevel) {
        return MinPowerLevel.value <= powerLevel &&
                powerLevel <= MaxPowerLevel.value;
    }
}
