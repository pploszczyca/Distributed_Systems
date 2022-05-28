namespace IOT_Client.Services;

public class ObjectListService
{
    private readonly IotServerService _iotServerService;

    public ObjectListService(IotServerService iotServerService)
    {
        _iotServerService = iotServerService;
    }

    public void PrintObjectNames(int port)
    {
        Console.WriteLine("DEVICES LIST:");

        _iotServerService
            .GetObjectInfoServicePrxHelper(port)
            .getObjectInformations()
            .ToList()
            .ForEach(element => Console.WriteLine(element.category + "/" + element.name));
    }
}