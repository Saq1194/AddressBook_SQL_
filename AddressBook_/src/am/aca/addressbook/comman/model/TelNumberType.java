package am.aca.addressbook.comman.model;

public enum TelNumberType {
    MOBILE(1),
    HOME(2);

    private int index;

    TelNumberType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}