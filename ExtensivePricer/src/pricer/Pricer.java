package pricer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Collections;

public class Pricer extends JFrame {
	
	private JPanel contentPane;
	
	private JTextField sNaughtPrice;
	private JTextField strikePrice;
	private JTextField interestRate;
	private JTextField term;
	private JTextField volatility;
	Choice choiceProduct = new Choice();
	Choice choiceAlgorithm = new Choice();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

		
		JPanel panelCalculator = new JPanel();
		tabbedPane.addTab("Calculator", null, panelCalculator, null);
		panelCalculator.setLayout(null);

		JPanel panelExtension = new JPanel();
		tabbedPane.addTab("Extension", null, panelExtension, null);
		
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
		
		displayParameter(panelCalculator);
	}
	
	public ArrayList<String> loadProductList(){
		ArrayList<String> ProductList = new ArrayList<String>();
//		Collections.addAll(ProductList, "American Option","European Option","Asian Option");
		ProductList = ProductAlgorithmManager.getInstance().getProductList();	
		return ProductList;
	}
	
	public ArrayList<String> loadAlgorithmList(){
		ArrayList<String> Algorithmlist = new ArrayList<String>();
		String product = choiceProduct.getSelectedItem();
		Algorithmlist = ProductAlgorithmManager.getInstance().getAlgorithmList(product);
		
		return Algorithmlist;
	}
	
	public void displayParameter(JPanel panelCalculator){
		JLabel labelSNaught = new JLabel("sNaught Price");
		labelSNaught.setBounds(30, 80, 100, 20);
		panelCalculator.add(labelSNaught);
		
		sNaughtPrice = new JTextField();
		sNaughtPrice.setBounds(130, 80, 100, 20);
		panelCalculator.add(sNaughtPrice);
		sNaughtPrice.setColumns(10);
		
		JLabel labelStrikePrice = new JLabel("Strike Price");
		labelStrikePrice.setBounds(30, 110, 100, 20);
		panelCalculator.add(labelStrikePrice);
		
		strikePrice = new JTextField();
		strikePrice.setColumns(10);
		strikePrice.setBounds(130, 110, 100, 20);
		panelCalculator.add(strikePrice);
		
		JLabel labelInterest = new JLabel("Interest Rate");
		labelInterest.setBounds(30, 140, 100, 20);
		panelCalculator.add(labelInterest);
		
		interestRate = new JTextField();
		interestRate.setColumns(10);
		interestRate.setBounds(130, 140, 100, 20);
		panelCalculator.add(interestRate);
		
		JLabel labelTerm = new JLabel("Term");
		labelTerm.setBounds(30, 170, 100, 20);
		panelCalculator.add(labelTerm);
		
		term = new JTextField();
		term.setColumns(10);
		term.setBounds(130, 170, 100, 20);
		panelCalculator.add(term);
		
		JLabel labelVolatility = new JLabel("Volatility");
		labelVolatility.setBounds(30, 200, 100, 20);
		panelCalculator.add(labelVolatility);
		
		volatility = new JTextField();
		volatility.setColumns(10);
		volatility.setBounds(130, 200, 100, 20);
		panelCalculator.add(volatility);


	}
}
