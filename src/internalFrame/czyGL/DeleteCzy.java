package internalFrame.czyGL;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.DatabaseOp;
import internalFrame.CzyGL;


public class DeleteCzy extends JPanel {
	private JTextField userName;
	private JPasswordField passField;
	private JTextField logName;
	private JTable table;
	private DefaultTableModel dftm;
	private String[] columnNames;
	public DeleteCzy() {
		super();
		setBounds(0, 0, 491, 287);
		setLayout(new GridBagLayout());

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(0, 0, 20, 0);
		gridBagConstraints.gridwidth = 12;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 35;
		gridBagConstraints.ipady = -195;
		add(scrollPane, gridBagConstraints);

		table = new JTable();
		table.setRowHeight(20);
		dftm = (DefaultTableModel) table.getModel();
		columnNames = new String[]{"登录名", "用户名", "密码", "权限"};
		dftm.setColumnIdentifiers(columnNames);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String uName, passstr, logName_1;
				int selRow = table.getSelectedRow();
				uName = table.getValueAt(selRow, 0).toString().trim();
				passstr = table.getValueAt(selRow, 2).toString().trim();
				logName_1 = table.getValueAt(selRow, 1).toString().trim();
				logName.setText(uName);
				passField.setText(passstr);
				userName.setText(logName_1);
			}
		});
		scrollPane.setViewportView(table);

		final JLabel label = new JLabel();
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.gridx = 0;
		add(label, gridBagConstraints_3);
		label.setText("登录名：");

		logName = new JTextField();
		logName.setEditable(false);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_4.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_4.weightx = 1;
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 3;
		add(logName, gridBagConstraints_4);

		final JLabel label_2 = new JLabel();
		label_2.setText("用户名：");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.gridy = 2;
		gridBagConstraints_7.gridx = 4;
		add(label_2, gridBagConstraints_7);

		userName = new JTextField();
		userName.setEditable(false);
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.weightx = 1.0;
		gridBagConstraints_8.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8.gridy = 2;
		gridBagConstraints_8.gridx = 5;
		add(userName, gridBagConstraints_8);

		final JLabel label_1 = new JLabel();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 6;
		add(label_1, gridBagConstraints_6);
		label_1.setText("密码：");

		passField = new JPasswordField();
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_5.weightx = 1.0;
		gridBagConstraints_5.gridy = 2;
		gridBagConstraints_5.gridx = 7;
		add(passField, gridBagConstraints_5);
		passField.setEditable(false);

		final JButton button = new JButton("删除");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(5, 0, 5, 0);
		gridBagConstraints_1.gridy = 7;
		gridBagConstraints_1.gridx = 4;
		add(button, gridBagConstraints_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(DeleteCzy.this,
						"确认要删除该操作员？");
				if (op == JOptionPane.OK_OPTION) {
					DatabaseOp.delete("delete from UserList where logName='"
							+ logName.getText() + "'"); //数据库查询
					userName.setText("");
					passField.setText("");
					logName.setText("");
					initTable();
				}
			}
		});

		final JButton button_1 = new JButton("关闭");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(5, 0, 5, 0);
		gridBagConstraints_2.gridy = 7;
		gridBagConstraints_2.gridx = 6;
		add(button_1, gridBagConstraints_2);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				CzyGL parent = (CzyGL) DeleteCzy.this.getRootPane().getParent();//数据库查询
				parent.doDefaultCloseAction();
			}
		});
	}
	public void initTable() {
		List ul = DatabaseOp.getCzyInfos();//数据库查询
		Iterator it = ul.iterator();
		String[] data = new String[4];
		dftm.setDataVector(null, columnNames);
		while (it.hasNext()) {
			List userlist = (List) it.next();
			data[0] = (String) userlist.get(0);
			data[1] = (String) userlist.get(1);
			data[2] = (String) userlist.get(2);
//			data[3] = ((String) userlist.get(3)).equals("1")? "系统管理员": "普通用户";
			if(((String)userlist.get(3)).equals("1"))
				data[3]="系统管理员";
			else
				data[3]="普通用户";
			dftm.addRow(data);
			
		}
		setVisible(true);
	}
}
