package implementations;

import java.util.ArrayList;
import java.util.List;

import api.ComputationCompleteRequest;
import api.ComputationCompleteResponse;
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
	public ComputationCompleteResponse completeComputation(ComputationCompleteRequest computationCompleteRequest) {
		//placeholder return
		return null;
	}
	
	private List<Integer> computeCollatzSequence(int x){
		List<Integer> result = new ArrayList<>();
		
		//add initial value to sequence
		result.add(x);
			
		//perform calculation until x == 1 (complete computation) 
		while(x != 1) {
			//check if x is even
			if(x % 2 == 0) {
				//collatz sequence requires even integers to be divided by 2
				x = x/2;
				result.add(x);
				//check if odd
			} else if(x % 2 == 1 ) { 
				//collatz sequence requires odd integers to be multiplied by 3 and add 1
				x = (x * 3) + 1;
				result.add(x);
			}
		}
	
		return result;
	}
}
