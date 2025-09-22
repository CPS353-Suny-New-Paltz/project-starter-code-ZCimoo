package api;
import project.annotations.NetworkAPIPrototype;


public class UserNetworkAPIPrototype {
	@NetworkAPIPrototype
	public void prototype(UserNetworkAPI network) {
		//input/source file
		ComputationInputResponse computationInput = 
				network.input(new ComputationInputRequest());
		
		//send request to compute engine
		ComputationResponse response = 
				network.sendRequest(new ComputationRequest());
		
		//output computation
		ComputationOutputResponse computationOutput =
				network.output(new ComputationOutputRequest());
		
		
	}
	
}