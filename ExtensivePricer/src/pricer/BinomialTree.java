package pricer;
import java.util.Map;

import pricer.spi.Algorithm;


public class BinomialTree implements Algorithm {
	public String algorithmName;
	
	public BinomialTree(){
		algorithmName = "binomialTree";
	}

	@Override
	public Map<String, Float> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(Map<String, Float> a) {
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

}
