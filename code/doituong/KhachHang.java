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
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-8s | %-25s | %-25s | %-15s |%n",
                MaKH, getHoten(), getDiachi(), getSdt());
    }

}