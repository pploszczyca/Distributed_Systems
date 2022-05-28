package sr.grpc.proto_imp;

import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import sr.grpc.gen.NumberUtilities;

public class NumberStreamObserver
        implements StreamObserver<NumberUtilities.Number> {
    private final StreamObserver<NumberUtilities.Number> responseObserver;

    private static final Logger logger =
            Logger.getLogger(NumberStreamObserver.class.getName());

    public NumberStreamObserver(
            StreamObserver<NumberUtilities.Number> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(NumberUtilities.Number number) {
        final var numberValue = number.getValue();
        final var result = numberValue * numberValue;
        final var numberResponse = NumberUtilities.Number
                .newBuilder()
                .setValue(result).build();

        logger.info(numberValue + "*" + numberValue + "=" + result);

        responseObserver.onNext(numberResponse);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.log(Level.WARNING, "Encountered error: ", throwable);
    }

    @Override
    public void onCompleted() {
        responseObserver.onCompleted();
    }
}
