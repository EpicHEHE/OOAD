import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Algorithm {
	ArrayList<String> ProductName = new ArrayList<String>();
	Map<String, Float> Parameter = new HashMap<String, Float>();

	Map<String, Float> getParameterMap();

	void setParameter(Map<String, Float> a);

	Float[] calculate();

}
