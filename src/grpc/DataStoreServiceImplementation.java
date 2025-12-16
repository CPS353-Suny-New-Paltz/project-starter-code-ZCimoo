package grpc;

import io.grpc.Server;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.ArrayList;

import api.DataStorageAPI;
import implementations.DataStorageImplementation;



public class DataStoreServiceImplementation extends DataStorageServiceGrpc.DataStorageServiceImplBase {
	
	private final DataStorageAPI internalDataStore = new DataStorageImplementation();
	
	@Override
	public void readData(grpc.DataReadRequest request, StreamObserver<grpc.DataReadResponse> responseObserver) {
		
		api.DataReadRequest apiRequest = new api.DataReadRequest(request.getInputPath());
		
		api.DataReadResponse apiResponse = internalDataStore.readData(apiRequest);
		
		grpc.DataReadResponse.Builder grpcResponseBuilder = grpc.DataReadResponse.newBuilder();
		
		if(apiResponse.getData() != null) {
			grpcResponseBuilder.addAllData(apiResponse.getData());
		}
		
		grpcResponseBuilder.setStatus(apiResponse.status());
		
		responseObserver.onNext(grpcResponseBuilder.build());
		responseObserver.onCompleted();
	}
	
	@Override
	public void writeData(grpc.DataWriteRequest request, StreamObserver<grpc.DataWriteResponse> responseObserver) {
		List<List<Integer>> javaData = new ArrayList<>();
		
		//translate from proto to java nested list
		for(grpc.IntList protoRow : request.getOutputDataList()) {
			javaData.add(protoRow.getValuesList());
		}
		
		String delimiter ="";
		//check for delimiter
		if(request.hasDelimiter()) {
			delimiter = request.getDelimiter();
		} else {
			delimiter = ",";
		}
		
		api.DataWriteRequest apiRequest = new api.DataWriteRequest(
				javaData,
				request.getOutputPath(),
				delimiter
		);
		
		api.DataWriteResponse apiResponse = internalDataStore.writeData(apiRequest);
		
		grpc.DataWriteResponse.Builder grpcResponseBuilder = grpc.DataWriteResponse.newBuilder()
				.setStatus(apiResponse.status());
		
		responseObserver.onNext(grpcResponseBuilder.build());
		responseObserver.onCompleted();
		
		
	}
	
	
	
}
