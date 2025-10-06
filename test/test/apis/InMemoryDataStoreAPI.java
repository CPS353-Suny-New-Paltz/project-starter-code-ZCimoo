package test.apis;

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
		String data = inputConfig.getInputData().toString();
		return new DataReadResponse() {
			public boolean status() {
				return true;
			}
			
			//implement getData to return inputConfig data as string for now.
			public String getData() {
				return data;
			}
		};
	}
	
	public DataWriteResponse writeData(DataWriteRequest dataWriteRequest) {
		
		outputConfig.getOutputData().add(dataWriteRequest.getData());
		
		return new DataWriteResponse() {
			public boolean status() {
				return true;
			}
			
			public String getData() {
				return dataWriteRequest.getData();			
			}
		};
	}

}
