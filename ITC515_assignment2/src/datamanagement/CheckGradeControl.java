package datamanagement;

public class CheckGradeControl {

	CheckGradeUserInterface checkGradeUi;
	String unitCode = null;
	Integer currentStudentId = null;
	boolean isGradechanged = false;

	public CheckGradeControl() {
	}
	
	
	// This code runs on execution of the program and sets up the
	// GUI.
	public void execute() {
		checkGradeUi = new CheckGradeUserInterface(this);
		checkGradeUi.setUnitActive(false);

		checkGradeUi.setStudentActive(false);
		checkGradeUi.setMarksActive(false);
		checkGradeUi.setChangeButtonActive(false);
		checkGradeUi.setMarkEditable(false);
		checkGradeUi.setGradeDisplayed(false);
		checkGradeUi.refreshWindow();

		ListUnitsControl listUnitControl = new ListUnitsControl();
		listUnitControl.listUnits(checkGradeUi);
		checkGradeUi.setVisible(true);
		checkGradeUi.setUnitActive(true);
	}
	

	public void unitSelected(String unitCode) {

		if (unitCode.equals("NONE")) {
			checkGradeUi.setStudentActive(false);
		}
		else {
      ListStudentsControl listStudentControl = new ListStudentsControl();
      listStudentControl.listStudents(checkGradeUi, unitCode);
      this.unitCode = unitCode;
      checkGradeUi.setStudentActive(true);
		}
		checkGradeUi.setMarksActive(false);
	}
	

	public void studentSelected(Integer studentId) {
		currentStudentId = studentId;
		if (currentStudentId.intValue() == 0) {
			checkGradeUi.refreshWindow();
			checkGradeUi.setMarksActive(false);
			checkGradeUi.setChangeButtonActive(false);
			checkGradeUi.setMarkEditable(false);
			checkGradeUi.setGradeDisplayed(false);
		}

		else {
			IStudent student = StudentManager.getInstance().getStudent(studentId);

			IStudentUnitRecord unitRecord = student.getUnitRecord(unitCode);

			checkGradeUi.setRecord(unitRecord);
			checkGradeUi.setMarksActive(true);
			checkGradeUi.setChangeButtonActive(true);
			checkGradeUi.setMarkEditable(false);
			checkGradeUi.setGradeDisplayed(false);
			isGradechanged = false;

		}
	}
	

	public String checkGrade(float assignment1Grade, float assignment2Grade, 
			float examGrade) {
		IUnit unit = UnitManager.getInstance().getUnit(unitCode);
		String grade = unit.getGrade(assignment1Grade, 
				assignment2Grade, examGrade);
		checkGradeUi.setChangeButtonActive(true);
		checkGradeUi.setMarkEditable(false);
		if (isGradechanged) {
			checkGradeUi.setGradeDisplayed(true);
		}
		return grade;
	}
	

	public void enableChangeMarks() {
		checkGradeUi.setChangeButtonActive(false);
		checkGradeUi.setGradeDisplayed(false);
		checkGradeUi.setMarkEditable(true);
		isGradechanged = true;
	}
	

	public void saveGrade(float assignment1, float assignment2, float exam) {
		IUnit unit = UnitManager.getInstance().getUnit(unitCode);
		IStudent student = StudentManager.getInstance().getStudent(currentStudentId);

		IStudentUnitRecord record = student.getUnitRecord(unitCode);
		record.setAssignment1(assignment1);
		record.setAssignment1(assignment2);
		record.setExam(exam);
		StudentUnitRecordManager.getStudentUnitRecordManager().saveRecord(record);
		checkGradeUi.setChangeButtonActive(true);
		checkGradeUi.setMarkEditable(false);
		checkGradeUi.setGradeDisplayed(false);
	}
}
