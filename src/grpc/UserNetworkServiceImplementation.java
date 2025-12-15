package grpc;

import api.UserNetworkAPI;
import grpc.UserNetworkServiceGrpc;
import grpc.ComputationRequest;
import grpc.ComputationResponse;
import io.grpc.stub.StreamObserver;

public class UserNetworkServiceImplementation extends UserNetworkServiceGrpc.UserNetworkServiceImplBase{
	
	private final UserNetworkAPI userNetwork;
	
	public UserNetworkServiceImplementation(UserNetworkAPI userNetwork) {
		this.userNetwork = userNetwork;
	}
	
	@Override
	public void submitRequest(ComputationRequest request, StreamObserver<ComputationResponse> responseObserver) {
		ComputationResponse response;
		
		try {
			System.out.println("GRPC: Received request for input: "+request.getInputPath());
			
			api.ComputationRequest internalRequest = new api.ComputationRequest(
					request.getInputPath(),
					request.getOutputPath(),
					request.getDelimiter()
			);
			
			api.ComputationResponse internalResponse = userNetwork.sendRequest(internalRequest);
			
			
			response = ComputationResponse.newBuilder()
					.setSuccess(internalResponse.wasSuccessful())
					.setStatusCode(200)
					.build();
					
	} catch (Exception e ) {
		System.err.println("GRPC Error: "+e.getMessage());
		
		//creates an error response
		response = ComputationResponse.newBuilder()
                .setSuccess(false)
                .setErrorMessage(e.getMessage())
                .setStatusCode(500)
                .build(); 
	}
	
	responseObserver.onNext(response);
	responseObserver.onCompleted();
	
	}
		
}
