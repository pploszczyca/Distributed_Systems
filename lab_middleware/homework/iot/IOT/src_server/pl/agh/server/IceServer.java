package pl.agh.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class IceServer {
    private static final int FIRST_ADAPTER_PORT = 10000;
    private static final int SECOND_ADAPTER_PORT = 10001;

    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;
        AdapterUtils adapterUtils = new AdapterUtils();

        try {
            communicator = Util.initialize(args);
            ObjectAdapter firstAdapter = makeAdapter(communicator, FIRST_ADAPTER_PORT);
            ObjectAdapter secondAdapter = makeAdapter(communicator, SECOND_ADAPTER_PORT);

            adapterUtils.addObjectsToFirstAdapter(firstAdapter);
            adapterUtils.addObjectToSecondAdapter(secondAdapter);

            firstAdapter.activate();
            secondAdapter.activate();

            communicator.waitForShutdown();

        } catch (Exception e) {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }

    private static ObjectAdapter makeAdapter(Communicator communicator, int port){
        return communicator.createObjectAdapterWithEndpoints("Adapter" + port,
                String.format("tcp -h 127.0.0.2 -p %d -z : udp -h 127.0.0.2 -p %d -z", port, port));
    }
}
