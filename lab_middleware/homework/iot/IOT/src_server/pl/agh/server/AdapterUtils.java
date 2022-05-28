package pl.agh.server;

import com.zeroc.Ice.Identity;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ObjectAdapter;
import config.ObjectInfoService;
import config.ObjectInformation;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.util.Pair;
import pl.agh.server.config.ObjectInfoServiceImp;
import pl.agh.server.device.fridge.BasicFridgeImp;
import pl.agh.server.device.lamp.ClassicLampImp;
import pl.agh.server.device.lamp.LightTemperatureLampImp;
import pl.agh.server.device.lamp.RgbLampImp;
import pl.agh.server.device.weather.WeatherStationImp;

public class AdapterUtils {
    private static final double MAX_FRIDGE_SIZE = 20.0;

    private static final int FRIDGE_AMOUNT = 5;

    private static final int LAMP_AMOUNT = 3;

    private static final int WEATHER_STATION_AMOUNT = 2;

    private final List<Identity> identityList = new LinkedList<>();

    public void addObjectsToFirstAdapter(ObjectAdapter adapter) {
        addListToAdapter(adapter, getBasicFridgeList(), "fridge");
        addListToAdapter(adapter, getWeatherStationList(), "weatherStation");
        addObjectInfoService(adapter);
    }

    public void addObjectToSecondAdapter(ObjectAdapter adapter) {
        addListToAdapter(adapter, getClassicLampList(), "classicLamp");
        addListToAdapter(adapter, getLightTemperatureLampList(), "tempLamp");
        addListToAdapter(adapter, getRgbLampList(), "rgbLamp");
        addObjectInfoService(adapter);
    }

    private void addListToAdapter(ObjectAdapter adapter,
                                  List<Pair<Integer, Object>> pairList,
                                  String name) {
        pairList.forEach(
                integerObjectPair -> {
                    final var identity =
                            new Identity(name + integerObjectPair.getKey(),
                                    name);

                    adapter.add(integerObjectPair.getValue(), identity);
                    identityList.add(identity);
                });
    }

    private List<Pair<Integer, Object>> getBasicFridgeList() {
        return IntStream
                .range(0, FRIDGE_AMOUNT)
                .mapToObj(
                        i -> new Pair<>(i,
                                (Object) new BasicFridgeImp(MAX_FRIDGE_SIZE)))
                .toList();
    }

    private List<Pair<Integer, Object>> getClassicLampList() {
        return IntStream
                .range(0, LAMP_AMOUNT)
                .mapToObj(i -> new Pair<>(i, (Object) new ClassicLampImp()))
                .toList();
    }

    private List<Pair<Integer, Object>> getLightTemperatureLampList() {
        return IntStream
                .range(0, LAMP_AMOUNT)
                .mapToObj(i -> new Pair<>(i,
                        (Object) new LightTemperatureLampImp()))
                .toList();
    }

    private List<Pair<Integer, Object>> getRgbLampList() {
        return IntStream
                .range(0, LAMP_AMOUNT)
                .mapToObj(i -> new Pair<>(i, (Object) new RgbLampImp()))
                .toList();
    }

    private List<Pair<Integer, Object>> getWeatherStationList() {
        return IntStream
                .range(0, WEATHER_STATION_AMOUNT)
                .mapToObj(i -> new Pair<>(i, (Object) new WeatherStationImp()))
                .toList();
    }

    public List<ObjectInformation> getIdentityList() {
        return identityList
                .stream()
                .map(identity -> new ObjectInformation(identity.category,
                        identity.name))
                .toList();
    }

    private void addObjectInfoService(ObjectAdapter adapter) {
        adapter.add(getObjectInfoService(),
                new Identity("objectList", "objectList"));
    }

    private ObjectInfoService getObjectInfoService() {
        return new ObjectInfoServiceImp(getIdentityList());
    }
}
