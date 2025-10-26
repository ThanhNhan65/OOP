package code.doituong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import code.kethua.*;
import code.danhsach.*;

public class HoaDon extends GiaoDich {
    private String MaHDB;
    private DanhSachChitietHoaDon dsct;
    private DanhSachKhachHang dskh;
    private DanhSachNhanVien dsnv;
    private static int MaHDNext = 1;

    public HoaDon() {
        super();
        this.MaHDB = "";
        this.dsct = new DanhSachChitietHoaDon();
    }

    public HoaDon(DanhSachKhachHang dskh, DanhSachNhanVien dsnv) {
        super();
        this.dskh = dskh;
        this.dsnv = dsnv;
        this.MaHDB = "HD" + String.format("%03d", MaHDNext++);
        this.dsct = new DanhSachChitietHoaDon();
    }


    public String getMaHD(){ 
        return MaHDB; 
    }
    public void setMaHD(String MaHDB){ 
        this.MaHDB = MaHDB; 
    }

    public DanhSachChitietHoaDon getdsct(){ 
        return dsct; 
    }
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
            ChiTietHoaDon ct = dsct.getct(i);
            if (ct.getHD().getMaHD().equals(this.MaHDB)) {
                thanhtien += ct.Tinhtien();
            }
        }
        return thanhtien;
    }

    public void Nhap(Scanner sc){
        this.setNgayGD(new Date());
        System.out.print("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        KhachHang kh = (dskh != null) ? dskh.Timkiem_MaKH(MaKH) : null;
        while (kh == null){
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
        
        System.out.println("\n+--------------------------------------------------------------------------------+");
        System.out.printf("| %-15s: %-60s |%n", "Hoa Don", MaHDB);
        System.out.printf("| %-15s: %-60s |%n", "Ngay", df.format(getNgayGD()));
        System.out.printf("| %-15s: %-60s |%n", "Ma Khach Hang", getKh().getMaKH());
        System.out.printf("| %-15s: %-60s |%n", "Ten Khach Hang", getKh().getHoten());
        System.out.printf("| %-15s: %-60.2f |%n", "Tong Tien", Thanhtien());
        System.out.println("+--------------------------------------------------------------------------------+");
        if (dsct.getN() > 0) {
            System.out.println("| CHI TIET:                                                                      |");
            System.out.println("+--------------------------------+------------+-----------------+-----------------+");
            System.out.printf("| %-30s | %-30s | %-10s | %-15s | %-15s |%n", 
                "Ma San Pham","San Pham", "So Luong", "Don Gia", "Thanh Tien");
            System.out.println("+--------------------------------+------------+-----------------+-----------------+");
            
            for(int i=0; i<dsct.getN(); i++){
                ChiTietHoaDon ct = dsct.getct(i);
                if(ct.getHD().getMaHD().equals(this.MaHDB)){
                    System.out.printf("| %-30s | %-30s | %10d | %15.2f | %15.2f |%n",
                        ct.getSP().getMa(),
                        ct.getSP().getTen(),
                        ct.getSL(),
                        ct.getSP().getGia(),
                        ct.Tinhtien());
                }
            }
            System.out.println("+--------------------------------+------------+-----------------+-----------------+");
        }
    }
}

   