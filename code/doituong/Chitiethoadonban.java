package code.doituong;

import java.util.Scanner;
import code.danhsach.*;
import code.giaodien.*;

public class Chitiethoadonban implements INhapXuat {
    private Hoadonban hd;
    private SanPham sp;
    private int soluong;
    private DanhSachSanPham dssp;
    private DSHD dshd;

    public Chitiethoadonban() {
        hd = new Hoadonban();
        sp = new SanPham();
        soluong = 0;
        dssp = new DanhSachSanPham();
        dshd = new DSHD();
    }

    public Chitiethoadonban(Hoadonban hd, SanPham sp, int soluong) {
        this.hd = hd;
        this.sp = sp;
        this.soluong = soluong;
    }

    public void setHDB(Hoadonban hd) {
        this.hd = hd;
    }

    public Hoadonban getHDB() {
        return hd;
    }

    public void setSP(SanPham sp) {
        this.sp = sp;
    }

    public SanPham getSP() {
        return sp;
    }

    public void setDSHD(DSHD dshd) {
        this.dshd = dshd;
    }

    public DSHD getDSHD() {
        return dshd;
    }

    public void setDSSP(DanhSachSanPham dssp) {
        this.dssp = dssp;
    }

    public DanhSachSanPham getDSSP() {
        return dssp;
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
        String MaHD = sc.nextLine();
        this.hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay hoa don: " + MaHD);
            System.out.println("Vui long nhap lai: ");
            MaHD = sc.nextLine();
            this.hd = dshd.Timkiem_MaHD(MaHD);
        }

        System.out.println("Vui long nhap ma san pham: ");
        String MaSP = sc.nextLine();
        this.sp = dssp.Timkiem(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai: ");
            MaSP = sc.nextLine();
            this.sp = dssp.Timkiem(MaSP);
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
        System.out.println(
            "Hoa don: " + hd.getMaHDB() +
            " | Ma san pham: " + sp.getMa() +
            " | Ten san pham: " + sp.getTen() +
            " | So luong: " + soluong +
            " | Thanh tien: " + Tinhtien()
        );
    }
}



