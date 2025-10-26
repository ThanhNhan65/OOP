package code.doituong;

import java.util.Scanner;
import code.danhsach.*;
import code.giaodien.*;

public class ChiTietHoaDon implements INhapXuat {
    private HoaDon hd;
    private SanPham sp;
    private int soluong;
    
    private DanhSachHoaDon dshd;
    private DanhSachSanPham dssp;
    
    public ChiTietHoaDon(DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        this.dshd = dshd;
        this.dssp = dssp;
        this.hd = new HoaDon();
        this.sp = new SanPham();
        this.soluong = 0;
    }
    
    public void setHD(HoaDon hd) {
        this.hd = hd;
    }

    public HoaDon getHD() {
        return hd;
    }

    public void setSP(SanPham sp) {
        this.sp = sp;
        
    }

    public SanPham getSP() {
        return sp;
    }


    public void setSL(int soluong) {
        this.soluong = soluong;
    }

    public int getSL() {
        return soluong;
    }

    public double Tinhtien() {
        return soluong * sp.getGia();
    }

    public void Nhap(Scanner sc) {
        System.out.println("Vui long nhap ma hoa don: ");
        String MaHD = sc.nextLine().trim();
        this.hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay hoa don: " + MaHD);
            System.out.println("Vui long nhap lai: ");
            MaHD = sc.nextLine().trim();
            this.hd = dshd.Timkiem_MaHD(MaHD);
        }

        System.out.println("Vui long nhap ma san pham: ");
        String MaSP = sc.nextLine().trim();
        this.sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai: ");
            MaSP = sc.nextLine().trim();
            this.sp = dssp.TimTheoMa(MaSP);
        }

        System.out.println("Vui long nhap so luong: ");
        this.soluong = sc.nextInt();
        while (soluong < 0) {
            System.out.println("So luong khong hop le. Vui long nhap lai: ");
            this.soluong = sc.nextInt();
        }
        sc.nextLine();
    }

    public void Xuat() {
        System.out.printf("| %-12s | %-12s | %-30s | %10d | %15.2f |%n",
            hd.getMaHD(), sp.getMa(), sp.getTen(), soluong, Tinhtien());
    }
}



