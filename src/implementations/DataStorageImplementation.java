package implementations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import api.DataReadRequest;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteRequest;
import api.DataWriteResponse;

public class DataStorageImplementation implements DataStorageAPI {
	
	@Override
	public DataReadResponse readData(DataReadRequest dataReadRequest) {
		
		//Checking if readRequest and input path are null
		if(dataReadRequest == null) {
			throw new IllegalArgumentException("DataReadRequest cannot be null");
		}
		if(dataReadRequest.getInputPath() == null) {
			throw new IllegalArgumentException("Input path cannot be null");
		}
		
		
		String inputPath = dataReadRequest.getInputPath();
		List<Integer> inputs = new ArrayList<>();
		
		//Buffered reader to read inputs from file
		try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
				String line;
				
				//Loop through lines in file
				while((line = reader.readLine()) != null) {
					
					//Split line into number strings
					String [] numAsString = line.split("\\s*,\\s*");
					
					//loop through array of strings
					for(String numString : numAsString) {
						//check if strings are empty
						if(!numString.isEmpty()) {
							
						//The following try/catch block handles possible non numeric values in the input data	
						try {
							//convert string to int add to input list
							inputs.add(Integer.parseInt(numString.trim()));
						} catch (NumberFormatException e) {
							System.out.println("Non-numeric value: "+numString);
						}
					}
				}
			}
				
				//if file read is successful
				return new DataReadResponseImplementation(true, inputs);
				
		} catch(IOException e ) {
			System.out.println("Error reading file: "+e.getMessage());
			
			//if file read fails
			return new DataReadResponseImplementation(false);
		}
		
	}
	@Override
	public DataWriteResponse writeData(DataWriteRequest dataWriteRequest) {
		//Validate the request and data
		if(dataWriteRequest == null) {
			throw new IllegalArgumentException("DataWriteRequest cannot be null");
		}
		if(dataWriteRequest.getData() == null){
			throw new IllegalArgumentException("Output data cannot be null");
		}
		
		
		String outputPath = dataWriteRequest.getOutputPath();
		
		List<List<Integer>> outputData = dataWriteRequest.getData();
		
		String delim = dataWriteRequest.getDelimiter();
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))){
			
			List<String> formattedOutput = new ArrayList<>();
			
			for(List<Integer> outputList : outputData) {
				
				//Stream list of integers and convert to string joined by delimiter
				String outputStr = outputList.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));

				formattedOutput.add(outputStr);
											
			}
			String finalOutput = String.join(delim, formattedOutput);
			//Write line and move to next line
			writer.write(finalOutput);
			
			
			//if data is written successfully
			return new DataWriteResponseImplementation(true);
			
			
			
		} catch(IOException e) {
			System.out.println("Writing error has occurred: "+ e.getMessage());
			
			//If datawrite has an error
			return new DataWriteResponseImplementation(false);
		}	
		
	}

}
