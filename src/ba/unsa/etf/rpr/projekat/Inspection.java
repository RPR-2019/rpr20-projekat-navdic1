package ba.unsa.etf.rpr.projekat;

public class Inspection {
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
    private InspectionEvaluation inspectionEvaluation;
    // private String inspectionStage; // auto?
    //private int controllorId;
    // private datum


    public Inspection(int inspectionId, Owner owner, Vehicle vehicle, String inspectionType, String inspectionEvaluation) {
        this.inspectionId = inspectionId;
        this.owner = owner;
        this.vehicle = vehicle;
        this.inspectionType = InspectionType.valueOf(inspectionType);
        this.inspectionEvaluation = InspectionEvaluation.valueOf(inspectionEvaluation);
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

    public InspectionEvaluation getInspectionEvaluation() {
        return inspectionEvaluation;
    }

    public void setInspectionEvaluation(InspectionEvaluation inspectionEvaluation) {
        this.inspectionEvaluation = inspectionEvaluation;
    }
}
