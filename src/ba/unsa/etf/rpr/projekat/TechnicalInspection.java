package ba.unsa.etf.rpr.projekat;

public class TechnicalInspection {
    enum InspectionType{
        REGULAR, EXTRAORDINARY, PREVENTIVE
    }
    enum InspectionStage{
        VISUAL_INSPECTION, IDENTIFICATION, EQUIPMENT_INSPECTION
    }
    private User user;
    private Vehicle vehicle;
    private InspectionType inspectionType;
    private InspectionStage inspectionStage;

    public TechnicalInspection(User user, Vehicle vehicle, InspectionType inspectionType, InspectionStage inspectionStage) {
        this.user = user;
        this.vehicle = vehicle;
        this.inspectionType = inspectionType;
        this.inspectionStage = inspectionStage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public InspectionStage getInspectionStage() {
        return inspectionStage;
    }

    public void setInspectionStage(InspectionStage inspectionStage) {
        this.inspectionStage = inspectionStage;
    }
}
