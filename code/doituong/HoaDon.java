package code.doituong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import code.kethua.*;
import code.danhsach.*;

public class HoaDon extends GiaoDich {
    private String MaHDB;
    private DanhSachChitietHoaDon dsct;
    private DanhSachSanPham dssp;
    private DanhSachKhachHang dskh;
    private DanhSachNhanVien dsnv;
    private static int MaHDNext = 1;

    public HoaDon() {
        super();
<<<<<<< HEAD
        this.MaHDB = "HD" + String.format("%03d", MaHDNext++);
=======
        this.MaHDB = null;
        this.dsct = new DanhSachChitietHoaDon();
>>>>>>> d607751381c78685c1758c54456ad1535aba5bb2
    }

    public HoaDon(DanhSachKhachHang dskh, DanhSachNhanVien dsnv, DanhSachSanPham dssp) {
        super();
        this.dsct = new DanhSachChitietHoaDon();
        this.dskh = dskh;
        this.dsnv = dsnv;
        this.dssp = dssp;
        this.MaHDB = "HD" + String.format("%03d", MaHDNext++);
    }

    public String getMaHDB(){ 
        return MaHDB; }
    public void setMaHD(String MaHDB){ 
<<<<<<< HEAD
        this.MaHDB = MaHDB; }
=======
        this.MaHDB = MaHDB; 
    }
>>>>>>> d607751381c78685c1758c54456ad1535aba5bb2

    public DanhSachChitietHoaDon getdsct(){ 
        return dsct; }
    public void setdsct(DanhSachChitietHoaDon dsct){ 
        this.dsct = dsct; 
    }

    public static int getMaHDNext(){
         return MaHDNext; 
        }
    public static void setMaHDNext(int value){ 
        MaHDNext = value; 
    }

    public double Thanhtien() {
        double thanhtien = 0;
        if (dsct == null) return 0;
        for (int i = 0; i < dsct.getN(); i++) {
            ChiTietHoaDon ct = dsct.getDSCT(i);
            if (ct.getHDB().getMaHDB().equals(this.MaHDB)) {
                thanhtien += ct.Tinhtien();
            }
        }
        return thanhtien;
    }

    public void Nhap(Scanner sc) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Nhap ngay giao dich (dd/MM/yyyy): ");
        String str = sc.nextLine();
        try {
            this.setNgayGD(df.parse(str));
        } catch (Exception ex) {
            System.out.println("Loi dinh dang ngay, dat mac dinh la ngay hien tai.");
            this.setNgayGD(new Date());
        }


        System.out.print("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        KhachHang kh = (dskh != null) ? dskh.Timkiem_MaKH(MaKH) : null;
        while (kh == null) {
            System.out.println("Khong tim thay khach hang, nhap lai: ");
            MaKH = sc.nextLine();
            kh = (dskh != null) ? dskh.Timkiem_MaKH(MaKH) : null;
        }
        this.setKh(kh);

        System.out.print("Nhap ma nhan vien: ");
        String MaNV = sc.nextLine();
        NhanVien nv = (dsnv != null) ? dsnv.TimKiemNhanVienTheoMa(MaNV) : null;
        while (nv == null) {
            System.out.println("Khong tim thay nhan vien, nhap lai: ");
            MaNV = sc.nextLine();
            nv = (dsnv != null) ? dsnv.TimKiemNhanVienTheoMa(MaNV) : null;
        }
        this.setNv(nv);
    }
    public void Xuat(){
        SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
<<<<<<< HEAD
        System.out.println("Hoa đơn: " + MaHDB +
=======
        System.out.println("Hoa don: " + MaHDB +
>>>>>>> d607751381c78685c1758c54456ad1535aba5bb2
                           " | Ngay: " + df.format(getNgayGD())+
                           " | Ma Khach hang: " + getKh().getMaKH() +
                           " | Ten Kh: " + getKh().getHoten()+
                           " | Thanh tien: " + Thanhtien());
        System.out.println("Chi tiet:");
         for(int i=0; i<dsct.getN(); i++){
            ChiTietHoaDon ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
<<<<<<< HEAD
                System.out.println("San pham: " + ct.getSP().getTen() +
                                   " | So luong: " + ct.getSL() +
                                   " | Don gia: " + ct.getSP().getGia() +
                                   " | Gia tien: " + ct.Tinhtien());   
=======
                System.out.println("  San pham: " + ct.getSP().getTen() +
                                   " | So luong: " + ct.getSL() +
                                   " | Don gia: " + ct.getSP().getGia() +
                                   " | Thanh tien: " + ct.Tinhtien());   
>>>>>>> d607751381c78685c1758c54456ad1535aba5bb2
            }
        }
    }
}

   