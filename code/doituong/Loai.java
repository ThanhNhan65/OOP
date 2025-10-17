package NhacCu;
import java.util.Scanner;
public class Loai{
    private String maLoai, tenLoai;
    public Loai(){}
    public Loai(String maLoai, String tenLoai){
        this.maLoai= maLoai;
        this.tenLoai= tenLoai;
    }
    public String getMaloai(){
        return maLoai;
    }
    public void setMaloai(String maLoai){
        this.maLoai= maLoai;
    }
    public String getTenloai(){
        return tenLoai;
    }
    public void setTenloai(String tenLoai){
        this.tenLoai= tenLoai;
    }
    public void nhap(Scanner sc){
        System.out.print("Nhap ma loai: ");
        maLoai= sc.nextLine();
        System.out.print("Nhap ten loai: ");
        tenLoai= sc.nextLine();
    }
    public void xuat(){
        System.out.printf("%-10s %-20s%n", maLoai, tenLoai);
    }
    public String toFile(){
        return maLoai+","+tenLoai;
    }
}
