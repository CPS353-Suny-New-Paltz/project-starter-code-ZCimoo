package api;

import java.util.List;

public interface ComputationStartResponse {

	ComputationIdentifier getComputationId();
	
	List<Integer> getSequence();

}