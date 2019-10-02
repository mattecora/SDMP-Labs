package cs478.project2.model;

import java.io.Serializable;

public class PhoneSpec implements Serializable {

    private String specName, specValue;

    public PhoneSpec(String specName, String specValue) {
        this.specName = specName;
        this.specValue = specValue;
    }

    public String getSpecName() {
        return specName;
    }

    public String getSpecValue() {
        return specValue;
    }

}
