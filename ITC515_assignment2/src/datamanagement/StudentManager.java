package datamanagement;

import org.jdom.*;

import java.util.HashMap;
import java.util.List;

public class StudentManager {
  
  private static StudentManager instance_ = null;

  private StudentMap studentMap_;
  private HashMap<String, StudentMap> studentsToUnitsMap_;

  
  // The StudentManager's get method. Will return the lone 
  // instance of this class.
  public static StudentManager get() {
    if (instance_ == null) {
      instance_ = new StudentManager();
    }

    return instance_;
  }

  
  // The private StudentManager constructor, potentially called as
  // a result of the get() method being called.
  private StudentManager() {
    studentMap_ = new StudentMap();
    studentsToUnitsMap_ = new HashMap<>();
  }

  
  // The method getStudent will return a Student based on the passed id
  // parameter. If the student doesn't exist, the createStudent method
  // is called with the passed id parameter and that result is returned.
  public IStudent getStudent(Integer id) {
    IStudent iStudent = studentMap_.get(id);
    
    return (iStudent != null ? iStudent : createStudent(id));
  }

  
  // The getStudentElement will take a passed id parameter and return
  // an Element based on the id matching a particular attribute value.
  // If there is no match, it will return null.
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

  
  // The createStudent method will create a Student based on a match to 
  // the passed id parameter. If the Student is not able to be created
  // then an exception is thrown describing the problem.
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

  
  // The createStudentProxy method will create and return a StudentProxy
  // based on a match to the passed id parameter. If the Proxy cannot be 
  // created, an exception is thrown describing the problem.
  private IStudent createStudentProxy(Integer id) {
    Element element = getStudentElement(id);

    if (element != null) {
      return new StudentProxy(id, element.getAttributeValue("fname"),
          element.getAttributeValue("lname"));
    }
    
    throw new RuntimeException("DBMD: createStudent : student not in file");
    
  }

  
  // The getStudentsByUnit method will create a StudentMap and return it.
  // Its contents are based on what students have a matching unit code to 
  // the passed unitCode parameter.
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