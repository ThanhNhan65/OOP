package code;

import java.util.Arrays;
import java.util.*;

public interface INhapXuat {
    void Nhap(Scanner sc);
    void Xuat();
    
}
            /*Lớp trừu tượng */
abstract class GiaoDich implements INhapXuat{
    Date ngayGD;
    Nhanvien nv;
    Khachhang kh;

    public GiaoDich(){
        ngayGD="";
        nv = new Nhanvien();
        kh = new Khachhang();
    }
    public GiaoDich(Date ngayGD, Nhanvien nv, Khachhang kh){
        this.ngayGD= ngayGD;
        this.kh=kh;
        this.nv=nv;
    }  
    public abstract double Thanhtien();
    public abstract void Xuat();
    public abstract void Nhap(Scanner sc);
}

    
        /*Lớp hóa đơn bán */
class Hoadonban extends GiaoDich{
    String MaHDB;
    DSCT dsct;
    public static String MaHDNext;
    
    public Hoadonban(){
        super();
        MaHDB="";
        dsct= new DSCT();
    }
    public Hoadonban(String MaHDB,Date ngayGD, Nhanvien nv, Khachhang kh, DSCT dsct ){
        super(Date ngayGD, Nhanvien nv, Khachhang kh);
        this.MaHD=MaHDB;
        this.dsct= dsct;
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
        double thanhtien=0;
        for(int i = 0; i < dsct.getN(); i++){
            Chitiethoaodonban ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                tong += ct.Tinhtien();
        }
    }
        return thanhtien;
    }
    public  void Nhap(Scanner sc){
        System.out.println("Nhap ma hoa don: ");
        this.MaHDB = sc.nextLine();
        System.out.println("Nhap ngay giao dich: ");
        this.ngayGD = sc.nextLine();
        System.out.println("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        this.kh= dskh.TimKiem(MaKH);
        while(kh == null){
            System.out.println("Khong tim thay khach hang: " + MaKH);
            System.out.println("Vui long nhap lai! ");
            MaKH = sc.nextLine();
            this.hd= dskh.Timkiem(MaKH);
        }
        System.out.println("Nhap ma Nhan vien: ");
        String MaNV = sc.nextLine();
        this.nv = dsnv.TimKiem(MaNV);
        while(kh == null){
            System.out.println("Khong tim thay nhan vien: " + MaNV);
            System.out.println("Vui long nhap lai! ");
            MaNV = sc.nextLine();
            this.hd= dsnv.Timkiem(MaNV);
        }
    }
    public void Xuat(){
        System.out.println("Hoa đơn: " + hd.MaHDB +
                           " | Ma Khach hang: " + kh.getMaKH() +
                           " | Ten Kh: " + kh.getTenKH()+
                           " | Thanh tien: " + Thanhtien());
        System.out.println("Chi tiet:");
         for(int i=0; i<dsct.getN(); i++){
            Chitiethoaodonban ct = dsct.getDSCT(i);
            if(ct.getHDB().getMaHDB().equals(this.MaHDB)){
                System.out.println("San pham: " + ct.getSP().getTenSP() +
                                   " | So luong: " + ct.getSL() +
                                   " | Don gia: " + ct.getSP().getDongia() +
                                   " | Gia tien: " + ct.Tinhtien());   
            }
        }
    }
}


/*Lớp danh sách hóa đơn*/
class DSHD{
    Hoadonban[] dshd;
    int n;
    public dshd(){
        n=0;
        dshd= new hoadonban[0];
    }
}
