module device {
    module lamp {
        enum LampStatus {ON=100, OFF=0};

        const int MinPowerLevel = 0;
        const int MaxPowerLevel = 100;

        exception PowerLevelOutOfRange {};
        exception RgbColorIsIncorect {};

        interface ClassicLamp {
          void setLampStatus(LampStatus lampStatus);
          LampStatus getLampStatus();
          void setLampPower(int powerLevel) throws PowerLevelOutOfRange;
          int getLampPower();
        };

        const int MinColorNumber = 0;
        const int MaxColorNumber = 255;

        struct RgbColor {
            int red;
            int green;
            int blue;
        };

        interface RgbLamp extends ClassicLamp {
            void setRgbColor(RgbColor rgbColor) throws RgbColorIsIncorect;
            RgbColor getRgbColor();
        };

        enum ColorTemperature {
            WARM=1000,
            NORMAL=5500,
            COOL=10000
        };

        interface LightTemperatureLamp extends ClassicLamp {
            void setColorTemperature(ColorTemperature colorTemperature);
            ColorTemperature getColorTemperature();
        };
    };
};