package models.agreement;

public class Contract {
    private String contractIdNo;
    private String bookingId;
    private int deposit;
    private int totalRentFee;
    private String idCustomer;

    public Contract() {
    }

    public Contract(String contractIdNo, String bookingId,
                    int deposit, int totalRentFee, String idCustomer) {
        this.contractIdNo = contractIdNo;
        this.bookingId = bookingId;
        this.deposit = deposit;
        this.totalRentFee = totalRentFee;
        this.idCustomer = idCustomer;
    }

    public String getContractIdNo() {
        return contractIdNo;
    }

    public void setContractIdNo(String contractIdNo) {
        this.contractIdNo = contractIdNo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getTotalRentFee() {
        return totalRentFee;
    }

    public void setTotalRentFee(int totalRentFee) {
        this.totalRentFee = totalRentFee;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Override
    public String toString() {
        return "Contract {" +
                "contractIdNo:" + contractIdNo +
                ", bookingId: " + bookingId +
                ", deposit: " + deposit +
                ", totalRentFee:" + totalRentFee +
                ", idCustomer: " + idCustomer +
                '}';
    }

}
