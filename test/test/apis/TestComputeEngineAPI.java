package test.apis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationCompleteRequest;
import api.ComputationCompleteResponse;
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
	public void testCompleteComputationReturnsDefault() {
		//create implementation
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		//call complete method with mock request
		ComputationCompleteResponse response =
				computeEngine.completeComputation(Mockito.mock(ComputationCompleteRequest.class));
		
		//placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
	}

}
