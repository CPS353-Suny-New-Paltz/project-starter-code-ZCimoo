package api;

import project.annotations.ProcessAPIPrototype;


public class PrototypeDataStorageAPI {
	@ProcessAPIPrototype
	public void prototype(DataStorageAPI dataStore) {
		//read data from source
		DataReadResponse readResponse =
				dataStore.readData(new DataReadRequest());
		//write to destination
		DataWriteResponse writeResponse = 
				dataStore.writeData(new DataWriteRequest(null));
	}
}