package api;

import java.util.List;

public class DataWriteRequest {

	private List<List<Integer>> outputData;
	private String outputPath;
	
	public DataWriteRequest(List<List<Integer>> data) {
		//checks if there is data and assigns it to the data variable
		if(data == null) {
		throw new IllegalArgumentException("Data cannot be null");
	}
		this.outputData = data;
}
	
	public DataWriteRequest(List<List<Integer>> data, String outputPath) {
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null");
		}
			this.outputData = data;
			this.outputPath = outputPath;
	}
	
	public String getOutputPath() {
		return this.outputPath;
	}
	
	public List<List<Integer>> getData() {
		 //returns data
		return outputData;
	}

}
