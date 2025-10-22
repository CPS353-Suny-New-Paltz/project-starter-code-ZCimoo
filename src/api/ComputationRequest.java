package api;

import java.util.List;

public class ComputationRequest {
	//default delimiter
	private String delimiter;
	private String defaultDelimiter = ",";
	
	public ComputationRequest() {
		this.delimiter = defaultDelimiter;
	}
	
	//custom delimiter
	public ComputationRequest(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public ComputationRequest(List<Integer> inputData) {
		this.delimiter = defaultDelimiter;
	}
	
	public ComputationRequest(List<Integer> inputData, String delimiter) {
		this.delimiter = delimiter;
		
	}

	//get method for delimiter
	public String getDelimiter() {
		return delimiter;
	}
	
}