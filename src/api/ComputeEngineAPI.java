package api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeEngineAPI {
	ComputationStartResponse start(ComputationStartRequest computationStartRequest);

	ComputationDataResponse readComputationData(ComputationDataRequest computationDataRequest);

	ComputationResultResponse writeComputationResults(ComputationResultRequest computationResultRequest);
	

}
