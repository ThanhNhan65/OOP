package code.danhsach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import code.doituong.*;
import code.kiemtra.InputUtils;

public class DanhSachKho {
    private Kho[] dskho;
    private int n;

    public DanhSachKho() {
        dskho = new Kho[0];
        n = 0;
    }

    public int getN() {
        return n;
    }

    public void docFile(DanhSachSanPham dssp, DanhSachChitietHoaDon dsct) {
        dskho = new Kho[0];
        n = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/kho.txt"));
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                String[] arr = line.split(",");

                String masp = arr[0].trim();
                int dauvao = Integer.parseInt(arr[1].trim());
                SanPham sp = dssp.TimTheoMa(masp);
                Kho k = new Kho(dssp, dsct);
                dskho = Arrays.copyOf(dskho, n + 1);

                k.setsp(sp);
                k.setdauvao(dauvao);
                dskho[n] = k;
                n++;

                line = br.readLine();
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/kho.txt"));
            for (int i = 0; i < n; i++) {
                Kho k = dskho[i];
                if (k == null)
                    continue;
                String mahd = k.getsp().getMa();
                int soluong = k.getDauvao();
                bw.write(mahd + "," + soluong);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Them(Scanner sc, DanhSachSanPham dssp, DanhSachChitietHoaDon dsct) {
        Kho k = new Kho(dssp, dsct);
        k.Nhap(sc);
        dskho = Arrays.copyOf(dskho, n + 1);
        dskho[n++] = k;
        System.out.println("Da them vao kho");
        ghiFile();
    }

    public void HienThi() {
        if (n == 0) {
            System.out.println("Danh sach kho rong");
            return;
        }
        for (int i = 0; i < n; i++) {
            dskho[i].Xuat();
        }
    }

    public void Sua(Scanner sc, DanhSachSanPham dssp) {
        System.out.println("Nhap san pham muon sua");
        String masp = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(masp))
            return;
        SanPham sp = dssp.TimTheoMa(masp);
        while (sp == null) {
            System.out.println("Nhap lai ma san pham:");
            masp = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(masp))
                return;
            sp = dssp.TimTheoMa(masp);
        }
        for (int i = 0; i < n; i++) {
            if (dskho[i].getsp().getMa().equals(masp)) {
                System.out.println("Sua lai dau vao");
                int newdauvao = sc.nextInt();
                dskho[i].setdauvao(newdauvao);
            }
        }
        ghiFile();
    }

    public void TimKiem(Scanner sc, DanhSachSanPham dssp) {
        System.out.println("Nhap san pham muon tim");
        String masp = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(masp))
            return;
        SanPham sp = dssp.TimTheoMa(masp);
        while (sp == null) {
            System.out.println("Nhap lai ma san pham:");
            masp = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(masp))
                return;
            sp = dssp.TimTheoMa(masp);
        }
        for (int i = 0; i < n; i++) {
            if (dskho[i].getsp().getMa().equals(masp)) {

                dskho[i].Xuat();
                break;
            }
        }
    }

    public void Xoa(Scanner sc, DanhSachSanPham dssp) {
        System.out.println("Nhap san pham muon xoa");
        String masp = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(masp))
            return;
        SanPham sp = dssp.TimTheoMa(masp);
        while (sp == null) {
            System.out.println("Nhap lai ma san pham:");
            masp = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(masp))
                return;
            sp = dssp.TimTheoMa(masp);
        }
        for (int i = 0; i < n;) {
            if (dskho[i].getsp().getMa().equals(masp)) {
                for (int j = i; j < n - 1; j++) {
                    dskho[j] = dskho[j + 1];
                }
                dskho = Arrays.copyOf(dskho, n - 1);
                n--;
            } else
                i++;

        }
        ghiFile();
    }

}