import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

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
				algorithmArrayList.add(algorithms.next());

			}
		} catch (ServiceConfigurationError serviceError) {
			algorithmArrayList = null;
			serviceError.printStackTrace();
		}
		return algorithmArrayList;
	}

}
