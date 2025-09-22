package api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {

	DataReadResponse readData(DataReadRequest dataReadRequest);

	DataWriteResponse writeData(DataWriteRequest dataWriteRequest);
}