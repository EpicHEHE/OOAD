package pricer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import pricer.spi.Algorithm;

public class AsianSimulation implements Algorithm {
	public String algorithmName;
	private ArrayList<String> productName;
	TreeMap<String, Float> parameter = new TreeMap<String, Float>();

	public AsianSimulation(){
		algorithmName = "AsianSimulation";
		productName.add("AsianOption");
		parameter.put("sNaught", (float) 0);
		parameter.put("strike", (float) 0);
		parameter.put("volatility", (float) 0);
		parameter.put("riskFreeRate", (float) 0);
		parameter.put("term", (float) 0);
		//parameter.put("side", (float) 0);
		parameter.put("numTimeIntervals", (float) 0);
		parameter.put("numTrials", (float) 0);
		
		}
	
	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return algorithmName;
	}
	
	@Override
	public TreeMap<String, Float> getParameterMap() {
		// TODO Auto-generated method stub
		return parameter;
	}

	

	@Override
	public ArrayList<String> getProductName() {
		// TODO Auto-generated method stub
		return productName;
	}

	@Override
	public Float[] calculate() {
		double sNaught = parameter.get("sNaught");
		double strike = parameter.get("strike");
		double volatility = parameter.get("volatility");
		double riskFreeRate = parameter.get("riskFreeRate");
		double term = parameter.get("term");
		double numTimeIntervals = parameter.get("numTimeIntervals");
		double numTrials = parameter.get("numTrials");
		boolean side = true;
		
		int i, trialCount;
		double deltaT = term/(double)numTimeIntervals;
		double trialRunningSum, trialAverage, trialPayoff;
		double simulationRunningSum, simulationAveragePayoff;
		double s;
		Random rand = new Random();
		simulationRunningSum = 0.0;
		for (trialCount = 1; trialCount <= numTrials; trialCount++) {
			s = sNaught;
			trialRunningSum = 0.0;
			double nns = 0;
			for (i = 0; i < numTimeIntervals; i++) {
				nns = rand.nextGaussian();
				s = s*Math.exp((riskFreeRate-volatility*volatility/2)*deltaT + 
					volatility*nns*Math.sqrt(deltaT));
				trialRunningSum += s;

			}
			trialAverage = trialRunningSum/numTimeIntervals;
			if (side == true)	// call option
				trialPayoff = Math.max(trialAverage - strike, 0.0);
			else				// put option
				trialPayoff = Math.max(strike - trialAverage,  0.0);
			simulationRunningSum += trialPayoff;			
		}
		simulationAveragePayoff = simulationRunningSum / numTrials;
		double valueOfOption;
		valueOfOption = simulationAveragePayoff * Math.exp(-riskFreeRate*term);
		return null;
	}

	@Override
	public void setParameter(TreeMap<String, Float> a) {
		// TODO Auto-generated method stub

	}

}
