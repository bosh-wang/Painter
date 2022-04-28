/*
  109403055 ��ޤG ���f��
*/


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class MyJFrame extends JFrame{
	
	/*           declaring area                    */
	JPanel upper1, upper2, upper3, upper4, upper5, upper6, paintArea, panelStatus;
	JLabel label1, label2;
	static JLabel statusLabel = new JLabel();
	JRadioButton radioButton_small, radioButton_medium, radioButton_big;
	JCheckBox checkbox1;
	JButton button_painterColor,button_deleteAll, button_eraser, button_storeImage;
	Color painterColor;
	
	PaintPanel paintPanel;
	
	int UNIT_WIDTH = 130;
	int UNIT_HEIGHT = 60;
	int gap = 10;
	int PAINT_WIDTH = 825;
	int PAINT_HEIGHT = 360;
	
	Data data = new Data();
	
	/*-----------------------*/
	
	//  constructor
	public MyJFrame() {
		
		upper1 = new JPanel();
		//upper1.setBackground(Color.black);
		upper1.setBounds(gap, gap, UNIT_WIDTH, UNIT_HEIGHT);
		upper1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.add(upper1);
		
		label1 = new JLabel("ø�Ϥu��");
		upper1.add(label1);
		
		// create comboBox
				JComboBox<String> combobox1 = new JComboBox<>();
				String[] paintersSelection = {"����", "���u", "����", "�x��"}; 
				for(int i=0;i<paintersSelection.length;i++)
					combobox1.addItem(paintersSelection[i]);
				//combobox1.setFont(new Font("�L�n������", Font.BOLD, 15));
				combobox1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String painter = (String)combobox1.getSelectedItem();
						switch(painter) {
						case "����":
							//System.out.println("��� ����");
							data.paintTool = "����";
							break;
						case "���u":
							//System.out.println("��� ���u");
							data.paintTool = "���u";
							break;
						case "����":
							//System.out.println("��� ����");
							data.paintTool = "����";
							break;
						case "�x��":
							//System.out.println("��� �x��");
							data.paintTool = "�x��";
							break;
						}
						
						data.eraser = false;
						
					}
				});
				upper1.add(combobox1);
				
				
		
		upper2 = new JPanel();
		//upper2.setBackground(Color.black);
		upper2.setBounds(gap*2+ UNIT_WIDTH, gap, UNIT_WIDTH, UNIT_HEIGHT);
		upper2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.add(upper2);
		
		label2 = new JLabel("����j�p");
		//label2.setFont(new Font("�з���", Font.BOLD, 15));
		upper2.add(label2);
		
		// create radioButton
		radioButton_small = new JRadioButton("�p");
		//radioButton_small.setFont(new Font("�L�n������", Font.BOLD, 15));
		
		//initialize the thickness
		radioButton_small.setSelected(true);
		radioButton_small.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("��� �p ����");
				data.thicknessInt = 3;
			}
		});
		upper2.add(radioButton_small);
		
		
		radioButton_medium = new JRadioButton("��");
		//radioButton_medium.setFont(new Font("�L�n������", Font.BOLD, 15));
		radioButton_medium.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("��� �� ����");
				data.thicknessInt = 7;
			}
		});
		upper2.add(radioButton_medium);
		

		radioButton_big = new JRadioButton("�j");
		//radioButton_big.setFont(new Font("�L�n������", Font.BOLD, 15));
		radioButton_big.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//0.System.out.println("��� �j ����");
				data.thicknessInt = 11;
			}
		});
		upper2.add(radioButton_big);
		
		
		// choose one option at one time
		ButtonGroup buttonGroup_Size = new ButtonGroup();
		buttonGroup_Size.add(radioButton_small);
		buttonGroup_Size.add(radioButton_medium);
		buttonGroup_Size.add(radioButton_big);
		
		
		upper3 = new JPanel();
		//upper3.setBackground(Color.black);
		upper3.setBounds(gap*3+ UNIT_WIDTH*2, gap, UNIT_WIDTH, UNIT_HEIGHT);
		upper3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.add(upper3);
		
		// create checkBox
		checkbox1 = new JCheckBox("��");
				//checkbox1.setFont(new Font("�з���", Font.BOLD, 15));
		checkbox1.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkbox1.isSelected()) {
					//System.out.println("��� ��");
					data.filled = true;
				}
				if(!checkbox1.isSelected()) {
					//System.out.println("���� ��");
					data.filled = false;
				}
						
			}
		});
		upper3.add(checkbox1);
		
		
		upper4 = new JPanel();
		//upper4.setBackground(Color.black);
		upper4.setBounds(gap*4+ UNIT_WIDTH*3, gap, UNIT_WIDTH, UNIT_HEIGHT);
		upper4.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.add(upper4);
		
		// create button
		button_painterColor = new JButton("�����C��");
		//button_painterColor.setFont(new Font("�з���", Font.BOLD, 15));
		button_painterColor.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("�����C��");
				painterColor = JColorChooser.showDialog(null, "set color", Color.black);
				data.color = painterColor;
			}
		});
		upper4.add(button_painterColor);
		
		
		upper5 = new JPanel();
		//upper5.setBackground(Color.black);
		upper5.setBounds(gap*5+ UNIT_WIDTH*4, gap, UNIT_WIDTH, UNIT_HEIGHT);
		upper5.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.add(upper5);
		
		button_deleteAll = new JButton("�M���e��");
		//button_deleteAll.setFont(new Font("�з���", Font.BOLD, 15));
		button_deleteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.clearPanel();
				data.paintTool = "����";
				//System.out.println("�M���e��");
				
			}
		});
		upper5.add(button_deleteAll);
		
		
		upper6 = new JPanel();
		//upper6.setBackground(Color.black);
		upper6.setBounds(gap*6+ UNIT_WIDTH*5, gap, UNIT_WIDTH, UNIT_HEIGHT+10);
		this.add(upper6);
		
		button_eraser = new JButton("�����");
		button_eraser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				data.eraser = true;
				data.paintTool = "";
				//System.out.println("��� �����");
			}
		});
		upper6.add(button_eraser);
		
		
		//bonus function store the image
		button_storeImage = new JButton("�x�s�Ϥ�");
		button_storeImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				paintPanel.storeImage();
			}
		});
		upper6.add(button_storeImage, BorderLayout.SOUTH);		
		
		
		
		//creating paint area
		paintArea = new JPanel();
		paintArea.setBounds(gap, gap*2 + UNIT_HEIGHT, PAINT_WIDTH, PAINT_HEIGHT);
		//paintArea.setBackground(Color.black);
		paintArea.setLayout(new BorderLayout());
		Border blackline = BorderFactory.createLineBorder(Color.black);
		paintArea.setBorder(blackline);

		paintPanel = new PaintPanel();
		paintArea.add(paintPanel);
		this.add(paintArea);
		
		
		//creating status bar
		panelStatus = new JPanel();
		//panelStatus.setBackground(Color.black);
		panelStatus.setBounds(gap, gap*2 + UNIT_HEIGHT+PAINT_HEIGHT, PAINT_WIDTH, 20);
		panelStatus.setLayout(new BorderLayout());
		
		
		this.add(panelStatus);
		
		//statusLabel = new JLabel("");
		panelStatus.add(statusLabel, BorderLayout.SOUTH);
		
		
	}
	
	
}
