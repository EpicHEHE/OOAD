package pricer.spi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Algorithm {
	ArrayList<String> productName = new ArrayList<String>();
	// public String algorithmName="";
	Map<String, Float> parameter = new HashMap<String, Float>();

	Map<String, Float> getParameterMap();

	public void setParameter(Map<String, Float> a);

	public String getAlgorithmName();

	Float[] calculate();

}
