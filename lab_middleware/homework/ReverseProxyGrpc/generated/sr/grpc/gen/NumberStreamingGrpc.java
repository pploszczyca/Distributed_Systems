package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: proto/numbers.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NumberStreamingGrpc {

  private NumberStreamingGrpc() {}

  public static final String SERVICE_NAME = "numbers.NumberStreaming";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.NumberUtilities.Number,
      sr.grpc.gen.NumberUtilities.Number> getNextSquaredNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "nextSquaredNumbers",
      requestType = sr.grpc.gen.NumberUtilities.Number.class,
      responseType = sr.grpc.gen.NumberUtilities.Number.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.NumberUtilities.Number,
      sr.grpc.gen.NumberUtilities.Number> getNextSquaredNumbersMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.NumberUtilities.Number, sr.grpc.gen.NumberUtilities.Number> getNextSquaredNumbersMethod;
    if ((getNextSquaredNumbersMethod = NumberStreamingGrpc.getNextSquaredNumbersMethod) == null) {
      synchronized (NumberStreamingGrpc.class) {
        if ((getNextSquaredNumbersMethod = NumberStreamingGrpc.getNextSquaredNumbersMethod) == null) {
          NumberStreamingGrpc.getNextSquaredNumbersMethod = getNextSquaredNumbersMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.NumberUtilities.Number, sr.grpc.gen.NumberUtilities.Number>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "nextSquaredNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.NumberUtilities.Number.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.NumberUtilities.Number.getDefaultInstance()))
              .setSchemaDescriptor(new NumberStreamingMethodDescriptorSupplier("nextSquaredNumbers"))
              .build();
        }
      }
    }
    return getNextSquaredNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NumberStreamingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumberStreamingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumberStreamingStub>() {
        @java.lang.Override
        public NumberStreamingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumberStreamingStub(channel, callOptions);
        }
      };
    return NumberStreamingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NumberStreamingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumberStreamingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumberStreamingBlockingStub>() {
        @java.lang.Override
        public NumberStreamingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumberStreamingBlockingStub(channel, callOptions);
        }
      };
    return NumberStreamingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NumberStreamingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumberStreamingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumberStreamingFutureStub>() {
        @java.lang.Override
        public NumberStreamingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumberStreamingFutureStub(channel, callOptions);
        }
      };
    return NumberStreamingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class NumberStreamingImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<sr.grpc.gen.NumberUtilities.Number> nextSquaredNumbers(
        io.grpc.stub.StreamObserver<sr.grpc.gen.NumberUtilities.Number> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getNextSquaredNumbersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNextSquaredNumbersMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                sr.grpc.gen.NumberUtilities.Number,
                sr.grpc.gen.NumberUtilities.Number>(
                  this, METHODID_NEXT_SQUARED_NUMBERS)))
          .build();
    }
  }

  /**
   */
  public static final class NumberStreamingStub extends io.grpc.stub.AbstractAsyncStub<NumberStreamingStub> {
    private NumberStreamingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumberStreamingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumberStreamingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<sr.grpc.gen.NumberUtilities.Number> nextSquaredNumbers(
        io.grpc.stub.StreamObserver<sr.grpc.gen.NumberUtilities.Number> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getNextSquaredNumbersMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class NumberStreamingBlockingStub extends io.grpc.stub.AbstractBlockingStub<NumberStreamingBlockingStub> {
    private NumberStreamingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumberStreamingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumberStreamingBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class NumberStreamingFutureStub extends io.grpc.stub.AbstractFutureStub<NumberStreamingFutureStub> {
    private NumberStreamingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumberStreamingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumberStreamingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_NEXT_SQUARED_NUMBERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NumberStreamingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NumberStreamingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NEXT_SQUARED_NUMBERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.nextSquaredNumbers(
              (io.grpc.stub.StreamObserver<sr.grpc.gen.NumberUtilities.Number>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NumberStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NumberStreamingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.NumberUtilities.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NumberStreaming");
    }
  }

  private static final class NumberStreamingFileDescriptorSupplier
      extends NumberStreamingBaseDescriptorSupplier {
    NumberStreamingFileDescriptorSupplier() {}
  }

  private static final class NumberStreamingMethodDescriptorSupplier
      extends NumberStreamingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NumberStreamingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NumberStreamingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NumberStreamingFileDescriptorSupplier())
              .addMethod(getNextSquaredNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
