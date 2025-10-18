package code.doituong;

import java.util.Arrays;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import code.kethua.*;
import code.danhsach.*;

    
        /*Lớp hóa đơn bán */
public class Hoadonban extends GiaoDich{
    private String MaHDB;
    private DSCT dsct;
    private DanhSachKhachHang dskh;
    public static int  MaHDNext = 1;
    
    public Hoadonban(){
        super();
        MaHDB="";
        dsct= new DSCT();
    }
    public Hoadonban(String MaHDB, Date ngayGD, NhanVien nv, KhachHang kh, DSCT dsct ){
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
    public void setdsct(DSCT dsct){
        this.dsct = dsct;
    }
    public DSCT getdsct(){
        return dsct;
    }
    public static int getMaHDNext(){
        return MaHDNext;
    }
    public double Thanhtien(){
        double thanhtien = 0;
        for(int i = 0; i < dsct.getN(); i++){
            Chitiethoadonban ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                thanhtien += ct.Tinhtien();
            }
        }
        return thanhtien;
    }
    public  void Nhap(Scanner sc){
        System.out.println("Nhap ma hoa don: ");
        this.MaHDB = sc.nextLine();

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
        this.setKh(dskh.Timkiem_MaKH(MaKH)); 
        while(getKh() == null){
            System.out.println("Khong tim thay khach hang: " + MaKH);
            System.out.println("Vui long nhap lai! ");
            MaKH = sc.nextLine();
            this.setKh(dskh.Timkiem_MaKH(MaKH)); 
        }
        System.out.println("Nhap ma Nhan vien: ");
        String MaNV = sc.nextLine();
        this.setNV(TimKiem_MaNV(MaNV));
        while(getKh() == null){
            System.out.println("Khong tim thay nhan vien: " + MaNV);
            System.out.println("Vui long nhap lai! ");
            MaNV = sc.nextLine();
            this.setNV(TimKiem_MaNV(MaNV));
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
            Chitiethoadonban ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                System.out.println("San pham: " + ct.getSP().getTen() +
                                   " | So luong: " + ct.getSL() +
                                   " | Don gia: " + ct.getSP().getGia() +
                                   " | Gia tien: " + ct.Tinhtien());   
            }
        }
    }
}

   