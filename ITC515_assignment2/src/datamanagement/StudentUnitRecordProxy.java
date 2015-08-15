package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentIdentification__;
	private String subjectCode__;
	private StudentUnitRecordManager studentUnitManager__;

	public StudentUnitRecordProxy(Integer studentIdentification,
			String subjectCode) {
		this.studentIdentification__ = studentIdentification;
		this.subjectCode__ = subjectCode;
		this.studentUnitManager__ = StudentUnitRecordManager.getStudentUnitRecordManager();
	}
	
	
	public Integer getStudentId() {
		return studentIdentification__;
	}
	
	
	public String getSubjectCode() {
		return subjectCode__;
	}
	
	
	public float getAssignment1() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).getAssignment1();
	}
	
	
	public void setAssignment1(float assignmentOneMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).setAssignment1(assignmentOneMark);
	}
	
	
	public float getAssignment2() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).getAssignment2();
	}

	
	public void setAssignment2(float assignmentTwoMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).setAssignment2(assignmentTwoMark);
	}
	
	
	public float getExam() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).getExam();
	}
	
	
	public void setExam(float examMark) {
		studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).setExam(examMark);
	}

		
	public float getTotalMarks() {
		return studentUnitManager__.getStudentUnitRecord(studentIdentification__,
				subjectCode__).getTotalMarks();
	}
}
