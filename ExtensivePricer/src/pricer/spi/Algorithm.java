package pricer.spi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public interface Algorithm {
	
	// public String algorithmName="";
	TreeMap<String, Float> parameter = new TreeMap<String, Float>();

	public TreeMap<String, Float> getParameterMap();

	public void setParameter(TreeMap<String, Float> a);
	public String getAlgorithmName();
	public ArrayList<String> getProductName();

	Float[] calculate();

}
