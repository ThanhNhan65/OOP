package code.doituong;

import java.util.Scanner;

public class NhanVienFullTime extends NhanVien {
    private String loai;
    private int ngay;
    private static final long luong_ngay = 300000;

    public NhanVienFullTime() {
        super();
        ngay = 0;
    }

    public NhanVienFullTime(String Hoten, String Diachi, long Sdt, String MaNV, int ngay, String loai) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = ngay;
        this.loai = "FullTime";
    }

    @Override
    public String getLoai() {
        return "FullTime";
    }

    public long getLuong() {
        return ngay * luong_ngay;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so ngay lam viec cua nhan vien: ");
        ngay = sc.nextInt();
    }

    public int getNgay() {
        return ngay;
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-6s | %-20s | %-20s | %-12d | %-12s | %-12d |\n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), getLuong());
    }
}
