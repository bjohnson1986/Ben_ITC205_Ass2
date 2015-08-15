package datamanagement;

import java.util.List;
import org.jdom.*;

public class UnitManager {

	private static UnitManager self__ = null;
	private UnitMap unitMap__;

	public static UnitManager UM() {
		if (self__ == null)
			self__ = new UnitManager();
		
		return self__;
	}
	
	
	private UnitManager() {
		unitMap__ = new UnitMap();
	}
	
	
	public IUnit getUnit(String subjectCode) {
		IUnit subject = unitMap__.get(subjectCode);
		
		return subject != null ? subject : createUnit(subjectCode);
	}
	
	
	@SuppressWarnings("unchecked")
	private IUnit createUnit(String subjectUnitCode) {
		IUnit subject;

		for (Element element : (List<Element>) XmlManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit"))
			if (subjectUnitCode.equals(element.getAttributeValue("uid"))) {

				subject = new Unit(element.getAttributeValue("uid"),
						element.getAttributeValue("name"), Float.valueOf(
						element.getAttributeValue("ps")).floatValue(), Float.valueOf(
						element.getAttributeValue("cr")).floatValue(), Float.valueOf(
						element.getAttributeValue("di")).floatValue(), Float.valueOf(
						element.getAttributeValue("hd")).floatValue(), Float.valueOf(
						element.getAttributeValue("ae")).floatValue(), Integer.valueOf(
						element.getAttributeValue("asg1wgt")).intValue(), Integer.valueOf(
						element.getAttributeValue("asg2wgt")).intValue(), Integer.valueOf(
						element.getAttributeValue("examwgt")).intValue(),
						StudentUnitRecordManager.getStudentUnitRecordManager().getRecordsBySubject(subjectUnitCode));
				
				unitMap__.put(subject.getSubjectCode(), subject);
				
				return subject;
			}

		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}
	
	
	@SuppressWarnings("unchecked")
	public UnitMap getUnits() {

		UnitMap unitMap;
		IUnit subject;
		unitMap = new UnitMap();
		
		for (Element j : (List<Element>) XmlManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit")) {
			subject = new UnitProxy(j.getAttributeValue("uid"),
					j.getAttributeValue("name"));
			unitMap.put(subject.getSubjectCode(), subject);
		} // unit maps are filled with PROXY units
		
		return unitMap;
	}

}
