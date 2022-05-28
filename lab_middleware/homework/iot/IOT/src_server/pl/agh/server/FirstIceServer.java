package pl.agh.server;

public class FirstIceServer {
    public static void main(String[] args) {
        final var server = new IceServer(IceServer.FIRST_ADAPTER_PORT, args);
        server.startServer();
    }
}
