package test.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import api.ComputationRequest;
import implementations.ComputeEngineImplementation;
import implementations.UserNetworkImplementation;

public class TestComputeEngine {
	
	@Test
	public void computeEngineTest(){
		//set input data
		InMemoryInputConfig inputConfig = new InMemoryInputConfig(Arrays.asList(1,10,25));
		
		//create list of results from computation
		List<String> results = new ArrayList<>();
		
		//create output config using results list
		InMemoryOutputConfig outputConfig = new InMemoryOutputConfig(results);
		
		//create InMemoryDataStore with input and output configs
		InMemoryDataStoreAPI dataStore = new InMemoryDataStoreAPI(inputConfig, outputConfig);
		
		//create empty implementations
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		UserNetworkImplementation userNetwork = new UserNetworkImplementation();
		
		//create computation  request for inputData
		ComputationRequest computationRequest = new ComputationRequest(inputConfig.getInputData());
		
		//send computation request through user network
		userNetwork.sendRequest(computationRequest);
		
		//expected calculation of the collatz sequence
		List<String> expectedResults = Arrays.asList(
				"[1]",
				"[10, 5, 16, 8, 4, 2, 1]",
	            "[25, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1]"
	        );
		
		//getting actual results from output config
		results = outputConfig.getOutputData();
		
		//checking if expected results = actual results
		assertEquals(expectedResults, results);
	}
}
