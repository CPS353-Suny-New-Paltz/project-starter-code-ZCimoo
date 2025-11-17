package project.checkpointtests;

import api.DataStorageAPI;
import api.ComputationRequest;
import api.ComputationResponse;
import api.ComputeEngineAPI;
import api.UserNetworkAPI;
import implementations.DataStorageImplementation;
import implementations.ComputeEngineImplementation;
import implementations.UserNetworkImplementation;


public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation 
        // of all 3 APIs
        
    	DataStorageAPI dataStore = new DataStorageImplementation();
    	ComputeEngineAPI computeEngine = new ComputeEngineImplementation();
    	UserNetworkAPI userNetwork = new UserNetworkImplementation(dataStore, computeEngine);
    	
    	
    	
        // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
        
    	ComputationRequest computationReq = new ComputationRequest(INPUT, OUTPUT, ",");
    	ComputationResponse computationResp = userNetwork.sendRequest(computationReq);
    	
    	
    	
        // Helpful hint: the working directory of this program is <root project dir>,
        // so you can refer to the files just using the INPUT/OUTPUT constants. You do not 
        // need to (and should not) actually create those files in your repo
    }
}
