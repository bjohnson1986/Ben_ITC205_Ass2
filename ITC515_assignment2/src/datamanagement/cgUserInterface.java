package datamanagement;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cgUserInterface extends javax.swing.JFrame implements IUnitLister,
		IStudentLister {
	private cgControl cgControl;
	private javax.swing.DefaultComboBoxModel unitComboBox;
	private javax.swing.DefaultComboBoxModel studentComboBox;
	float assignment1Value;
	float assignment2Value;
	float examValue;
	Integer studentId;

	public cgUserInterface(cgControl ctl) {
		this.cgControl = ctl;
		unitComboBox = new javax.swing.DefaultComboBoxModel(new String[0]);
		studentComboBox = new javax.swing.DefaultComboBoxModel(new String[0]);
		initComponents();
		unitListComboBox.setModel(unitComboBox);
		studentListComboBox.setModel(studentComboBox);
		errorLabel.setText("");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		titleLabel = new javax.swing.JLabel();
		unitPanel = new javax.swing.JPanel();
		unitListComboBox = new javax.swing.JComboBox();
		studentPanel = new javax.swing.JPanel();
		studentListComboBox = new javax.swing.JComboBox();
		marksPanel = new javax.swing.JPanel();
		assignment1Label = new javax.swing.JLabel();
		assignment2Label = new javax.swing.JLabel();
		examLabel = new javax.swing.JLabel();
		assignment1TextField = new javax.swing.JTextField();
		assignment2TextField = new javax.swing.JTextField();
		examTextField = new javax.swing.JTextField();
		changeButton = new javax.swing.JButton();
		gradePanel = new javax.swing.JPanel();
		gradeLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		titleLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
		titleLabel.setText("Check Grade UI");

		unitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit"));

		unitListComboBox.setModel(unitComboBox);
		unitListComboBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				unitListComboBoxItemStateChanged(evt);
			}
		});

		javax.swing.GroupLayout unitPanelLayout = new javax.swing.GroupLayout(
				unitPanel);
		unitPanel.setLayout(unitPanelLayout);
		unitPanelLayout.setHorizontalGroup(unitPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				unitPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(unitListComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE, 185,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		unitPanelLayout.setVerticalGroup(unitPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				unitPanelLayout
						.createSequentialGroup()
						.addComponent(unitListComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		studentPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Student"));

		studentListComboBox.setModel(studentComboBox);
		studentListComboBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				studentListComboBoxItemStateChanged(evt);
			}
		});

		javax.swing.GroupLayout studentPanelLayout = new javax.swing.GroupLayout(
				studentPanel);
		studentPanel.setLayout(studentPanelLayout);
		studentPanelLayout.setHorizontalGroup(studentPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				studentPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(studentListComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE, 185,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		studentPanelLayout.setVerticalGroup(studentPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				studentPanelLayout
						.createSequentialGroup()
						.addComponent(studentListComboBox,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		marksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marks"));

		assignment1Label.setText("Asg1:");

		assignment2Label.setText("Asg2:");

		examLabel.setText("Exam:");

		assignment1TextField.setEditable(false);
		assignment1TextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				gradeTextFieldKeyTyped(evt);
			}
		});

		assignment2TextField.setEditable(false);
		assignment2TextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				gradeTextFieldKeyTyped(evt);
			}
		});

		examTextField.setEditable(false);
		examTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				gradeTextFieldKeyTyped(evt);
			}
		});

		changeButton.setText("Change");
		changeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeButtonActionPerformed(evt);
			}
		});
		checkGradeButton = new javax.swing.JButton();
		
				checkGradeButton.setText("Check Grade");
				checkGradeButton.setActionCommand("checkGrade");
				checkGradeButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						checkGradeButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout marksPanelLayout = new javax.swing.GroupLayout(
				marksPanel);
		marksPanelLayout.setHorizontalGroup(
			marksPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(marksPanelLayout.createSequentialGroup()
					.addGroup(marksPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(marksPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(assignment1Label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(assignment1TextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(assignment2Label))
						.addGroup(marksPanelLayout.createSequentialGroup()
							.addGap(85)
							.addComponent(changeButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(marksPanelLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(marksPanelLayout.createSequentialGroup()
							.addComponent(assignment2TextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(examLabel))
						.addComponent(checkGradeButton))
					.addGap(18)
					.addComponent(examTextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		marksPanelLayout.setVerticalGroup(
			marksPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(marksPanelLayout.createSequentialGroup()
					.addGroup(marksPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(assignment1Label)
						.addComponent(assignment1TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignment2Label)
						.addComponent(assignment2TextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(examLabel)
						.addComponent(examTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(marksPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeButton)
						.addComponent(checkGradeButton))
					.addContainerGap())
		);
		marksPanel.setLayout(marksPanelLayout);

		gradePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Grade"));

		gradeLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		gradeLabel.setForeground(new java.awt.Color(255, 0, 0));
		gradeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		gradeLabel.setText("grade");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				gradePanel);
		gradePanel.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				gradeLabel, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addGap(34, 34, 34)
						.addComponent(gradeLabel)
						.addContainerGap(43, Short.MAX_VALUE)));
		
		errorLabel = new JLabel();
		errorLabel.setText("Error message");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		saveButton = new javax.swing.JButton();
		
				saveButton.setText("Save");
				saveButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saveButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(marksPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(unitPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(studentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(gradePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup()
							.addGap(157)
							.addComponent(titleLabel))
						.addGroup(layout.createSequentialGroup()
							.addGap(165)
							.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel)
					.addGap(13)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(unitPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(gradePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(marksPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveButton)
					.addGap(11)
					.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void unitListComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox1ItemStateChanged
		String cU = (String) unitListComboBox.getSelectedItem();
		refreshWindow();
		clearStudents();
		if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
			if (cU.equals((String) unitListComboBox.getItemAt(0))) {
				cU = "NONE";
			}
			cgControl.unitSelected(cU);
		}
	}// GEN-LAST:event_jComboBox1ItemStateChanged

	private void studentListComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox2ItemStateChanged
		refreshWindow();
		String cS = (String) studentListComboBox.getSelectedItem();
		if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
			if (cS.equals((String) studentListComboBox.getItemAt(0))) {
				studentId = new Integer(0);
				cgControl.studentSelected(studentId);
			} else {
				studentId = new Integer(cS.split("\\s")[0]);
			}
			cgControl.studentSelected(studentId);
		}
	}// GEN-LAST:event_jComboBox2ItemStateChanged

	private void checkGradeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		assignment1Value = new Float(assignment1TextField.getText()).floatValue();
		assignment2Value = new Float(assignment2TextField.getText()).floatValue();
		examValue = new Float(examTextField.getText()).floatValue();
		//lblErrMsg.setText("");
		try {
			String s = cgControl.checkGrade(assignment1Value, assignment2Value, examValue);
			gradeLabel.setText(s);
		}
		catch (RuntimeException re) {
			errorLabel.setText(re.getMessage());
		}
	}// GEN-LAST:event_jButton3ActionPerformed

	private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		cgControl.enableChangeMarks();
		gradeLabel.setText("");
		//lblErrMsg.setText("");
	}// GEN-LAST:event_jButton1ActionPerformed

	private void gradeTextFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyTyped
		gradeLabel.setText("");
		errorLabel.setText("");
	}// GEN-LAST:event_jTextField1KeyTyped

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		float asg1 = new Float(assignment1TextField.getText()).floatValue();
		float asg2 = new Float(assignment2TextField.getText()).floatValue();
		float exam = new Float(examTextField.getText()).floatValue();
		errorLabel.setText("");
		try {
			cgControl.saveGrade(asg1, asg2, exam);
			//jButton3ActionPerformed(null);
		}
		catch (RuntimeException re) {
			errorLabel.setText(re.getMessage());
		}
	}// GEN-LAST:event_jButton2ActionPerformed

	public void clearUnits() {
		unitComboBox.removeAllElements();
		unitComboBox.addElement("<none selected>");
		clearStudents();
	}

	public void addUnit(IUnit u) {
		unitComboBox.addElement(u.getUnitCode());
	}

	public void setUnitActive(boolean b) {
		unitListComboBox.setEnabled(b);
		errorLabel.setText("");
	}

	public void clearStudents() {
		studentComboBox.removeAllElements();
		studentComboBox.addElement("<none selected>");
	}

	public void addStudent(IStudent student) {
		studentComboBox.addElement(student.getId().toString() + " : "
				+ student.getFirstName() + " " + student.getLastName());
	}

	public void setStudentActive(boolean b) {
		studentListComboBox.setEnabled(b);
		errorLabel.setText("");
	}

	public void setRecord(IStudentUnitRecord record) {
		assignment1TextField.setText(new Float(record.getAssignment1()).toString());
		assignment2TextField.setText(new Float(record.getAssignment2()).toString());
		examTextField.setText(new Float(record.getExam()).toString());
		gradeLabel.setText("");
	}

	public void refreshWindow() {
		assignment1TextField.setText("");
		assignment2TextField.setText("");
		examTextField.setText("");
		gradeLabel.setText("");
		errorLabel.setText("");
		assignment1TextField.setEditable(false);
		assignment2TextField.setEditable(false);
		examTextField.setEditable(false);
	}

	public void setMarksActive(boolean b) {
		checkGradeButton.setEnabled(b);
	}

	public void setChangeButtonActive(boolean b) {
		changeButton.setEnabled(b);
		// gradeLB.setText("");
	}

	public void setMarkEditable(boolean b) {
		assignment1TextField.setEditable(b);
		assignment2TextField.setEditable(b);
		examTextField.setEditable(b);
	}

	public void setGradeDisplayed(boolean b) {
		saveButton.setEnabled(b);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton changeButton;
	private javax.swing.JButton checkGradeButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JComboBox unitListComboBox;
	private javax.swing.JComboBox studentListComboBox;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JLabel assignment1Label;
	private javax.swing.JLabel assignment2Label;
	private javax.swing.JLabel examLabel;
	private javax.swing.JLabel gradeLabel;
	private javax.swing.JLabel errorLabel;
	private javax.swing.JPanel unitPanel;
	private javax.swing.JPanel studentPanel;
	private javax.swing.JPanel marksPanel;
	private javax.swing.JPanel gradePanel;
	private javax.swing.JTextField assignment1TextField;
	private javax.swing.JTextField assignment2TextField;
	private javax.swing.JTextField examTextField;
}
