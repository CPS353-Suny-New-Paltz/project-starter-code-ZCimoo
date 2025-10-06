package api;

public class DataWriteRequest {

	private String data;
	
	public DataWriteRequest(String data) {
		//checks if there is data and assigns it to the data variable
		if(data == null) {
		throw new IllegalArgumentException("Data cannot be null");
	}
		this.data = data;
}
	public String getData() {
		 //returns data
		return data;
	}

}
