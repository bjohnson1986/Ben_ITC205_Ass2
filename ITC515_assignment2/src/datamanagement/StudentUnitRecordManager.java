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

	//Get if exist, or create,  Student 
	public IStudentUnitRecord getStudentUnitRecord(Integer studentIdentification, String subjectCode) {
		IStudentUnitRecord studentPerUnit = studentMap__.get(studentIdentification.toString() + subjectCode);
		return studentPerUnit != null ? studentPerUnit : setStudentUnitRecord(studentIdentification, subjectCode);
	}

	//Create new 
	@SuppressWarnings("unchecked")
	private IStudentUnitRecord setStudentUnitRecord(Integer studentIdentification, String subjectCode) {
		IStudentUnitRecord newStudentPerUnit;
		for (Element element : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
			
			//Boolean flag to check if current element has the same studentID and subjectCode in the XML database.
			boolean isEqualToStudentAndSubject = subjectCode.toString().equals(element.getAttributeValue("sid")) && studentIdentification.equals(element.getAttributeValue("uid"));
			if (isEqualToStudentAndSubject) {
				newStudentPerUnit = new StudentUnitRecord(new Integer(element.getAttributeValue("sid")),
						element.getAttributeValue("uid"),
						new Float(element.getAttributeValue("asg1")).floatValue(), new Float(element.getAttributeValue("asg2")).floatValue(), new Float(element.getAttributeValue("exam")).floatValue());
				studentMap__.put(newStudentPerUnit.getStudentID().toString() + newStudentPerUnit.getUnitCode(), newStudentPerUnit);
				return newStudentPerUnit;
			}
		}
		throw new RuntimeException(
				"DBMD: createStudent : student unit record not in file.");
	}

	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByUnit(String unitCode) {
		StudentUnitRecordList unitRecord = subjectToStudentRecord__.get(unitCode);
		if (unitRecord != null)
			return unitRecord;
		unitRecord = new StudentUnitRecordList();
		for (Element el : (List<Element>) XmlManager.getXML().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record")) {
			if (unitCode.equals(el.getAttributeValue("uid")))
				unitRecord.add(new StudentUnitRecordProxy(new Integer(el
						.getAttributeValue("sid")), el.getAttributeValue("uid")));
		}
		if (unitRecord.size() > 0)
			subjectToStudentRecord__.put(unitCode, unitRecord); // be careful - this could be empty
		return unitRecord;
	}

	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByStudent(Integer studentID) {
		StudentUnitRecordList studentRecord = studentToSubjectRecord__.get(studentID);
		if (studentRecord != null)
			return studentRecord;
		studentRecord = new StudentUnitRecordList();
		for (Element i : (List<Element>) XmlManager.getXML().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record"))
			if (studentID.toString().equals(i.getAttributeValue("sid")))
				studentRecord.add(new StudentUnitRecordProxy(new Integer(i
						.getAttributeValue("sid")), i.getAttributeValue("uid")));
		if (studentRecord.size() > 0)
			studentToSubjectRecord__.put(studentID, studentRecord); // be careful - this could be empty
		return studentRecord;
	}

	@SuppressWarnings("unchecked")
	public void saveRecord(IStudentUnitRecord student) {
		for (Element i : (List<Element>) XmlManager.getXML().getDocument()
				.getRootElement().getChild("studentUnitRecordTable")
				.getChildren("record")) {
			if (student.getStudentID().toString().equals(i.getAttributeValue("sid"))
					&& student.getUnitCode().equals(i.getAttributeValue("uid"))) {
				i.setAttribute("asg1", new Float(student.getAsg1()).toString());

				i.setAttribute("asg2", new Float(student.getAsg2()).toString());
				i.setAttribute("exam", new Float(student.getExam()).toString());
				XmlManager.getXML().saveDocument(); // write out the XML file for
																						// continuous save
				return;
			}
		}

		throw new RuntimeException(
				"DBMD: saveRecord : no such student record in data");
	}
}
