package code.doituong;

import java.util.Scanner;

public class NhanVienFullTime extends NhanVien {
    private int ngay;
    private static long luong = 300000;

    public NhanVienFullTime() {
        super();
        ngay = 0;
    }

    public int getngay() {
        return ngay;
    }

    @Override
    public String getLoai() {
        return "FullTime";
    }

    public NhanVienFullTime(String Hoten, String Diachi, long Sdt, String MaNV, int ngay) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = ngay;
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
        System.out.printf("| %-8s | %-25s | %-25s | %-15s | %-10s | %8d ngay | %12.0f |%n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), ngay, (double) getLuong());
    }
}
