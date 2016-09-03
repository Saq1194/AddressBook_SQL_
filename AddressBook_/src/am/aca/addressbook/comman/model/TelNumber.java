package am.aca.addressbook.comman.model;

public class TelNumber {
    private String telNumber;
    private TelNumberType telNumberType;
    private Integer telNumberId;

    public TelNumber(String telnumber) {
        this.telNumber = telnumber;

    }


    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public TelNumberType getTelNumberType() {
        return telNumberType;
    }

    public void setTelNumberType(TelNumberType telNumberType) {
        this.telNumberType = telNumberType;
    }

    public Integer getTelNumberId() {
        return telNumberId;
    }

    public void setTelNumberId(Integer telNumberId) {
        this.telNumberId = telNumberId;
    }
}
