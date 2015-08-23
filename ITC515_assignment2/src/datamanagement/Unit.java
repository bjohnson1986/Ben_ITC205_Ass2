package datamanagement;

public class Unit implements IUnit {
	private String subjectCode__;
	private String subjectName__;
	private float alternativeExitCutOff__;
	private float passCutOff__;
	private float creditCutOff__;
	private float distinctionCutOff__;
	private float highDistinctionCutOff__;
	private int assignment1__, assignment2__, exam__;
	private StudentUnitRecordList studentPerUnitRecordList__;

	public Unit(String subjectUnitCode, String subjectUnitName, float passGrade,
			float creditGrade, float distinctionGrade, float highDistinctionGrade,
			float alternativeExitGrade, int assignmentWeightOne,
			int assignmentWeightTwo, int examWeight,
			StudentUnitRecordList constructRecordList) {

		subjectCode__ = subjectUnitCode;
		subjectName__ = subjectUnitName;
		alternativeExitCutOff__ = alternativeExitGrade;
		passCutOff__ = passGrade;
		creditCutOff__ = creditGrade;
		distinctionCutOff__ = distinctionGrade;
		highDistinctionCutOff__ = highDistinctionGrade;

		checkCutOffsAreValid(passCutOff__, creditCutOff__, distinctionCutOff__, highDistinctionCutOff__, alternativeExitCutOff__);
		setAssessmentWeights(assignmentWeightOne, assignmentWeightTwo, examWeight);
		studentPerUnitRecordList__ = constructRecordList == null ? new StudentUnitRecordList()
				: constructRecordList;
	}

	public String getUnitCode() {
		return this.subjectCode__;
	}

	public String getUnitName() {

		return this.subjectName__;
	}

	public float getAlternativeExitCutoff() {
		return this.alternativeExitCutOff__;
	}

	public void setAlternativeExitCutoff(float cutoff) {
		this.alternativeExitCutOff__ = cutoff;
	}

	public float getPassCutoff() {
		return this.passCutOff__;
	}

	public void setPassCutoff(float cutoff) {
		this.passCutOff__ = cutoff;
	}

	public float getCreditCutoff() {
		return this.creditCutOff__;
	}

	public void setCreditCutoff(float cutoff) {
		this.creditCutOff__ = cutoff;
	}

	public float getDistinctionCutoff() {
		return this.distinctionCutOff__;
	}

	public void setDistinctionCutoff(float cutoff) {
		this.distinctionCutOff__ = cutoff;
	}

	public float getHighDistinctionCutoff() {
		return this.highDistinctionCutOff__;

	}

	public void setHighDistinctionCutoff(float cutoff) {
		this.highDistinctionCutOff__ = cutoff;
	}

	public void addStudentRecord(IStudentUnitRecord record) {
		studentPerUnitRecordList__.add(record);
	}

	public IStudentUnitRecord getStudentRecord(int studentId) {
		for (IStudentUnitRecord record : studentPerUnitRecordList__) {
			if (record.getStudentId() == studentId)
				return record;
		}
		return null;
	}

	public StudentUnitRecordList getStudentRecordList() {
		return studentPerUnitRecordList__;
	}

	@Override
	public int getAssignment1Weight() {
		return assignment1__;
	}

	@Override
	public int getAssignment2Weight() {
		return assignment2__;
	}

	@Override
	public int getExamWeight() {
		return exam__;
	}

	@Override
	public void setAssessmentWeights(int assignmentWeightOne,
			int assignmentWeightTwo, int examWeight) {

		boolean isIndividualAssessmentWeightValid = (assignmentWeightOne < 0
				|| assignmentWeightOne > 100 || assignmentWeightTwo < 0
				|| assignmentWeightTwo > 100 || examWeight < 0 || examWeight > 100);

		if (isIndividualAssessmentWeightValid) {
			throw new RuntimeException(
					"Assessment weights cant be less than zero or greater than 100");
		}

		boolean isTotalAssessmentWeightEqualTo100 = assignmentWeightOne
				+ assignmentWeightTwo + examWeight != 100;
		
		if (isTotalAssessmentWeightEqualTo100)
			throw new RuntimeException("Assessment weights must add to 100");
		
		assignment1__ = assignmentWeightOne;
		assignment2__ = assignmentWeightTwo;
		exam__ = examWeight;
	}


	private void checkCutOffsAreValid(float passGrade, float creditGrade, float
	 distinctionGrade, float highDistinctionGrade, float alternativeExitGrade) {
	
	 boolean isGradesValid = alternativeExitGrade < 0 || alternativeExitGrade > 100 ||
	 passGrade < 0 || passGrade > 100 ||
	 creditGrade < 0 || creditGrade > 100 ||
	 distinctionGrade < 0 || distinctionGrade > 100 ||
	 highDistinctionGrade < 0 || highDistinctionGrade > 100;
	
	 if (isGradesValid){
		 throw new
		 RuntimeException("Assessment cutoffs cannot be less than zero or greater than 100.");
	 }
	 else if (alternativeExitGrade >= passGrade) {
		 throw new RuntimeException("AE cutoff must be less than PS cutoff.");
	 }
	 else if (passGrade >= creditGrade) {
		 throw new RuntimeException("PS cutoff must be less than CR cutoff.");
	 }
	 else if (creditGrade >= distinctionGrade) {
		 throw new RuntimeException("CR cutoff must be less than DI cutoff.");
	 }
	 else if (distinctionGrade >= highDistinctionGrade) {
		 throw new RuntimeException("DI cutoff must be less than HD cutoff.");
	 }
	}

	public String getGrade(float assignmentOneMarks, float assignmentTwoMarks,
			float examMarks) {
		float totalAssessmentMarks = assignmentOneMarks + assignmentTwoMarks
				+ examMarks;

		boolean isMarksValid = assignmentOneMarks < 0
				|| assignmentOneMarks > assignment1__ || assignmentTwoMarks < 0
				|| assignmentTwoMarks > assignment2__ || examMarks < 0
				|| examMarks > exam__;

		if (isMarksValid) {
			throw new RuntimeException(
					"Marks cannot be less than zero or greater than assessment weights.");
		}

		if (totalAssessmentMarks < alternativeExitCutOff__) {
			return "FL";
		} 
		else if (totalAssessmentMarks < passCutOff__){
			return "AE";
		}
		else if (totalAssessmentMarks < creditCutOff__){
			return "PS";
		}
		else if (totalAssessmentMarks < distinctionCutOff__){
			return "CR";
		}
		else if (totalAssessmentMarks < highDistinctionCutOff__){
			return "DI";
		}
		else{
			return "HD";
		}
	}

}