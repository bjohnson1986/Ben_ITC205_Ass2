package datamanagement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CheckGradeUserInterface extends JFrame implements IUnitLister,
    IStudentLister {
  //Variable used for storing data in the GUI.
  private CheckGradeControl checkGradeControl_;
  private DefaultComboBoxModel<String> unitComboBox_;
  private DefaultComboBoxModel<String> studentComboBox_;
  private float assignment1Value_;
  private float assignment2Value_;
  private float examValue_;
  private Integer studentId_;
  
  //Variables storing GUI components.
  private JButton changeButton_;
  private JButton checkGradeButton_;
  private JButton saveButton_;
  private JComboBox unitListComboBox_;
  private JComboBox studentListComboBox_;
  private JLabel titleLabel_;
  private JLabel assignment1Label_;
  private JLabel assignment2Label_;
  private JLabel examLabel_;
  private JLabel gradeLabel_;
  private JLabel errorLabel_;
  private JPanel unitPanel_;
  private JPanel studentPanel_;
  private JPanel marksPanel_;
  private JPanel gradePanel_;
  private JTextField assignment1TextField_;
  private JTextField assignment2TextField_;
  private JTextField examTextField_;

  
  public CheckGradeUserInterface(CheckGradeControl checkGradeControl) {
    this.checkGradeControl_ = checkGradeControl;
    unitComboBox_ = new DefaultComboBoxModel<>(new String[0]);
    studentComboBox_ = new DefaultComboBoxModel<>(new String[0]);
    initializeComponents();
    unitListComboBox_.setModel(unitComboBox_);
    studentListComboBox_.setModel(studentComboBox_);
    errorLabel_.setText("");
  }

  
  private void initializeComponents() {

    titleLabel_ = new JLabel();
    unitPanel_ = new JPanel();
    unitListComboBox_ = new JComboBox<>();
    studentPanel_ = new JPanel();
    studentListComboBox_ = new JComboBox<>();
    marksPanel_ = new JPanel();
    assignment1Label_ = new JLabel();
    assignment2Label_ = new JLabel();
    examLabel_ = new JLabel();
    assignment1TextField_ = new JTextField();
    assignment2TextField_ = new JTextField();
    examTextField_ = new JTextField();
    changeButton_ = new JButton();
    gradePanel_ = new JPanel();
    gradeLabel_ = new JLabel();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    titleLabel_.setFont(new Font("Tahoma", 0, 16)); // NOI18N
    titleLabel_.setText("Check Grade UI");

    unitPanel_.setBorder(BorderFactory.createTitledBorder("Unit"));

    unitListComboBox_.setModel(unitComboBox_);
    unitListComboBox_.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent event) {
        unitListComboBoxItemStateChanged(event);
      }
    });

    GroupLayout unitPanelLayout = new GroupLayout(unitPanel_);
    unitPanel_.setLayout(unitPanelLayout);
    unitPanelLayout.setHorizontalGroup(unitPanelLayout.createParallelGroup(
        GroupLayout.Alignment.LEADING).addGroup(
        unitPanelLayout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(unitListComboBox_, GroupLayout.PREFERRED_SIZE, 185,
                GroupLayout.PREFERRED_SIZE)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    unitPanelLayout.setVerticalGroup(unitPanelLayout.createParallelGroup(
        GroupLayout.Alignment.LEADING).addGroup(
        unitPanelLayout
            .createSequentialGroup()
            .addComponent(unitListComboBox_, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    studentPanel_.setBorder(BorderFactory.createTitledBorder("Student"));

    studentListComboBox_.setModel(studentComboBox_);
    studentListComboBox_.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent event) {
        studentListComboBoxItemStateChanged(event);
      }
    });

    GroupLayout studentPanelLayout = new GroupLayout(studentPanel_);
    studentPanel_.setLayout(studentPanelLayout);
    studentPanelLayout.setHorizontalGroup(studentPanelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
            studentPanelLayout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(studentListComboBox_, GroupLayout.PREFERRED_SIZE,
                    185, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    studentPanelLayout.setVerticalGroup(studentPanelLayout.createParallelGroup(
        GroupLayout.Alignment.LEADING).addGroup(
        studentPanelLayout
            .createSequentialGroup()
            .addComponent(studentListComboBox_, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    marksPanel_.setBorder(BorderFactory.createTitledBorder("Marks"));

    assignment1Label_.setText("Asg1:");

    assignment2Label_.setText("Asg2:");

    examLabel_.setText("Exam:");

    assignment1TextField_.setEditable(false);
    assignment1TextField_.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent event) {
        gradeTextFieldKeyTyped(event);
      }
    });

    assignment2TextField_.setEditable(false);
    assignment2TextField_.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent event) {
        gradeTextFieldKeyTyped(event);
      }
    });

    examTextField_.setEditable(false);
    examTextField_.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent event) {
        gradeTextFieldKeyTyped(event);
      }
    });

    changeButton_.setText("Change");
    changeButton_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        changeButtonActionPerformed(event);
      }
    });
    checkGradeButton_ = new JButton();

    checkGradeButton_.setText("Check Grade");
    checkGradeButton_.setActionCommand("checkGrade");
    checkGradeButton_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        checkGradeButtonActionPerformed(event);
      }
    });

    GroupLayout marksPanelLayout = new GroupLayout(marksPanel_);
    marksPanelLayout.setHorizontalGroup(marksPanelLayout.createParallelGroup(
        Alignment.LEADING).addGroup(
        marksPanelLayout
            .createSequentialGroup()
            .addGroup(
                marksPanelLayout
                    .createParallelGroup(Alignment.LEADING)
                    .addGroup(
                        marksPanelLayout
                            .createSequentialGroup()
                            .addContainerGap()
                            .addComponent(assignment1Label_)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(assignment1TextField_,
                                GroupLayout.PREFERRED_SIZE, 59,
                                GroupLayout.PREFERRED_SIZE).addGap(18)
                            .addComponent(assignment2Label_))
                    .addGroup(
                        marksPanelLayout
                            .createSequentialGroup()
                            .addGap(85)
                            .addComponent(changeButton_,
                                GroupLayout.PREFERRED_SIZE, 84,
                                GroupLayout.PREFERRED_SIZE)))
            .addGap(18)
            .addGroup(
                marksPanelLayout
                    .createParallelGroup(Alignment.TRAILING)
                    .addGroup(
                        marksPanelLayout
                            .createSequentialGroup()
                            .addComponent(assignment2TextField_,
                                GroupLayout.PREFERRED_SIZE, 59,
                                GroupLayout.PREFERRED_SIZE).addGap(18)
                            .addComponent(examLabel_))
                    .addComponent(checkGradeButton_))
            .addGap(18)
            .addComponent(examTextField_, GroupLayout.PREFERRED_SIZE, 59,
                GroupLayout.PREFERRED_SIZE).addGap(15)));
    marksPanelLayout.setVerticalGroup(marksPanelLayout.createParallelGroup(
        Alignment.LEADING).addGroup(
        marksPanelLayout
            .createSequentialGroup()
            .addGroup(
                marksPanelLayout
                    .createParallelGroup(Alignment.BASELINE)
                    .addComponent(assignment1Label_)
                    .addComponent(assignment1TextField_,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(assignment2Label_)
                    .addComponent(assignment2TextField_,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(examLabel_)
                    .addComponent(examTextField_, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addGroup(
                marksPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(changeButton_).addComponent(checkGradeButton_))
            .addContainerGap()));
    marksPanel_.setLayout(marksPanelLayout);

    gradePanel_.setBorder(BorderFactory.createTitledBorder("Grade"));

    gradeLabel_.setFont(new Font("Tahoma", 0, 24)); // NOI18N
    gradeLabel_.setForeground(new Color(255, 0, 0));
    gradeLabel_.setHorizontalAlignment(SwingConstants.CENTER);
    gradeLabel_.setText("grade");

    GroupLayout gradeLayout = new GroupLayout(gradePanel_);
    gradePanel_.setLayout(gradeLayout);
    gradeLayout.setHorizontalGroup(gradeLayout.createParallelGroup(
        GroupLayout.Alignment.LEADING).addComponent(gradeLabel_,
        GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153,
        Short.MAX_VALUE));
    gradeLayout.setVerticalGroup(gradeLayout.createParallelGroup(
        GroupLayout.Alignment.LEADING).addGroup(
        gradeLayout.createSequentialGroup().addGap(34, 34, 34)
            .addComponent(gradeLabel_).addContainerGap(43, Short.MAX_VALUE)));

    errorLabel_ = new JLabel();
    errorLabel_.setText("Error message");
    errorLabel_.setForeground(Color.RED);
    errorLabel_.setFont(new Font("Tahoma", Font.PLAIN, 12));
    saveButton_ = new JButton();

    saveButton_.setText("Save");
    saveButton_.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        saveButtonActionPerformed(event);
      }
    });

    GroupLayout layout = new GroupLayout(getContentPane());
    layout
        .setHorizontalGroup(layout
            .createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addGroup(
                        layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(errorLabel_,
                                        GroupLayout.DEFAULT_SIZE, 400,
                                        Short.MAX_VALUE))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                Alignment.LEADING, false)
                                            .addComponent(marksPanel_,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                Alignment.LEADING)
                                                            .addComponent(
                                                                unitPanel_,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(
                                                                studentPanel_,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18)
                                                    .addComponent(
                                                        gradePanel_,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))))
                            .addGroup(
                                layout.createSequentialGroup().addGap(157)
                                    .addComponent(titleLabel_))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(165)
                                    .addComponent(saveButton_,
                                        GroupLayout.PREFERRED_SIZE, 86,
                                        GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
        .addGroup(
            layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel_)
                .addGap(13)
                .addGroup(
                    layout
                        .createParallelGroup(Alignment.LEADING)
                        .addGroup(
                            layout
                                .createSequentialGroup()
                                .addComponent(unitPanel_,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(studentPanel_,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                        .addComponent(gradePanel_, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(marksPanel_, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(saveButton_)
                .addGap(11)
                .addComponent(errorLabel_, GroupLayout.PREFERRED_SIZE, 30,
                    GroupLayout.PREFERRED_SIZE).addContainerGap()));
    getContentPane().setLayout(layout);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  
  private void unitListComboBoxItemStateChanged(ItemEvent event) {// GEN-FIRST:event_jComboBox1ItemStateChanged
    String unitSelected = (String) unitListComboBox_.getSelectedItem();
    refreshWindow();
    clearStudents();
    if (event.getStateChange() == ItemEvent.SELECTED) {
      if (unitSelected.equals((String) unitListComboBox_.getItemAt(0))) {
        unitSelected = "NONE";
      }
      checkGradeControl_.unitSelected(unitSelected);
    }
  }

  
  private void studentListComboBoxItemStateChanged(ItemEvent event) {// GEN-FIRST:event_jComboBox2ItemStateChanged
    refreshWindow();
    String studentSelected = (String) studentListComboBox_.getSelectedItem();
    if (event.getStateChange() == ItemEvent.SELECTED) {
      if (studentSelected.equals((String) studentListComboBox_.getItemAt(0))) {
        studentId_ = new Integer(0);
        checkGradeControl_.studentSelected(studentId_);
      } else {
        studentId_ = new Integer(studentSelected.split("\\s")[0]);
      }
      checkGradeControl_.studentSelected(studentId_);
    }
  }

  private void checkGradeButtonActionPerformed(ActionEvent event) {// GEN-FIRST:event_jButton3ActionPerformed
    assignment1Value_ = new Float(assignment1TextField_.getText()).floatValue();
    assignment2Value_ = new Float(assignment2TextField_.getText()).floatValue();
    examValue_ = new Float(examTextField_.getText()).floatValue();
    try {
      String grade = checkGradeControl_.checkGrade(assignment1Value_,
          assignment2Value_, examValue_);
      gradeLabel_.setText(grade);
    } catch (RuntimeException e) {
      errorLabel_.setText(e.getMessage());
    }
  }
  

  private void changeButtonActionPerformed(ActionEvent event) {// GEN-FIRST:event_jButton1ActionPerformed
    checkGradeControl_.enableChangeMarks();
    gradeLabel_.setText("");
  }


  private void gradeTextFieldKeyTyped(KeyEvent event) {// GEN-FIRST:event_jTextField1KeyTyped
    gradeLabel_.setText("");
    errorLabel_.setText("");
  }


  private void saveButtonActionPerformed(ActionEvent event) {// GEN-FIRST:event_jButton2ActionPerformed
    float assignment1 = new Float(assignment1TextField_.getText()).floatValue();
    float assignment2 = new Float(assignment2TextField_.getText()).floatValue();
    float exam = new Float(examTextField_.getText()).floatValue();
    errorLabel_.setText("");
    try {
      checkGradeControl_.saveGrade(assignment1, assignment2, exam);
    } catch (RuntimeException e) {
      errorLabel_.setText(e.getMessage());
    }
  }


  public void clearUnits() {
    unitComboBox_.removeAllElements();
    unitComboBox_.addElement("<none selected>");
    clearStudents();
  }
  

  public void addUnit(IUnit unit) {
    unitComboBox_.addElement(unit.getUnitCode());
  }

  
  public void setUnitActive(boolean isActive) {
    unitListComboBox_.setEnabled(isActive);
    errorLabel_.setText("");
  }

  
  public void clearStudents() {
    studentComboBox_.removeAllElements();
    studentComboBox_.addElement("<none selected>");
  }

  
  public void addStudent(IStudent student) {
    studentComboBox_.addElement(student.getId().toString() + " : "
        + student.getFirstName() + " " + student.getLastName());
  }

  
  public void setStudentActive(boolean isActive) {
    studentListComboBox_.setEnabled(isActive);
    errorLabel_.setText("");
  }

  
  public void setRecord(IStudentUnitRecord studentUnitRecord) {
    assignment1TextField_.setText(new Float(studentUnitRecord.getAssignment1()).toString());
    assignment2TextField_.setText(new Float(studentUnitRecord.getAssignment2()).toString());
    examTextField_.setText(new Float(studentUnitRecord.getExam()).toString());
    gradeLabel_.setText("");
  }
  

  public void refreshWindow() {
    assignment1TextField_.setText("");
    assignment2TextField_.setText("");
    examTextField_.setText("");
    gradeLabel_.setText("");
    errorLabel_.setText("");
    assignment1TextField_.setEditable(false);
    assignment2TextField_.setEditable(false);
    examTextField_.setEditable(false);
  }
  

  public void setMarksActive(boolean isActive) {
    checkGradeButton_.setEnabled(isActive);
  }

  
  public void setChangeButtonActive(boolean isActive) {
    changeButton_.setEnabled(isActive);
  }

  
  public void setMarkEditable(boolean isActive) {
    assignment1TextField_.setEditable(isActive);
    assignment2TextField_.setEditable(isActive);
    examTextField_.setEditable(isActive);
  }

  
  public void setGradeDisplayed(boolean isActive) {
    saveButton_.setEnabled(isActive);
  }
  
}
