package datamanagement;

public class Student implements IStudent {

  private Integer identification_;
  private String firstName_;
  private String lastName_;
  private StudentUnitRecordList studentUnitRecordList_;

  
  public Student(Integer identification, String firstName, String lastName,
                 StudentUnitRecordList studentUnitRecordList) {
    this.identification_ = identification;
    this.firstName_ = firstName;
    this.lastName_ = lastName;
    this.studentUnitRecordList_ = (studentUnitRecordList == null ? new StudentUnitRecordList()
                                   : studentUnitRecordList);
  }


  public Integer getID() {
    return this.identification_;
  }

  
  public String getFirstName() {
    return this.firstName_;
  }

  
  public void setFirstName(String firstName) {
    this.firstName_ = firstName;
  }

  
  public String getLastName() {
    return this.lastName_;
  }

  
  public void setLastName(String lastName) {
    this.lastName_ = lastName;
  }

  
  public void addUnitRecord(IStudentUnitRecord studentUnitRecord) {
    studentUnitRecordList_.add(studentUnitRecord);
  }

  
  public IStudentUnitRecord getUnitRecord(String unitCode) {
    for (IStudentUnitRecord r : studentUnitRecordList_) {
      if (r.getUnitCode().equals(unitCode)) {
        return r;
      }
    }

    return null;
  }

  
  public StudentUnitRecordList getUnitRecords() {
    return this.studentUnitRecordList_;
  }

}