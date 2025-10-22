package api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeEngineAPI {
	ComputationStartResponse start(ComputationStartRequest computationStartRequest);

	ComputationCompleteResponse completeComputation(ComputationCompleteRequest computationCompleteRequest);
	

}
