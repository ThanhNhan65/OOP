package code.danhsach;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import code.doituong.*;
import code.kiemtra.InputUtils;

public class DanhSachKhachHang {
    private KhachHang[] dskh;
    private int n;
    public Scanner sc = new Scanner(System.in);

    public DanhSachKhachHang() {
        n = 0;
        dskh = new KhachHang[0];
    }

    public DanhSachKhachHang(int n, KhachHang[] dskh) {
        this.n = n;
        this.dskh = dskh;
    }

    public void GhiVaoFile(String File) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(File, false));
            for (int i = 0; i < n; i++) {
                KhachHang kh = dskh[i];
                writer.write(kh.getHoten() + "," + kh.getDiachi() + "," + kh.getSdt() + "," + kh.MaKH);
                writer.newLine();
            }
            writer.close();
            System.out.println("Da cap nhat danh sach vao file thanh cong");
        } catch (Exception e) {
            System.out.println("loi khi vao file: " + e);
        }
    }

    public void DocTuFile(String File) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(File));
            String line = input.readLine();
            while (line != null) {
                String[] chuoi = line.split(",");

                String HoTen = chuoi[0].trim();
                String Diachi = chuoi[1].trim();
                String Sdt = chuoi[2].trim();
                String MaKH = chuoi[3].trim();
                line = input.readLine();
                KhachHang kh = new KhachHang(HoTen, Diachi, Sdt, MaKH);
                int so = Integer.parseInt(MaKH.substring(2));
                if (so > KhachHang.dem) {
                    KhachHang.dem = so;
                }
                dskh = Arrays.copyOf(dskh, n + 1);
                dskh[n] = kh;
                n++;
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void HienThiDS() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (int i = 0; i < n; i++) {
            dskh[i].Xuat();
        }
    }

    public void Them() {
        dskh = Arrays.copyOf(dskh, n + 1);
        dskh[n] = new KhachHang();
        dskh[n].Nhap(sc);
        n++;
        GhiVaoFile("data/danhsachKH.txt");
    }

    public void Xoa(String MaKH) {
        if (InputUtils.ThoatNeuEnter(MaKH))
            return;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                for (int j = i; j < n - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                dskh = Arrays.copyOf(dskh, n - 1);
                n--;
                System.out.println("Da xoa khach hang nay");
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Khong tim thay khach hang ");
        GhiVaoFile("data/danhsachKH.txt");
    }

    public void SuaKH() {
        KhachHang kh = null;
        System.out.println("-----SUA THONG TIN KHACH HANG-----");
        System.out.println("1. Sua ten khach hang.");
        System.out.println("2. Sua dia chi khach hang.");
        System.out.println("3. Sua so dien thoai khach hang.");
        System.out.println("-----------------------------------");
        System.out.print("Chon: ");
        int c = sc.nextInt();
        sc.nextLine();

        String MaKH;
        boolean found = false;
        do {
            System.out.print("Nhap ma khach hang (Enter de thoat): ");
            MaKH = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaKH))
                return;
            for (int i = 0; i < n; i++) {
                if (dskh[i].getMaKH().equals(MaKH)) {
                    found = true;
                    switch (c) {
                        case 1:
                            System.out.print("Sua ten khach hang: ");
                            String ten_moi = sc.nextLine();
                            if (InputUtils.ThoatNeuEnter(ten_moi))
                                return;
                            kh = SuaTheoHoTen(MaKH, ten_moi);
                            break;
                        case 2:
                            System.out.print("Sua dia chi khach hang: ");
                            String diachi_moi = sc.nextLine();
                            if (InputUtils.ThoatNeuEnter(diachi_moi))
                                return;
                            kh = SuaTheoDiaChi(MaKH, diachi_moi);
                            break;
                        case 3:
                            System.out.print("Sua so dien thoai khach hang: ");
                            String sdt_moi = sc.nextLine();
                            while (sdt_moi.length() != 10) {
                                System.out.print("Nhap lai so dien thoai: ");
                                sdt_moi = sc.nextLine();
                            }
                            kh = SuaTheoSDT(MaKH, sdt_moi);
                            break;
                        default:
                            System.out.println("Thoat chuong trinh.");
                            break;
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Khong tim thay ma khach hang, vui long nhap lai.");
            }
        } while (!found);

        if (kh != null) {
            System.out.println("Da sua thong tin khach hang.");
            kh.Xuat();
        }
        GhiVaoFile("data/danhsachKH.txt");

    }

    public void TimkiemKH() {
        KhachHang kh = null;
        System.out.println("-----TIM KIEM KHACH HANG-----");
        System.out.println("1.Tim kiem theo ten.");
        System.out.println("2.Tim kiem theo Ma khach hang.");
        System.out.println("-----------------------------");
        System.out.println("Chon: ");
        int c = sc.nextInt();
        sc.nextLine();
        switch (c) {
            case 1:
                System.out.println("Nhap ho ten khach hang ban muon tim kiem: ");
                kh = Timkiem_HoTen(sc.nextLine());
                if (InputUtils.ThoatNeuEnter(sc.nextLine()))
                    return;
                break;
            case 2:
                System.out.println("Nhap ma khach hang ban muon tim kiem: ");
                kh = Timkiem_MaKH(sc.nextLine());
                if (InputUtils.ThoatNeuEnter(sc.nextLine()))
                    return;
                break;
            default:
                System.out.println("Thoat chuong trinh");
                break;
        }
        if (kh != null) {
            System.out.println("Da tim thay khach hang: ");
            kh.Xuat();
        }
    }

    public KhachHang Timkiem_MaKH(String MaKH) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                return dskh[i];
            }
        }
        return null;
    }

    public KhachHang Timkiem_HoTen(String hoten) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getHoten().equals(hoten)) {
                return dskh[i];
            }
        }
        return null;
    }

    public KhachHang SuaTheoHoTen(String MaKH, String hoten_moi) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                dskh[i].setHoten(hoten_moi);
                return dskh[i];
            }
        }
        return null;
    }

    public KhachHang SuaTheoDiaChi(String MaKH, String diachi_moi) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                dskh[i].setDiachi(diachi_moi);
                return dskh[i];
            }
        }
        return null;
    }

    public KhachHang SuaTheoSDT(String MaKH, String sdt_moi) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                dskh[i].setSdt(sdt_moi);
                return dskh[i];
            }
        }
        return null;
    }
}