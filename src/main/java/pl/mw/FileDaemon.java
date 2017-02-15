package pl.mw;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.stream.ActorMaterializer;
import akka.stream.Attributes;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;
import akka.stream.javadsl.Tcp;
import akka.stream.javadsl.Tcp.IncomingConnection;
import akka.stream.javadsl.Tcp.ServerBinding;
import akka.util.ByteString;
import akka.util.Timeout;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * Created by mwisniewski.
 */
public class FileDaemon {

    public static void main(String[] args) throws Exception {
        Timeout timeout = Timeout.apply(5L, TimeUnit.SECONDS);
        
        ActorSystem system = ActorSystem.create("System", Configuration.getConfig());
        ActorMaterializer mat = ActorMaterializer.create(system);

        Attributes logLevels = Attributes.logLevels(Logging.InfoLevel(), Logging.InfoLevel(), Logging.InfoLevel());
        final Source<IncomingConnection, CompletionStage<ServerBinding>> connections = Tcp.get(system).bind("127.0.0.1", Configuration.getPort());

        connections.runForeach(connection -> {
            System.out.println("New connection from: " + connection.remoteAddress());

            final Flow<ByteString, ByteString, NotUsed> echo = Flow.of(ByteString.class)
                    .log("input").withAttributes(logLevels)
                    .via(new Decoder()) //TODO: tutaj trzeba zdekodować do FDMessage (HEADER + PAYLOAD)
                    .map(ByteString::utf8String)
                    .map(elem -> (String) elem)
                    .log("output").withAttributes(logLevels)
                    .map(ByteString::fromString);
            
            connection.handleWith(echo, mat);

        }, mat);
    }
    
}
