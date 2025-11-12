package test.apis;

import java.util.List;

import org.mockito.Mockito;

import api.DataReadRequest;
import api.DataReadResponse;
import api.DataStorageAPI;
import api.DataWriteRequest;
import api.DataWriteResponse;

public class InMemoryDataStoreAPI implements DataStorageAPI {
	
	private final InMemoryInputConfig inputConfig;
	private final InMemoryOutputConfig outputConfig;
	
	//sets in/output configs
	public InMemoryDataStoreAPI(InMemoryInputConfig inputConfig,InMemoryOutputConfig outputConfig ) {
		
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}
	
	@Override
	public DataReadResponse readData(DataReadRequest dataReadRequest) {
		//assign input data as string
		List<Integer> data = inputConfig.getInputData();
		return new DataReadResponse() {
			public boolean status() {
				return true;
			}
			
			//implement getData to return inputConfig data as string for now.
			public List<Integer> getData() {
				return data;
			}
		};
	}
	
	public DataWriteResponse writeData(DataWriteRequest dataWriteRequest) {
		
		//List of all sequences
		List<List<Integer>> allResults = dataWriteRequest.getData();
		
		//Tests output list
		List<String> resultsList = outputConfig.getOutputData();
		
		for(List<Integer> oneResult : allResults) {
			String resultAsString = oneResult.toString();
			resultsList.add(resultAsString);
		}
		
		return new DataWriteResponse() {
			public boolean status() {
				return true;
			}

		};
	}

}
