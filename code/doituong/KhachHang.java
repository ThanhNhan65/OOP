package code.doituong;
import java.util.Scanner;

import code.kethua.*;

public class KhachHang extends ConNguoi {
    public String MaKH;
    public static int dem = 0;

    public KhachHang() {
        MaKH = "";
    }

    public KhachHang(String Hoten, String Diachi, long Sdt, String MaKH) {
        super(Hoten, Diachi, Sdt);
        this.MaKH = MaKH;
    }

    @Override
    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        dem++;
        MaKH = String.format("KH%03d", dem);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-21s | %-20s | %-15s |\n", MaKH, Hoten, Diachi, Sdt);
    }

    public String getMaKH() {
        return MaKH;
    }

}
