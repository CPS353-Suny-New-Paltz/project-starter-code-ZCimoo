package implementations;

import api.ComputationDataRequest;
import api.ComputationDataResponse;
import api.ComputationResultRequest;
import api.ComputationResultResponse;
import api.ComputationStartRequest;
import api.ComputationStartResponse;
import api.ComputeEngineAPI;

public class ComputeEngineImplementation implements ComputeEngineAPI {
	
	@Override
	public ComputationStartResponse start(ComputationStartRequest computationStartRequest) {
		//placeholder return
		return null;
	}

	@Override
	public ComputationDataResponse readComputationData(ComputationDataRequest computationDataRequest) {
		//placeholder return
		return null;
	}
	
	@Override
	public ComputationResultResponse writeComputationResults(ComputationResultRequest computationResultRequest) {
		//placeholder return
		return null;
	}
}
