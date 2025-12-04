package test.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputeEngineAPI;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteResponse;
import implementations.UserNetworkImplementation;

public class TestUserNetworkAPI {
	
	@Test
	public void testSendRequestWorks() {
		//Create mock DataStorage and ComputeEngine
		DataStorageAPI mockDS = Mockito.mock(DataStorageAPI.class);
		ComputeEngineAPI mockCE = Mockito.mock(ComputeEngineAPI.class);
		
		//Create implementation
		UserNetworkImplementation userNetwork = new UserNetworkImplementation(mockDS, mockCE);
		
		//Calls read data and returns empty list (the response)
        DataReadResponse mockReadResponse = Mockito.mock(DataReadResponse.class);
        Mockito.when(mockDS.readData(Mockito.any())).thenReturn(mockReadResponse);
        Mockito.when(mockReadResponse.getData()).thenReturn(new ArrayList<Integer>());
        Mockito.when(mockReadResponse.status()).thenReturn(true);

        //Calls write data and returns "true"
        DataWriteResponse mockWriteResponse = Mockito.mock(DataWriteResponse.class);
        Mockito.when(mockDS.writeData(Mockito.any())).thenReturn(mockWriteResponse);
        Mockito.when(mockWriteResponse.status()).thenReturn(true);
        
        //Creates mockRequest with values to pass validation
        ComputationRequest mockRequest = Mockito.mock(ComputationRequest.class);
        Mockito.when(mockRequest.getInputPath()).thenReturn("dummy-input.txt");
        Mockito.when(mockRequest.getOutputPath()).thenReturn("dummy-output.txt");
		
		
		//Call sendRequest method with mock request
		ComputationResponse response = 
				userNetwork.sendRequest(mockRequest);
		
		//Checks if ComputationRequest was successful
		assertEquals(response.wasSuccessful(), true);
		
		
	}

}
