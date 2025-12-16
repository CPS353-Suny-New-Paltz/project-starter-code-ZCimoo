package client;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import grpc.ComputationRequest;
import grpc.ComputationResponse;
import grpc.UserNetworkServiceGrpc;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;

public class UserNetworkClient {
	
	private final UserNetworkServiceGrpc.UserNetworkServiceBlockingStub blockingStub;
	
	public UserNetworkClient(ManagedChannel channel) {
		
		blockingStub = UserNetworkServiceGrpc.newBlockingStub(channel);
		
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Network Compute Client");
			
			//ask for input path
			System.out.println("Enter input path : ");
			String inputPath = scanner.nextLine().trim();
			
			//ask for output path
			System.out.println("Enter output path: ");
			String outputPath = scanner.nextLine().trim();
			
			//ask for delimiter
			System.out.println("Enter delimiter or, press Enter for default ',': ");
			String delimiter = scanner.nextLine().trim();
			if(delimiter.isEmpty()) {
				delimiter = ","; //sets default delimiter
			}
			
			System.out.println("Sending request to server...");
			
			//Create request
			ComputationRequest request = ComputationRequest.newBuilder()
					.setInputPath(inputPath)
					.setOutputPath(outputPath)
					.setDelimiter(delimiter)
					.build();
			
			//send request and receive resposne
			
			ComputationResponse response;
			
			try {
				response = blockingStub.submitRequest(request);
			} catch (StatusRuntimeException e) {
				System.err.println("RPC Failed: "+e.getStatus());
				return;
			}
			
			System.out.println("\nResult");
			if(response.getSuccess()) {
				System.out.println("Status: Success");
				System.out.println("Code: "+response.getStatusCode());
				System.out.println("Check output file for results!");
			} else {
				System.out.println("Status: Failed");
				System.out.println("Error Message: "+response.getErrorMessage());
			}
			
			
		} finally {
			scanner.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
        
        String target = "127.0.0.1:8000";
        
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        try {
            UserNetworkClient client = new UserNetworkClient(channel);
            client.run();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}
