package code.doituong;

import java.util.Scanner;
import code.kethua.*;

public class NhanVien extends ConNguoi {
    private String MaNV;
    public static int dem = 1;
    private String loai;

    public NhanVien() {
        super();
        MaNV = "";
        loai = "";
    }

    public NhanVien(String Hoten, String Diachi, long Sdt, String MaNV) {
        super(Hoten, Diachi, Sdt);
        this.MaNV = String.format("NV%03d", dem++);
    }

    public String getLoai() {
        return "NhanVien";
    }

    public String getMaNV() {
        return MaNV;
    }

    @Override
    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        MaNV = this.MaNV = String.format("NV%03d", dem);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-20s | %-20s | %-15d | %-14s | %-12d | %-12.0f |\n", MaNV, getHoten(), getDiachi(),
                getSdt());
    }
}
