package sr.grpc.proto_imp;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.NumberStreamingGrpc;
import sr.grpc.gen.NumberUtilities;

public class NumberStreamingImp extends
        NumberStreamingGrpc.NumberStreamingImplBase {
    @Override
    public StreamObserver<NumberUtilities.Number> nextSquaredNumbers(
            StreamObserver<NumberUtilities.Number> responseObserver) {
        return new NumberStreamObserver(responseObserver);
    }
}
