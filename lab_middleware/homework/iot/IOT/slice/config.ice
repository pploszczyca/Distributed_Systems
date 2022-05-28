module config {
    struct ObjectInformation {
        string category;
        string name;
    };

    sequence <ObjectInformation> ObjectInformationSequence;

    interface ObjectInfoService {
        ObjectInformationSequence getObjectInformations();
    };
};