package datamanagement;

public class StudentProxy implements IStudent {
  
  private Integer identification_;
  private String firstName_;
  private String lastName_;
  private StudentManager studentManager_;
  

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
    this.studentManager_.getStudent(identification_).setFirstName(firstName);
  }

  
  public void setLastName(String lastName) {
    this.studentManager_.getStudent(identification_).setLastName(lastName);
  }

  
  public void addUnitRecord(IStudentUnitRecord studentRecord) {
    this.studentManager_.getStudent(identification_).addUnitRecord(studentRecord);
  }

  
  public IStudentUnitRecord getUnitRecord(String unitCode) {
    return this.studentManager_.getStudent(identification_).getUnitRecord(unitCode);
  }

  
  public StudentUnitRecordList getUnitRecords() {
    return this.studentManager_.getStudent(identification_).getUnitRecords();
  }
  
}