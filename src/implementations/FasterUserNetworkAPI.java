package implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import api.ComputationStartRequest;
import api.ComputationStartResponse;
import api.ComputeEngineAPI;
import api.DataStorageAPI;

public class FasterUserNetworkAPI extends AbstractUserNetworkAPI {
	
	private ExecutorService executor;
	private static final int MAX_THREADS = 4;
	
	public FasterUserNetworkAPI(DataStorageAPI ds, ComputeEngineAPI ce) {
		super(ds, ce);
		this.executor = Executors.newFixedThreadPool(MAX_THREADS);
	}
	
	@Override
	protected List<List<Integer>> runComputation(List<Integer> inputData){
		
		
		CompletionService<List<Integer>> completionService = new ExecutorCompletionService<>(executor);
		int taskCount = 0;
		
		//Send tasks to thread pool
		for(int x : inputData) {
			
			//Create task to perform
			Callable<List<Integer>> compute = () -> {
				ComputationStartRequest csRequest = new ComputationStartRequest(x);
				ComputationStartResponse csResponse = computeEngine.start(csRequest);
				return csResponse.getSequence();
			};
			
			completionService.submit(compute);
			taskCount++;
		}
		
		//List to collect results
		List<List<Integer>> outputData = new ArrayList<>();
		
		for(int i= 0; i < taskCount; i++) {
			try {
				
				Future<List<Integer>> future = completionService.take();
				outputData.add(future.get());
				
			} catch (InterruptedException | ExecutionException e) {
				
				System.out.println("Error in thread: "+e.getMessage());
				
				//Add empty list if thread fails so order isnt messed up
				outputData.add(new ArrayList<>());
			}
		}
		
		return outputData;
		
	}
	
	public void shutdown() {
		if(this.executor != null) {
			this.executor.shutdown();
		}
	}

}
