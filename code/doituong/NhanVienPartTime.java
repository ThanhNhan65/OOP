package code.doituong;

import java.util.Scanner;

import code.kiemtra.InputUtils;

public class NhanVienPartTime extends NhanVien {
    private int gio;
    private static long luong = 25000;

    public NhanVienPartTime() {
        super();
        gio = 0;
    }

    public NhanVienPartTime(String Hoten, String Diachi, String Sdt, String MaNV, int gio) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.gio = gio;
    }

    @Override
    public String getLoai() {
        return "PartTime";
    }

    public int getgio() {
        return gio;
    }

    public long tinhLuong() {
        return gio * luong;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        String input = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(input))
            return;
        this.gio = Integer.parseInt(input);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-4s | %-20s | %-20s | %-15s | %-10s | %4d gio  | %12d |%n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), gio, tinhLuong());
    }
}
