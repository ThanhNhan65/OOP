package code.doituong;

import java.util.Scanner;

public class NhanVienPartTime extends NhanVien {
    private String loai;
    private int gio;
    private static final long luong_gio = 25000;

    public NhanVienPartTime() {
        super();
        gio = 0;
    }

    public NhanVienPartTime(String Hoten, String Diachi, long Sdt, String MaNV, int gio, String loai) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.gio = gio;
        this.loai = "PartTime";
    }

    @Override
    public String getLoai() {
        return "PartTime";
    }

    public int getGio() {
        return gio;
    }

    public long getLuong() {
        return gio * luong_gio;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        gio = sc.nextInt();
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-20s | %-20s | %-12d | %-12s | %-12d |\n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), getLuong());
    }
}
