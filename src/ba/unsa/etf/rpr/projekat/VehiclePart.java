package ba.unsa.etf.rpr.projekat;

public class VehiclePart {
    private String partName;
    private boolean technicalValidity;
    private TechnicalInspection.InspectionStage inspectionStage;

    public VehiclePart(String partName, boolean technicalValidity, TechnicalInspection.InspectionStage inspectionStage) {
        this.partName = partName;
        this.technicalValidity = technicalValidity;
        this.inspectionStage = inspectionStage;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public boolean isTechnicalValidity() {
        return technicalValidity;
    }

    public void setTechnicalValidity(boolean technicalValidity) {
        this.technicalValidity = technicalValidity;
    }

    public TechnicalInspection.InspectionStage getInspectionStage() {
        return inspectionStage;
    }

    public void setInspectionStage(TechnicalInspection.InspectionStage inspectionStage) {
        this.inspectionStage = inspectionStage;
    }
}
