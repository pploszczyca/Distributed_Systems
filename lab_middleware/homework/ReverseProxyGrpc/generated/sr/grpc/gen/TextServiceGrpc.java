package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: proto/text.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TextServiceGrpc {

  private TextServiceGrpc() {}

  public static final String SERVICE_NAME = "text.TextService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.TextUtilities.CountingArguments,
      sr.grpc.gen.TextUtilities.CountingOutput> getCountRepeatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CountRepeats",
      requestType = sr.grpc.gen.TextUtilities.CountingArguments.class,
      responseType = sr.grpc.gen.TextUtilities.CountingOutput.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.TextUtilities.CountingArguments,
      sr.grpc.gen.TextUtilities.CountingOutput> getCountRepeatsMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.TextUtilities.CountingArguments, sr.grpc.gen.TextUtilities.CountingOutput> getCountRepeatsMethod;
    if ((getCountRepeatsMethod = TextServiceGrpc.getCountRepeatsMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getCountRepeatsMethod = TextServiceGrpc.getCountRepeatsMethod) == null) {
          TextServiceGrpc.getCountRepeatsMethod = getCountRepeatsMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.TextUtilities.CountingArguments, sr.grpc.gen.TextUtilities.CountingOutput>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CountRepeats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.TextUtilities.CountingArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.TextUtilities.CountingOutput.getDefaultInstance()))
              .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("CountRepeats"))
              .build();
        }
      }
    }
    return getCountRepeatsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TextServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServiceStub>() {
        @java.lang.Override
        public TextServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServiceStub(channel, callOptions);
        }
      };
    return TextServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TextServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServiceBlockingStub>() {
        @java.lang.Override
        public TextServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServiceBlockingStub(channel, callOptions);
        }
      };
    return TextServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TextServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TextServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TextServiceFutureStub>() {
        @java.lang.Override
        public TextServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TextServiceFutureStub(channel, callOptions);
        }
      };
    return TextServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TextServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void countRepeats(sr.grpc.gen.TextUtilities.CountingArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.TextUtilities.CountingOutput> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCountRepeatsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCountRepeatsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                sr.grpc.gen.TextUtilities.CountingArguments,
                sr.grpc.gen.TextUtilities.CountingOutput>(
                  this, METHODID_COUNT_REPEATS)))
          .build();
    }
  }

  /**
   */
  public static final class TextServiceStub extends io.grpc.stub.AbstractAsyncStub<TextServiceStub> {
    private TextServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServiceStub(channel, callOptions);
    }

    /**
     */
    public void countRepeats(sr.grpc.gen.TextUtilities.CountingArguments request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.TextUtilities.CountingOutput> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCountRepeatsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TextServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TextServiceBlockingStub> {
    private TextServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public sr.grpc.gen.TextUtilities.CountingOutput countRepeats(sr.grpc.gen.TextUtilities.CountingArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCountRepeatsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TextServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TextServiceFutureStub> {
    private TextServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TextServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.TextUtilities.CountingOutput> countRepeats(
        sr.grpc.gen.TextUtilities.CountingArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCountRepeatsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COUNT_REPEATS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TextServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TextServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COUNT_REPEATS:
          serviceImpl.countRepeats((sr.grpc.gen.TextUtilities.CountingArguments) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.TextUtilities.CountingOutput>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TextServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.TextUtilities.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TextService");
    }
  }

  private static final class TextServiceFileDescriptorSupplier
      extends TextServiceBaseDescriptorSupplier {
    TextServiceFileDescriptorSupplier() {}
  }

  private static final class TextServiceMethodDescriptorSupplier
      extends TextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TextServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TextServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TextServiceFileDescriptorSupplier())
              .addMethod(getCountRepeatsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
