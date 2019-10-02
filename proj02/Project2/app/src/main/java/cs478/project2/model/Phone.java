package cs478.project2.model;

import java.io.Serializable;
import java.util.List;

public class Phone implements Serializable {

    // Properties for main activity list
    private String brand, model, screenSize, priceRange;

    // Properties for phone pictures
    private Integer lowResPicture, highResPicture;

    // Properties for website
    private String url;

    // Properties for detailed view
    private List<PhoneSpec> specs;

    public Phone(String brand, String model, String screenSize, String priceRange, Integer lowResPicture, Integer highResPicture, String url, List<PhoneSpec> specs) {
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.priceRange = priceRange;
        this.lowResPicture = lowResPicture;
        this.highResPicture = highResPicture;
        this.url = url;
        this.specs = specs;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public Integer getLowResPicture() {
        return lowResPicture;
    }

    public Integer getHighResPicture() {
        return highResPicture;
    }

    public String getUrl() {
        return url;
    }

    public List<PhoneSpec> getSpecs() {
        return specs;
    }

}
