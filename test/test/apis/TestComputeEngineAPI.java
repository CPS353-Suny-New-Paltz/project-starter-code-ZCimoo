package test.apis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationStartRequest;
import api.ComputationStartResponse;
import implementations.ComputeEngineImplementation;

public class TestComputeEngineAPI {
	
	@Test
	public void testStart_CalculatesCollatzFor5_Correctly() {
		//Create implementation
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		ComputationStartRequest startRequest = new ComputationStartRequest(5);
		
		List<Integer> expectedList = Arrays.asList(5, 16, 8, 4, 2, 1);
		
		ComputationStartResponse startResponse = computeEngine.start(startRequest);
		
		List<Integer> actualList = startResponse.getSequence();
		
		assertEquals(expectedList, actualList);
	}
	
	@Test 
	public void testStart_InputZero_ReturnsEmptyList() {
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		ComputationStartRequest request = new ComputationStartRequest(0);
		
		ComputationStartResponse response = computeEngine.start(request);
		
		//Check we get a response 
		assertNotNull(response, "Should return a response object even on validation failure");
		
		//Check we get our sentinel value (empty list)
		List<Integer> actualList = response.getSequence();
		
		assertTrue(actualList.isEmpty(), "Expected an empty list for invalid input");
		
	}
}
