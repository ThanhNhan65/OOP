package code.doituong;

import java.util.Scanner;

import code.kethua.*;

public class KhachHang extends ConNguoi {
    public String MaKH;
    private static int dem = 1;

    public KhachHang() {
        MaKH = String.format("KH%03d", dem++);
    }
    public KhachHang(String Hoten, String Diachi, String Sdt, String MaKH) {
        super(Hoten, Diachi, Sdt);
        this.MaKH = MaKH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public static void setdem(int value){
        dem = value;
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