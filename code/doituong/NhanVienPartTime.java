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

    public long getLuong() {
        return gio * luong;
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        String input = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(input))
            return;
        int gio = Integer.parseInt(input);
    }

    @Override
    public void Xuat() {
        System.out.printf("| %-8s | %-25s | %-25s | %-15s | %-10s | %8d gio  | %12.0f |%n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), gio, (double) getLuong());
    }
}
