import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class interfc_parser_allv1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	//  C:/Евг_документы/Диплом/Данные
	private String directoriaSave = "Данные таблиц/";
	private String directoriaSaveYmolch = "Данные таблиц/";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfc_parser_allv1 frame = new interfc_parser_allv1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interfc_parser_allv1() {
		setTitle("\u041F\u0430\u0440\u0441\u0438\u043D\u0433 \u0442\u0430\u0431\u043B\u0438\u0446  \u0441 \u0441\u0430\u0439\u0442\u0430 nngasu.ru");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 439);
		contentPane = new JPanel();
		JTextArea textArea = new JTextArea();
		JTextArea textArea_1 = new JTextArea();
		JButton btnNewButton_6 = new JButton("\u041F\u0440\u0438\u043D\u044F\u0442\u044C");
		btnNewButton_6.setEnabled(false);
		
		setResizable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("");
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u0414\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0438\u044F");
		menuBar.add(mnNewMenu);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("\u0417\u0430\u0434\u0430\u0442\u044C \u043D\u043E\u0432\u0443\u044E");
		JCheckBoxMenuItem chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("По умолчанию");
		chckbxmntmNewCheckItem.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) { //чекбокс в меню
				if(chckbxmntmNewCheckItem.getState()== true) {
				textField.enable(true);
				btnNewButton_6.setEnabled(true);
				chckbxmntmNewCheckItem_1.setState(false);
				}
				else 
					textField.enable(false);				
					
				//directoriaSave = textField.getText();					
			}
		});
		
		
		mnNewMenu.add(chckbxmntmNewCheckItem);
		
		
		chckbxmntmNewCheckItem_1.setState(true);
		chckbxmntmNewCheckItem_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chckbxmntmNewCheckItem_1.getState()== true) {
					textField.setText("");
					btnNewButton_6.setEnabled(false);
					directoriaSave = directoriaSaveYmolch;
					chckbxmntmNewCheckItem.setState(false);
				}
				if(chckbxmntmNewCheckItem_1.getState()== false) {
					chckbxmntmNewCheckItem.setState(true);
					
				}
			}
		});
		mnNewMenu.add(chckbxmntmNewCheckItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("\u0414\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u043E");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C \u043F\u043E\u043B\u044F");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText("");
				textArea_1.setText("");
			}
		});
		
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0422\u0430\u0431\u043B\u0438\u0446\u044B:");
		lblNewLabel.setBounds(20, 30, 165, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u0412\u0430\u043A\u0430\u043D\u0442\u043D\u044B\u0435 \u043C\u0435\u0441\u0442\u0430");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				textArea.setText("Вакантные места для приема (перевода) обучающихся.");
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					vacantPlaces vacPlaces = new vacantPlaces(directoriaSave);
					textArea_1.setText(vacPlaces.rasplFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(20, 62, 250, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u0424\u0438\u043D\u0430\u043D\u0441\u043E\u0432\u043E-\u0445\u043E\u0437\u044F\u0439\u0441\u0442\u0432\u0435\u043D\u043D\u0430\u044F \u0434\u0435\u044F\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("Информация о поступлении и расходовании финансовых и материальных средств.");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					smallTable2 smlTb = new smallTable2(directoriaSave);
					textArea_1.setText(smlTb.raspFile1+ "\n"+smlTb.raspFile2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(20, 96, 250, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u0422\u0440\u0443\u0434\u043E\u0443\u0441\u0442\u0440\u043E\u0439\u0441\u0442\u0432\u043E \u0432\u044B\u043F\u0443\u0441\u043A\u043D\u0438\u043A\u043E\u0432");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				textArea.setText("Сведения о трудоустройстве выпускников за разные года.");

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					trydoystroistvoVipyskn trV = new trydoystroistvoVipyskn(directoriaSave);
					textArea_1.setText(trV.rasplFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(20, 130, 250, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("\u0427\u0438\u0441\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C \u043E\u0431\u0443\u0447\u0430\u044E\u0449\u0438\u0445\u0441\u044F");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("Информация о численности обучающихся по реализуемым образовательным программам в Нижегородском государственном архитектурно-строительном университете.");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					studentChislenst stChisln = new studentChislenst(directoriaSave);
					textArea_1.setText(stChisln.raspFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(20, 164, 250, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B \u043F\u0440\u0438\u0435\u043C\u0430");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("Актуальная информация о результатах приема.");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					resultPriemaInf resPriema = new resultPriemaInf(directoriaSave);
					textArea_1.setText(resPriema.raspFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(20, 198, 250, 23);
		contentPane.add(btnNewButton_5);
		
		
		textArea.setBounds(290, 66, 356, 76);
		contentPane.add(textArea);
		
		JButton btnNewButton_5_1 = new JButton("\u041F\u0435\u0440\u0435\u0432\u043E\u0434\u044B, \u0432\u043E\u0441\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u0438\u044F \u0438 \u043E\u0442\u0447\u0438\u0441\u043B\u0435\u043D\u0438\u044F");
		btnNewButton_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("Информация о результатах перевода, восстановления и отчисления в Нижегородском государственном архитектурно-строительном университете (с нарастающим итогом)."	);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					textArea_1.setText("");
					perevodAndOtchisl perAndOtch = new perevodAndOtchisl(directoriaSave);
					textArea_1.setText(perAndOtch.raspFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5_1.setBounds(20, 232, 250, 23);
		contentPane.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("\u0412\u0441\u0435 \u0442\u0430\u0431\u043B\u0438\u0446\u044B");
		btnNewButton_5_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textArea.setText("Сбор информации со всех выше приведенных таблиц.");
			

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textArea_1.setText("");
					StringBuffer strbf = new StringBuffer();
					perevodAndOtchisl perAndOtch = new perevodAndOtchisl(directoriaSave);
					strbf.append(perAndOtch.raspFile + "\n");
					
					vacantPlaces vacPlaces = new vacantPlaces(directoriaSave);
					strbf.append(vacPlaces.rasplFile + "\n");
					
					personnelPars persPars = new personnelPars(directoriaSave);
					strbf.append(persPars.raspFile + "\n");
					
					trydoystroistvoVipyskn trV = new trydoystroistvoVipyskn(directoriaSave);
					strbf.append(trV.rasplFile + "\n");
					
					smallTable2 smlTb = new smallTable2(directoriaSave);
					strbf.append(smlTb.raspFile1 + "\n" + smlTb.raspFile2 + "\n");
					
					studentChislenst stChisln = new studentChislenst(directoriaSave);
					strbf.append(stChisln.raspFile + "\n");
					
					resultPriemaInf resPriema = new resultPriemaInf(directoriaSave);
					strbf.append(resPriema.raspFile + "\n");
					
					
					textArea_1.setText(strbf.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_5_2.setBounds(20, 266, 250, 23);
		contentPane.add(btnNewButton_5_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 160, 356, 204);
		contentPane.add(scrollPane);
				
		scrollPane.setViewportView(textArea_1);
		
		
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		
		JLabel lblNewLabel_1 = new JLabel("\u041F\u0443\u0442\u044C \u043A \u043F\u0430\u043F\u043A\u0435 \u0441 \u0444\u0430\u0439\u043B\u0430\u043C\u0438: ");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(191, 11, 157, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
				
		//directoriaSave = textField.getText();
		//textArea.setText(directoriaSave);
		textField.setEnabled(false);
		textField.setBounds(191, 30, 356, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				directoriaSave = textField.getText();
				if(directoriaSave == null) {
					directoriaSave = "Данные таблиц/";
				}
				textArea.setText(directoriaSave);
			}
		});
		btnNewButton_6.setBounds(557, 29, 89, 23);
		contentPane.add(btnNewButton_6);
	}
}
