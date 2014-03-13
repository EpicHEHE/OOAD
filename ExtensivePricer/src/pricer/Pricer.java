package pricer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.Collections;

public class Pricer extends JFrame {
	
	private JPanel contentPane;
	
	private int a;
	private int b;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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

		
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("Calculator", null, panel1, null);
		panel1.setLayout(null);

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Extension", null, panel2, null);
		
		JLabel label = new JLabel("Type");
		label.setBounds(30, 20, 100, 20);
		panel1.add(label);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(130, 20, 100, 20);
		panel1.add(choice_1);
		ArrayList<String> ProductList = loadProductList();
		for (int i=0;i<ProductList.size();i++){
			choice_1.add(ProductList.get(i));	
		}
		
		JLabel label_1 = new JLabel("Algorithm");
		label_1.setBounds(30, 50, 100, 20);
		panel1.add(label_1);
		
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(130, 50, 100, 20);
		panel1.add(choice_2);
		
		displayParameter(panel1);
	}
	
	public ArrayList<String> loadProductList(){
		ArrayList<String> ProductList = new ArrayList<String>();
		Collections.addAll(ProductList, "American Option","European Option","Asian Option");
//		ProductList = ProductAlgorithmManager.getInstance().getProductList();
		
		return ProductList;
	}
	
	
	public void displayParameter(JPanel panel1){
		JLabel label_2 = new JLabel("sNaught Price");
		label_2.setBounds(30, 80, 100, 20);
		panel1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 80, 100, 20);
		panel1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("Strike Price");
		label_3.setBounds(30, 110, 100, 20);
		panel1.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(130, 110, 100, 20);
		panel1.add(textField_2);
		
		JLabel label_4 = new JLabel("Interest Rate");
		label_4.setBounds(30, 140, 100, 20);
		panel1.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(130, 140, 100, 20);
		panel1.add(textField_3);
		
		JLabel label_5 = new JLabel("Term");
		label_5.setBounds(30, 170, 100, 20);
		panel1.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(130, 170, 100, 20);
		panel1.add(textField_4);
		
		JLabel label_6 = new JLabel("Volatility");
		label_6.setBounds(30, 200, 100, 20);
		panel1.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(130, 200, 100, 20);
		panel1.add(textField_5);


	}
}
