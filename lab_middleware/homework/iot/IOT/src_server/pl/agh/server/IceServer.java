package pl.agh.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import pl.agh.server.utilities.AdapterUtils;

public class IceServer {
    public static final int FIRST_ADAPTER_PORT = 11000;

    public static final int SECOND_ADAPTER_PORT = 11001;

    private final AdapterUtils adapterUtils = new AdapterUtils();

    private int status = 0;

    private final Communicator communicator;

    private final int port;

    public IceServer(int port, String[] args) {
        this.port = port;
        communicator = Util.initialize(args);
    }

    private static ObjectAdapter makeAdapter(Communicator communicator,
                                             int port) {
        return communicator.createObjectAdapterWithEndpoints("Adapter" + port,
                String.format(
                        "tcp -h 127.0.0.2 -p %d -z : udp -h 127.0.0.2 -p %d -z",
                        port, port));
    }

    public void startServer() {
        try {
            initAndRunAdapter();
            communicator.waitForShutdown();
            communicator.destroy();
        } catch (Exception e) {
            printErrorAndSetStatus(e);
        }
        System.exit(status);
    }

    private void initAndRunAdapter() {
        ObjectAdapter adapter = makeAdapter(communicator, port);

        if (port == FIRST_ADAPTER_PORT) {
            adapterUtils.addObjectsToFirstAdapter(adapter);
        } else if (port == SECOND_ADAPTER_PORT) {
            adapterUtils.addObjectToSecondAdapter(adapter);
        }

        adapter.activate();
    }

    private void printErrorAndSetStatus(Exception e) {
        System.err.println(e);
        status = 1;
    }
}
