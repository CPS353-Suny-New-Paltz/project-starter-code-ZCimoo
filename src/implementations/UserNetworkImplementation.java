package implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.ComputationInputRequest;
import api.ComputationInputResponse;
import api.ComputationOutputRequest;
import api.ComputationOutputResponse;
import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputationStartRequest;
import api.ComputationStartResponse;
import api.ComputeEngineAPI;
import api.DataReadRequest;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteRequest;
import api.DataWriteResponse;
import api.UserNetworkAPI;

public class UserNetworkImplementation implements UserNetworkAPI {
	
	private DataStorageAPI dataStore;
	private ComputeEngineAPI computeEngine;
	
	
	
	
	public UserNetworkImplementation(DataStorageAPI ds, ComputeEngineAPI ce) {
		this.dataStore = ds;
		this.computeEngine = ce;
	}
	
	@Override
	public ComputationResponse sendRequest(ComputationRequest computationRequest) {
		try {
			//Validate request and input/output paths
			if(computationRequest == null) {
				throw new IllegalArgumentException("ComputationRequest cannot be null");
			}
			if(computationRequest.getOutputPath() == null || computationRequest.getInputPath() == null) {
				throw new IllegalArgumentException("Input/output paths cannot be null");
			}
			
			//Get input and output paths from user
			String inputPath = computationRequest.getInputPath();
			String outputPath = computationRequest.getOutputPath();
			
			//Create data read request for the inputPath specified by user
			DataReadRequest drRequest= new DataReadRequest(inputPath);
			
			//Send and catch data store response 
			DataReadResponse drResponse = dataStore.readData(drRequest);
			
			//Checks for file read failure
			if(!drResponse.status()) {
				throw new IOException("Failed to read input file");
			}
			
			//Gets data from dataRead as a list
			List<Integer> inputData = drResponse.getData();
			
			//create list to hold lists of Collatz Sequences completed
			List<List<Integer>> outputData = new ArrayList<>();
			
			//compute Collatz Sequence for list of input data
			for(int x : inputData) {
				ComputationStartRequest csRequest = new ComputationStartRequest(x);
				ComputationStartResponse csResponse = computeEngine.start(csRequest);
				
				//Checks for sentinel value (empty list)
				List<Integer> result = csResponse.getSequence();
				if(result.isEmpty()) {
					System.out.println("Computation failed for input: "+x);
				}
				
				outputData.add(result);
				
			}
			
			//Create request to write outputData
			DataWriteRequest dwRequest = new DataWriteRequest(outputData, outputPath);
			
			//Catch DataWriteResponse 
			DataWriteResponse dwResponse = dataStore.writeData(dwRequest);
			
			//checks if data write was successful then returns ComputationResponse
			if(dwResponse.status()) {
				return new ComputationResponseImplementation(true);
			} else {
				return new ComputationResponseImplementation(false);
			}
		} catch (Exception e) {
			
			System.out.println("Error in UserNetwork: "+e.getMessage());
			
			return new ComputationResponseImplementation(false);
		}
		
		
	}
	
	@Override
	public ComputationInputResponse input(ComputationInputRequest computationInputRequest) {
		//placeholder return
		return null;
	}
	@Override
	public ComputationOutputResponse output(ComputationOutputRequest computationOutputRequest) {
		//placeholder return
		return null;
	}

}
