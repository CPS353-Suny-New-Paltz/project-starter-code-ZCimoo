package client;

import java.util.List;
import java.util.concurrent.TimeUnit;

import api.DataReadRequest;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteResponse;
import grpc.DataStorageServiceGrpc;
import api.DataWriteRequest;
import implementations.DataReadResponseImplementation;
import implementations.DataWriteResponseImplementation;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;

public class DataStorageClient implements DataStorageAPI {
	
	private final DataStorageServiceGrpc.DataStorageServiceBlockingStub blockingStub;
	private final ManagedChannel channel;
	
	public DataStorageClient(String host, int port) {
		this.channel = Grpc.newChannelBuilder(host + ":" + port, InsecureChannelCredentials.create())
				.build();
		this.blockingStub = DataStorageServiceGrpc.newBlockingStub(channel);
		
	}
	
	public void shutdown() throws InterruptedException{
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	@Override
	public DataReadResponse readData(DataReadRequest request) {
		grpc.DataReadRequest protoRequest = grpc.DataReadRequest.newBuilder()
				.setInputPath(request.getInputPath())
				.build();
		
		grpc.DataReadResponse protoResponse;
		try {
			protoResponse = blockingStub.readData(protoRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new DataReadResponseImplementation(false);
		}
		
		List<Integer> dataList = protoResponse.getDataList();
		
		return new DataReadResponseImplementation(protoResponse.getStatus(), dataList);
	}
	
	@Override
	public DataWriteResponse writeData(DataWriteRequest request) {
		grpc.DataWriteRequest.Builder protoRequestBuilder = grpc.DataWriteRequest.newBuilder()
				.setOutputPath(request.getOutputPath())
				.setDelimiter(request.getDelimiter());
		
		for(List<Integer> row : request.getData()) {
			grpc.IntList.Builder listBuilder = grpc.IntList.newBuilder();
			listBuilder.addAllValues(row);
			protoRequestBuilder.addOutputData(listBuilder.build());
		}
		
		grpc.DataWriteResponse protoResponse;
		
		try {
			protoResponse = blockingStub.writeData(protoRequestBuilder.build());
		} catch (Exception e) {
			return new DataWriteResponseImplementation(false);
		}
		
		return new DataWriteResponseImplementation(protoResponse.getStatus());
	}


}
