package pricer;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import pricer.spi.Algorithm;


public class BinomialTree implements Algorithm {
	public String algorithmName;
	private ArrayList<String> productName;
	
	public BinomialTree(){
		algorithmName = "binomialTree";
		productName.add("AmericanOption");
	}

	@Override
	public TreeMap<String, Float> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(TreeMap<String, Float> a) {
		// TODO Auto-generated method stub

	}

	@Override
	public Float[] calculate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return algorithmName;
	}

	@Override
	public ArrayList<String> getProductName() {
		return productName;
	}

}
