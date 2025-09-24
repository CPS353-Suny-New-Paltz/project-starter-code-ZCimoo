package test.apis;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.DataReadRequest;
import api.DataReadResponse;
import api.DataWriteRequest;
import api.DataWriteResponse;
import implementations.DataStorageImplementation;

public class TestDataStorageAPI {

	@Test
	public void testReadDataReturnsDefault() {
		//Create implementation
		DataStorageImplementation dataStore = new DataStorageImplementation();
		
		//Create readData method with mock request
		DataReadResponse response = 
				dataStore.readData(Mockito.mock(DataReadRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response,"Expected null response, not implemented yet");
		
	}
	
	@Test
	public void testWriteDataReturnsDefault() {
		//Create implementation
		DataStorageImplementation dataStore = new DataStorageImplementation();
		
		//Create writeData method with mock request
		DataWriteResponse response = 
				dataStore.writeData(Mockito.mock(DataWriteRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response,"Expected null response, not implemented yet");
		
	}
	
}
