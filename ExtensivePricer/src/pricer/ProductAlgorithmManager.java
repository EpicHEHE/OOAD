package pricer;

import java.util.ArrayList;
import java.util.TreeMap;

import pricer.spi.Algorithm;

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
		ArrayList<String> productList = new ArrayList<String>();
		for (String keyName : productMap.keySet()) {
			productList.add(keyName);
		}
		return productList;
	}

	public ArrayList<String> getAlgorithmList(String productName) {
		ArrayList<String> algorithmList = new ArrayList<String>();
		TreeMap<String, Algorithm> algorithmMap = productMap.get(productName);
		for (String keyName : algorithmMap.keySet()) {
			algorithmList.add(keyName);
		}
		return algorithmList;
	}

	public void addProducttoMap(String productName) {
		productMap.put(productName, new TreeMap<String, Algorithm>());
		notifyProductChange();
	}

	public void addAlgorithmtoMap(ArrayList<Algorithm> newAlgorithms) {
		for (int i = 0; i < newAlgorithms.size(); i++) {
			ArrayList<String> productName = newAlgorithms.get(i).getProductName();
			for (int k = 0; i < productName.size(); i++) {
				productMap.get(productName.get(k)).put(
						newAlgorithms.get(i).getAlgorithmName(),
						newAlgorithms.get(i));
			}
		}
		notifyAlgorithmChange();
	}

	public void dropProduct(String productName) {
		productMap.remove(productName);
	}

	public void dropAlgorithm(String productName, String algorithmName) {
		productMap.get(productName).remove(algorithmName);
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
