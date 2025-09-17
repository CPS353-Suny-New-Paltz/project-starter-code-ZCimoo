package api;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserNetworkAPI {

	ComputationResponse sendRequest(ComputationRequest computationRequest);

	ComputationInputResponse input(ComputationInputRequest computationInputRequest);

	ComputationOutputResponse output(ComputationOutputRequest computationOutputRequest);

	

}
