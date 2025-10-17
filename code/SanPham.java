package NhacCu;
import java.util.Scanner;
public class SanPham{
    private String ma, ten, hang;
    private Loai loai;
    private double gia;
    private int soluong;
    public SanPham(){}
    public SanPham(String ma, String ten, String hang, Loai loai, double gia, int soluong){
        this.ma= ma;
        this.ten= ten;
        this.hang= hang;
        this.loai= loai;
        this.gia= gia;
        this.soluong= soluong;
    }
    public String getMa(){
        return ma;
    }
    public void setMa(String ma){
        this.ma= ma;
    }
    public String getTen(){
        return ten;
    }
    public void setTen(String ten){
        this.ten= ten;
    }
    public String getHang(){
        return hang;
    }
    public void setHang(String hang){
        this.hang=hang;
    }
    public Loai getLoai(){
        return loai;
    }
    public void setLoai(Loai loai){
        this.loai= loai;
    }
    public double getGia(){
        return gia;
    }
    public void setGia(double gia){
        this.gia= gia;
    }
    public int getSoluong(){
        return soluong;
    }
    public void setSoluong(int soluong){
        this.soluong=soluong;
    }

    public void nhap(Scanner sc, DanhSachLoai dsl){
        System.out.print("Nhap ma sp: ");
        ma= sc.nextLine();
        System.out.print("Nhap ten sp: ");
        ten= sc.nextLine();
        System.out.print("Nhap ten hang: ");
        hang= sc.nextLine();
        System.out.print("Nhap gia: ");
        gia= sc.nextDouble();
        System.out.print("Nhap so luong: ");
        soluong= sc.nextInt();
        sc.nextLine();
        Loai l;
        do{
            System.out.print("Nhap ma loai: ");
            String maloai= sc.nextLine().trim();
            l= dsl.timkiem(maloai);
            if(l==null){
                System.out.println("Ma loai kh co");
            }
        }while(l==null);
        this.loai= l;
    }
    public void xuat(){
        System.out.printf("%-10s %-20s %-12s %-12s %10.2f %6d%n", ma, ten, hang, loai.getTenloai(), gia, soluong);
    }
    public String tofile(){
        return ma+","+ten+","+hang+","+loai.getTenloai()+","+gia+","+soluong;
    }
}
