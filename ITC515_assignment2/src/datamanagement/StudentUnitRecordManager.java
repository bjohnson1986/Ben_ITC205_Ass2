package datamanagement;

import java.util.List;
import org.jdom.*;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager unitPerStudentRecordManager = null;
	private StudentUnitRecordMap recordByStudentAndSubject;
	private java.util.HashMap<String, StudentUnitRecordList> unitPerStudentSubjectCode;
	private java.util.HashMap<Integer, StudentUnitRecordList> unitPerStudentStudentIdentification;

	public static StudentUnitRecordManager instance() {
		if (unitPerStudentRecordManager == null)
			unitPerStudentRecordManager = new StudentUnitRecordManager();
		return unitPerStudentRecordManager;
	}

	private StudentUnitRecordManager() {
		recordByStudentAndSubject = new StudentUnitRecordMap();
		unitPerStudentSubjectCode = new java.util.HashMap<>();
		unitPerStudentStudentIdentification = new java.util.HashMap<>();
	}

	public IStudentUnitRecord getStudentUnitRecord(Integer studentID,
			String unitCode) {
		IStudentUnitRecord studentPerUnit = recordByStudentAndSubject.get(studentID.toString() + unitCode);
		return studentPerUnit != null ? studentPerUnit : createStudentUnitRecord(studentID, unitCode);
	}

	@SuppressWarnings("unchecked")
	private IStudentUnitRecord createStudentUnitRecord(Integer uid, String sid) {
		IStudentUnitRecord newStudentPerUnit;
		for (Element i : (List<Element>) XmlManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
			if (uid.toString().equals(i.getAttributeValue("sid"))
					&& sid.equals(i.getAttributeValue("uid"))) {
				newStudentPerUnit = new StudentUnitRecord(new Integer(i.getAttributeValue("sid")),
						i.getAttributeValue("uid"),
						new Float(i.getAttributeValue("asg1")).floatValue(), new Float(
								i.getAttributeValue("asg2")).floatValue(), new Float(
								i.getAttributeValue("exam")).floatValue());
				recordByStudentAndSubject.put(newStudentPerUnit.getStudentID().toString() + newStudentPerUnit.getUnitCode(), newStudentPerUnit);
				return newStudentPerUnit;
			}
		}
		throw new RuntimeException(
				"DBMD: createStudent : student unit record not in file.");
	}

	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByUnit(String unitCode) {
		StudentUnitRecordList unitRecord = unitPerStudentSubjectCode.get(unitCode);
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
			unitPerStudentSubjectCode.put(unitCode, unitRecord); // be careful - this could be empty
		return unitRecord;
	}

	@SuppressWarnings("unchecked")
	public StudentUnitRecordList getRecordsByStudent(Integer studentID) {
		StudentUnitRecordList studentRecord = unitPerStudentStudentIdentification.get(studentID);
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
			unitPerStudentStudentIdentification.put(studentID, studentRecord); // be careful - this could be empty
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
