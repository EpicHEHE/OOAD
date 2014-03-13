import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import spi.Algorithm;

public class AlgorithmService {
	private static AlgorithmService service;
	private ServiceLoader<Algorithm> loader;

	private AlgorithmService() {
		loader = ServiceLoader.load(Algorithm.class);
	}

	public static synchronized AlgorithmService getInstance() {
		if (service == null) {
			service = new AlgorithmService();
		}
		return service;
	}

	public ArrayList<Algorithm> loadAlgorithms() {
		ArrayList<Algorithm> algorithmArrayList = new ArrayList<Algorithm>();
		try {
			Iterator<Algorithm> algorithms = loader.iterator();
			while (algorithms.hasNext()) {
				Algorithm a = algorithms.next();
				System.out.println(a.algorithmName);
				algorithmArrayList.add(a);

			}
		} catch (ServiceConfigurationError serviceError) {
			algorithmArrayList = null;
			serviceError.printStackTrace();
		}
		
		return algorithmArrayList;
	}

}
