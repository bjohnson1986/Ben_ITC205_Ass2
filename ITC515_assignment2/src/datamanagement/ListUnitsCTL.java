package datamanagement;

public class ListUnitsCTL {
  
  private UnitManager unitManager_;

  
  public ListUnitsCTL() {
    unitManager_ = UnitManager.UM();
  }

  
  public void listUnits(IUnitLister unitLister) {
    unitLister.clearUnits();
    
    UnitMap units = unitManager_.getUnits();
    for (String s : units.keySet()) {
      unitLister.addUnit(units.get(s));
    }
  }
  
}
