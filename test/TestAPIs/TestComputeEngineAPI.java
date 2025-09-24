import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import api.ComputationDataRequest;
import api.ComputationDataResponse;
import api.ComputationResultRequest;
import api.ComputationResultResponse;
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
	public void testReadComputationDataReturnsDefault() {
		//Create implementation
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		//Call readComputationData method with mock request
		ComputationDataResponse response = 
				computeEngine.readComputationData(Mockito.mock(ComputationDataRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response,"Expected null response, not implemented yet");
		
	}
	
	@Test
	public void testWriteComputationResultsReturnsDefault() {
		//Create implementation
		ComputeEngineImplementation computeEngine = new ComputeEngineImplementation();
		
		//Call writeComputationResults with a mock request
		ComputationResultResponse response = 
				computeEngine.writeComputationResults(Mockito.mock(ComputationResultRequest.class));
		
		//Placeholder return because implementation is not complete
		assertNull(response, "Expected null response, not implemented yet");
				
	}

}
