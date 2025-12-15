package grpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import api.DataStorageAPI;
import api.ComputeEngineAPI;
import api.UserNetworkAPI;
import implementations.DataStorageImplementation;
import implementations.ComputeEngineImplementation;
import implementations.UserNetworkImplementation;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.protobuf.services.ProtoReflectionService;



public class UserNetworkServer {
	private Server server;
	
	private void start() throws IOException {
		//Initialize components
		DataStorageAPI dataStore = new DataStorageImplementation();
		ComputeEngineAPI computeEngine = new ComputeEngineImplementation();
		UserNetworkAPI userNetwork = new UserNetworkImplementation(dataStore, computeEngine);
		
		int port = 50051;
		
		server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
				.addService(new UserNetworkServiceImplementation(userNetwork))
				.addService(ProtoReflectionService.newInstance())
				.build()
				.start();
		
		System.out.println("Server started on port: "+port);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override 
			public void run() {
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				try {
					if (server != null) {
						server.shutdown().awaitTermination(20, TimeUnit.SECONDS);
					}
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.out.println("*** server shut down");
			}
				
		});
				
	}
	
	private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
          server.awaitTermination();
        }
      }

      public static void main(String[] args) throws Exception {
          UserNetworkServer server = new UserNetworkServer(); 
          server.start();
          server.blockUntilShutdown();
      }
	

}
