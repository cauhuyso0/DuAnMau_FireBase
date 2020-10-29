package vn.com.huy_firebase.model;

public class Sach {

    private String maSach;
    private String maTheLoai;
    private String tenSach;
    private String baoVeASM;
    private String NXB;
    private double giaBia;
    private int soLuong;

    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", baoVeASM='" + baoVeASM + '\'' +
                ", NXB='" + NXB + '\'' +
                ", giaBia=" + giaBia +
                ", soLuong=" + soLuong +
                '}';
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return baoVeASM;
    }

    public void setTacGia(String tacGia) {
        this.baoVeASM = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Sach(String maSach, String maTheLoai, String tenSach, String baoVeASM, String NXB, double giaBia, int soLuong) {
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.baoVeASM = baoVeASM;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public Sach() {
    }

}
