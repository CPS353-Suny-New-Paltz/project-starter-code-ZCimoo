package api;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserNetworkAPI {

	ComputationResponse sendRequest(ComputationRequest computationRequest);
	
}