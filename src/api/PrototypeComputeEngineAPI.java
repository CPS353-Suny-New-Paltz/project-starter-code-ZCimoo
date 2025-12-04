package api;

import project.annotations.ConceptualAPIPrototype;

public class PrototypeComputeEngineAPI {
	@ConceptualAPIPrototype
	public void prototype(ComputeEngineAPI computeEngine) {
		//start a computation
		ComputationStartResponse startResponse = 
				computeEngine.start(new ComputationStartRequest());
		

	}
}
