package api;
import project.annotations.NetworkAPIPrototype;


public class UserNetworkAPIPrototype {
	@NetworkAPIPrototype
	public void prototype(UserNetworkAPI network) {
		
		//send request to compute engine
		ComputationResponse response = 
				network.sendRequest(new ComputationRequest());
		
		
	}
	
}