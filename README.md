# Software Engineering Project Starter Code

We have chosen to use the Collatz Sequence for our computation.

![<Image showing project diagram with three APIs>](https://github.com/CPS353-Suny-New-Paltz/project-starter-code-ZCimoo/blob/main/APIDiagram.JPG?raw=true)

## Multi-Threading
The MultithreadedNetworkAPI uses a fixed thread pool with an upper bound of **4 threads**.

## Performance Tuning
Addressed critical bottleneck in orgiinal multithreaded implementation by optimizing result collection.

**Baseline**  Time: 0.341s,	Inputs/sec 293,255,	Improvement: N/A


**Original Multithreaded Implementation** Time: 0.251s,	Inputs/sec 398,406,	Improvement: Baseline for improvement


**Faster Multithreaded Implementation** Time: 0.203s, 	Inputs/sec 492,610,	Improvement: +23.64%

**Link to Benchmark Test:** [BenchmarkTest.java](test/benchmarks/BenchmarkTest.java)

**Fix: Optimize Threaded Result Collection using ExecutorCompletionService**

-**Issue:** Original multithreaded network implementation had a sequential bottleneck during result collection. The code called future.get()
	causing "Head-ofLine Blocking" forcing threads to wait for the result of the first submitted task.

	
-**The Fix:** Replaced mechanism with ExecutorCompletionService in the new FasterUserNetworkAPI. CompletionService allows results to be retrieved
in order they are complete, not submitted.

-**Code:** [FasterUserNetworkAPI.java](src/implementations/FasterUserNetworkAPI.java)