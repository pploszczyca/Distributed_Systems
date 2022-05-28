package sr.grpc.proto_imp;

import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import sr.grpc.gen.NumberUtilities;

public class ClientNumberStreamObserver
        implements StreamObserver<NumberUtilities.Number> {
    private static final Logger logger =
            Logger.getLogger(ClientNumberStreamObserver.class.getName());

    @Override
    public void onNext(NumberUtilities.Number number) {
        logger.info("Got squared number: " + number.getValue());
    }

    @Override
    public void onError(Throwable throwable) {
        logger.log(Level.WARNING, "Encountered error: ", throwable);
    }

    @Override
    public void onCompleted() {
        logger.info("Completed number streaming");
    }
}
