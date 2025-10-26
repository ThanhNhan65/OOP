package code.doituong;

import java.util.Scanner;
import code.giaodien.*;

public class Loai implements INhapXuat{
    private String maLoai, tenLoai;
    private static int manext=1;
    public Loai(){
        this.maLoai= String.format("L%03d", manext++);
    }
    public Loai(String maLoai, String tenLoai){
        this.maLoai= String.format("L%03d", manext++);
        this.tenLoai= tenLoai;
    }
    public String getMaloai(){
        return maLoai;
    }
    public static void setmanext(int value){
        manext= value;
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
    public void Nhap(Scanner sc){
        System.out.print("Nhap ten loai: ");
        tenLoai= sc.nextLine();
    }
    public void Xuat(){
        System.out.printf("| %-10s | %-20s |%n", maLoai, tenLoai);
    }
    public String toFile(){
        return maLoai+","+tenLoai;
    }
}
