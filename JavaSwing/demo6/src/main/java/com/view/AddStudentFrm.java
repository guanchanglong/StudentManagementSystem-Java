package com.view;

import com.entity.Student;
import com.entity.StudentClass;
import com.service.ClassService;
import com.service.StudentService;
import com.service.impl.ClassServiceImpl;
import com.service.impl.StudentServiceImpl;
import com.utils.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddStudentFrm extends JInternalFrame {

	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final ClassService classService = (ClassServiceImpl)context.getBean("ClassServiceImpl");
	private final StudentService studentService = (StudentServiceImpl)context.getBean("StudentServiceImpl");

	private JTextField studentNameTextField;
	private JPasswordField studentPasswordField;
	private JComboBox studentClassComboBox;
	private ButtonGroup sexButtonGroup;
	private JRadioButton studentSexManRadioButton;
	private JRadioButton studentSexFemalRadioButton;
	private JRadioButton studentSexUnkonwRadioButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentFrm frame = new AddStudentFrm();
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
	public AddStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u5B66\u751F");
		setBounds(100, 100, 450, 300);

		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);

		JLabel label_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		label_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentClassComboBox = new JComboBox();
		studentClassComboBox.setModel(new DefaultComboBoxModel(new String[] {}));

		JLabel label_2 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/password.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentPasswordField = new JPasswordField();

		JLabel label_3 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		label_3.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u6027\u522B.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentSexManRadioButton = new JRadioButton("\u7537");
		studentSexManRadioButton.setSelected(true);

		studentSexFemalRadioButton = new JRadioButton("\u5973");

		studentSexUnkonwRadioButton = new JRadioButton("\u4FDD\u5BC6");

		sexButtonGroup = new ButtonGroup();
		sexButtonGroup.add(studentSexManRadioButton);
		sexButtonGroup.add(studentSexFemalRadioButton);
		sexButtonGroup.add(studentSexUnkonwRadioButton);

		JButton submitButton = new JButton("\u786E\u8BA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				studentAddAct(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(91)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_2)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(studentPasswordField, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_1)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(studentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
												.addGap(92))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(resetButton)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_3)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(studentSexManRadioButton)
																.addGap(10)
																.addComponent(studentSexFemalRadioButton)
																.addGap(10)
																.addComponent(studentSexUnkonwRadioButton)))
												.addContainerGap())))
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(132)
								.addComponent(submitButton)
								.addContainerGap(221, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(studentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(studentPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(studentSexManRadioButton)
										.addComponent(studentSexUnkonwRadioButton)
										.addComponent(studentSexFemalRadioButton))
								.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(submitButton)
										.addComponent(resetButton))
								.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setStudentClassInfo();
	}

	protected void resetValue(ActionEvent ae) {
		// TODO Auto-generated method stub
		studentNameTextField.setText("");
		studentPasswordField.setText("");
		studentClassComboBox.setSelectedIndex(0);
		sexButtonGroup.clearSelection();
		studentSexManRadioButton.setSelected(true);
	}

	protected void studentAddAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		String studentName = studentNameTextField.getText().toString();
		String studentPassword = studentPasswordField.getText().toString();
		if(StringUtil.isEmpty(studentName)){
			JOptionPane.showMessageDialog(this, "请填写学生姓名!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword)){
			JOptionPane.showMessageDialog(this, "请填写密码!");
			return;
		}
		StudentClass sc = (StudentClass)studentClassComboBox.getSelectedItem();
		String sex = studentSexManRadioButton.isSelected() ? studentSexManRadioButton.getText() : (studentSexFemalRadioButton.isSelected() ? studentSexFemalRadioButton.getText() : studentSexUnkonwRadioButton.getText());
		Student student = new Student();
		student.setName(studentName);
		student.setClassId(sc.getId());
		student.setPassword(studentPassword);
		student.setSex(sex);
		if(studentService.addStudent(student)){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetValue(ae);
	}

	private void setStudentClassInfo(){
		List<StudentClass> classList = classService.getClassList(new StudentClass());
		for (StudentClass sc : classList) {
			studentClassComboBox.addItem(sc);
		}
	}
}
