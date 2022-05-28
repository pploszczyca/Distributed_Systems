package sr.grpc.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import sr.grpc.gen.NumberStreamingGrpc;
import sr.grpc.gen.NumberUtilities;
import sr.grpc.gen.TextServiceGrpc;
import sr.grpc.gen.TextUtilities;
import sr.grpc.proto_imp.ClientNumberStreamObserver;

public class Client {
    private static final Logger logger =
            Logger.getLogger(Client.class.getName());

    private final TextServiceGrpc.TextServiceBlockingStub blockingStub;

    private final NumberStreamingGrpc.NumberStreamingStub numberStreamingStub;

    private final Scanner scanner = new Scanner(System.in);

    public Client(Channel channel) {
        blockingStub = TextServiceGrpc.newBlockingStub(channel);
        numberStreamingStub = NumberStreamingGrpc.newStub(channel);
    }

    public void runLoop() {
        var isLoopRunning = true;

        printCommand();

        while (isLoopRunning) {
            final var splitLine = scanner.nextLine().split(" ");
            final var command = splitLine[0];

            switch (command) {
                case "countRepeats" ->
                        sendCountingMessage(splitLine[1], splitLine[2]);
                case "nextSquaredNumbers" ->
                        sendNumbersMessage(parseSplitLineToDouble(splitLine));
                case "exit" -> {
                    logger.info("Exit");
                    isLoopRunning = false;
                }
                default -> System.out.println("Bad command");
            }
        }
    }

    private void printCommand() {
        System.out.println("""
                COMMANDS:
                - countRepeats <inputText> <textToFind>
                - nextSquaredNumbers <number 1> <number 2> ...
                - exit""");
    }

    private void sendCountingMessage(String inputText, String textToFind) {
        var request = TextUtilities
                .CountingArguments
                .newBuilder()
                .setInputText(inputText)
                .setTextToFind(textToFind)
                .build();

        try {
            final var response = blockingStub.countRepeats(request);
            logger.info("Result: " + response.getCountedRepeats());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }

    private List<Double> parseSplitLineToDouble(String[] splitLine) {
        return Arrays
                .stream(splitLine)
                .toList()
                .subList(1, splitLine.length)
                .stream()
                .map(Double::parseDouble)
                .toList();
    }

    private void sendNumbersMessage(List<Double> doubles) {
        var requestObserver = numberStreamingStub.nextSquaredNumbers(
                new ClientNumberStreamObserver());

        try {
            var numberList = doubles.stream().map(this::makeNumber);

            numberList.forEach(number -> {
                logger.info("Sent: " + number.getValue());
                requestObserver.onNext(number);
            });

        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();
    }

    private NumberUtilities.Number makeNumber(double value) {
        return NumberUtilities.Number.newBuilder().setValue(value).build();
    }

    public static void main(String[] args) throws Exception {
        final var target = "localhost:10000";
        final var awaitTerminationTime = 5;
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();

        try {
            Client client = new Client(channel);
            client.runLoop();
        } finally {
            channel.awaitTermination(awaitTerminationTime, TimeUnit.SECONDS);
        }
    }
}
