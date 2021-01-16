package ba.unsa.etf.rpr.projekat;


public class VehiclePart {
    private String partName;
    private boolean technicalValidity;


    public VehiclePart(String partName, boolean technicalValidity) {
        this.partName = partName;
        this.technicalValidity = technicalValidity;
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

}
