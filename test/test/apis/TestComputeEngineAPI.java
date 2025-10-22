package test.apis;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationCompleteRequest;
import api.ComputationCompleteResponse;
import api.ComputationStartRequest;
import api.ComputationStartResponse;
import implementations.ComputeEngineImplementation;

public class TestComputeEngineAPI {
	
	@Test
	public void testStartComputationReturnsDefault() {
		//Create implementation
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		//Call start method with mock request
		ComputationStartResponse response = 
				computeEngine.start(Mockito.mock(ComputationStartRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
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
