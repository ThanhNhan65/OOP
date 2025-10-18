package code.doituong;

import java.util.Arrays;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import code.kethua.*;
import code.danhsach.*;

    
        /*Lớp hóa đơn bán */
public class HoaDon extends GiaoDich{
    private String MaHDB;
    private DanhSachChitietHoaDon dsct;
    private static int  MaHDNext = 1;
    
    public HoaDon(){
        super();
        this.dsct = new DanhSachChitietHoaDon();
        this.MaHDB = "HD" + String.format("%03d", MaHDNext++);
    }
    public HoaDon(String MaHDB, Date ngayGD, NhanVien nv, KhachHang kh, DanhSachChitietHoaDon dsct ){
        super(ngayGD, nv, kh);
        this.MaHDB = MaHDB;
        this.dsct = dsct;
    }
    public void setMaHD(String MaHDB){
        this.MaHDB= MaHDB;
    }
    public String getMaHDB(){
        return MaHDB;
    }
    public void setdsct(DanhSachChitietHoaDon dsct){
        this.dsct = dsct;
    }
    public DanhSachChitietHoaDon getdsct(){
        return dsct;
    }
    public static int getMaHDNext(){
        return MaHDNext;
    }
    public static void setMaHDNext(int value){
            MaHDNext= value;
    }
    public double Thanhtien(){
        double thanhtien = 0;
        for(int i = 0; i < dsct.getN(); i++){
            ChiTietHoaDon ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                thanhtien += ct.Tinhtien();
            }
        }
        return thanhtien;
    }
    public  void Nhap(Scanner sc){

        System.out.println("Nhap ngay giao dich: ");
        String str = sc.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try{
            this.setNgayGD(df.parse(str));
        }catch(Exception ex){
            System.out.println("Loi dinh dang ngay. Su dung dd/MM/yyyy\nDat mac dinh la ngay hien tai.");
            this.setNgayGD(new Date());
        }

        System.out.println("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        KhachHang kh= new DanhSachKhachHang().Timkiem_MaKH(MaKH);
        this.setKh(kh); 
        while(getKh() == null){
            System.out.println("Khong tim thay khach hang: " + MaKH);
            System.out.println("Vui long nhap lai! ");
            MaKH = sc.nextLine();
            kh= new DanhSachKhachHang().Timkiem_MaKH(MaKH);
            this.setKh(kh); 
        }
        System.out.println("Nhap ma Nhan vien: ");
        String MaNV = sc.nextLine();
        NhanVien nv = new DanhSachNhanVien().TimKiemNhanVienTheoMa(MaNV);
        this.setNv(nv);
        while(getKh() == null){
            System.out.println("Khong tim thay nhan vien: " + MaNV);
            System.out.println("Vui long nhap lai! ");
            MaNV = sc.nextLine();
            nv = new DanhSachNhanVien().TimKiemNhanVienTheoMa(MaNV);
            this.setNv(nv);
        }
    }
    public void Xuat(){
        SimpleDateFormat df= new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Hoa đơn: " + MaHDB +
                           " | Ngay: " + df.format(getNgayGD())+
                           " | Ma Khach hang: " + getKh().getMaKH() +
                           " | Ten Kh: " + getKh().getHoten()+
                           " | Thanh tien: " + Thanhtien());
        System.out.println("Chi tiet:");
         for(int i=0; i<dsct.getN(); i++){
            ChiTietHoaDon ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                System.out.println("San pham: " + ct.getSP().getTen() +
                                   " | So luong: " + ct.getSL() +
                                   " | Don gia: " + ct.getSP().getGia() +
                                   " | Gia tien: " + ct.Tinhtien());   
            }
        }
    }
}

   