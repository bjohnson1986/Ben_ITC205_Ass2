package datamanagement;

public class StudentUnitRecord implements IStudentUnitRecord {
  
  private Integer studentId_;
  private String unitCode_;
  private float assignmentOneResult_;
  private float assignmentTwoResult_;
  private float examResult_;

  
  // StudentUnitRecord constructor. Takes the passed arguments to set the 
  // variables for the object instance.
  public StudentUnitRecord(Integer id, String unitCode, float assignment1, 
                           float assignment2, float exam) {
    this.studentId_ = id;
    this.unitCode_ = unitCode;
    this.setAsg1(assignment1);
    this.setAsg2(assignment2);
    this.setExam(exam);
  }

  
  public Integer getStudentID() {
    return this.studentId_;
  }

  
  public String getUnitCode() {
    return this.unitCode_;
  }

  
  // Standard setter for assignment 1 but the argument passed must be greater
  // than zero and also less than the total weight of the assignment.
  public void setAsg1(float assignment1) {
    if (assignment1 < 0 || 
        assignment1 > UnitManager.UM().getUnit(this.unitCode_).getAsg1Weight()) {
      throw new RuntimeException("Mark cannot be less than zero," +
                                 " or greater than assessment weight");
    }
    this.assignmentOneResult_ = assignment1;
  }

  
  public float getAsg1() {
    return this.assignmentOneResult_;
  }

  
  // Standard setter for assignment 2 but the argument passed must be greater
  // than zero and also less than the total weight of the assignment.
  public void setAsg2(float assignment2) {
    if (assignment2 < 0 || 
        assignment2 > UnitManager.UM().getUnit(this.unitCode_).getAsg2Weight()) {
      throw new RuntimeException("Mark cannot be less than zero," +
                                 " or greater than assessment weight");
    }
    this.assignmentTwoResult_ = assignment2;
  }

  
  public float getAsg2() {
    return this.assignmentTwoResult_;
  }

  
  // Standard setter for the exam mark but the argument passed must be greater
  // than zero and also less than the total weight of the exam.
  public void setExam(float exam) {
    if (exam < 0 || 
        exam > UnitManager.UM().getUnit(this.unitCode_).getExamWeight()) {
      throw new RuntimeException("Mark cannot be less than zero," +
                                 " or greater than assessment weight");
    }
    this.examResult_ = exam;
  }

  
  public float getExam() {
    return this.examResult_;
  }

  
  // The method getTotal will return the total marks of each assignment,
  // plus the final exam.
  public float getTotal() {
    return this.assignmentOneResult_ + this.assignmentTwoResult_ + this.examResult_;

  }
  
}