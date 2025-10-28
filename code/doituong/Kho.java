package code.doituong;

import java.util.Scanner;

import code.danhsach.*;
import code.kiemtra.InputUtils;

public class Kho {
    private SanPham sp;
    private int Dauvao;
    private int Daura;
    private int Ton;
    private DanhSachSanPham dssp;
    private DanhSachChitietHoaDon dsct;

    public Kho() {
    }

    public Kho(DanhSachSanPham dssp, DanhSachChitietHoaDon dsct) {
        this.dssp = dssp;
        this.dsct = dsct;
    }

    public void setsp(SanPham sp) {
        this.sp = sp;
    }

    public void setdauvao(int dauvao) {
        this.Dauvao = dauvao;
    }

    public SanPham getsp() {
        return sp;
    }

    public int getDauvao() {
        return Dauvao;
    }

    public int tinhDaura() {
        int tong = 0;
        for (int i = 0; i < dsct.getN(); i++)
            if (sp.getMa().equals(dsct.getct(i).getSP().getMa()))
                tong += dsct.getct(i).getSL();
        return tong;
    }

    public int tinhTon() {
        return Dauvao - tinhDaura();
    }

    public void Nhap(Scanner sc) {
        System.out.println("Nhap ma san pham:");
        String masp = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(masp))
            return;
        this.sp = dssp.TimTheoMa(masp);
        while (sp == null) {
            System.out.println("Nhap lai ma san pham:");
            masp = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(masp))
                return;
            this.sp = dssp.TimTheoMa(masp);
        }
        int check = tinhDaura();
        System.out.println("Nhap dau vao (so nguyen >= " + check + "):");
        while (true) {
            String line = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(line))
                return;
            try {
                int val = Integer.parseInt(line.trim());
                if (val < 0) {
                    System.out.println("Nhap lai:");
                    continue;
                }
                if (val < check) {
                    System.out.println("Nhap lai:");
                    continue;
                }
                this.Dauvao = val;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nhap khong hop le. Vui long nhap mot so nguyen:");
            }
        }
    }

    public void Xuat() {
        System.out.printf("| %-10s | %-25s | %10d | %10d | %10d |%n",
                sp.getMa(), sp.getTen(), Dauvao, tinhDaura(), tinhTon());
    }
}