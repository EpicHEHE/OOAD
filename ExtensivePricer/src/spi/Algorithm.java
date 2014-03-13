package spi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Algorithm {
	ArrayList<String> productName = new ArrayList<String>();
	String algorithmName = "";
	Map<String, Float> parameter = new HashMap<String, Float>();

	Map<String, Float> getParameterMap();

	void setParameter(Map<String, Float> a);

	Float[] calculate();

}
