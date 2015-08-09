package datamanagement;

import java.util.List;
import org.jdom.*;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager unitPerStudentRecordManager__ = null;
	private StudentUnitRecordMap studentMap__;
	private java.util.HashMap<String, StudentUnitRecordList> subjectToStudentRecord__;
	private java.util.HashMap<Integer, StudentUnitRecordList> studentToSubjectRecord__;

	//Check if singleton class exists, return self.
	public static StudentUnitRecordManager getStudentUnitRecordManager() {
		if (unitPerStudentRecordManager__ == null)
			unitPerStudentRecordManager__ = new StudentUnitRecordManager();
		return unitPerStudentRecordManager__;
	}

	//Constructor method.
	private StudentUnitRecordManager() {
		studentMap__ = new StudentUnitRecordMap();
		subjectToStudentRecord__ = new java.util.HashMap<>();
		studentToSubjectRecord__ = new java.util.HashMap<>();
	}

	//Accessor method to return SutdentUnitRecord. 
	public IStudentUnitRecord getStudentUnitRecord(Integer studentIdentification, String subjectCode) {
		IStudentUnitRecord studentPerUnit = studentMap__.get(studentIdentification.toString() + subjectCode);
		return studentPerUnit != null ? studentPerUnit : setStudentUnitRecord(studentIdentification, subjectCode);
	}

	//Mutator method to change assessment marks.
	@SuppressWarnings("unchecked")
	private IStudentUnitRecord setStudentUnitRecord(Integer studentIdentification, String subjectCode) {
		IStudentUnitRecord newStudentPerUnit;
		for (Element element : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
			
			//Boolean flag to check if current element has the same studentID and subjectCode in the XML database.
			boolean isEqualToStudentAndSubject = subjectCode.toString().equals(element.getAttributeValue("sid")) && studentIdentification.equals(element.getAttributeValue("uid"));
			if (isEqualToStudentAndSubject) {
				newStudentPerUnit = new StudentUnitRecord(new Integer(element.getAttributeValue("sid")), element.getAttributeValue("uid"), new Float(element.getAttributeValue("asg1")).floatValue(), new Float(element.getAttributeValue("asg2")).floatValue(), new Float(element.getAttributeValue("exam")).floatValue());
				studentMap__.put(newStudentPerUnit.getStudentID().toString() + newStudentPerUnit.getUnitCode(), newStudentPerUnit);
				return newStudentPerUnit;
			}
		}
		throw new RuntimeException(
				"DBMD: createStudent : student unit record not in file.");
	}

	//Get object by unit.
	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByUnit(String subjectCode) {
		StudentUnitRecordList studentUnitList = subjectToStudentRecord__.get(subjectCode);
		//Check if subject exists, and return it if it does.
		if (studentUnitList != null)
			return studentUnitList;
		//Create new record of student against subject.
		studentUnitList = new StudentUnitRecordList();
		for (Element element : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
			//Check if current record has the correct subject, and if so add a new record of the subject being undertaken by the student.
			if (subjectCode.equals(element.getAttributeValue("uid")))
				studentUnitList.add(new StudentUnitRecordProxy(new Integer(element.getAttributeValue("sid")), element.getAttributeValue("uid")));
		}
		if (studentUnitList.size() > 0)
			subjectToStudentRecord__.put(subjectCode, studentUnitList); // be careful - this could be empty
		return studentUnitList;
	}

	//Get object by student.
	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByStudent(Integer studentIdentification) {
		StudentUnitRecordList studentRecord = studentToSubjectRecord__.get(studentIdentification);
		//Check if student exists, and return it if it does.
		if (studentRecord != null)
			return studentRecord;
		//Create new record of student against subject.
		studentRecord = new StudentUnitRecordList();
		for (Element element : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record"))
			//Check if current record is the correct student, and if they are add a new record of the student undertaking this subject.
			if (studentIdentification.toString().equals(element.getAttributeValue("sid")))
				studentRecord.add(new StudentUnitRecordProxy(new Integer(element.getAttributeValue("sid")), element.getAttributeValue("uid")));
		if (studentRecord.size() > 0)
			studentToSubjectRecord__.put(studentIdentification, studentRecord); // be careful - this could be empty
		return studentRecord;
	}

	//Save changes to the XML database.
	@SuppressWarnings("unchecked")
	public void saveRecord(IStudentUnitRecord student) {
		for (Element element : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
		boolean isMatchStudentSubject = student.getStudentID().toString().equals(element.getAttributeValue("sid")) && student.getUnitCode().equals(element.getAttributeValue("uid"));
			if (isMatchStudentSubject) {
				element.setAttribute("asg1", new Float(student.getAsg1()).toString());
				element.setAttribute("asg2", new Float(student.getAsg2()).toString());
				element.setAttribute("exam", new Float(student.getExam()).toString());
				XmlManager.getXML().saveDocument(); // write out the XML file for
																						// continuous save
				return;
			}
		}

		throw new RuntimeException(
				"DBMD: saveRecord : no such student record in data");
	}
}
