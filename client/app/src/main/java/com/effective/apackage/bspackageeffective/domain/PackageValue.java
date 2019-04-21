package com.effective.apackage.bspackageeffective.domain;


import java.util.ArrayList;
import java.util.Map;

public class PackageValue {
    private String packageName;
    private double packageValue;
    private int packagePrice;
    private String etc;
    private String state;
    private ArrayList<ItemValue> itemValues;

    public double getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(double packageValue) {
        this.packageValue = packageValue;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    private Map<String, Integer> components;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }

    public ArrayList<ItemValue> getItemValues() {
        return itemValues;
    }

    public void setItemValues(ArrayList<ItemValue> itemValues) {
        this.itemValues = itemValues;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PackageValue{" +
                "packageName='" + packageName + '\'' +
                ", packageValue=" + packageValue +
                ", packagePrice=" + packagePrice +
                ", etc='" + etc + '\'' +
                ", state='" + state + '\'' +
                ", itemValues=" + itemValues +
                ", components=" + components +
                '}';
    }
}
