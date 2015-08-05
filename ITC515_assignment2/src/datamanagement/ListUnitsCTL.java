package datamanagement;

public class ListUnitsCTL {
  
  private UnitManager unitManager_;

  // Constructor for the ListUnitsCTL class. It retrieves
  // the UnitManager instance.
  public ListUnitsCTL() {
    unitManager_ = UnitManager.UM();
  }
  

  // The listUnits method will take a list of units (unitLister) and
  // initially empty it. It then adds all units to the unitLister.
  public void listUnits(IUnitLister unitLister) {
    unitLister.clearUnits();
    
    UnitMap units = unitManager_.getUnits();
    for (String s : units.keySet()) {
      unitLister.addUnit(units.get(s));
    }
  }
  
}
