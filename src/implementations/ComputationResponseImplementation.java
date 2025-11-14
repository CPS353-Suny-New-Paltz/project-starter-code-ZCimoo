package implementations;

import api.ComputationResponse;


//Implementation of ComputationResponse because I couldnt figure out how to return a response
//for user network implementation
public class ComputationResponseImplementation implements ComputationResponse {
    private boolean wasSuccessful;

    public ComputationResponseImplementation(boolean result) {
        this.wasSuccessful = result;
    }

    @Override
    public boolean wasSuccessful() {
        return this.wasSuccessful;
    }
}