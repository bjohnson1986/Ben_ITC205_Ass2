package datamanagement;

public class ListUnitsControl {
  
  private UnitManager unitManager_;

  
  // Constructor for the ListUnitsCTL class. It retrieves
  // the UnitManager instance.
  public ListUnitsControl() {
    unitManager_ = UnitManager.getUnitManager();
  }
  

  // The listUnits method will take a list of units (unitLister) and
  // initially empty it. It then adds all units to the unitLister.
  public void listUnits(IUnitLister unitLister) {
    unitLister.clearUnits();
    
    UnitMap units = unitManager_.getUnits();
    for (String unitCode : units.keySet()) {
      unitLister.addUnit(units.get(unitCode));
    }
  }
  
}
