package code.doituong;

import java.util.Scanner;
import code.kethua.*;

public class NhanVien extends ConNguoi {
    private String MaNV;
    public static int dem = 1;
    private String loai;

    public NhanVien() {
        super();
        this.MaNV = String.format("NV%03d", dem++);
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
        this.MaNV = String.format("NV%03d", dem++);
        super.Nhap(sc);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-8s | %-25s | %-25s | %-15s |%n",
                MaNV, getHoten(), getDiachi(), getSdt());
    }
}
