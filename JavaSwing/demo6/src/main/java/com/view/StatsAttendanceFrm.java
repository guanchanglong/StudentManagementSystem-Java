package com.view;

import com.entity.Course;
import com.entity.Teacher;
import com.service.AdminService;
import com.service.AttendanceService;
import com.service.CourseService;
import com.service.impl.AdminServiceImpl;
import com.service.impl.CourseServiceImpl;
import com.utils.Chooser;
import com.utils.StringUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;

public class StatsAttendanceFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final AdminService adminService = (AdminServiceImpl)context.getBean("AdminServiceImpl");
	private final AttendanceService attendanceService = (AttendanceService) context.getBean("AttendanceServiceImpl");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");

	private JTextField dateTextField;
	private JTable statsListTable;
	private List<Course> courseList = new ArrayList<Course>();
	private JComboBox courseComboBox;
	private JScrollPane statsListScrollPane;
	private JPanel statsListPanel;
	private ChartPanel chartPanel ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsAttendanceFrm frame = new StatsAttendanceFrm();
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
	public StatsAttendanceFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u7B7E\u5230\u4FE1\u606F\u7EDF\u8BA1\u60C5\u51B5");
		setBounds(100, 100, 701, 696);

		JLabel label = new JLabel("\u8BFE\u7A0B\uFF1A");
		label.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangedAct(ie);
			}
		});

		JLabel label_1 = new JLabel("\u65E5\u671F\uFF1A");
		label_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u65E5\u671F.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		dateTextField = new JTextField();
		dateTextField.setColumns(10);

		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchAttendanceAct(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		statsListPanel = new JPanel();
		statsListPanel.setBorder(new TitledBorder(null, "\u7B7E\u5230\u4FE1\u606F\u7EDF\u8BA1\u60C5\u51B5", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5207\u6362\u663E\u793A\u65B9\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(60)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(statsListPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(label)
												.addGap(18)
												.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
												.addGap(27)
												.addComponent(label_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addGap(27)
												.addComponent(searchButton)))
								.addContainerGap(89, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(36)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1)
										.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(searchButton))
								.addGap(46)
								.addComponent(statsListPanel, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
								.addContainerGap())
		);

		JButton button = new JButton("\u5217\u8868\u663E\u793A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//列表显示
				clearPanel();
				statsListPanel.setLayout(new BorderLayout());
				statsListPanel.add(statsListScrollPane);
			}
		});
		button.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u5217\u8868.png")));

		JButton button_1 = new JButton("\u67F1\u72B6\u56FE\u663E\u793A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dateString = dateTextField.getText().toString();
				if(StringUtil.isEmpty(dateString)){
					JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "请选择日期!");
					return;
				}
				//柱状图显示
				clearPanel();
				Course course = (Course)courseComboBox.getSelectedItem();
				drawBar(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
			}
		});
		button_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u67F1\u72B6\u56FE.png")));

		JButton button_2 = new JButton("\u997C\u72B6\u56FE\u663E\u793A");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//饼状图显示
				String dateString = dateTextField.getText().toString();
				if(StringUtil.isEmpty(dateString)){
					JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "请选择日期!");
					return;
				}
				//柱状图显示
				clearPanel();
				Course course = (Course)courseComboBox.getSelectedItem();
				drawCircle(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
			}
		});
		button_2.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u997C\u72B6\u56FE.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(53)
								.addComponent(button)
								.addGap(47)
								.addComponent(button_1)
								.addGap(60)
								.addComponent(button_2)
								.addGap(67))
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(button_1)
										.addComponent(button_2))
								.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		statsListScrollPane = new JScrollPane();
		GroupLayout gl_statsListPanel = new GroupLayout(statsListPanel);
		gl_statsListPanel.setHorizontalGroup(
				gl_statsListPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(statsListScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
		);
		gl_statsListPanel.setVerticalGroup(
				gl_statsListPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statsListPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(statsListScrollPane, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
								.addContainerGap())
		);

		statsListTable = new JTable();
		statsListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u4EBA\u6570", "\u7F3A\u5E2D\u4EBA\u6570", "\u9009\u8BFE\u4EBA\u6570", "\u65E5\u671F"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		statsListScrollPane.setViewportView(statsListTable);
		statsListPanel.setLayout(gl_statsListPanel);
		getContentPane().setLayout(groupLayout);
		Chooser.getInstance().register(dateTextField);
		setCourseCombox();
		setTable();
	}
	protected void drawCircle(int attendanceNum, int selected_num, String dateString) {
		setLanuage();
		DefaultPieDataset dataSet = new DefaultPieDataset();//创建数据集
		dataSet.setValue("出勤",attendanceNum);//设置数据
		dataSet.setValue("缺勤人树",selected_num-attendanceNum);
		dataSet.setValue("总人数",selected_num);
		JFreeChart chart = ChartFactory.createPieChart3D("学生考勤签到统计", dataSet, true, true, false);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(540,420));
		statsListPanel.add(chartPanel,BorderLayout.CENTER);
		statsListPanel.setLayout(new FlowLayout());
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}

	protected void courseChangedAct(ItemEvent ie) {
		if(ie.getStateChange() == ItemEvent.SELECTED){
			setTable();
		}
	}

	private void searchAttendanceAct(ActionEvent ae){
		setTable();
	}
	private void setCourseCombox(){
		courseList = courseService.getCourseList(new Course());
		for (Course course : courseList) {
			if("教师".equals(MainFrm.userType.getName())){
				Teacher teacher = (Teacher)MainFrm.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					courseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseComboBox.addItem(course);
		}

	}
	private void setTable(){
		Course course = (Course)courseComboBox.getSelectedItem();
		String dateString = dateTextField.getText().toString();
		List<HashMap<String, String>> attendanceStatsList = attendanceService.getAttendanceStatsList(course.getId(), dateString);
		DefaultTableModel dft = (DefaultTableModel) statsListTable.getModel();
		dft.setRowCount(0);
		for(HashMap<String, String> attendanceStats : attendanceStatsList){
			Set<Entry<String, String>> entrySet = attendanceStats.entrySet();
			int attendanceNum = 0 ;
			String dateString2 = "";
			for(Entry<String, String> entry : entrySet){
				attendanceNum = Integer.parseInt(entry.getKey());
				dateString2 = entry.getValue();
			}
			Vector v = new Vector();
			v.add(course.getName());
			v.add(attendanceNum);
			v.add(course.getSelected_num()-attendanceNum);
			v.add(course.getSelected_num());
			v.add(dateString2);
			dft.addRow(v);
		}
	}

	private void drawBar(int attendanceNum,int studentNum, String date){
		setLanuage();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//创建一个数据集
		dataSet.addValue(attendanceNum, date, "出席人数");//添加数据
		dataSet.addValue(studentNum-attendanceNum, date, "缺勤人数");
		dataSet.addValue(studentNum, date, "总人数");
		//创建一个chart对象，把数据集放进去
		JFreeChart chart = ChartFactory.createBarChart3D("学生签到统计情况", "出席类别", "天数", dataSet, PlotOrientation.VERTICAL, true, false, false);
		//创建一个图标panel
		chartPanel= new ChartPanel(chart);
		//将图标panel添加到要显示的panel上
		chartPanel.setPreferredSize(new Dimension(500,420));
		statsListPanel.add(chartPanel,BorderLayout.CENTER);
		statsListPanel.setLayout(new FlowLayout());
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}

	private void clearPanel(){
		statsListPanel.removeAll();
		statsListPanel.updateUI();
		statsListPanel.repaint();
	}

	private void setLanuage(){
		//创建主题样式
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
		//设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
		//设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));
		//设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));
		//应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
	}

	private int getAttendanceNum(Course course,String dateString){
		List<HashMap<String, String>> attendanceStatsList = attendanceService.getAttendanceStatsList(course.getId(), dateString);
		int attendanceNum = 0 ;
		for(HashMap<String, String> attendanceStats : attendanceStatsList){
			Set<Entry<String, String>> entrySet = attendanceStats.entrySet();
			for(Entry<String, String> entry : entrySet){
				attendanceNum = Integer.parseInt(entry.getKey());
			}
		}
		return attendanceNum;
	}
}
