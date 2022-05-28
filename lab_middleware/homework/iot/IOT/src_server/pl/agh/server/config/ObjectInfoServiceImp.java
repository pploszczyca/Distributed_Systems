package pl.agh.server.config;

import com.zeroc.Ice.Current;
import config.ObjectInfoService;
import config.ObjectInformation;
import java.util.List;

public class ObjectInfoServiceImp implements ObjectInfoService {
    private final ObjectInformation[] objectInformations;

    public ObjectInfoServiceImp(List<ObjectInformation> objectInformations) {
        this.objectInformations =
                objectInformations.toArray(new ObjectInformation[0]);
    }

    @Override
    public ObjectInformation[] getObjectInformations(Current current) {
        return objectInformations;
    }
}
