package code;

import java.util.Scanner;

public class NhanVien extends ConNguoi {
    public String MaNV;
    public String Chucvu;
    public int ChamCong;
    public long Luong;
    public static int dem = 0;

    public NhanVien() {
        super();
        MaNV = "";
        Chucvu = "";
        ChamCong = 0;
        Luong = 0;
    }

    public NhanVien(String Hoten, String Diachi, long Sdt, String Chucvu, int ChamCong, String MaNV, long Luong) {
        super(Hoten, Diachi, Sdt);
        this.MaNV = MaNV;
        this.Chucvu = Chucvu;
        this.ChamCong = ChamCong;
        this.Luong = getLuong();
    }

    public String getMaNV() {
        return MaNV;
    }

    public String getChucVu() {
        return Chucvu;
    }

    public void setChucvu(String Chucvu) {
        this.Chucvu = Chucvu;
    }

    public void setChamCong(int ChamCong) {
        this.ChamCong = ChamCong;
    }

    public int getChamCong() {
        return ChamCong;
    }

    public void setLuong(long Luong) {
        this.Luong = Luong;
    }

    public long getLuong() {
        if (Chucvu.equals("Quan ly")) {
            return ChamCong * 500000;
        } else if (Chucvu.equals("Nhan vien")) {
            return ChamCong * 300000;
        } else {
            return 0;
        }
    }

    @Override
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        super.Nhap();
        System.out.print("Nhap chuc vu nhan vien: ");
        Chucvu = sc.nextLine();
        System.out.print("Nhap so ngay nhan vien di lam: ");
        ChamCong = sc.nextInt();
        dem++;
        MaNV = String.format("NV%03d", dem);
        Luong = getLuong();
    }

    @Override
    public void Xuat() {
        Luong = getLuong();
        System.out.printf("| %-6s | %-20s | %-20s | %-15d | %-14s | %-12d | %-12.0f |\n", MaNV, Hoten, Diachi, Sdt,
                Chucvu, ChamCong, (double) Luong);
    }
}
