using device.fridge;

namespace IOT_Client.Services;

public class FridgeService
{
    private readonly IotServerService _iotServerService;
    private const int FridgePort = 11000;

    public FridgeService(IotServerService iotServerService)
    {
        _iotServerService = iotServerService;
    }

    public void AddProduct(string fridgeName, Product product)
    {
        GetFridge(fridgeName).addProduct(product);
    }

    public Product TakeProductsByName(string fridgeName, string productName)
    {
        return GetFridge(fridgeName).takeProductOutByName(productName);
    }

    public Product[] GetProductsIn(string fridgeName)
    {
        return GetFridge(fridgeName).getProductsIn();
    }

    public int NumberOfProductsIn(string fridgeName)
    {
        return GetFridge(fridgeName).numberOfProductsIn();
    }

    public void SetFreezingLevel(string fridgeName, FreezingLevel freezingLevel)
    {
        GetFridge(fridgeName).setFreezingLevel(freezingLevel);
    }

    public FreezingLevel GetFreezingLevel(string fridgeName)
    {
        return GetFridge(fridgeName).getFreezingLevel();
    }

    private BasicFridgePrx GetFridge(string fridgeName)
    {
        return _iotServerService.GetBasicFridgePrx(fridgeName, FridgePort);
    }
}