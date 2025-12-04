package implementations;

import java.util.ArrayList;
import java.util.List;

import api.ComputationStartRequest;
import api.ComputationStartResponse;
import api.ComputeEngineAPI;

public class ComputeEngineImplementation implements ComputeEngineAPI {
	
	@Override
	public ComputationStartResponse start(ComputationStartRequest computationStartRequest) {
		try {
			//Validate request and int to compute
			if(computationStartRequest == null) {
				throw new IllegalArgumentException("ComputationStartRequest cannot be null");
			}
			
		
			int intToCompute = computationStartRequest.getComputeInt();
			if(intToCompute < 1) {
				throw new IllegalArgumentException("Collatz input must be a positive integer");
			}
			
			List<Integer> sequence = this.computeCollatzSequence(intToCompute);
			
			return new ComputationStartResponse() {
				@Override
				public List<Integer> getSequence(){
					return sequence;
				}
			};
		} catch(Exception e) {
			System.out.println("Error in compute engine: "+e.getMessage());
			
			//returns sentinel value, an empty list to indicate failure
			return new ComputationStartResponse() {
				@Override
				public List<Integer> getSequence(){
					return new ArrayList<>();
				}
			};
			
		}
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
