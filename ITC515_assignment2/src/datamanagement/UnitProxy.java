package datamanagement;

public class UnitProxy implements IUnit {
	private String subjectCode__;
	private String subjectName__;
	private UnitManager unitManager__;

	public UnitProxy(String subjectUnitCode, String subjectUnitName) {
		this.subjectCode__ = subjectUnitCode;
		this.subjectName__ = subjectUnitName;
		unitManager__ = UnitManager.getInstance();
	}

		
	public String getUnitCode() {
		return this.subjectCode__;
	}

		
	public String getUnitName() {
		return this.subjectName__;
	}
	
		
	public float getAlternativeExitCutoff() {
		return unitManager__.getUnit(subjectCode__).getAlternativeExitCutoff();
	}

		
	public void setAlternativeExitCutoff(float cutoff) {
		unitManager__.getUnit(subjectCode__).setAlternativeExitCutoff(cutoff);
	}

		
	public float getPassCutoff() {
		return unitManager__.getUnit(subjectCode__).getPassCutoff();
	}
		
	
	public void setPassCutoff(float cutoff) {
		unitManager__.getUnit(subjectCode__).setPassCutoff(cutoff);
	}

		
	public float getCreditCutoff() {
		return unitManager__.getUnit(subjectCode__).getCreditCutoff();
	}
	
		
	public void setCreditCutoff(float cutoff) {
		unitManager__.getUnit(subjectCode__).setCreditCutoff(cutoff);
	}
	
	
	public float getDistinctionCuttoff() {
		return unitManager__.getUnit(subjectCode__).getDistinctionCuttoff();
	}
	
		
	public void setDistinctionCutoff(float cutoff) {
		unitManager__.getUnit(subjectCode__).setDistinctionCutoff(cutoff);
	}

		
	public float getHighDistinctionCutoff() {

		return unitManager__.getUnit(subjectCode__).getHighDistinctionCutoff();
	}
	
		
	public void setHighDistinctionCutoff(float cutoff) {
		unitManager__.getUnit(subjectCode__).setHighDistinctionCutoff(cutoff);
	}

		
	public String getGrade(float assignmentOneMarks, float assignmentTwoMarks, float examOneMarks) {
		return unitManager__.getUnit(subjectCode__).getGrade(assignmentOneMarks, assignmentTwoMarks, examOneMarks);
	}
	
	
	public void addStudentRecord(IStudentUnitRecord record) {
		unitManager__.getUnit(subjectCode__).addStudentRecord(record);
	}
	
	
	public IStudentUnitRecord getStudentRecord(int studentId) {
		return unitManager__.getUnit(subjectCode__).getStudentRecord(studentId);
	}

		
	public StudentUnitRecordList getStudentRecordList() {
		return unitManager__.getUnit(subjectCode__).getStudentRecordList();
	}

		
	public int getAssignment1Weight() {
		return unitManager__.getUnit(subjectCode__).getAssignment1Weight();
	}

		
	public int getAssignment2Weight() {
		return unitManager__.getUnit(subjectCode__).getAssignment2Weight();
	}

		
	public int getExamWeight() {
		return unitManager__.getUnit(subjectCode__).getExamWeight();
	}

		
	public void setAssessmentWeights(int assignmentWeightOne, int assignmentWeightTwo, int examWeight) {
		unitManager__.getUnit(subjectCode__).setAssessmentWeights(assignmentWeightOne, assignmentWeightTwo,
				examWeight);
	}
}
