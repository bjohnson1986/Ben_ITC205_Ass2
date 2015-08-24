package datamanagement;

public class CheckGradeControl {

	private CheckGradeUserInterface checkGradeUi_;
	private String unitCode_ = null;
	private Integer currentStudentId_ = null;
	private boolean isGradeChanged_ = false;

	public CheckGradeControl() {
	}
	
	
	// This code runs on execution of the program and sets up the
	// GUI.
	public void execute() {
		checkGradeUi_ = new CheckGradeUserInterface(this);
		checkGradeUi_.setUnitActive(false);

		checkGradeUi_.setStudentActive(false);
		checkGradeUi_.setMarksActive(false);
		checkGradeUi_.setChangeButtonActive(false);
		checkGradeUi_.setMarkEditable(false);
		checkGradeUi_.setGradeDisplayed(false);
		checkGradeUi_.refreshWindow();

		ListUnitsControl listUnitControl = new ListUnitsControl();
		listUnitControl.listUnits(checkGradeUi_);
		checkGradeUi_.setVisible(true);
		checkGradeUi_.setUnitActive(true);
	}
	

	public void unitSelected(String unitCode) {

		if (unitCode.equals("NONE")) {
			checkGradeUi_.setStudentActive(false);
		}
		else {
			ListStudentsControl listStudentControl = new ListStudentsControl();
			listStudentControl.listStudents(checkGradeUi_, unitCode);
			this.unitCode_ = unitCode;
			checkGradeUi_.setStudentActive(true);
		}
		checkGradeUi_.setMarksActive(false);
	}
	

	public void studentSelected(Integer studentId) {
		currentStudentId_ = studentId;
		if (currentStudentId_.intValue() == 0) {
			checkGradeUi_.refreshWindow();
			checkGradeUi_.setMarksActive(false);
			checkGradeUi_.setChangeButtonActive(false);
			checkGradeUi_.setMarkEditable(false);
			checkGradeUi_.setGradeDisplayed(false);
		}

		else {
			IStudent student = StudentManager.getInstance().getStudent(studentId);

			IStudentUnitRecord unitRecord = student.getUnitRecord(unitCode_);

			checkGradeUi_.setRecord(unitRecord);
			checkGradeUi_.setMarksActive(true);
			checkGradeUi_.setChangeButtonActive(true);
			checkGradeUi_.setMarkEditable(false);
			checkGradeUi_.setGradeDisplayed(false);
			isGradeChanged_ = false;

		}
	}
	

	public String checkGrade(float assignment1Grade, float assignment2Grade, 
			float examGrade) {
		IUnit unit = UnitManager.getInstance().getUnit(unitCode_);
		String grade = unit.getGrade(assignment1Grade, 
				assignment2Grade, examGrade);
		checkGradeUi_.setChangeButtonActive(true);
		checkGradeUi_.setMarkEditable(false);
		if (isGradeChanged_) {
			checkGradeUi_.setGradeDisplayed(true);
		}
		return grade;
	}
	

	public void enableChangeMarks() {
		checkGradeUi_.setChangeButtonActive(false);
		checkGradeUi_.setGradeDisplayed(false);
		checkGradeUi_.setMarkEditable(true);
		isGradeChanged_ = true;
	}
	

	public void saveGrade(float assignment1, float assignment2, float exam) {
		IUnit unit = UnitManager.getInstance().getUnit(unitCode_);
		IStudent student = StudentManager.getInstance().getStudent(currentStudentId_);

		IStudentUnitRecord studentUnitRecord = student.getUnitRecord(unitCode_);
		studentUnitRecord.setAssignment1(assignment1);
		studentUnitRecord.setAssignment1(assignment2);
		studentUnitRecord.setExam(exam);
		StudentUnitRecordManager.getInstance().saveRecord(studentUnitRecord);
		checkGradeUi_.setChangeButtonActive(true);
		checkGradeUi_.setMarkEditable(false);
		checkGradeUi_.setGradeDisplayed(false);
	}
}
