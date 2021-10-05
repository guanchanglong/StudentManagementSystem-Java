package com.view;

import com.entity.Teacher;
import com.service.TeacherService;
import com.service.impl.TeacherServiceImpl;
import com.utils.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class ManageTeacherFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final TeacherService teacherService = (TeacherServiceImpl)context.getBean("TeacherServiceImpl");

	private JTable teacherListTable;
	private JTextField searchTeacherNameTextField;
	private JTextField editTeacherNameTextField;
	private JTextField editTeacherTitleTextField;
	private JTextField editTeacherAgeTextField;
	private JPasswordField editTeacherPasswordField;
	private JRadioButton editTeacherSexManRadioButton;
	private JRadioButton editTeacherSexFemalRadioButton;
	private JButton deleteTeacherButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageTeacherFrm frame = new ManageTeacherFrm();
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
	public ManageTeacherFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6559\u5E08\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 805, 691);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u8001\u5E08.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		searchTeacherNameTextField = new JTextField();
		searchTeacherNameTextField.setColumns(10);

		JButton searchTeacherButton = new JButton("\u67E5\u8BE2");
		searchTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		searchTeacherButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6559\u5E08\u4FE1\u606F\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(178)
												.addComponent(label)
												.addGap(33)
												.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
												.addGap(66)
												.addComponent(searchTeacherButton))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(92)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))))
								.addContainerGap(92, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(33)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(searchTeacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label)
										.addComponent(searchTeacherButton))
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
								.addGap(29)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(56, Short.MAX_VALUE))
		);

		JLabel label_1 = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		label_1.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editTeacherNameTextField = new JTextField();
		editTeacherNameTextField.setColumns(10);

		JLabel label_2 = new JLabel("\u6559\u5E08\u6027\u522B\uFF1A");
		label_2.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u6027\u522B.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		ButtonGroup buttonGroup = new ButtonGroup();
		editTeacherSexManRadioButton = new JRadioButton("\u7537");
		editTeacherSexManRadioButton.setSelected(true);

		editTeacherSexFemalRadioButton = new JRadioButton("\u5973");
		buttonGroup.add(editTeacherSexManRadioButton);
		buttonGroup.add(editTeacherSexFemalRadioButton);

		JLabel label_3 = new JLabel("\u6559\u5E08\u804C\u79F0\uFF1A");
		label_3.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u804C\u79F0\u8BC4\u5B9A.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editTeacherTitleTextField = new JTextField();
		editTeacherTitleTextField.setColumns(10);

		JLabel label_4 = new JLabel("\u6559\u5E08\u5E74\u9F84\uFF1A");
		label_4.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5E74\u9F84.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editTeacherAgeTextField = new JTextField();
		editTeacherAgeTextField.setColumns(10);

		JLabel label_5 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_5.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5BC6\u7801.png")));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editTeacherPasswordField = new JPasswordField();

		JButton editTeacherSubmitButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editTeacherSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editTeacherAct(ae);
			}
		});
		editTeacherSubmitButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u786E\u8BA4.png")));
		editTeacherSubmitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		deleteTeacherButton = new JButton("\u5220\u9664\u4FE1\u606F");
		deleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteTeacher(ae);
			}
		});
		deleteTeacherButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5220\u9664.png")));
		deleteTeacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(24)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(editTeacherNameTextField, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(label_3)
														.addComponent(label_5))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(editTeacherPasswordField, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
														.addComponent(editTeacherTitleTextField))))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addGap(27)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(label_2)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(editTeacherSexManRadioButton)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(editTeacherSexFemalRadioButton))
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(label_4)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(editTeacherAgeTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_panel.createSequentialGroup()
												.addGap(39)
												.addComponent(editTeacherSubmitButton)
												.addGap(50)
												.addComponent(deleteTeacherButton)))
								.addGap(67))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(editTeacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2)
										.addComponent(editTeacherSexManRadioButton)
										.addComponent(editTeacherSexFemalRadioButton))
								.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(editTeacherTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(editTeacherAgeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5)
										.addComponent(editTeacherPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(editTeacherSubmitButton)
										.addComponent(deleteTeacherButton))
								.addGap(19))
		);
		panel.setLayout(gl_panel);

		teacherListTable = new JTable();
		teacherListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedTableRow(arg0);
			}
		});
		teacherListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u6559\u5E08ID", "\u6559\u5E08\u59D3\u540D", "\u6559\u5E08\u6027\u522B", "\u6559\u5E08\u804C\u79F0", "\u6559\u5E08\u5E74\u9F84", "\u767B\u5F55\u5BC6\u7801"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(teacherListTable);
		getContentPane().setLayout(groupLayout);
		setTable(new Teacher());
		setAuthority();
	}
	protected void editTeacherAct(ActionEvent ae) {
		int row = teacherListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择要修改的数据！");
			return;
		}
		String teacherName = editTeacherNameTextField.getText().toString();
		String teacherSex = editTeacherSexManRadioButton.isSelected() ? editTeacherSexManRadioButton.getText().toString() : editTeacherSexFemalRadioButton.getText().toString();
		String teacherTitle = editTeacherTitleTextField.getText().toString();
		int teacherAge = 0;
		try {
			teacherAge = Integer.parseInt(editTeacherAgeTextField.getText().toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "年龄只允许输入数字！");
			return;
		}
		String teacherPassword = editTeacherPasswordField.getText().toString();
		if(StringUtil.isEmpty(teacherName)){
			JOptionPane.showMessageDialog(this, "教师姓名必须填写！");
			return;
		}
		if(StringUtil.isEmpty(teacherTitle)){
			JOptionPane.showMessageDialog(this, "教师职称必须填写！");
			return;
		}
		if(teacherAge == 0 || teacherAge < 0){
			JOptionPane.showMessageDialog(this, "教师年龄必须大于0！");
			return;
		}
		if(StringUtil.isEmpty(teacherPassword)){
			JOptionPane.showMessageDialog(this, "教师登录密码必须填写！");
			return;
		}
		Teacher teacher = new Teacher();
		teacher.setId(Integer.parseInt(teacherListTable.getValueAt(row, 0).toString()));
		teacher.setName(teacherName);
		teacher.setSex(teacherSex);
		teacher.setTitle(teacherTitle);
		teacher.setAge(teacherAge);
		teacher.setPassword(teacherPassword);
		if(teacherService.update(teacher)){
			JOptionPane.showMessageDialog(this, "修改成功！");
		}else{
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		setTable(new Teacher());
	}

	protected void searchTeacher(ActionEvent e) {
		String teacherNameString = searchTeacherNameTextField.getText().toString();
		Teacher teacher = new Teacher();
		teacher.setName(teacherNameString);
		setTable(teacher);
	}

	protected void deleteTeacher(ActionEvent ae) {
		int row = teacherListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选择要删除的数据！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "确定要删除么？") != JOptionPane.OK_OPTION)return;
		int id = Integer.parseInt(teacherListTable.getValueAt(row, 0).toString());
		if(teacherService.delete(id)){
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		setTable(new Teacher());
	}

	private void setTable(Teacher teacher){
		if("教师".equals(MainFrm.userType.getName())){
			Teacher tLogined = (Teacher) MainFrm.userObject;
			teacher.setName(tLogined.getName());
			searchTeacherNameTextField.setText(teacher.getName());
		}
		DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();
		dft.setRowCount(0);
		List<Teacher> teacherList = teacherService.getTeacherList(teacher);
		for (Teacher t : teacherList) {
			Vector v = new Vector();
			v.add(t.getId());
			v.add(t.getName());
			v.add(t.getSex());
			v.add(t.getTitle());
			v.add(t.getAge());
			v.add(t.getPassword());
			dft.addRow(v);
		}
	}
	protected void selectedTableRow(MouseEvent me) {
		DefaultTableModel dft = (DefaultTableModel) teacherListTable.getModel();
		editTeacherNameTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 1).toString());
		editTeacherTitleTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 3).toString());
		editTeacherAgeTextField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 4).toString());
		editTeacherPasswordField.setText(dft.getValueAt(teacherListTable.getSelectedRow(), 5).toString());
		String sex = dft.getValueAt(teacherListTable.getSelectedRow(), 2).toString();
		if(sex.equals(editTeacherSexManRadioButton.getText()))editTeacherSexManRadioButton.setSelected(true);
		if(sex.equals(editTeacherSexFemalRadioButton.getText()))editTeacherSexFemalRadioButton.setSelected(true);
	}
	private void setAuthority(){
		if("教师".equals(MainFrm.userType.getName())){
			deleteTeacherButton.setEnabled(false);
			searchTeacherNameTextField.setEditable(false);
		}
	}
}
