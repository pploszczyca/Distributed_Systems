package pl.agh.server.device.fridge;

import com.zeroc.Ice.Current;
import device.fridge.BadDateFormat;
import device.fridge.BasicFridge;
import device.fridge.FreezingLevel;
import device.fridge.NoProduct;
import device.fridge.NoSpace;
import device.fridge.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BasicFridgeImp implements BasicFridge {
    private final double maxFridgeSize;

    private final Map<String, Product> productMap;

    private FreezingLevel freezingLevel;

    private final SimpleDateFormat simpleDateFormat;

    public BasicFridgeImp(double maxFridgeSize) {
        this.maxFridgeSize = maxFridgeSize;
        this.productMap = new HashMap<>();
        this.freezingLevel = FreezingLevel.MIN;
        this.simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Override
    public void addProduct(Product product, Current current)
            throws NoSpace, BadDateFormat {
        if (!isProductCanBeAdded(product)) {
            throw new NoSpace();
        }
        if(!isDateProper(product.expirationDate)) {
            throw new BadDateFormat();
        }

        productMap.put(product.name, product);
    }

    @Override
    public Product takeProductOutByName(String productName, Current current)
            throws NoProduct {
        if (!productMap.containsKey(productName)) {
            throw new NoProduct();
        }
        return productMap.remove(productName);
    }

    @Override
    public Product[] getProductsIn(Current current) {
        return productMap.values().toArray(Product[]::new);
    }

    @Override
    public int numberOfProductsIn(Current current) {
        return productMap.size();
    }

    @Override
    public void setFreezingLevel(FreezingLevel freezingLevel,
                                 Current current) {
        this.freezingLevel = freezingLevel;
    }

    @Override
    public FreezingLevel getFreezingLevel(Current current) {
        return this.freezingLevel;
    }

    private boolean isProductCanBeAdded(Product product) {
        return calculateCurrentSize() + product.size < maxFridgeSize;
    }

    private double calculateCurrentSize() {
        return productMap
                .values()
                .stream()
                .mapToDouble(product -> product.size)
                .sum();
    }

    private boolean isDateProper(String stringDate) {
        try {
            Date date = simpleDateFormat.parse(stringDate);
            Date todayDate = new Date();

            return date.after(todayDate);
        } catch (ParseException e) {
            return false;
        }
    }
}
