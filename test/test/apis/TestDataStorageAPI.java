package test.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.DataReadRequest;
import api.DataReadResponse;
import api.DataWriteRequest;
import api.DataWriteResponse;
import implementations.DataStorageImplementation;

public class TestDataStorageAPI {

	@Test
	public void readData_InvalidPath_ReturnsFalse() {
		//Create implementation
		DataStorageImplementation dataStore = new DataStorageImplementation();
		
		//create mock request
		DataReadRequest mockDataReadRequest = Mockito.mock(DataReadRequest.class);
		Mockito.when(mockDataReadRequest.getInputPath()).thenReturn("fake/input.txt");
		
		//Call readData method with mock request
		DataReadResponse response = 
				dataStore.readData(mockDataReadRequest);
		
		//Checks if response is false because of non existent input
		assertEquals(response.status(), false);
		
		
	}
	
	@Test
	public void writeData_InvalidPath_ReturnsFalse() {
		//Create implementation
		DataStorageImplementation dataStore = new DataStorageImplementation();
		
		//Create mock request with fake output
		DataWriteRequest mockDataWriteRequest = Mockito.mock(DataWriteRequest.class);
		Mockito.when(mockDataWriteRequest.getOutputPath()).thenReturn("fake/output.txt");
		
		//Add coverage for getData
		Mockito.when(mockDataWriteRequest.getData()).thenReturn(new ArrayList<>());
		
		
		//Call writeData method with mock request
		DataWriteResponse response = 
				dataStore.writeData(mockDataWriteRequest);
		
		//Check false because of fake output path
		assertEquals(response.status(), false);
		
	}
	
}
