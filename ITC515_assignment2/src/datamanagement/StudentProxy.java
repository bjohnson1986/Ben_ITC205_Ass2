package datamanagement;

public class StudentProxy implements IStudent {
  
  private Integer identification_;
  private String firstName_;
  private String lastName_;
  private StudentManager studentManager_;
  

  // StudentProxy constructor. Sets the relevant variables to the passed
  // integers. Additionally, retrieves the StudentManager instance.
  public StudentProxy(Integer id, String firstName, String lastName) {
    this.identification_ = id;
    this.firstName_ = firstName;
    this.lastName_ = lastName;
    this.studentManager_ = StudentManager.get();
  }

  
  public Integer getID() {
    return this.identification_;
  }

  
  public String getFirstName() {
    return this.firstName_;
  }

  
  public String getLastName() {
    return this.lastName_;
  }

  
  public void setFirstName(String firstName) {
    this.studentManager_.getStudent(this.identification_).setFirstName(firstName);
  }

  
  public void setLastName(String lastName) {
    this.studentManager_.getStudent(this.identification_).setLastName(lastName);
  }

  
  // The method addUnitRecord adds a single passed student unit record to
  // the StudentManager of this StudentProxy object.
  public void addUnitRecord(IStudentUnitRecord studentRecord) {
    this.studentManager_.getStudent(this.identification_).addUnitRecord(studentRecord);
  }

  
  // The getUnitRecord method differs from the standard getter that
  // returns the entire unit record list, rather it returns one unit
  // record based on a passed unit code.
  public IStudentUnitRecord getUnitRecord(String unitCode) {
    return this.studentManager_.getStudent(this.identification_).getUnitRecord(unitCode);
  }

  
  public StudentUnitRecordList getUnitRecords() {
    return this.studentManager_.getStudent(this.identification_).getUnitRecords();
  }
  
}