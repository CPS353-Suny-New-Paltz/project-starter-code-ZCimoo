package api;

public class ComputationRequest {
	//default delimiter
	public String delimiter;
	
	public ComputationRequest() {
		this.delimiter = ",";
	}
	
	//custom delimiter
	public ComputationRequest(String delimiter) {
		this.delimiter = delimiter;
	}
	
	//get method for delimiter
	public String getDelimiter() {
		return delimiter;
	}
	
}
