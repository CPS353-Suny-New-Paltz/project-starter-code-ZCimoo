package benchmarks;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationRequest;
import api.DataStorageAPI;
import api.DataWriteRequest;
import implementations.AbstractUserNetworkAPI;
import implementations.ComputeEngineImplementation;
import implementations.DataWriteResponseImplementation;
import implementations.FasterUserNetworkAPI;
import implementations.MultithreadedNetworkAPI;
import implementations.UserNetworkImplementation;

public class BenchmarkTest {
	

// class to run benchmark
private void runBenchmark(String name, AbstractUserNetworkAPI networkAPI, String input, int inputSize, int iterations) {
        
        System.out.println("--- Starting " + name + " ---");
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < iterations; i++) {
            ComputationRequest request = new ComputationRequest(input, "benchmarkOutput.txt");
            networkAPI.sendRequest(request);
        }
        
        // ensure multithreaded classes shutdown
        if (networkAPI instanceof MultithreadedNetworkAPI) {
            ((MultithreadedNetworkAPI) networkAPI).shutdown();
        } else if (networkAPI instanceof FasterUserNetworkAPI) {
            ((FasterUserNetworkAPI) networkAPI).shutdown();
        }
        
        long endTime = System.currentTimeMillis();
        
        double durationInSec = (endTime - startTime) / 1000.0;
        long totalInputs = (long) inputSize * iterations;
        
        System.out.println("Total Time: " + durationInSec + " seconds");
        if (durationInSec > 0) {
            System.out.println("Inputs/sec: " + (totalInputs / durationInSec));
        } else {
            System.out.println("Inputs/sec: too fast to measure");
        }
        System.out.println("--------------------------------\n");
    }

@Test
public void testFinalPerformanceComparison() {
    int inputSize = 100000;
    int iterations = 100; 

    //build large string
    StringBuilder inputBuilder = new StringBuilder();
    for(int i = 1; i <= inputSize; i++) {
        inputBuilder.append(i).append(",");
    }
    String massiveInput = inputBuilder.toString();
    
    //mock data store 
    DataStorageAPI mockDataStore = Mockito.mock(DataStorageAPI.class);
    when(mockDataStore.writeData(any(DataWriteRequest.class)))
        .thenReturn(new DataWriteResponseImplementation(true));
    
    
    ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
    
    //sequential baseline
    UserNetworkImplementation sequentialNetwork = new UserNetworkImplementation(mockDataStore, computeEngine);
    runBenchmark("1. SEQUENTIAL BASELINE (Expected Fast)", sequentialNetwork, massiveInput, inputSize, iterations);

    //original multithreaded class
    MultithreadedNetworkAPI slowParallelNetwork = new MultithreadedNetworkAPI(mockDataStore, computeEngine);
    runBenchmark("2. ORIGINAL PARALLEL (Expected Slowest)", slowParallelNetwork, massiveInput, inputSize, iterations);

    //faster one with fix
    FasterUserNetworkAPI fixedParallelNetwork = new FasterUserNetworkAPI(mockDataStore, computeEngine);
    runBenchmark("3. FASTER PARALLEL (The Fix)", fixedParallelNetwork, massiveInput, inputSize, iterations);
}
	
	
	
}
