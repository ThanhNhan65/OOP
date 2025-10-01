
import java.util.Scanner;

import java.util.Arrays;

public class KhachHang extends ConNguoi{
    public int MaKH;
    public double SoTien;
    public KhachHang() {
        MaKH = 0;
        SoTien = 0;
    }
    public KhachHang(String Hoten, String Diachi, Long Sdt, int MaKH, int SoTien) {
        super(Hoten, Diachi, Sdt);
        this.MaKH = MaKH;
        this.SoTien = SoTien;
    }

    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        super.Nhap();
        System.out.println("Nhap Ma khach hang: ");
        MaKH = sc.nextInt();
        System.out.println("So tien khach hang hien co: ");
        SoTien = sc.nextInt();
    }

    public void Xuat() {
        super.Xuat();
        System.out.println("Ma khach hang: " + MaKH);
        System.out.println("So tien khach hang co: " + SoTien);
    }

    public int getMaKH() {
        return MaKH;
    }
}
