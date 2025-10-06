package test.apis;

import java.util.List;

public class InMemoryOutputConfig {
	
	private final List<String> outputData;
	
	public InMemoryOutputConfig(List<String> outputData){
		
		this.outputData = outputData;
		
	}
	
	public List<String> getOutputData(){
		
		return outputData;
	}
	
	public void writeOutput(String line) {
		outputData.add(line);
	}

}
