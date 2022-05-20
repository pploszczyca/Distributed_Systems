# Reactive-Streams
## Task 1
### Stop
```
14:03:35.438 [helloActor-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
14:03:35.547 [actorMath-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
received message: hello world
math main: actor system ready
actorMath: received command: add
actorMath: add result = 8
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMultiply: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: multiply result = 15
actorDivide: received command: divide
actorMultiply: sending response
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorMultiply: multiply result = 10
actorMultiply: sending response
actorDivide: divide result = 5
actorDivide: sending response
actorDivide: received command: divide
actorMath: received result: 15
actorDivide: divide result = 3
actorDivide: sending response
actorMath: received result: 10
actorDivide: received command: divide
actorMath: received result: 5
actorMath: received result: 3
14:03:35.591 [actorMath-akka.actor.default-dispatcher-6] ERROR akka.actor.typed.Behavior$ - Supervisor StopSupervisor saw failure: / by zero
java.lang.ArithmeticException: / by zero
	at edu.agh.reactive.math.MathActorDivide.onMathCommandDivide(MathActorDivide.java:32)
	at akka.actor.typed.javadsl.BuiltReceive.receive(ReceiveBuilder.scala:213)
	at akka.actor.typed.javadsl.BuiltReceive.receiveMessage(ReceiveBuilder.scala:204)
	at akka.actor.typed.javadsl.Receive.receive(Receive.scala:53)
	at akka.actor.typed.javadsl.AbstractBehavior.receive(AbstractBehavior.scala:64)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.InterceptorImpl$$anon$2.apply(InterceptorImpl.scala:57)
	at akka.actor.typed.internal.SimpleSupervisor.aroundReceive(Supervision.scala:131)
	at akka.actor.typed.internal.InterceptorImpl.receive(InterceptorImpl.scala:85)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.adapter.ActorAdapter.handleMessage(ActorAdapter.scala:131)
	at akka.actor.typed.internal.adapter.ActorAdapter.aroundReceive(ActorAdapter.scala:107)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:580)
	at akka.actor.ActorCell.invoke(ActorCell.scala:548)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:270)
	at akka.dispatch.Mailbox.run(Mailbox.scala:231)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:243)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:295)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1016)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1665)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1598)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also http://www.slf4j.org/codes.html#replay
Math main: sending second package of messages
Math main: messages send
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorMath: received command: multiply
actorMultiply: multiply result = 15
actorMath: sending to actorMultiply
actorMultiply: sending response
actorMultiply: received command: multiply
actorMultiply: multiply result = 10
actorMultiply: sending response
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received result: 15
actorMath: received result: 10
14:03:37.589 [actorMath-akka.actor.default-dispatcher-7] INFO akka.actor.LocalActorRef - Message [edu.agh.reactive.math.MathActor$MathCommandDivide] to Actor[akka://actorMath/user/actorDivide#-356886726] was not delivered. [1] dead letters encountered. If this is not an expected behavior then Actor[akka://actorMath/user/actorDivide#-356886726] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
14:03:37.592 [actorMath-akka.actor.default-dispatcher-7] INFO akka.actor.LocalActorRef - Message [edu.agh.reactive.math.MathActor$MathCommandDivide] to Actor[akka://actorMath/user/actorDivide#-356886726] was not delivered. [2] dead letters encountered. If this is not an expected behavior then Actor[akka://actorMath/user/actorDivide#-356886726] may have terminated unexpectedly. This logging can be turned off or adjusted with configuration settings 'akka.log-dead-letters' and 'akka.log-dead-letters-during-shutdown'.
14:03:37.593 [streams-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
>>> Press ENTER to exit <<<
```

### Resume
```
14:04:04.135 [helloActor-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
14:04:04.280 [actorMath-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
received message: hello world
math main: actor system ready
actorMath: received command: add
actorMath: add result = 8
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorMultiply: multiply result = 15
actorDivide: received command: divide
actorMultiply: sending response
actorMultiply: received command: multiply
actorDivide: divide result = 5
actorDivide: sending response
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: multiply result = 10
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorDivide: received command: divide
actorDivide: divide result = 3
actorDivide: sending response
actorMath: received result: 15
actorMultiply: sending response
actorDivide: received command: divide
actorMath: received result: 5
actorMath: received result: 3
actorMath: received result: 10
14:04:04.342 [actorMath-akka.actor.default-dispatcher-8] ERROR akka.actor.typed.Behavior$ - Supervisor ResumeSupervisor saw failure: / by zero
java.lang.ArithmeticException: / by zero
	at edu.agh.reactive.math.MathActorDivide.onMathCommandDivide(MathActorDivide.java:32)
	at akka.actor.typed.javadsl.BuiltReceive.receive(ReceiveBuilder.scala:213)
	at akka.actor.typed.javadsl.BuiltReceive.receiveMessage(ReceiveBuilder.scala:204)
	at akka.actor.typed.javadsl.Receive.receive(Receive.scala:53)
	at akka.actor.typed.javadsl.AbstractBehavior.receive(AbstractBehavior.scala:64)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.InterceptorImpl$$anon$2.apply(InterceptorImpl.scala:57)
	at akka.actor.typed.internal.SimpleSupervisor.aroundReceive(Supervision.scala:131)
	at akka.actor.typed.internal.InterceptorImpl.receive(InterceptorImpl.scala:85)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.adapter.ActorAdapter.handleMessage(ActorAdapter.scala:131)
	at akka.actor.typed.internal.adapter.ActorAdapter.aroundReceive(ActorAdapter.scala:107)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:580)
	at akka.actor.ActorCell.invoke(ActorCell.scala:548)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:270)
	at akka.dispatch.Mailbox.run(Mailbox.scala:231)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:243)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:295)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1016)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1665)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1598)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also http://www.slf4j.org/codes.html#replay
Math main: sending second package of messages
Math main: messages send
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorMath: received command: multiply
actorMultiply: multiply result = 15
actorMultiply: sending response
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorDivide: received command: divide
actorMultiply: multiply result = 10
actorMultiply: sending response
actorMath: received result: 15
actorDivide: divide result = 5
actorDivide: sending response
actorMath: received result: 10
actorDivide: received command: divide
actorMath: received result: 5
actorDivide: divide result = 3
actorDivide: sending response
actorMath: received result: 3
14:04:06.332 [streams-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
>>> Press ENTER to exit <<<

```

### Restart
```
14:04:40.150 [helloActor-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
14:04:40.267 [actorMath-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
received message: hello world
math main: actor system ready
actorMath: received command: add
actorMath: add result = 8
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorDivide: received command: divide
actorMultiply: received command: multiply
actorDivide: divide result = 5
actorDivide: sending response
actorDivide: received command: divide
actorMultiply: multiply result = 15
actorDivide: divide result = 3
actorDivide: sending response
actorMultiply: sending response
actorDivide: received command: divide
actorMath: received result: 5
actorMultiply: received command: multiply
actorMath: received result: 3
actorMultiply: multiply result = 10
actorMultiply: sending response
actorMath: received result: 15
actorMath: received result: 10
14:04:40.321 [actorMath-akka.actor.default-dispatcher-7] ERROR akka.actor.typed.Behavior$ - Supervisor RestartSupervisor saw failure: / by zero
java.lang.ArithmeticException: / by zero
	at edu.agh.reactive.math.MathActorDivide.onMathCommandDivide(MathActorDivide.java:32)
	at akka.actor.typed.javadsl.BuiltReceive.receive(ReceiveBuilder.scala:213)
	at akka.actor.typed.javadsl.BuiltReceive.receiveMessage(ReceiveBuilder.scala:204)
	at akka.actor.typed.javadsl.Receive.receive(Receive.scala:53)
	at akka.actor.typed.javadsl.AbstractBehavior.receive(AbstractBehavior.scala:64)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.InterceptorImpl$$anon$2.apply(InterceptorImpl.scala:57)
	at akka.actor.typed.internal.RestartSupervisor.aroundReceive(Supervision.scala:275)
	at akka.actor.typed.internal.InterceptorImpl.receive(InterceptorImpl.scala:85)
	at akka.actor.typed.Behavior$.interpret(Behavior.scala:274)
	at akka.actor.typed.Behavior$.interpretMessage(Behavior.scala:230)
	at akka.actor.typed.internal.adapter.ActorAdapter.handleMessage(ActorAdapter.scala:131)
	at akka.actor.typed.internal.adapter.ActorAdapter.aroundReceive(ActorAdapter.scala:107)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:580)
	at akka.actor.ActorCell.invoke(ActorCell.scala:548)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:270)
	at akka.dispatch.Mailbox.run(Mailbox.scala:231)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:243)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:295)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1016)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1665)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1598)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
SLF4J: A number (1) of logging calls during the initialization phase have been intercepted and are
SLF4J: now being replayed. These are subject to the filtering rules of the underlying logging system.
SLF4J: See also http://www.slf4j.org/codes.html#replay
Math main: sending second package of messages
Math main: messages send
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMath: received command: multiply
actorMultiply: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: multiply result = 15
actorMultiply: sending response
actorMath: received command: multiply
actorMath: sending to actorMultiply
actorMultiply: received command: multiply
actorMath: received command: multiply
actorDivide: received command: divide
actorMath: sending to actorMultiply
actorMultiply: multiply result = 10
actorMultiply: sending response
actorDivide: divide result = 5
actorDivide: sending response
actorMath: received result: 15
actorDivide: received command: divide
actorDivide: divide result = 3
actorDivide: sending response
actorMath: received result: 10
actorMath: received result: 5
actorMath: received result: 3
14:04:42.311 [streams-akka.actor.default-dispatcher-3] INFO akka.event.slf4j.Slf4jLogger - Slf4jLogger started
>>> Press ENTER to exit <<<
```

## Task 2
### backpressure()
```
Value in processing: 1
Value in processing: 2
Value in processing: 3
Value in processing: 4
Value in processing: 5
Value in processing: 6
Value in processing: 7
Value in processing: 8
Value in processing: 9
Value in processing: 10
Value in processing: 11
Value in processing: 12
Value in processing: 13
Value in processing: 14
Value in processing: 15
Value in processing: 16
Value in processing: 17
Value in processing: 18
Value in processing: 19
Value in processing: 20
```

### dropTail()
```
Value in processing: 1
Value in processing: 2
Value in processing: 3
Value in processing: 4
Value in processing: 5
Value in processing: 6
Value in processing: 7
Value in processing: 8
Value in processing: 9
Value in processing: 10
Value in processing: 11
Value in processing: 12
Value in processing: 13
Value in processing: 14
Value in processing: 15
Value in processing: 20
```

### dropHead()
```
Value in processing: 5
Value in processing: 6
Value in processing: 7
Value in processing: 8
Value in processing: 9
Value in processing: 10
Value in processing: 11
Value in processing: 12
Value in processing: 13
Value in processing: 14
Value in processing: 15
Value in processing: 16
Value in processing: 17
Value in processing: 18
Value in processing: 19
Value in processing: 20
```

### fail()
```
14:19:54.585 [streams-akka.actor.default-dispatcher-7] ERROR akka.stream.impl.fusing.Buffer - Failing because buffer is full and overflowStrategy is: [Fail] in stream [class akka.stream.impl.fusing.Buffer$$anon$26]
```

## Task 3
```java
final Source<Integer, NotUsed> in = Source.from(List.of(1, 2, 3, 4, 5));
final Sink<List<String>, CompletionStage<List<String>>> sink = Sink.head();
final Flow<Integer, Integer, NotUsed> addOneFlow = Flow.of(Integer.class).map(value -> value + 1);
final Flow<Integer, Integer, NotUsed> multiplyByTenFlow = Flow.of(Integer.class).map(value -> value * 10);
final Flow<Integer, String, NotUsed> intToStringFlow = Flow.fromFunction(Object::toString);

final RunnableGraph<CompletionStage<List<String>>> result = RunnableGraph.fromGraph(
        GraphDSL
            .create(
                sink,
                (builder, out) -> {
                    final UniformFanOutShape<Integer, Integer> broadcast = builder.add(Broadcast.create(2));
                    final UniformFanInShape<Integer, Integer> zip = builder.add(Merge.create(2));
                    
                    final Outlet<Integer> source = builder.add(in).out();
                    
                            builder
                            .from(source)
                            .viaFanOut(broadcast)
                            .via(builder.add(addOneFlow))
                            .viaFanIn(zip)
                            .via(builder.add(intToStringFlow.grouped(1000)))
                            .to(out);
                            builder.from(broadcast).via(builder.add(multiplyByTenFlow)).toFanIn(zip);
                            return ClosedShape.getInstance();
                }
            )
        );

RunnableGraph
    .fromGraph(result)
    .run(materializer)
    .thenAccept(list -> list.forEach(System.out::println));
```