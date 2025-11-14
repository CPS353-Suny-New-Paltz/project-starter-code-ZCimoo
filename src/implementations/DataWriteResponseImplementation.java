package implementations;

import api.DataWriteResponse;

//Implementation of DataWriteResponse, couldn't figure out how to return a response without implementation.
public class DataWriteResponseImplementation implements DataWriteResponse {
	
	private boolean status;
	
	public DataWriteResponseImplementation(boolean result) {
		this.status = result;
	}
	
	@Override
	public boolean status() {
		return this.status;

	}
	
	

}
