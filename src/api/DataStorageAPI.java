package api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {
	void processAPI();

	DataReadResponse readData(DataReadRequest dataReadRequest);

	DataWriteResponse writeData(DataWriteRequest dataWriteRequest);
}
