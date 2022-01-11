package readerManage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.readerType;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class borrow extends JPanel {
	private JTextField txtType;
	private JTextField txtName;
	private JTextField txtQty;
	private JTextField txtTime;
	private JTextField txtPunish;
	private JTextField txtStatus;

	/**
	 * Create the panel.
	 */
	public static borrow s=new borrow();
	private JTextField txtDay;
	public static borrow getInstance() {
		return s;
	}
	private borrow() {
		setBackground(new Color(153, 255, 255));
		setBorder(null);
		setLayout(null);
		
		JLabel label_id = new JLabel("类别编号");
		label_id.setFont(new Font("楷体", Font.PLAIN, 12));
		label_id.setBounds(45, 49, 83, 15);
		add(label_id);
		
		JLabel label_readerTypeName = new JLabel("类别名称");
		label_readerTypeName.setFont(new Font("楷体", Font.PLAIN, 12));
		label_readerTypeName.setBounds(45, 113, 83, 15);
		add(label_readerTypeName);
		
		JLabel label_qty = new JLabel("可借书数量");
		label_qty.setFont(new Font("楷体", Font.PLAIN, 12));
		label_qty.setBounds(45, 177, 83, 15);
		add(label_qty);
		
		JLabel lblNewLabel_3 = new JLabel("可续借次数");
		lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(45, 305, 83, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("罚款率（元/天）");
		lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(45, 369, 83, 15);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("证件有效期");
		lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(45, 433, 83, 15);
		add(lblNewLabel_5);
		
		txtType = new JTextField();
		txtType.setBackground(new Color(255, 255, 255));
		txtType.setBounds(139, 44, 149, 21);
		add(txtType);
		txtType.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(255, 255, 255));
		txtName.setBounds(139, 109, 149, 21);
		add(txtName);
		txtName.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setBackground(new Color(255, 255, 255));
		txtQty.setBounds(139, 174, 149, 21);
		add(txtQty);
		txtQty.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBackground(new Color(255, 255, 255));
		txtTime.setBounds(139, 304, 149, 21);
		add(txtTime);
		txtTime.setColumns(10);
		
		txtPunish = new JTextField();
		txtPunish.setBackground(new Color(255, 255, 255));
		txtPunish.setBounds(139, 369, 149, 21);
		add(txtPunish);
		txtPunish.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBackground(new Color(255, 255, 255));
		txtStatus.setBounds(139, 434, 149, 21);
		add(txtStatus);
		txtStatus.setColumns(10);
		
		JLabel labelDay = new JLabel("可借书天数");
		labelDay.setFont(new Font("楷体", Font.PLAIN, 12));
		labelDay.setBounds(45, 241, 83, 15);
		add(labelDay);
		
		txtDay = new JTextField();
		txtDay.setBounds(139, 239, 149, 21);
		add(txtDay);
		txtDay.setColumns(10);
	}
	public void setView(readerType model) {
		if(model!=null) {
		txtType.setText(String.valueOf(model.getType()));
		txtName.setText(model.getTypeName());
		txtQty.setText(String.valueOf(model.getCanLendQty()));
		txtDay.setText(String.valueOf(model.getCanLendDay()));
		txtTime.setText(String.valueOf(model.getContinueTimes()));
		txtPunish.setText(String.valueOf(model.getPunishRate()));
		txtStatus.setText(String.valueOf(model.getDateVaild()));
		}
		else {
			JOptionPane.showMessageDialog(null, "空值非法操作");
		}
	}
	
	public readerType  viewToModel() {
		readerType model=null;
		if(txtType.getText().equals("")||txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "未输入类型值,非法操作","警告！",JOptionPane.WARNING_MESSAGE);
		}
		else {
		    model=new readerType ();
			model.setType(Integer.parseInt(txtType.getText()));
			model.setTypeName(txtName.getText());
			model.setCanLendQty(Integer.parseInt(txtQty.getText()));
			model.setCanLendDay(Integer.parseInt(txtDay.getText()));
		    model.setPunishRate(Double.parseDouble(txtPunish.getText()));
		    model.setDateVaild(Integer.parseInt(txtTime.getText()));
		    model.setDateVaild(Integer.parseInt(txtStatus.getText()));
		}
		return model;	
	}
	
}
