package sr.grpc.proto_imp;

import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;
import sr.grpc.gen.TextServiceGrpc;
import sr.grpc.gen.TextUtilities;
import sr.grpc.gen.TextUtilities.CountingOutput;

public class TextServiceImp extends TextServiceGrpc.TextServiceImplBase {
    private static final Logger logger =
            Logger.getLogger(TextServiceImp.class.getName());
    @Override
    public void countRepeats(TextUtilities.CountingArguments request,
                             StreamObserver<TextUtilities.CountingOutput> responseObserver) {
        final var countedRepeats = countRepeatsInText(request.getInputText(),
                request.getTextToFind());
        final var result = CountingOutput
                .newBuilder()
                .setCountedRepeats(countedRepeats)
                .build();

        logger.info("For: " + request.getInputText() + " , " + request.getTextToFind() + " the result is " + countedRepeats);

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    private int countRepeatsInText(String inputText, String textToFind) {
        return inputText.split(textToFind, -1).length - 1;
    }
}
