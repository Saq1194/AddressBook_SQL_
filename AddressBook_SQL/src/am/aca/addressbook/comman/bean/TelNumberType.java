package am.aca.addressbook.comman.bean;

public enum TelNumberType {
    MOBILE(1),
    HOME(2);

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    TelNumberType(int index) {
        this.index = index;
    }

}