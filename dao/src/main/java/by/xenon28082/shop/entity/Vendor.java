package by.xenon28082.shop.entity;

public class Vendor {
    private long vendorId;
    private String name;

    public Vendor(long vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
