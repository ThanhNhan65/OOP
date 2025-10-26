package code.doituong;

import java.util.Scanner;

import code.kethua.*;

public class KhachHang extends ConNguoi {
    public String MaKH;
    public static int dem = 1;

    public KhachHang() {
        MaKH = String.format("KH%03d", dem++);
    }

    public String getMaKH() {
        return MaKH;
    }

    public KhachHang(String Hoten, String Diachi, long Sdt, String MaKH) {
        super(Hoten, Diachi, Sdt);
        this.MaKH = String.format("KH%03d", dem++);
    }

    @Override
    public void Nhap(Scanner sc) {
        super.Nhap(sc);
<<<<<<< HEAD
        MaKH = String.format("KH%03d", dem++);
=======
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    @Override
    public void Xuat() {
<<<<<<< HEAD
        System.out.printf("| %-6s | %-21s | %-20s | %-15s |\n", MaKH, getHoten(), getDiachi(), getSdt());
=======
        System.out.printf("| %-8s | %-25s | %-25s | %-15s |%n", 
            MaKH, getHoten(), getDiachi(), getSdt());
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

}
