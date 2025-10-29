package api;

import java.util.List;

public class ComputationRequest {
	//default delimiter
	private String delimiter;
	private String inputPath;
	private String outputPath;
	
	//default request, takes input path and delimiter
	public ComputationRequest(String input, String output, String delimiter) {
		this.inputPath = input;
		this.outputPath = output;
		this.delimiter = delimiter;
	}
	
	//request with only input path and output path, uses default delimiter
	public ComputationRequest(String input, String output) {
		this(input, output, ",");
	}
	
	//request with only input path, output path null for now will probably be changed to a default location
	public ComputationRequest(String input) {
		this(input, null, ",");
	}
	
	//empty request
	public ComputationRequest() {
		this(null, null, ",");
	}
	
	//get method for delimiter
	public String getDelimiter() {
		return delimiter;
	}
	
	//get method for input path
	public String getInputPath() {
		return inputPath;
	}
	
	//get method for output path
	public String getOutputPath() {
		return outputPath;
	}
	
}