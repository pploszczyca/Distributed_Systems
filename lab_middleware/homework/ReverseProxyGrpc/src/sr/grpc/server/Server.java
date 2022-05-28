package sr.grpc.server;

import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import sr.grpc.proto_imp.NumberStreamingImp;
import sr.grpc.proto_imp.TextServiceImp;

public class Server {
    private static final Logger logger =
            Logger.getLogger(Server.class.getName());

    private io.grpc.Server server;

    private final int port;

    private final ServerType serverType;

    public Server(int port, ServerType serverType) {
        this.port = port;
        this.serverType = serverType;
    }

    public void run() throws IOException, InterruptedException {
        start();
        blockUntilShutdown();
    }

    private void start() throws IOException {
        var serverBuilder = ServerBuilder.forPort(port);

        serverBuilder = makeServer(serverBuilder);
        server = serverBuilder
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println(
                    "*** shutting down gRPC server since JVM is shutting down");
            try {
                Server.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    private ServerBuilder<?> makeServer(ServerBuilder<?> serverBuilder) {
        return serverType == ServerType.TEXT ? makeTextServer(serverBuilder) :
                makeNumericServer(serverBuilder);
    }

    private ServerBuilder<?> makeTextServer(ServerBuilder<?> serverBuilder) {
        return serverBuilder.addService(new TextServiceImp());
    }

    private ServerBuilder<?> makeNumericServer(ServerBuilder<?> serverBuilder) {
        return serverBuilder.addService(new NumberStreamingImp());
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
