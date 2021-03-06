package datamanagement;

import java.util.HashMap;
import java.util.List;

import org.jdom.Element;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager instance__ = null;
	private StudentUnitRecordMap studentMap__;
	private HashMap<String, StudentUnitRecordList> subjectToStudentRecord__;
	private HashMap<Integer, StudentUnitRecordList> studentToSubjectRecord__;

	// Check if singleton class exists, return self.
	public static StudentUnitRecordManager getInstance() {
		if (instance__ == null)
		 instance__ = new StudentUnitRecordManager();
		return instance__;
	}
	
	
	private StudentUnitRecordManager() {
		studentMap__ = new StudentUnitRecordMap();
		subjectToStudentRecord__ = new HashMap<>();
		studentToSubjectRecord__ = new HashMap<>();
	}
	
	
	// Accessor method to return StudentUnitRecord.
	public IStudentUnitRecord getStudentUnitRecord(Integer studentId, String subjectCode) {
		IStudentUnitRecord studentPerUnit = studentMap__.get(studentId.toString() + subjectCode);
		return studentPerUnit != null ? studentPerUnit : setStudentUnitRecord(
				studentId, subjectCode);
	}

		
	// Mutator method to change assessment marks.
	@SuppressWarnings("unchecked")
	private IStudentUnitRecord setStudentUnitRecord(Integer subjectCode, String studentId) {
		IStudentUnitRecord newStudentPerUnit;
		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record")) {
			// Boolean flag to check if current element has the same studentID and
			// subjectCode in the XML database.
			boolean isEqualToStudentAndSubject = subjectCode.toString().equals(
					i.getAttributeValue("sid"))
					&& studentId.equals(i.getAttributeValue("uid"));
			if (isEqualToStudentAndSubject) {
				newStudentPerUnit = new StudentUnitRecord(new Integer(
						i.getAttributeValue("sid")),
						i.getAttributeValue("uid"), new Float(
						i.getAttributeValue("asg1")).floatValue(), new Float(
						i.getAttributeValue("asg2")).floatValue(), new Float(
						i.getAttributeValue("exam")).floatValue());
				studentMap__.put(newStudentPerUnit.getStudentId().toString()
						+ newStudentPerUnit.getUnitCode(), newStudentPerUnit);
				return newStudentPerUnit;
			}
		}
		throw new RuntimeException(
				"DBMD: createStudent : student unit record not in file");
	}
	
	
	// Accessor to return a record using subject.
	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsBySubject(String subjectCode) {
		StudentUnitRecordList unitRecord = subjectToStudentRecord__.get(subjectCode);
		if (unitRecord != null)
			return unitRecord;
		unitRecord = new StudentUnitRecordList();
		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record")) {
			if (subjectCode.equals(i.getAttributeValue("uid")))
				unitRecord.add(new StudentUnitRecordProxy(new Integer(
				i.getAttributeValue("sid")), i.getAttributeValue("uid")));
		}
		if (unitRecord.size() > 0)// be careful - this could be empty
			subjectToStudentRecord__.put(subjectCode, unitRecord); 
		return unitRecord;
	}
	
	
	// Accessor to return a record by student.
	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByStudent(Integer studentId) {
		StudentUnitRecordList studentRecord = studentToSubjectRecord__.get(studentId);
		if (studentRecord != null)
			return studentRecord;
		// Create new record of student against subject.
		studentRecord = new StudentUnitRecordList();
		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record"))
			// Check if current record is the correct student, and if they are add a
			// new record of the student undertaking this subject.
			if (studentId.toString().equals(i.getAttributeValue("sid")))
				studentRecord.add(new StudentUnitRecordProxy(new Integer(
						i.getAttributeValue("sid")), i.getAttributeValue("uid")));
		if (studentRecord.size() > 0)// be careful - this could be empty
			studentToSubjectRecord__.put(studentId, studentRecord); 
		return studentRecord;
	}
	
	
	// Save changes to the XML database.
	@SuppressWarnings("unchecked")
	public void saveRecord(IStudentUnitRecord student) {
		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record")) {
			boolean isMatchStudentSubject = student.getStudentId().toString()
					.equals(i.getAttributeValue("sid")) && student.getUnitCode()
					.equals(i.getAttributeValue("uid"));
			if (isMatchStudentSubject) {
				i.setAttribute("asg1", new Float(student.getAssignment1()).toString());
				i.setAttribute("asg2", new Float(student.getAssignment2()).toString());
				i.setAttribute("exam", new Float(student.getExam()).toString());
				XmlManager.getInstance().saveDocument(); // write out the XML file for continuous save.
				return;
			}
		}
		throw new RuntimeException("DBMD: saveRecord : no such student record in data");
	}
}
