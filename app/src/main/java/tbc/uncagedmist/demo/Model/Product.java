package tbc.uncagedmist.demo.Model;

public class Product {
    private String productId,stateId,productName,productImage;

    public Product() {
    }

    public Product(String productId, String stateId, String productName, String productImage) {
        this.productId = productId;
        this.stateId = stateId;
        this.productName = productName;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
