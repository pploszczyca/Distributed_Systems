package sr.grpc.server;

import java.io.IOException;

public class NumericServer {
    public static void main(String[] args)
            throws IOException, InterruptedException {
        final Server server = new Server(50053, ServerType.NUMERIC);
        server.run();
    }
}
