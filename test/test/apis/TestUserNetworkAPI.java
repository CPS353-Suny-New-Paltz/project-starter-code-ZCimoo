package test.apis;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationInputRequest;
import api.ComputationInputResponse;
import api.ComputationOutputRequest;
import api.ComputationOutputResponse;
import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputeEngineAPI;
import api.DataStorageAPI;
import implementations.UserNetworkImplementation;

public class TestUserNetworkAPI {
	
	@Test
	public void testSendRequestReturnsDefault() {
		//Create mock DataStorage and ComputeEngine
		DataStorageAPI mockDS = Mockito.mock(DataStorageAPI.class);
		ComputeEngineAPI mockCE = Mockito.mock(ComputeEngineAPI.class);
		
		//Create implementation
		UserNetworkImplementation userNetwork = new UserNetworkImplementation(mockDS, mockCE);
		
		//Call sendRequest method with mock request
		ComputationResponse response = 
				userNetwork.sendRequest(Mockito.mock(ComputationRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
		
		
	}
	
	@Test
	public void testInputReturnsDefault() {
		//Create mock DataStorage and ComputeEngine
		DataStorageAPI mockDS = Mockito.mock(DataStorageAPI.class);
		ComputeEngineAPI mockCE = Mockito.mock(ComputeEngineAPI.class);
		
		//Create implementation
		UserNetworkImplementation userNetwork = new UserNetworkImplementation(mockDS, mockCE);
		
		//Call input method with mock request
		ComputationInputResponse response = 
				userNetwork.input(Mockito.mock(ComputationInputRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
		
		
	}
	
	@Test
	public void testOutputReturnsDefault() {
		//Create mock DataStorage and ComputeEngine
		DataStorageAPI mockDS = Mockito.mock(DataStorageAPI.class);
		ComputeEngineAPI mockCE = Mockito.mock(ComputeEngineAPI.class);
		//Create implementation
		UserNetworkImplementation userNetwork = new UserNetworkImplementation(mockDS, mockCE);
		
		//Call output method with mock request
		ComputationOutputResponse response = 
				userNetwork.output(Mockito.mock(ComputationOutputRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
		
		
	}

}
