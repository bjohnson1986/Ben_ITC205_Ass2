package datamanagement;

public class cgControl {

	cgUI cgUI;
	String coureCode = null;
	Integer currentStudentID = null;
	boolean changed = false;

	public cgControl() {
	}

	public void execute() {
		cgUI = new cgUI(this);
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

		if (code.equals("NONE"))
			cgUI.setStudentActive(false);
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

	public String checkGrade(float f, float g, float h) {
		IUnit u = UnitManager.UM().getUnit(coureCode);
		String s = u.getGrade(f, g, h);
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

	public void saveGrade(float asg1, float asg2, float exam) {

		IUnit u = UnitManager.UM().getUnit(coureCode);
		IStudent s = StudentManager.get().getStudent(currentStudentID);

		IStudentUnitRecord r = s.getUnitRecord(coureCode);
		r.setAsg1(asg1);
		r.setAsg2(asg2);
		r.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(r);
		cgUI.setChangeButtonActive(true);
		cgUI.setMarkEditable(false);
		cgUI.setGradeDisplayed(false);
	}
}
