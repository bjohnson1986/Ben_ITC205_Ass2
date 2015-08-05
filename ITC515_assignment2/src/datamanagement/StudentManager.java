package datamanagement;

import org.jdom.*;

import java.util.HashMap;
import java.util.List;


public class StudentManager {
  
  private static StudentManager instance_ = null;

  private StudentMap studentMap_;
  private HashMap<String, StudentMap> studentsToUnitsMap_;

  
  public static StudentManager get() {
    if (instance_ == null) {
      instance_ = new StudentManager();
    }

    return instance_;
  }

  
  private StudentManager() {
    studentMap_ = new StudentMap();
    studentsToUnitsMap_ = new HashMap<>();
  }

  
  public IStudent getStudent(Integer id) {
    IStudent iStudent = studentMap_.get(id);
    
    return (iStudent != null ? iStudent : createStudent(id));
  }

  
  @SuppressWarnings("unchecked")
  private Element getStudentElement(Integer id) {
    for (Element el : (List<Element>) XMLManager.getXML().getDocument()
        .getRootElement().getChild("studentTable").getChildren("student")) {
      if (id.toString().equals(el.getAttributeValue("sid"))) {
        return el;
      }
    }
    
    return null;
  }

  
  private IStudent createStudent(Integer id) {
    IStudent iStudent;
    Element element = getStudentElement(id);
    if (element != null) {
      StudentUnitRecordList recordList = StudentUnitRecordManager.instance()
                                         .getRecordsByStudent(id);
      iStudent = new Student(new Integer(element.getAttributeValue("sid")),
                             element.getAttributeValue("fname"), 
                             element.getAttributeValue("lname"), recordList);
      studentMap_.put(iStudent.getID(), iStudent);
      return iStudent;
    }
    
    throw new RuntimeException("DBMD: createStudent : student not in file");
  }

  
  private IStudent createStudentProxy(Integer id) {
    Element element = getStudentElement(id);

    if (element != null) {
      return new StudentProxy(id, element.getAttributeValue("fname"),
          element.getAttributeValue("lname"));
    }
    
    throw new RuntimeException("DBMD: createStudent : student not in file");
    
  }

  
  public StudentMap getStudentsByUnit(String unitCode) {
    StudentMap sMap = studentsToUnitsMap_.get(unitCode);
    if (sMap != null) {
      return sMap;
    }

    sMap = new StudentMap();
    IStudent iStudent;
    StudentUnitRecordList unitRecords = StudentUnitRecordManager.instance()
        .getRecordsByUnit(unitCode);
    for (IStudentUnitRecord s : unitRecords) {
      iStudent = createStudentProxy(new Integer(s.getStudentID()));
      sMap.put(iStudent.getID(), iStudent);
    }
    studentsToUnitsMap_.put(unitCode, sMap);
    
    return sMap;
  }
  
}