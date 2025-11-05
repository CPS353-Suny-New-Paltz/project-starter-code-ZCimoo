package implementations;

import java.util.List;

import api.DataReadResponse;

//Implementation of DataWriteResponse, couldn't figure out how to return a response without implementation.
public class DataReadResponseImplementation implements DataReadResponse {
	
private boolean status;

private List<Integer> data;
	
	//gets a result this one is likely used if read was unsuccessful
	public DataReadResponseImplementation(boolean result) {
		this.status = result;
	}
	
	//gets a result (likely true if this one takes data) and dataRead from file
	public DataReadResponseImplementation(boolean result, List<Integer> readData) {
		this.status = result;
		this.data = readData;
	}
	
	@Override
	public boolean status() {
		return this.status;

	}
	
	public List<Integer> getData(){
		return this.data;
	}
}
