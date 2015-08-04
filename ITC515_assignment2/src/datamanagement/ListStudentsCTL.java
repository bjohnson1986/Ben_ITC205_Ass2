package datamanagement;

public class ListStudentsCTL {
  
  private StudentManager studentManager_;

  
  public ListStudentsCTL() {
    studentManager_ = StudentManager.get();
  }

  
  public void listStudents(IStudentLister studentLister, String unitCode) {
    studentLister.clearStudents();
    
    StudentMap students = studentManager_.getStudentsByUnit(unitCode);
    for (Integer id : students.keySet()) {
      studentLister.addStudent(students.get(id));
    }
  }
  
}
