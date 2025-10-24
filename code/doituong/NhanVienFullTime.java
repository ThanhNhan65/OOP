package code.doituong;

import java.util.Scanner;

public class NhanVienFullTime extends NhanVien {
    private int ngay;
    private static long luong = 300000;

    public NhanVienFullTime() {
        super();
        ngay = 0;
    }

    @Override
    public String getLoai() {
        return "FullTime";
    }

    public NhanVienFullTime(String Hoten, String Diachi, long Sdt, String MaNV) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = 0;
    }

    public long getLuong() {
        return ngay * luong;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        ngay = sc.nextInt();
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-20s | %-20s | %-15d | %-14s | %-12d | %-12d |\n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), getLuong());
    }
}
