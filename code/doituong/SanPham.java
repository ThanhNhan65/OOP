package code.doituong;

import java.util.Scanner;
import code.giaodien.*;
import code.kiemtra.InputUtils;
import code.doituong.*;
import code.danhsach.*;

public class SanPham implements INhapXuat {
    private String ma, ten, hang;
    private Loai loai;
    private double gia;
    private DanhSachLoai dsl;
    private static int manext = 1;

    public SanPham() {
        this.ma = String.format("SP%03d", manext++);
    }

    public SanPham(String ma, String ten, String hang, Loai loai, double gia) {
        this.ma = ma;
        this.ten = ten;
        this.hang = hang;
        this.loai = loai;
        this.gia = gia;
    }

    public void setDanhSachLoai(DanhSachLoai dsl) {
        this.dsl = dsl;
    }

    public static void setmanext(int manext) {
        SanPham.manext = manext;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public Loai getLoai() {
        return loai;
    }

    public void setLoai(Loai loai) {
        this.loai = loai;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void Nhap(Scanner sc) {
        System.out.print("Nhap ten san pham: ");
        ten = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(ten))
            return;
        System.out.print("Nhap ten hang: ");
        hang = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(hang))
            return;
        while (true) {
            System.out.print("Nhap gia: ");
            String input = sc.nextLine().trim();
            if (InputUtils.ThoatNeuEnter(input)) return;
            try {
                double g = Double.parseDouble(input);
                if (g < 0) {
                    System.out.println("Gia phai >= 0. Nhap lai.");
                    continue;
                }
                this.gia = g;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Gia phai la so. Nhap lai.");
            }
        }

        if (dsl != null) {
            Loai l = new Loai();
            do {
                System.out.print("Nhap ma loai: ");
                String maLoai = sc.nextLine().trim();
                if (InputUtils.ThoatNeuEnter(maLoai))
                    return;
                l = dsl.TimTheoMa(maLoai);
                if (l == null) {
                    System.out.println("Ma loai khong co");
                }
            } while (l == null);
            this.loai = l;
        }
    }

    public void Xuat() {
        System.out.printf("| %-10s | %-25s | %-20s | %-12s | %12.2f |%n",
                ma, ten, hang, loai.getMaloai(), gia);
    }

    public String tofile() {
        return ma + "," + ten + "," + hang + "," + loai.getMaloai() + "," + gia;
    }
}
