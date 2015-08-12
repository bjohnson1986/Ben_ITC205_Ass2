package datamanagement;

public class Student implements IStudent {

  private Integer identification_;
  private String firstName_;
  private String lastName_;
  private StudentUnitRecordList studentUnitRecordList_;

  
  // Constructor for the Student class. Will create the Student object based
  // on the values of the parameters passed.
  public Student(Integer identification, String firstName, String lastName,
                 StudentUnitRecordList studentUnitRecordList) {
    this.identification_ = identification;
    this.firstName_ = firstName;
    this.lastName_ = lastName;
    this.studentUnitRecordList_ = (studentUnitRecordList == null ?
                                   new StudentUnitRecordList() :
                                   studentUnitRecordList);
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

  
  // The addUnitRecord method will add the passed student unit record
  // to this object's student unit record list.
  public void addUnitRecord(IStudentUnitRecord studentUnitRecord) {
    studentUnitRecordList_.add(studentUnitRecord);
  }

  
  // The getUnitRecord method differs from the standard getter that
  // returns the entire unit record list, rather it returns one unit
  // record, if it exists, based on a passed unit code.
  public IStudentUnitRecord getUnitRecord(String unitCode) {
    for (IStudentUnitRecord record : studentUnitRecordList_) {
      if (record.getUnitCode().equals(unitCode)) {
        return record;
      }
    }

    return null;
  }

  
  public StudentUnitRecordList getUnitRecords() {
    return this.studentUnitRecordList_;
  }

}