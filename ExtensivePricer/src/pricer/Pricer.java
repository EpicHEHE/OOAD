package pricer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import pricer.spi.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Pricer extends JFrame {
	
	private JPanel contentPane;
	
	Choice choiceProduct = new Choice();
	Choice choiceAlgorithm = new Choice();
	JPanel panelCalculator = new JPanel();
	JPanel panelExtension = new JPanel();
    JLabel labelPut = new JLabel();
	JLabel labelCall = new JLabel();
	TreeMap<String, JTextField> textFieldMap = new TreeMap<String, JTextField>();
	TreeMap<String, Double> parameterInputMap = new TreeMap<String, Double>();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println(System.getProperty("java.class.path"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pricer frame = new Pricer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Pricer() {
		displayGUI();
	}
	
	public void displayGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane);

		
		tabbedPane.addTab("Calculator", null, panelCalculator, null);
		panelCalculator.setLayout(null);

		tabbedPane.addTab("Extension", null, panelExtension, null);
		
		JButton addJARButton = new JButton("add JAR");
		addJARButton.setHorizontalAlignment(SwingConstants.RIGHT);
		addJARButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "jar", "jar");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(Pricer.this);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				    		   chooser.getSelectedFile().getName());
				       AlgorithmService.getInstance().loadAlgorithms(chooser.getSelectedFile());
				    }
			}
		});
		panelExtension.add(addJARButton);
		
		JLabel labelType = new JLabel("Type");
		labelType.setBounds(30, 20, 100, 20);
		panelCalculator.add(labelType);
		
		choiceProduct.setBounds(130, 20, 150, 20);
		panelCalculator.add(choiceProduct);
		ArrayList<String> ProductList = loadProductList();
		for (int i = 0; i < ProductList.size(); i++){
			choiceProduct.add(ProductList.get(i));	
		}
		
		JLabel labelAlgorithm = new JLabel("Algorithm");
		labelAlgorithm.setBounds(30, 50, 100, 20);
		panelCalculator.add(labelAlgorithm);
		ArrayList<String> AlgorithmList = loadAlgorithmList();
		for (int i = 0; i < AlgorithmList.size(); i++){
			choiceProduct.add(AlgorithmList.get(i));	
		}
		
		choiceAlgorithm.setBounds(130, 50, 150, 20);
		panelCalculator.add(choiceAlgorithm);
				
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList = loadParameter(choiceProduct.getSelectedItem(), choiceAlgorithm.getSelectedItem());
		
		for (int j = 0; j < parameterList.size(); j++){
			JLabel label = new JLabel(parameterList.get(j));
			label.setBounds(30, 80+30*j, 100, 20);
			panelCalculator.add(label);
			
			JTextField textFieldName = new JTextField();
			textFieldName.setBounds(130, 80+30*j, 150, 20);
			panelCalculator.add(textFieldName);
			textFieldName.setColumns(10);
			
			textFieldMap.put(parameterList.get(j), textFieldName);

		}
		
		JButton calculateButton = new JButton("Calculate");
		calculateButton.setBounds(150, 80+30*parameterList.size(), 100, 25);
		panelCalculator.add(calculateButton);
		//the label to show the price for put and call option price
		JLabel put = new JLabel("Put");
		JLabel call = new JLabel("Call");
		put.setBounds(400, 20, 100, 20);
		call.setBounds(500, 20, 100, 20);
		panelCalculator.add(put);
		panelCalculator.add(call);
			
		labelPut.setBounds(400, 50, 100, 20);
		labelCall.setBounds(500, 50, 100, 20);
		panelCalculator.add(labelPut);
		panelCalculator.add(labelCall);

		calculateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				double[] priceArray = new double[2];
				priceArray = calculatePrice();
				labelPut.setText(String.valueOf(priceArray[0]));
				labelCall.setText(String.valueOf(priceArray[1]));
			}
		});
		
		JButton volatilityButton = new JButton("Show Volatility Smile");
		volatilityButton.setBounds(400, 80+30*parameterList.size(), 200, 25);
		panelCalculator.add(volatilityButton);

	}
	
	public double[] calculatePrice(){
		double [] priceArray = new double[2];
//		priceArray[0] = 1.0;
//		priceArray[1] = 2.0;

		String productName = choiceProduct.getSelectedItem();
		String algorithmName = choiceAlgorithm.getSelectedItem();
		for (String parameterName: textFieldMap.keySet()){
			parameterInputMap.put(parameterName, Double.parseDouble(textFieldMap.get(parameterName).getText()));
		}
		
		Algorithm algorithm = ProductAlgorithmManager.getInstance().getAlgorithmSelected(productName, algorithmName);
		algorithm.setParameter(parameterInputMap);
		priceArray = algorithm.calculate();
		
		return priceArray;
	}
	
	public ArrayList<String> loadProductList(){
		ArrayList<String> ProductList = new ArrayList<String>();
		Collections.addAll(ProductList, "American Option","European Option","Asian Option");
//		ProductList = ProductAlgorithmManager.getInstance().getProductList();	
		return ProductList;
	}
	
	public ArrayList<String> loadAlgorithmList(){
		ArrayList<String> Algorithmlist = new ArrayList<String>();
//		String product = choiceProduct.getSelectedItem();
//		Algorithmlist = ProductAlgorithmManager.getInstance().getAlgorithmList(product);
		
		return Algorithmlist;
	}
	
	public ArrayList<String> loadParameter(String productName, String algorithmName){
		ArrayList<String> parameterList = new ArrayList<String>();
//		parameterList =	ProductAlgorithmManager.getInstance().getParameterList(productName, algorithmName);
		Collections.addAll(parameterList, "sNaught Price","Strike Price","Interest Rate", "Term", "Volatility");
		return parameterList;
	}
}
