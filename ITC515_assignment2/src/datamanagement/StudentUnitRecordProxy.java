package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentIdentification__;
	private String subjectCode__;
	private StudentUnitRecordManager studentUnitManager__;

	public StudentUnitRecordProxy(Integer studentIdentification, String subjectCode) {
		this.studentIdentification__ = studentIdentification;
		this.subjectCode__ = subjectCode;
		this.studentUnitManager__ = StudentUnitRecordManager.getStudentUnitRecordManager();
	}

	public Integer getStudentID() {
		return studentIdentification__;
	}

	public String getUnitCode() {
		return subjectCode__;
	}

	public float getAsg1() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).getAsg1();
	}
	
	public void setAsg1(float assignmentOneMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).setAsg1(assignmentOneMark);
	}

	public float getAsg2() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).getAsg2();
	}
	
	public void setAsg2(float assignmentTwoMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).setAsg2(assignmentTwoMark);
	}

	public float getExam() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).getExam();
	}
	
	public void setExam(float examMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).setExam(examMark);
	}

	public float getTotal() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__, subjectCode__).getTotal();
	}
}
