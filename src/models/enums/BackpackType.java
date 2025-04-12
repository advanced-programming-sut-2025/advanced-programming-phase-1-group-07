package models.enums;

public enum BackpackType {
    DEFAULT(12);

    public int getMaxCapacity() {
        return maxCapacity;
    }

    final public int maxCapacity;

    BackpackType(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
