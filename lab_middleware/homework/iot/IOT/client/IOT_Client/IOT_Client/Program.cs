// See https://aka.ms/new-console-template for more information

using device.fridge;
using device.lamp;
using IOT_Client.Services;

namespace IOT_Client
{
    public static class Program
    {
        public static int Main(string[] args)
        {
            var iotServerService = new IotServerService(args);
            var objectListService = new ObjectListService(iotServerService);
            var weatherStationService = new WeatherStationService(iotServerService);
            var lampService = new LampService(iotServerService);
            var fridgeService = new FridgeService(iotServerService);

            Console.WriteLine(
                "Pass command: <Device Name> <Command> <Args>\nTo print all devices type: list\nFor help type: help\nTo exit type: exit\n");

            while (true)
            {
                Console.Write("> ");
                var line = Console.ReadLine();
                var splitLine = line?.Split();
                var deviceName = splitLine?[0];

                try
                {
                    switch (deviceName)
                    {
                        case { } name when name.Contains("weatherStation"):
                            ParseWeatherStation(weatherStationService, splitLine);
                            break;
                        case { } name when name.Contains("Lamp"):
                            ParseLamp(lampService, splitLine);
                            break;
                        case { } name when name.Contains("fridge"):
                            ParseFridge(fridgeService, splitLine);
                            break;
                        case "list":
                            objectListService.PrintObjectNames();
                            break;
                        case "help":
                            PrintHelp();
                            break;
                        case "exit":
                            return 0;
                        default:
                            Console.WriteLine("Bad Command");
                            break;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }
            }
        }

        private static void ParseWeatherStation(WeatherStationService weatherStationService,
            IReadOnlyList<string>? splitLine)
        {
            var deviceName = splitLine?[0]!;
            var command = splitLine?[1];

            switch (command)
            {
                case "getWeatherInfo":
                    ParseGetWeatherInfo(weatherStationService, deviceName);
                    break;
                case "getCurrentTime":
                    Console.WriteLine(weatherStationService.GetCurrentTime(deviceName));
                    break;
                default:
                    Console.WriteLine("Bad Command");
                    break;
            }
        }

        private static void ParseLamp(LampService lampService, IReadOnlyList<string>? splitLine)
        {
            var deviceName = splitLine?[0]!;
            var command = splitLine?[1];

            switch (command)
            {
                case "setLampStatus":
                    ParseSetLampStatus(lampService, deviceName, splitLine?[2]!);
                    break;
                case "getLampStatus":
                    Console.WriteLine(lampService.GetLampStatus(deviceName));
                    break;
                case "setLampPower":
                    ParseSetLampPower(lampService, deviceName, int.Parse(splitLine?[2]!));
                    break;
                case "getLampPower":
                    Console.WriteLine(lampService.GetLampPower(deviceName));
                    break;
                case "setRgbColor":
                    ParseSetRgbColor(lampService, deviceName, splitLine?[2]!, splitLine?[3]!, splitLine?[4]!);
                    break;
                case "getRgbColor":
                    ParseGetRgbColor(lampService, deviceName);
                    break;
                case "setColorTemperature":
                    ParseSetColorTemperature(lampService,  deviceName, splitLine?[2]!);
                    break;
                case "getColorTemperature":
                    Console.WriteLine(lampService.GetColorTemperature(deviceName));
                    break;
                default:
                    Console.WriteLine("Bad Command");
                    break;
            }
        }

        private static void ParseFridge(FridgeService fridgeService, IReadOnlyList<string>? splitLine)
        {
            var deviceName = splitLine?[0]!;
            var command = splitLine?[1];

            switch (command)
            {
                case "addProduct":
                    ParseAddProduct(fridgeService, deviceName, splitLine?[2]!, splitLine?[3]!, splitLine?[4]!);
                    break;

                case "takeProductOutByName":
                    ParseTakeProductOutByName(fridgeService, deviceName, splitLine?[2]!);
                    break;

                case "getProductsIn":
                    ParseGetProductsIn(fridgeService, deviceName);
                    break;

                case "numberOfProductsIn":
                    Console.WriteLine($"{fridgeService.NumberOfProductsIn(deviceName)}");
                    break;

                case "setFreezingLevel":
                    ParseSetFreezingLevel(fridgeService, deviceName, splitLine?[2]!);
                    break;

                case "getFreezingLevel":
                    Console.WriteLine($"{fridgeService.GetFreezingLevel(deviceName)}");
                    break;
                default:
                    Console.WriteLine("Bad Command");
                    break;
            }
        }

        private static void ParseGetWeatherInfo(WeatherStationService weatherStationService, string deviceName)
        {
            var weatherInfo = weatherStationService.GetWeatherInfo(deviceName);
            Console.WriteLine(
                $"Temperature: {weatherInfo.temperature} C\nHumidity: {weatherInfo.humidity * 100} %\nPressure: {weatherInfo.pressure} hPa");
        }

        private static void ParseSetLampStatus(LampService lampService, string deviceName, string lampStatusString)
        {
            var lampStatus = lampStatusString.Equals("ON") ? LampStatus.ON : LampStatus.OFF;

            lampService.SetLampStatus(deviceName, lampStatus);
        }

        private static void ParseSetLampPower(LampService lampService, string deviceName, int lampPower)
        {
            try
            {
                lampService.SetLampPower(deviceName, lampPower);
            }
            catch (PowerLevelOutOfRange)
            {
                Console.WriteLine("Power level out of range, try again.");
            }
        }

        private static void ParseSetRgbColor(LampService lampService, string deviceName, string red, string green,
            string blue)
        {
            try
            {
                var rgbColor = new RgbColor(int.Parse(red), int.Parse(green),
                    int.Parse(blue));

                lampService.SetRgbColor(deviceName, rgbColor);
            }
            catch (RgbColorIsIncorect)
            {
                Console.WriteLine("RGB Color is incorrect, try again.");
            }
        }

        private static void ParseGetRgbColor(LampService lampService, string deviceName)
        {
            var rgbLamp = lampService.GetRgbColor(deviceName);

            Console.WriteLine($"Red={rgbLamp.red}, Green={rgbLamp.green}, Blue={rgbLamp.blue}");
        }

        private static void ParseSetColorTemperature(LampService lampService, string deviceName, string colorTemperatureString)
        {
            var colorTemperatureInt = int.Parse(colorTemperatureString);
            var colorTemperature = colorTemperatureInt switch
            {
                1000 => ColorTemperature.WARM,
                5500 => ColorTemperature.NORMAL,
                _ => ColorTemperature.COOL
            };

            lampService.SetColorTemperature(deviceName, colorTemperature);
        }

        private static void ParseAddProduct(FridgeService fridgeService, string deviceName, string productName,
            string productSizeString, string productExpirationDate)
        {
            try
            {
                var product = new Product(productName, double.Parse(productSizeString), productExpirationDate);

                fridgeService.AddProduct(deviceName, product);
            }
            catch (NoSpace)
            {
                Console.WriteLine("No space available.");
            }
            catch (BadDateFormat)
            {
                Console.WriteLine("Bad date format.");
            }
        }

        private static void ParseTakeProductOutByName(FridgeService fridgeService, string deviceName,
            string productName)
        {
            try
            {
                var product = fridgeService.TakeProductsByName(deviceName, productName);

                Console.WriteLine(
                    $"Took out this product: name={product.name}, size={product.size}, expirationDate={product.expirationDate}");
            }
            catch (NoProduct)
            {
                Console.WriteLine("Product not found.");
            }
        }

        private static void ParseGetProductsIn(FridgeService fridgeService, string deviceName)
        {
            Console.WriteLine("Products in:");

            fridgeService
                .GetProductsIn(deviceName)
                .ToList()
                .ForEach(product => Console.WriteLine($" - {ProductToString(product)}"));
        }

        private static string ProductToString(Product product)
        {
            return $"name={product.name}, size={product.size}, expirationDate={product.expirationDate}";
        }

        private static void ParseSetFreezingLevel(FridgeService fridgeService, string deviceName,
            string freezingLevelString)
        {
            var freezingLevel = freezingLevelString switch
            {
                "MIN" => FreezingLevel.MIN,
                "AVERAGE" => FreezingLevel.AVERAGE,
                _ => FreezingLevel.MAX
            };

            fridgeService.SetFreezingLevel(deviceName, freezingLevel);
        }

        private static void PrintHelp()
        {
            Console.WriteLine("COMMANDS:");
            
            Console.WriteLine("Fridge:\n" +
                              " - addProduct <Name> <Size> <ExpirationDate>\n" +
                              " - takeProductOutByName <Product Name>\n" +
                              " - getProductsIn\n" +
                              " - numberOfProductsIn\n" +
                              " - setFreezingLevel <MIN/AVERAGE/MAX>\n" +
                              " - getFreezingLevel\n");
            
            Console.WriteLine("Lamp:\n" +
                              " - setLampStatus <ON/OFF>\n" +
                              " - getLampStatus\n" +
                              " - setLampPower <Power 0-100>\n" +
                              " - getLampPower\n" +
                              " - setRgbColor <Red> <Green> <Blue>\n" +
                              " - getRgbColor\n" +
                              " - setColorTemperature <WARM/NORMAL/COOL>\n" +
                              " - getColorTemperature\n");
            
            Console.WriteLine("Weather Station:\n" +
                              " - getWeatherInfo\n" +
                              " - getCurrentTime\n");
        }
    }
}