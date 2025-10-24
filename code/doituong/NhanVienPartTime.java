package code.doituong;

import java.util.Scanner;

public class NhanVienPartTime extends NhanVien {
    private int gio;
    private static long luong = 25000;

    public NhanVienPartTime() {
        super();
        gio = 0;
    }

    public NhanVienPartTime(String Hoten, String Diachi, long Sdt, String MaNV) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.gio = gio;
    }

    @Override
    public String getLoai() {
        return "PartTime";
    }

    public long getLuong() {
        return gio * luong;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        gio = sc.nextInt();
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-20s | %-20s | %-15d | %-14s | %-12d | %-12d |\n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), getLuong());
    }
}
