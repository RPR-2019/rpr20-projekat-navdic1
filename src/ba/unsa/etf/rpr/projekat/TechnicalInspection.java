package ba.unsa.etf.rpr.projekat;

public class TechnicalInspection {
    public enum InspectionType {
        REGULAR, EXTRAORDINARY, PREVENTIVE;
    }

    public enum InspectionEvaluation {
        FAILED, PASSED, NO_DATA;
    }

    private int inspectionId;
    private Owner owner;
    private Vehicle vehicle;
    private InspectionType inspectionType;
    private InspectionEvaluation inspectionEvalation;
    // private String inspectionStage; // auto?
    //private int controllorId;
    // private datum


    public TechnicalInspection(int inspectionId, Owner owner, Vehicle vehicle, InspectionType inspectionType, InspectionEvaluation inspectionEvalation) {
        this.inspectionId = inspectionId;
        this.owner = owner;
        this.vehicle = vehicle;
        this.inspectionType = inspectionType;
        this.inspectionEvalation = inspectionEvalation;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    public InspectionEvaluation getInspectionEvalation() {
        return inspectionEvalation;
    }

    public void setInspectionEvalation(InspectionEvaluation inspectionEvalation) {
        this.inspectionEvalation = inspectionEvalation;
    }
}
