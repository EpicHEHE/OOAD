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
	
/*	private JTextField sNaughtPrice;
	private JTextField strikePrice;
	private JTextField interestRate;
	private JTextField term;
	private JTextField volatility;
*/	Choice choiceProduct = new Choice();
	Choice choiceAlgorithm = new Choice();
	JPanel panelCalculator = new JPanel();
    JLabel labelPut = new JLabel();
	JLabel labelCall = new JLabel();


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

		JPanel panelExtension = new JPanel();
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
		for (int i=0;i<ProductList.size();i++){
			choiceProduct.add(ProductList.get(i));	
		}
		
		JLabel labelAlgorithm = new JLabel("Algorithm");
		labelAlgorithm.setBounds(30, 50, 100, 20);
		panelCalculator.add(labelAlgorithm);
		ArrayList<String> AlgorithmList = loadAlgorithmList();
		for (int i=0;i<AlgorithmList.size();i++){
			choiceProduct.add(AlgorithmList.get(i));	
		}
		
		choiceAlgorithm.setBounds(130, 50, 150, 20);
		panelCalculator.add(choiceAlgorithm);
				
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList = loadParameter(choiceProduct.getSelectedItem(), choiceAlgorithm.getSelectedItem());
		TreeMap<String, Double> parameterInputMap = new TreeMap<String, Double>();
		
		for (int j=0;j<parameterList.size();j++){
			JLabel label = new JLabel(parameterList.get(j));
			label.setBounds(30, 80+30*j, 100, 20);
			panelCalculator.add(label);
			
			JTextField textFieldName = new JTextField();
			textFieldName.setBounds(130, 80+30*j, 150, 20);
			panelCalculator.add(textFieldName);
			textFieldName.setColumns(10);
			textFieldName.getInputContext();
			
//			parameterInputMap.put(parameterList.get(j), textFieldName.getInputContext());

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

	}
	
	public double[] calculatePrice(){
		double [] priceArray = new double[2];
		priceArray[0] = 1.0;
		priceArray[1] = 2.0;
//		String productName = choiceProduct.getSelectedItem();
//		String algorithmName = choiceAlgorithm.getSelectedItem();
		
//		priceArray = ProductAlgorithmManager.getInstance().getAlgorithmSelected(productName, algorithmName).calculate();
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
/*		JLabel labelSNaught = new JLabel("sNaught Price");
		labelSNaught.setBounds(30, 80, 100, 20);
		panelCalculator.add(labelSNaught);
		
		sNaughtPrice = new JTextField();
		sNaughtPrice.setBounds(130, 80, 150, 20);
		panelCalculator.add(sNaughtPrice);
		sNaughtPrice.setColumns(10);
		
		JLabel labelStrikePrice = new JLabel("Strike Price");
		labelStrikePrice.setBounds(30, 110, 100, 20);
		panelCalculator.add(labelStrikePrice);
		
		strikePrice = new JTextField();
		strikePrice.setColumns(10);
		strikePrice.setBounds(130, 110, 150, 20);
		panelCalculator.add(strikePrice);
		
		JLabel labelInterest = new JLabel("Interest Rate");
		labelInterest.setBounds(30, 140, 100, 20);
		panelCalculator.add(labelInterest);
		
		interestRate = new JTextField();
		interestRate.setColumns(10);
		interestRate.setBounds(130, 140, 150, 20);
		panelCalculator.add(interestRate);
		
		JLabel labelTerm = new JLabel("Term");
		labelTerm.setBounds(30, 170, 100, 20);
		panelCalculator.add(labelTerm);
		
		term = new JTextField();
		term.setColumns(10);
		term.setBounds(130, 170, 150, 20);
		panelCalculator.add(term);
		
		JLabel labelVolatility = new JLabel("Volatility");
		labelVolatility.setBounds(30, 200, 100, 20);
		panelCalculator.add(labelVolatility);
		
		volatility = new JTextField();
		volatility.setColumns(10);
		volatility.setBounds(130, 200, 150, 20);
		panelCalculator.add(volatility);
*/

	}
}
