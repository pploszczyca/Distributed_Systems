package sr.grpc.server;

import java.io.IOException;

public class SecondTextServer {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        final Server server = new Server(50052, ServerType.TEXT);
        server.run();
    }
}
