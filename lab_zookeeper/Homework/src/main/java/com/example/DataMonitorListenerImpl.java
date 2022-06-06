package com.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class DataMonitorListenerImpl
        implements DataMonitorListener {

    private Optional<Process> childProcess = Optional.empty();

    @Override
    public void exists(byte[] data) {
        if(data == null) {
            childProcess.ifPresent(process -> {
                System.out.println("Killing process");
                process.destroy();

                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                }
            });
            childProcess = Optional.empty();
        } else {
            childProcess.ifPresent(process -> {
                System.out.println("Stopping child");
                process.destroy();

                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            System.out.println(Arrays.toString(data));

            try {
                System.out.println("Starting child");
                childProcess = Optional.of(Runtime.getRuntime().exec("gedit"));
                new StreamWriter(childProcess.get().getInputStream(), System.out);
                new StreamWriter(childProcess.get().getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closing(int rc) {

    }
}
