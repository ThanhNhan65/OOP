package code.doituong;

import java.util.Scanner;

public class NhanVienFullTime extends NhanVien {
<<<<<<< HEAD
    private String loai;
    private int ngay;
    private static final long luong_ngay = 300000;
=======
    private int ngay;
    private static long luong = 300000;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c

    public NhanVienFullTime() {
        super();
        ngay = 0;
    }
<<<<<<< HEAD

    public NhanVienFullTime(String Hoten, String Diachi, long Sdt, String MaNV, int ngay, String loai) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = ngay;
        this.loai = "FullTime";
=======
    public int getngay(){
        return ngay;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    @Override
    public String getLoai() {
        return "FullTime";
    }

<<<<<<< HEAD
    public long getLuong() {
        return ngay * luong_ngay;
=======
    public NhanVienFullTime(String Hoten, String Diachi, long Sdt, String MaNV, int ngay) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = ngay;
    }

    public long getLuong() {
        return ngay * luong;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
<<<<<<< HEAD
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
=======
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        ngay = sc.nextInt();
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-8s | %-25s | %-25s | %-15s | %-10s | %8d ngay | %12.0f |%n",
            getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), ngay, (double)getLuong());
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }
}
