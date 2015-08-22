package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentId__;
	private String subjectCode__;
	private StudentUnitRecordManager studentUnitManager__;

	public StudentUnitRecordProxy(Integer studentId,
			String subjectCode) {
		this.studentId__ = studentId;
		this.subjectCode__ = subjectCode;
		this.studentUnitManager__ = StudentUnitRecordManager.getInstance();
	}
	
	
	public Integer getStudentId() {
		return studentId__;
	}
	
	
	public String getSubjectCode() {
		return subjectCode__;
	}
	
	
	public float getAssignment1() {
		return studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).getAssignment1();
	}
	
	
	public void setAssignment1(float assignmentOneMark) {
		studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).setAssignment1(assignmentOneMark);
	}
	
	
	public float getAssignment2() {
		return studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).getAssignment2();
	}

	
	public void setAssignment2(float assignmentTwoMark) {
		studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).setAssignment2(assignmentTwoMark);
	}
	
	
	public float getExam() {
		return studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).getExam();
	}
	
	
	public void setExam(float examMark) {
		studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).setExam(examMark);
	}

		
	public float getTotalMarks() {
		return studentUnitManager__.getStudentUnitRecord(studentId__,
				subjectCode__).getTotalMarks();
	}
}
