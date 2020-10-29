package vn.com.huy_firebase.model;

public class LoaiSach {
    private String maTheLoai;
    private String tenTheLoai;
    private String moTa;
    private int viTri;

    public LoaiSach(){

    }

    public LoaiSach(String tenTheLoai, String moTa, int viTri) {
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public LoaiSach(String maTheLoai, String tenTheLoai, String moTa, int viTri) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return "LoaiSach{" +
                "maTheLoai='" + maTheLoai + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                ", moTa='" + moTa + '\'' +
                ", viTri=" + viTri +
                '}';
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }
}
