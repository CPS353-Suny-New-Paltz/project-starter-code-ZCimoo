package api;

import java.util.List;

public class DataWriteRequest {

	private List<List<Integer>> outputData;
	private String outputPath;
	
	//Set delimiter to a default value
	private String delimiter = ",";
	
	//method that just takes data, will use default values for delimiter and outputPath
	public DataWriteRequest(List<List<Integer>> data) {
		//checks if there is data and assigns it to the data variable
		if(data == null) {
		throw new IllegalArgumentException("Data cannot be null");
	}
		//need to add a default output path
		this.outputData = data;
}
	
	//method that takes data and outputPath
	public DataWriteRequest(List<List<Integer>> data, String outputPath) {
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null");
		}
			this.outputData = data;
			this.outputPath = outputPath;
	}
	
	//method that takes outputPath and user defined delimiter 
	public DataWriteRequest(List<List<Integer>> data, String outputPath, String delimiter) {
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null");
		}
			this.outputData = data;
			this.outputPath = outputPath;
			this.delimiter = delimiter;
	}
	
	public String getOutputPath() {
		return this.outputPath;
	}
	
	public List<List<Integer>> getData() {
		 //returns data
		return outputData;
	}
	
	public String getDelimiter() {
		return delimiter;
	}

}
