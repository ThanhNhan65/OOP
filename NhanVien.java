

import java.util.Scanner;

public class NhanVien extends ConNguoi {
    public int MaNV;
    public String ChucVu;
    public int STT;
    public int n;
    int iNumber[] = new int[n + 10];

    public NhanVien() {
        super();
        int MaNV = 0;
        String ChucVu = "";
    }

    public NhanVien(String Hoten, String Diachi, Long Sdt, String Matkhau, int MaNV, String ChucVu, int STT) {
        super(Hoten, Diachi, Sdt);
        this.MaNV = MaNV;
        this.ChucVu = ChucVu;
    }

    public int getMaNV() {
        return MaNV;
    }

    public String getChucVu(String ChucVu) {
        return ChucVu;
    }
    
    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        super.Nhap();
        System.out.print("Nhap ma nhan vien: ");
        MaNV = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhap chuc vu nhan vien: " );
        ChucVu = sc.nextLine();
    }

    public void Xuat() {
        super.Xuat();
        System.out.println("Ma nhan vien: " + MaNV);
        System.out.println("Chuc vu cua nhan vien: " + ChucVu);
    }
}
