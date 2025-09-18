package api;

import project.annotations.ConceptualAPIPrototype;

public class PrototypeComputeEngine {
	
	@ConceptualAPIPrototype
	public void prototype(ComputeEngineAPI computeEngine) {
		//start a computation
		ComputationStartResponse startResponse = 
				computeEngine.start(new ComputationStartRequest());
		//read computation data
		ComputationDataResponse dataResponse = 
				computeEngine.readComputationData(new ComputationDataRequest(startResponse.getComputationId()));
		//write computation results
		ComputationResultResponse resultRespone = 
				computeEngine.writeComputationResults(new ComputationResultRequest(startResponse.getComputationId()));
		
	}
}
