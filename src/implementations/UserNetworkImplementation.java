package implementations;

import api.ComputationInputRequest;
import api.ComputationInputResponse;
import api.ComputationOutputRequest;
import api.ComputationOutputResponse;
import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputeEngineAPI;
import api.DataStorageAPI;
import api.UserNetworkAPI;

public class UserNetworkImplementation implements UserNetworkAPI {
	
	private DataStorageAPI dataStore;
	private ComputeEngineAPI computeEngine;
	
	public UserNetworkImplementation(DataStorageAPI ds, ComputeEngineAPI ce) {
		this.dataStore = ds;
		this.computeEngine = ce;
	}
	
	@Override
	public ComputationResponse sendRequest(ComputationRequest computationRequest) {
		//placeholder return
		return null;
	}
	@Override
	public ComputationInputResponse input(ComputationInputRequest computationInputRequest) {
		//placeholder return
		return null;
	}
	@Override
	public ComputationOutputResponse output(ComputationOutputRequest computationOutputRequest) {
		//placeholder return
		return null;
	}

}
