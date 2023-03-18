package DTO;

public class ThongKe_DTO {
    private String proId;
    private String proName;
    private int quantity;
    private float price;
    private float totalPrice;

    public ThongKe_DTO() {
    }

    public ThongKe_DTO(String proId, String proName, int quantity, float price, float totalPrice) {
        this.proId = proId;
        this.proName = proName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
