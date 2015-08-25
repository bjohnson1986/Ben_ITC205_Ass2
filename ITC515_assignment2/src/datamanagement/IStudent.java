package datamanagement;

public interface IStudent {
  
  public Integer getId();
  
  public String getFirstName();
  public void setFirstName(String firstName);
  
  public String getLastName();
  public void setLastName(String lastName);
  
  public void addUnitRecord(IStudentUnitRecord unitRecord);
  public IStudentUnitRecord getUnitRecord(String unitCode);
  
  public StudentUnitRecordList getUnitRecords();

}
