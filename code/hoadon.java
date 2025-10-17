package code;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.*;
import java.text.SimpleDateFormat;

public interface INhapXuat {
    void Nhap(Scanner sc);
    void Xuat();
    
}
            /*Lớp trừu tượng */
abstract class GiaoDich implements INhapXuat{
    private Date ngayGD;
    private Nhanvien nv;
    private Khachhang kh;

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
    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public Nhanvien getNv() {
        return nv;
    }

    public void setNv(Nhanvien nv) {
        this.nv = nv;
    }

    public Khachhang getKh() {
        return kh;
    }

    public void setKh(Khachhang kh) {
        this.kh = kh;
    }
    public abstract double Thanhtien();
    public abstract void Xuat();
    public abstract void Nhap(Scanner sc);
}

    
        /*Lớp hóa đơn bán */
class Hoadonban extends GiaoDich{
    private String MaHDB;
    private DSCT dsct;
    public static int  MaHDNext = 1;
    
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
        String str = sc.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.setNgayGD(df.parse(str));

        System.out.println("Nhap ma khach hang: ");
        String MaKH = sc.nextLine();
        this.setKh(dskh.TimKiem(MaKH)); 
        while(kh == null){
            System.out.println("Khong tim thay khach hang: " + MaKH);
            System.out.println("Vui long nhap lai! ");
            MaKH = sc.nextLine();
            this.setKh(dskh.TimKiem(MaKH)); 
        }
        System.out.println("Nhap ma Nhan vien: ");
        String MaNV = sc.nextLine();
        this.setNV(TimKiem(MaNV));
        while(kh == null){
            System.out.println("Khong tim thay nhan vien: " + MaNV);
            System.out.println("Vui long nhap lai! ");
            MaNV = sc.nextLine();
            this.setNV(TimKiem(MaNV));
        }
    }
    public void Xuat(){
        SimpleDateFormat df= new SimpleDateFormat("dd\MM\yyyy")
        System.out.println("Hoa đơn: " + MaHDB +
                           " | Ngay: " + df.format(getNgayGD())+
                           " | Ma Khach hang: " + getKh().getMaKH() +
                           " | Ten Kh: " + getKh().getTenKH()+
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
        public DSHD(){
            n=0;
            dshd= new Hoadonban[0];
        }
        public int getN() {
            return n;
        }

        public void ReadFile(){
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try{
                BufferedReader input = new BufferedReader(new FileReader(data\\hoadonban.txt));
                String line = input.readLine();
                while(line != null){
                    String[] arr = line.split(",");

                    String maHD = arr[0].trim();
                    String maKH = arr[1].trim();
                    String maNV = arr[2].trim();
                    Date ngayGD = df.parse(arr[3].trim());

                    Khachhang kh = dskh.TimKiem(maKH);
                    Nhanvien nv = dsnv.Timkem(maNV);

                    Hoadonban hd = new Hoadonban(maHD, ngayGD, kh, nv, new DSCT());
                    
                    dshd = Arrays.copyOf(dshd, n+ 1);
                    dshd[n]= hd;
                    n++;

                    line = input.readLine();
                }
            }catch(Exception ex){
                        ex.printStackTrace();
                    }
        }

        public void WriteFile(){
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try{
                FileWriter fw = new BufferedWriter("data\\chitiethoadon.txt");
                for(int i=0; i<n ;i++){
                    fw.write(dshd[i].getMaHDB() + ","+dshd[i].kh.getMaKH() + ","+dshd[i].nv.getMaNV());
                    fw.newLine();   
                }
                fw.close();
            }catch (Exception e) {
                    System.out.println(e);
                }
        }
    }

    public void Them(Scanner sc){
        Sytem.out.println("Nhap ma hon can thue");
        String newMaHDB = sc.nextLine();

        System.out.println("Nhap ma khach hang")
        String newMaKH = sc.nextLine();

        Khachhang
    }