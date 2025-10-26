package code.doituong;

import java.util.Scanner;
import code.kethua.*;

public class NhanVien extends ConNguoi {
    private String MaNV;
    public static int dem = 1;
<<<<<<< HEAD

    public NhanVien() {
        super();
=======
    private String loai;

    public NhanVien() {
        super();
        this.MaNV = String.format("NV%03d", dem++);
        loai = "";
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    public NhanVien(String Hoten, String Diachi, long Sdt, String MaNV) {
        super(Hoten, Diachi, Sdt);
<<<<<<< HEAD
        this.MaNV = MaNV;
=======
        this.MaNV = String.format("NV%03d", dem++);
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
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
<<<<<<< HEAD
        System.out.printf("| %-6s | %-20s | %-20s | %-12d |\n", MaNV, getHoten(), getDiachi(), getSdt());
=======
        System.out.printf("| %-8s | %-25s | %-25s | %-15s |%n", 
            MaNV, getHoten(), getDiachi(), getSdt());
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }
}
