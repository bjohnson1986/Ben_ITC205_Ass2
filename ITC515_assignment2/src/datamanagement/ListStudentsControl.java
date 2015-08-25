package datamanagement;

public class ListStudentsControl {
  
  private StudentManager studentManager_;

  
  // Constructor for the ListStudentsCTL class. It retrieves the
  // StudentManager instance.
  public ListStudentsControl() {
    studentManager_ = StudentManager.getInstance();
  }

  
  // The listStudents method will take a list of students (studentLister) 
  // and initially empty it. It then adds students to the studentLister based 
  // on which ones do the unit that matches the passed unitCode parameter.
  public void listStudents(IStudentLister studentLister, String unitCode) {
    studentLister.clearStudents();
    
    StudentMap students = studentManager_.getStudentsByUnit(unitCode);
    for (Integer id : students.keySet()) {
      studentLister.addStudent(students.get(id));
    }
  }
  
}
