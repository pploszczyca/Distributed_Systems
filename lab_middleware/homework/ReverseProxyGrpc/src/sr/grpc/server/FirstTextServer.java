package sr.grpc.server;

import java.io.IOException;

public class FirstTextServer {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        final Server server = new Server(50051, ServerType.TEXT);
        server.run();
    }
}

