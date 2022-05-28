package pl.agh.server;

public class SecondIceServer {
    public static void main(String[] args) {
        final var server = new IceServer(IceServer.SECOND_ADAPTER_PORT, args);
        server.startServer();
    }
}
