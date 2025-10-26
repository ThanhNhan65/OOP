package code.kethua;

import java.util.Scanner;
import code.giaodien.*;

public abstract class ConNguoi implements INhapXuat {
    private String Hoten;
    private String Diachi;
    private String Sdt;

    public ConNguoi() {
        Hoten = "";
        Diachi = "";
        Sdt = "";
    }

    public ConNguoi(String Hoten, String Diachi, String Sdt) {
        this.Hoten = Hoten;
        this.Diachi = Diachi;
        this.Sdt = Sdt;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getHoten() {
        return Hoten;
    }

    public String getDiachi() {
        return Diachi;
    }

    public String getSdt() {
        return Sdt;
    }

    @Override
    public void Nhap(Scanner sc) {
        System.out.print("Nhap ho va ten: ");
        Hoten = sc.nextLine();

        System.out.print("Nhap dia chi: ");
        Diachi = sc.nextLine();

        System.out.print("Nhap so dien thoai: ");
        Sdt = sc.nextLine();
        while(Sdt.length()!=10){
            System.out.print("Nhap lai so dien thoai: ");
            Sdt = sc.nextLine();
        }
    }

    @Override
    public abstract void Xuat();
}
