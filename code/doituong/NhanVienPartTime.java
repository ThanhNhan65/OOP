package code.doituong;

import java.util.Scanner;

public class NhanVienPartTime extends NhanVien {
<<<<<<< HEAD
    private String loai;
    private int gio;
    private static final long luong_gio = 25000;
=======
    private int gio;
    private static long luong = 25000;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c

    public NhanVienPartTime() {
        super();
        gio = 0;
    }

<<<<<<< HEAD
    public NhanVienPartTime(String Hoten, String Diachi, long Sdt, String MaNV, int gio, String loai) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.gio = gio;
        this.loai = "PartTime";
=======
    public NhanVienPartTime(String Hoten, String Diachi, long Sdt, String MaNV, int gio) {
        super(Hoten, Diachi, Sdt, MaNV);
        this.gio = gio;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    @Override
    public String getLoai() {
        return "PartTime";
    }
<<<<<<< HEAD

    public int getGio() {
=======
    public int getgio(){
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
        return gio;
    }

    public long getLuong() {
<<<<<<< HEAD
        return gio * luong_gio;
=======
        return gio * luong;
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }

    public void Nhap(Scanner sc) {
        super.Nhap(sc);
        System.out.println("Nhap so gio lam viec cua nhan vien: ");
        gio = sc.nextInt();
    }

    @Override
    public void Xuat() {
<<<<<<< HEAD
        System.out.printf("| %-6s | %-20s | %-20s | %-12d | %-12s | %-12d |\n",
                getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), getLuong());
=======
        System.out.printf("| %-8s | %-25s | %-25s | %-15s | %-10s | %8d gio  | %12.0f |%n",
            getMaNV(), getHoten(), getDiachi(), getSdt(), getLoai(), gio, (double)getLuong());
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }
}
