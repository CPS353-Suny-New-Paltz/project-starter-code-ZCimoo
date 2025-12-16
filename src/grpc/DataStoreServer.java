package grpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

public class DataStoreServer {
	
	private Server server;
	
	private void start() throws IOException {
		int port = 8001;
		
		server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new DataStoreServiceImplementation()) // Use the class we just made above
                .build()
                .start();
		
		System.out.println("Data Store Server started on port: "+port);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (server != null) {
                        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
	}
	
	private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DataStoreServer server = new DataStoreServer();
        server.start();
        server.blockUntilShutdown();
    }
}


