import java.util.ArrayList;
import java.util.TreeMap;

import spi.Algorithm;

public class ProductAlgorithmManager {
	private TreeMap<String, TreeMap<String, Algorithm>> productMap;
	private static ProductAlgorithmManager manager;

	private ProductAlgorithmManager() {
		productMap = new TreeMap<String, TreeMap<String, Algorithm>>();
	}

	public static synchronized ProductAlgorithmManager getInstance() {
		if (manager == null) {
			manager = new ProductAlgorithmManager();
		}
		return manager;

	}

	public ArrayList<String> getProductList() {
		return null;
	}

	public ArrayList<String> getAlgorithmList(String productName) {
		return null;
	}

	public void addProducttoMap(String productName) {

	}

	public void addAlgorithmtoMap(ArrayList<Algorithm> newAlgorithms) {

	}

	public void dropProduct(String productName) {

	}

	public void dropAlgorithm(String productName, String algorithmName) {

	}

	public void saveProductMaptoFile() {

	}

	public void getProductMaptoFile() {

	}

	public void notifyAlgorithmChange() {

	}

	public void notifyProductChange() {

	}
}
