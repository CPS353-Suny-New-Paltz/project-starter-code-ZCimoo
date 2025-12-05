package implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputationStartRequest;
import api.ComputationStartResponse;
import api.ComputeEngineAPI;
import api.DataReadRequest;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteRequest;
import api.DataWriteResponse;
import api.UserNetworkAPI;

public class UserNetworkImplementation extends AbstractUserNetworkAPI implements UserNetworkAPI {
	

	public UserNetworkImplementation(DataStorageAPI ds, ComputeEngineAPI ce) {
		super(ds,ce);
	}
	
	@Override
	protected List<List<Integer>> runComputation(List<Integer> inputData){
		List<List<Integer>> outputData = new ArrayList<>();
		
		//Single threaded loop
		for(int x : inputData) {
			ComputationStartRequest csRequest = new ComputationStartRequest(x);
			ComputationStartResponse csResponse = computeEngine.start(csRequest);
			
			List<Integer> result = csResponse.getSequence();
            if (result.isEmpty()) {
                System.out.println("Computation failed for input: " + x);
            }
            outputData.add(result);
        }
        
        return outputData;
	}
}
