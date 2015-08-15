package datamanagement;

public class cgControl {

	cgUserInterface cgUI;
	String coureCode = null;
	Integer currentStudentID = null;
	boolean changed = false;

	public cgControl() {
	}
	
	
	// This code runs on execution of the program and sets up the
	// GUI.
	public void execute() {
		cgUI = new cgUserInterface(this);
		cgUI.setUnitActive(false);

		cgUI.setStudentActive(false);
		cgUI.setMarksActive(false);
		cgUI.setChangeButtonActive(false);
		cgUI.setMarkEditable(false);
		cgUI.setGradeDisplayed(false);
		cgUI.refreshWindow();

		ListUnitsCTL luCTL = new ListUnitsCTL();
		luCTL.listUnits(cgUI);
		cgUI.setVisible(true);
		cgUI.setUnitActive(true);
	}
	

	public void unitSelected(String code) {

		if (code.equals("NONE")) {
			cgUI.setStudentActive(false);
		}
		else {
			ListStudentsCTL lsCTL = new ListStudentsCTL();
			lsCTL.listStudents(cgUI, code);
			coureCode = code;
			cgUI.setStudentActive(true);
		}
		cgUI.setMarksActive(false);
	}
	

	public void studentSelected(Integer id) {
		currentStudentID = id;
		if (currentStudentID.intValue() == 0) {
			cgUI.refreshWindow();
			cgUI.setMarksActive(false);
			cgUI.setChangeButtonActive(false);
			cgUI.setMarkEditable(false);
			cgUI.setGradeDisplayed(false);
		}

		else {
			IStudent s = StudentManager.get().getStudent(id);

			IStudentUnitRecord r = s.getUnitRecord(coureCode);

			cgUI.setRecord(r);
			cgUI.setMarksActive(true);
			cgUI.setChangeButtonActive(true);
			cgUI.setMarkEditable(false);
			cgUI.setGradeDisplayed(false);
			changed = false;

		}
	}
	

	public String checkGrade(float assignment1Grade, float assignment2Grade, float examGrade) {
		IUnit unit = UnitManager.UM().getUnit(coureCode);
		String s = unit.getGrade(assignment1Grade, assignment2Grade, examGrade);
		cgUI.setChangeButtonActive(true);
		cgUI.setMarkEditable(false);
		if (changed) {
			cgUI.setGradeDisplayed(true);
		}
		return s;
	}
	

	public void enableChangeMarks() {
		cgUI.setChangeButtonActive(false);
		cgUI.setGradeDisplayed(false);
		cgUI.setMarkEditable(true);
		changed = true;
	}
	

	public void saveGrade(float assignment1, float assignment2, float exam) {
		IUnit unit = UnitManager.UM().getUnit(coureCode);
		IStudent student = StudentManager.get().getStudent(currentStudentID);

		IStudentUnitRecord record = student.getUnitRecord(coureCode);
		record.setAssignment1(assignment1);
		record.setAssignment1(assignment2);
		record.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(record);
		cgUI.setChangeButtonActive(true);
		cgUI.setMarkEditable(false);
		cgUI.setGradeDisplayed(false);
	}
}
