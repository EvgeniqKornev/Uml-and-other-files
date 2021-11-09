import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class parser2newInteraface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private String tableName;
	
	private JTextArea textArea;
	private JTextArea textArea_1;
	JButton btnNewButton;
	JButton btnNewButton_1;
	
	private String directoriaSave = "Данные таблиц/";
	private String directoriaSaveYmolch = "Данные таблиц/";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parser2newInteraface frame = new parser2newInteraface();
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
	public parser2newInteraface() {
		setResizable(false);
		setTitle("\u041F\u0430\u0440\u0441\u0438\u043D\u0433 \u0442\u0430\u0431\u043B\u0438\u0446  \u0441 \u0441\u0430\u0439\u0442\u0430 nngasu.ru");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 441);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u0414\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0438\u044F");
		menuBar.add(mnNewMenu);
		btnNewButton = new JButton("\u041F\u0440\u0438\u043D\u044F\u0442\u044C");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				directoriaSave = textField.getText();
				if(directoriaSave == null) {
					directoriaSave = "Данные таблиц/";
				}
				textField_1.setText(directoriaSave);
			}
		});
		textField = new JTextField();
		textField_1 = new JTextField();
		btnNewButton_1 = new JButton("\u041F\u0430\u0440\u0441\u0438\u043D\u0433 \u0432\u044B\u0431\u0440\u0430\u043D\u043D\u043E\u0439 \u0442\u0430\u0431\u043B\u0438\u0446\u044B");
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("\u041F\u043E \u0443\u043C\u043E\u043B\u0447\u0430\u043D\u0438\u044E");
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("\u0417\u0430\u0434\u0430\u0442\u044C \u043D\u043E\u0432\u0443\u044E");
		chckbxmntmNewCheckItem_1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(chckbxmntmNewCheckItem_1.getState()== true) {
					textField.setText("");
					btnNewButton.setEnabled(false);
					directoriaSave = directoriaSaveYmolch;
					chckbxmntmNewCheckItem.setState(false);
					
				}
				if(chckbxmntmNewCheckItem_1.getState()== false) {
					chckbxmntmNewCheckItem.setState(true);
					
				}
			}
		}); 
		
		chckbxmntmNewCheckItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxmntmNewCheckItem.getState()== true) {
					textField.setEnabled(true);
					textField_1.setText(directoriaSave);
					btnNewButton.setEnabled(true);
					chckbxmntmNewCheckItem_1.setState(false);
					}
					else {
						textField.setEnabled(false);
						textField_1.setText(directoriaSaveYmolch);
					}
			}
		});
		mnNewMenu.add(chckbxmntmNewCheckItem);
		
		
		chckbxmntmNewCheckItem_1.setSelected(true);
		mnNewMenu.add(chckbxmntmNewCheckItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("\u0414\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u043E");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C \u043F\u043E\u043B\u044F");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText("");
				textArea_1.setText("");
			}
		});
		
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0422\u0430\u0431\u043B\u0438\u0446\u044B \u0434\u043B\u044F \u043F\u0430\u0440\u0441\u0438\u043D\u0433\u0430:");
		lblNewLabel.setBounds(10, 76, 170, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0423\u043A\u0430\u0436\u0438\u0442\u0435 \u043D\u043E\u0432\u044B\u0439 \u043F\u0443\u0442\u044C:");
		lblNewLabel_1.setBounds(10, 28, 187, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u0422\u0435\u043A\u0443\u0449\u0438\u0439 \u043F\u0443\u0442\u044C:");
		lblNewLabel_1_1.setBounds(10, 53, 170, 14);
		contentPane.add(lblNewLabel_1_1);
		
		
		textField.setEnabled(false);
		textField.setBounds(203, 25, 350, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(203, 53, 350, 20);
		contentPane.add(textField_1);
		
		
		btnNewButton.setBounds(563, 24, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 303, 207);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"\u0412\u0430\u043A\u0430\u043D\u0442\u043D\u044B\u0435 \u043C\u0435\u0441\u0442\u0430", "\u0424\u0438\u043D\u0430\u043D\u0441\u043E\u0432\u043E-\u0445\u043E\u0437\u044F\u0439\u0441\u0442\u0432\u0435\u043D\u043D\u0430\u044F \u0434\u0435\u044F\u0442\u0435\u043B\u044C\u043D\u043E\u0441\u0442\u044C", "\u0422\u0440\u0443\u0434\u043E\u0443\u0441\u0442\u0440\u043E\u0439\u0441\u0442\u0432\u043E \u0432\u044B\u043F\u0443\u0441\u043A\u043D\u0438\u043A\u043E\u0432", "\u0427\u0438\u0441\u043B\u0435\u043D\u043D\u043E\u0441\u0442\u044C \u043E\u0431\u0443\u0447\u0430\u044E\u0449\u0438\u0445\u0441\u044F", "\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442\u044B \u043F\u0440\u0438\u0435\u043C\u0430", "\u041F\u0435\u0440\u0435\u0432\u043E\u0434\u044B, \u0432\u043E\u0441\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u0438\u044F \u0438 \u043E\u0442\u0447\u0438\u0441\u043B\u0435\u043D\u0438\u044F", "\u0412\u0441\u0435 \u0442\u0430\u0431\u043B\u0438\u0446\u044B"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
		        if (!evt.getValueIsAdjusting()) { // Игнорируем событие mouseDown
		            // Получаем выбранное значение
		            String val = list.getSelectedValue().toString();
		            // Устанавливаем полученное значение в текстовое поле
		            tableName = val;
		            dopolnName(tableName);
		        }
				
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(338, 116, 314, 53);
		contentPane.add(scrollPane_2);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_2.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(338, 181, 314, 175);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		
		btnNewButton_1.setBounds(20, 333, 279, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(tableName) {
				case "Вакантные места":
					textArea_1.setText("");
					vacantPlaces vacPlaces;
					try {
						vacPlaces = new vacantPlaces(directoriaSave);
						textArea_1.setText(vacPlaces.rasplFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
				
				case "Финансово-хозяйственная деятельность":	
					textArea_1.setText("");
					smallTable2 smlTb;
					try {
						smlTb = new smallTable2(directoriaSave);
						textArea_1.setText(smlTb.raspFile1+ "\n"+smlTb.raspFile2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
					
				case "Трудоустройство выпускников":	
					textArea_1.setText("");
					trydoystroistvoVipyskn trV;
					try {
						trV = new trydoystroistvoVipyskn(directoriaSave);
						textArea_1.setText(trV.rasplFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
					
				case "Численность обучающихся":	
					textArea_1.setText("");
					studentChislenst stChisln;
					try {
						stChisln = new studentChislenst(directoriaSave);
						textArea_1.setText(stChisln.raspFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
					
				case "Результаты приема":	
					textArea_1.setText("");
					resultPriemaInf resPriema;
					try {
						resPriema = new resultPriemaInf(directoriaSave);
						textArea_1.setText(resPriema.raspFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
					
				case "Переводы, восстановления и отчисления":	
					textArea_1.setText("");
					perevodAndOtchisl perAndOtch;
					try {
						perAndOtch = new perevodAndOtchisl(directoriaSave);
						textArea_1.setText(perAndOtch.raspFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					break;
					
				case "Все таблицы":	
					try {
						textArea_1.setText("");
						StringBuffer strbf = new StringBuffer();
						perevodAndOtchisl perAndOtch1 = new perevodAndOtchisl(directoriaSave);
						strbf.append(perAndOtch1.raspFile + "\n");
						
						vacantPlaces vacPlaces1 = new vacantPlaces(directoriaSave);
						strbf.append(vacPlaces1.rasplFile + "\n");
																		
						trydoystroistvoVipyskn trV1 = new trydoystroistvoVipyskn(directoriaSave);
						strbf.append(trV1.rasplFile + "\n");
						
						smallTable2 smlTb1 = new smallTable2(directoriaSave);
						strbf.append(smlTb1.raspFile1 + "\n" + smlTb1.raspFile2 + "\n");
						
						studentChislenst stChisln1 = new studentChislenst(directoriaSave);
						strbf.append(stChisln1.raspFile + "\n");
						
						resultPriemaInf resPriema1 = new resultPriemaInf(directoriaSave);
						strbf.append(resPriema1.raspFile + "\n");
						
						
						textArea_1.setText(strbf.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
			}
			}
			});
		
	}
	
	public void dopolnName(String name) {
		
		switch(name) {
			case "Вакантные места":
				textArea.setText("Вакантные места для приема (перевода) обучающихся.");
				break;
			
			case "Финансово-хозяйственная деятельность":	
				textArea.setText("Информация о поступлении и расходовании финансовых и материальных средств.");
				break;
				
			case "Трудоустройство выпускников":	
				textArea.setText("Сведения о трудоустройстве выпускников за разные года.");
				break;
				
			case "Численность обучающихся":	
				textArea.setText("Информация о численности обучающихся по реализуемым "
						+ "образовательным программам в Нижегородском государственном архитектурно-строительном университете.");
				break;
				
			case "Результаты приема":	
				textArea.setText("Актуальная информация о результатах приема.");
				break;
				
			case "Переводы, восстановления и отчисления":	
				textArea.setText("Информация о результатах перевода, восстановления и "
						+ "отчисления в Нижегородском государственном архитектурно-строительном университете (с нарастающим итогом).");
				break;
				
			case "Все таблицы":	
				textArea.setText("Сбор информации со всех выше приведенных таблиц.");
				break;
		}
	}
}
