package code.doituong;

import java.util.Scanner;

import code.kiemtra.InputUtils;

public class NhanVienFullTime extends NhanVien {
    private int ngay;
    private static long luong = 300000;

    public NhanVienFullTime() {
        super();
        ngay = 0;
    }
    public NhanVienFullTime(String Hoten, String Diachi, String Sdt, String MaNV, int ngay) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.ngay = ngay;
    }

    public int getngay() {
        return ngay;
    }

    @Override
    public String getLoai() {
        return "FullTime";
    }


    public long tinhLuong() {
        return ngay * luong;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so ngay lam viec cua nhan vien: ");
        String input = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(input))
            return;
        this.ngay = Integer.parseInt(input);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-10s | %4d ngay | %12d |%n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), ngay, tinhLuong());
    }
}
