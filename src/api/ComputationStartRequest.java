package api;

public class ComputationStartRequest {
	
	//integer that the computation is going to use
	private int computeInt;

	//take in and set integer
	public ComputationStartRequest(int x) {
		// TODO Auto-generated constructor stub
		this.computeInt = x;
	}
	
	public int getComputeInt() {
		return this.computeInt;
	}

}
