package benchmarks;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationRequest;
import api.DataStorageAPI;
import api.DataWriteRequest;
import implementations.ComputeEngineImplementation;
import implementations.DataWriteResponseImplementation;
import implementations.MultithreadedNetworkAPI;
import implementations.UserNetworkImplementation;

public class BenchmarkTest {
	
	@Test
	public void testSequentialBottleneck() {
		int inputSize = 100000;
		
		//build large string input
		StringBuilder inputBuilder = new StringBuilder();
		for(int i = 1; i <= inputSize; i++) {
			inputBuilder.append(i).append(",");
		}
		String massiveInput = inputBuilder.toString();
		
		//Mock data store
		DataStorageAPI mockDataStore = Mockito.mock(DataStorageAPI.class);
		
		when(mockDataStore.writeData(any(DataWriteRequest.class)))
			.thenReturn(new DataWriteResponseImplementation(true));
		
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		UserNetworkImplementation userNetwork = new UserNetworkImplementation(mockDataStore, computeEngine);
		
		System.out.println("Starting benchmark for " + inputSize + " inputs...");
		
		int iterations = 10;
		
		long startTime = System.currentTimeMillis();
		
		for( int i = 0; i < iterations; i++) {
			ComputationRequest request = new ComputationRequest(massiveInput, "benchmarkOutput.txt");
			userNetwork.sendRequest(request);
		}
		
		long endTime = System.currentTimeMillis();
		
		double durationInSec = (endTime - startTime) / 1000.0;
		
		long totalInputs = (long) inputSize * iterations;
		
		System.out.println("Benchmark Results: ");
		System.out.println("Total Time: "+ durationInSec + " seconds");
		if(durationInSec > 0) {
			System.out.println("Inputs/sec: " + (totalInputs/durationInSec));
		} else {
			System.out.println("Inputs/sec: too fast to measure");
		}
			
	}
	
	@Test
	public void testParallelPerformance() {
int inputSize = 100000;
		
		//build large string input
		StringBuilder inputBuilder = new StringBuilder();
		for(int i = 1; i <= inputSize; i++) {
			inputBuilder.append(i).append(",");
		}
		String massiveInput = inputBuilder.toString();
		
		//Mock data store
		DataStorageAPI mockDataStore = Mockito.mock(DataStorageAPI.class);
		
		when(mockDataStore.writeData(any(DataWriteRequest.class)))
			.thenReturn(new DataWriteResponseImplementation(true));
		
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		MultithreadedNetworkAPI multiNetwork = new MultithreadedNetworkAPI(mockDataStore, computeEngine);
		
		int iterations = 10;
        System.out.println("Starting PARALLEL benchmark (4 Threads) for " + inputSize + " inputs...");
        
        long startTime = System.currentTimeMillis();
        
        try {
            for (int i = 0; i < iterations; i++) {
                ComputationRequest request = new ComputationRequest(massiveInput, "benchmarkOutput.txt");
                multiNetwork.sendRequest(request); 
            }
        } finally {
            
            multiNetwork.shutdown();
        }
        
        long endTime = System.currentTimeMillis();
        double durationInSec = (endTime - startTime) / 1000.0;
        long totalInputs = (long) inputSize * iterations;
        
        System.out.println("Parallel Benchmark Results: ");
        System.out.println("Total Time: " + durationInSec + " seconds");
        if (durationInSec > 0) {
            System.out.println("Inputs/sec: " + (totalInputs / durationInSec));
        }
	}
	
}
