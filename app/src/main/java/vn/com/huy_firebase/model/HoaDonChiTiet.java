package vn.com.huy_firebase.model;

public class HoaDonChiTiet {
    private String maHDCT;
    private String tenSach;
    private int soLuongMua;

    public HoaDonChiTiet(){

    }
    public HoaDonChiTiet(String tenSach, int soLuongMua){
        this.tenSach = tenSach;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(String maHDCT, String tenSach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.tenSach = tenSach;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }


    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", tenSach='" + tenSach + '\'' +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
