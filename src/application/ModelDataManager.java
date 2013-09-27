package application;

import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;

import graphics.BasicModel;
import resource.ResourceLoader;

public class ModelDataManager implements Runnable {

	ConcurrentSkipListMap<Integer, BasicModel> _loadedModels;
	LinkedBlockingQueue<Integer> _modelRequests;

	HashMap<Integer, String> _filenames;

	String baseDir;

	public ModelDataManager() {
		baseDir = Application.getCData().getModelDir();
		_loadedModels = new ConcurrentSkipListMap<Integer, BasicModel>();
		_modelRequests = new LinkedBlockingQueue<Integer>();
		_filenames = new HashMap<Integer, String>();
		_filenames.put(DataManager.M_TRIANGLE, "triangle.txt");
		_filenames.put(DataManager.M_FLOOR, "podloga.txt");
		_filenames.put(DataManager.M_MENU, "3dcos.txt");
		_filenames.put(DataManager.M_CURSOR, "kursor.txt");
		_filenames.put(DataManager.M_EXITBUTTON, "exbut.txt");
		_filenames.put(DataManager.M_BALL, "ball.txt");
		_filenames.put(DataManager.M_WALL, "wall.txt");
		_filenames.put(DataManager.M_CUBE, "cube.txt");
		_filenames.put(DataManager.M_FLOOR1, "floor.txt");
		_filenames.put(DataManager.M_HOLE, "hole.txt");
		_filenames.put(DataManager.M_END, "end.txt");
	}

	public BasicModel getModel(Integer modelNumber) {
		if (_loadedModels.containsKey(modelNumber)) {
			return _loadedModels.get(modelNumber);
		}
		BasicModel model = ResourceLoader.loadModelFromTxt(baseDir
				+ _filenames.get(modelNumber));
		_loadedModels.put(modelNumber, model);
		return model;
	}

	public void run() {
		while (true) {
			while (!_modelRequests.isEmpty()) {
				Integer number = _modelRequests.poll();
				_loadedModels.put(number, ResourceLoader
						.loadModelFromTxt(baseDir + _filenames.get(number)));
			}
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {

			}
		}
	}
	
	public void placeModelRequest(Integer modelNumber) {
		_modelRequests.add(modelNumber);
	}

}
