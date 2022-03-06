package models.agreement;

public class Voucher {
    private String name;
    private String customerId;
    private String voucher;

    public Voucher() {

    }

    public Voucher(String name, String customerId, String voucher) {
        this.name = name;
        this.customerId = customerId;
        this.voucher = voucher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                ", voucher='" + voucher + '\'' +
                '}';
    }
}
