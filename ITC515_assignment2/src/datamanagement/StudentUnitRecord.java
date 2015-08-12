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
    this.setAssignment1(assignment1);
    this.setAssignment2(assignment2);
    this.setExam(exam);
  }

  
  public Integer getStudentId() {
    return this.studentId_;
  }

  
  public String getUnitCode() {
    return this.unitCode_;
  }

  
  // Standard setter for assignment 1 but the argument passed must be greater
  // than zero and also less than the total weight of the assignment.
  public void setAssignment1(float assignment1) {
    // The following boolean and if structure is in conjunction with standard 53.
    boolean isInvalid = (assignment1 < 0) || 
                        (assignment1 > UnitManager.UM().
                         getUnit(this.unitCode_).getAsg1Weight());
    if (isInvalid) {
      throw new RuntimeException("Mark cannot be less than zero," +
                                 " or greater than assessment weight");
    }
    
    this.assignmentOneResult_ = assignment1;
  }

  
  public float getAssignment1() {
    return this.assignmentOneResult_;
  }

  
  // Standard setter for assignment 2 but the argument passed must be greater
  // than zero and also less than the total weight of the assignment.
  public void setAssignment2(float assignment2) {
    // The following boolean and if structure is in conjunction with standard 53.
    boolean isInvalid = (assignment2 < 0) ||
                        (assignment2 > UnitManager.UM().
                         getUnit(this.unitCode_).getAsg2Weight());
    if (isInvalid) {
      throw new RuntimeException("Mark cannot be less than zero," +
                                 " or greater than assessment weight");
    }
    
    this.assignmentTwoResult_ = assignment2;
  }

  
  public float getAssignment2() {
    return this.assignmentTwoResult_;
  }

  
  // Standard setter for the exam mark but the argument passed must be greater
  // than zero and also less than the total weight of the exam.
  public void setExam(float exam) {
    // The following boolean and if structure is in conjunction with standard 53.
    boolean isInvalid = (exam < 0) ||
                        (exam > UnitManager.UM().
                         getUnit(this.unitCode_).getExamWeight());
    if (isInvalid) {
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