package code.doituong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import code.kethua.*;
import code.kiemtra.InputUtils;
import code.danhsach.*;

public class HoaDon extends GiaoDich {
    private String MaHDB;
    private DanhSachChitietHoaDon dsct;
    private DanhSachKhachHang dskh;
    private DanhSachNhanVien dsnv;
    private static int MaHDNext = 1;

    public HoaDon() {
        super();
        this.MaHDB = String.format("HD%03d", MaHDNext++);
        this.dsct = new DanhSachChitietHoaDon();
    }

    public HoaDon(DanhSachKhachHang dskh, DanhSachNhanVien dsnv) {
        super();
        this.dskh = dskh;
        this.dsnv = dsnv;
        this.MaHDB = String.format("HD%03d", MaHDNext++);
        this.dsct = new DanhSachChitietHoaDon();
    }

    public String getMaHD() {
        return MaHDB;
    }

    public void setMaHD(String MaHDB) {
        this.MaHDB = MaHDB;
    }

    public DanhSachChitietHoaDon getdsct() {
        return dsct;
    }

    public void setdsct(DanhSachChitietHoaDon dsct) {
        this.dsct = dsct;
    }

    public static int getMaHDNext() {
        return MaHDNext;
    }

    public static void setMaHDNext(int value) {
        MaHDNext = value;
    }

    public double Thanhtien() {
        double thanhtien = 0;
        if (dsct == null)
            return 0;
        for (int i = 0; i < dsct.getN(); i++) {
            ChiTietHoaDon ct = dsct.getct(i);
            if (ct.getHD().getMaHD().equals(this.MaHDB)) {
                thanhtien += ct.Tinhtien();
            }
        }
        return thanhtien;
    }

    public void Nhap(Scanner sc) {
        this.setNgayGD(new Date());
        System.out.print("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(MaKH))
            return;
        KhachHang kh = (dskh != null) ? dskh.Timkiem_MaKH(MaKH) : null;
        while (kh == null) {
            System.out.println("Khong tim thay khach hang, nhap lai: ");
            MaKH = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaKH))
                return;
            kh = (dskh != null) ? dskh.Timkiem_MaKH(MaKH) : null;
        }
        this.setKh(kh);

        System.out.print("Nhap ma nhan vien: ");
        String MaNV = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(MaNV))
            return;
        NhanVien nv = (dsnv != null) ? dsnv.TimKiemNhanVienTheoMa(MaNV) : null;
        while (nv == null) {
            System.out.println("Khong tim thay nhan vien, nhap lai: ");
            MaNV = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaNV))
                return;
            nv = (dsnv != null) ? dsnv.TimKiemNhanVienTheoMa(MaNV) : null;
        }
        this.setNv(nv);
    }

    public void Xuat() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("\n");
        System.out.printf("| %-15s: %-90s %n", "Hoa Don", MaHDB);
        System.out.printf("| %-15s: %-90s %n", "Ngay", df.format(getNgayGD()));
        System.out.printf("| %-15s: %-90s %n", "Ma Khach Hang", getKh().getMaKH());
        System.out.printf("| %-15s: %-90s %n", "Ten Khach Hang", getKh().getHoten());
        System.out.printf("| %-15s: %-90.2f %n", "Tong Tien", Thanhtien());
        if (dsct.getN() > 0) {
            System.out.println("CHI TIET:");

            for (int i = 0; i < dsct.getN(); i++) {
                ChiTietHoaDon ct = dsct.getct(i);
                if (ct.getHD().getMaHD().equals(this.MaHDB)) {
                    System.out.printf("| %-10s | %-28s | %8d | %13.2f | %17.2f |%n",
                            ct.getSP().getMa(),
                            ct.getSP().getTen(),
                            ct.getSL(),
                            ct.getSP().getGia(),
                            ct.Tinhtien());
                }
            }
        }
    }
}
