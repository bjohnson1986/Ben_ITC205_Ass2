package datamanagement;

import java.util.List;

import org.jdom.Element;

public class UnitManager {

  private static UnitManager instance__ = null;
	private UnitMap unitMap__;

	public static UnitManager getInstance() {
		if (instance__ == null)
		 instance__ = new UnitManager();

		return instance__;
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
		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit"))
			if (subjectUnitCode.equals(i.getAttributeValue("uid"))) {
				subject = new Unit(i.getAttributeValue("uid"),
						i.getAttributeValue("name"), Float.valueOf(
								i.getAttributeValue("ps")).floatValue(), Float.valueOf(
								i.getAttributeValue("cr")).floatValue(), Float.valueOf(
								i.getAttributeValue("di")).floatValue(), Float.valueOf(
								i.getAttributeValue("hd")).floatValue(), Float.valueOf(
								i.getAttributeValue("ae")).floatValue(), Integer.valueOf(
								i.getAttributeValue("asg1wgt")).intValue(), Integer.valueOf(
								i.getAttributeValue("asg2wgt")).intValue(), Integer.valueOf(
								i.getAttributeValue("examwgt")).intValue(),
						StudentUnitRecordManager.getInstance()
								.getRecordsBySubject(subjectUnitCode));
				unitMap__.put(subject.getUnitCode(), subject);
				return subject;
			}
		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}

	@SuppressWarnings("unchecked")
	public UnitMap getUnits() {

		UnitMap unitMap;
		IUnit subject;
		unitMap = new UnitMap();

		for (Element i : (List<Element>) XmlManager.getInstance().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit")) {
			subject = new UnitProxy(i.getAttributeValue("uid"),
					i.getAttributeValue("name"));
			unitMap.put(subject.getUnitCode(), subject);
		} // unit maps are filled with PROXY units

		return unitMap;
	}

}
