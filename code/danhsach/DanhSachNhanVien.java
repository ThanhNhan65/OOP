package code.danhsach;

import java.util.Scanner;
import java.util.Arrays;
import java.io.Reader;
import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import code.doituong.*;

public class DanhSachNhanVien {
    public NhanVien[] dsnv;
    public int n;

    public DanhSachNhanVien() {
        dsnv = new NhanVien[0];
        n = 0;
    }

    public DanhSachNhanVien(int n, NhanVien[] dsnv) {
        this.n = n;
        this.dsnv = dsnv;
    }

    public void HienThiDS() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");
        System.out.printf("| %-6s | %-20s | %-20s | %-15s | %-14s | %-12s | %-12s |\n", "Ma", "Ho va ten", "Dia chi",
                "So dien thoai", "Luong");
        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");

        for (int i = 0; i < n; i++) {
            dsnv[i].Xuat();
        }

        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");
    }

    public void Them(Scanner sc) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        System.out.println("Chon loai nhan vien ban muon them(1.FullTime or 2.PartTime)");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                dsnv[n] = new NhanVienFullTime();
                break;
            case 2:
                dsnv[n] = new NhanVienPartTime();
                break;
        }
        dsnv[n].Nhap(sc);
        n++;
        GhiVaoFile("data/danhsachNV.txt");
    }

    public void GhiVaoFile(String File) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(File, false));
            for (int i = 0; i < n; i++) {
                NhanVien nv = dsnv[i];
                writer.write(
                        nv.getHoten() + "," + nv.getDiachi() + "," + nv.getSdt() + "," + nv.getMaNV() + ","
                                + nv.getLoai());
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

                String HoTen = chuoi[0];
                String Diachi = chuoi[1];
                long Sdt = Long.parseLong(chuoi[2]);
                String MaNV = chuoi[3];
                line = input.readLine();
                NhanVien nv = new NhanVien();
                if (nv.getLoai().equals("FullTime")) {
                    nv = new NhanVienFullTime(HoTen, Diachi, Sdt, MaNV);
                } else {
                    nv = new NhanVienPartTime(HoTen, Diachi, Sdt, MaNV);
                }
                int so = Integer.parseInt(MaNV.substring(2));
                if (so > nv.dem) {
                    nv.dem = so;
                }
                dsnv = Arrays.copyOf(dsnv, n + 1);
                dsnv[n] = nv;
                n++;
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Xoa(String MaNV) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV)) {
                for (int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n - 1);
                n--;
                found = true;
                System.out.println("da xoa nhan vien nay");
                break;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay nhan vien");
        }
        GhiVaoFile("data/danhsachNV.txt");
    }

    public void Sua_Chi_Tiet(Scanner sc) {
        NhanVien nv = null;
        boolean found = false;
        System.out.println("-----SUA THONG TIN NHAN VIEN-----");
        System.out.println("1. Sua Ho va ten nhan vien.");
        System.out.println("2. Sua dia chi cua nhan vien.");
        System.out.println("3. Sua Sdt cua nhan vien.");
        System.out.println("---------------------------------");
        System.out.print("Vui long chon: ");
        int chon = sc.nextInt();
        sc.nextLine();

        if (chon == 0) {
            System.out.println("Thoat khoi chuong trinh");
            return;
        }

        String MaNV;
        do {
            System.out.print("Nhap ma nhan vien: ");
            MaNV = sc.nextLine();
            for (int i = 0; i < n; i++) {
                if (dsnv[i].getMaNV().equals(MaNV)) {
                    found = true;
                    switch (chon) {
                        case 1:
                            System.out.println("Nhap ho va ten moi: ");
                            String HoTen_moi = sc.nextLine();
                            nv = SuaTheoHoTen(HoTen_moi, MaNV);
                            if (nv != null)
                                nv.Xuat();
                            break;
                        case 2:
                            System.out.println("Nhap dia chi moi cua nhan vien: ");
                            String DiaChi_moi = sc.nextLine();
                            nv = SuaTheoDiaChi(DiaChi_moi, MaNV);
                            if (nv != null)
                                nv.Xuat();
                            break;
                        case 3:
                            System.out.println("Nhap so dien thoai moi cua nhan vien: ");
                            long Sdt_moi = sc.nextLong();
                            nv = SuaTheoSDT(Sdt_moi, MaNV);
                            if (nv != null)
                                nv.Xuat();
                            break;
                        default:
                            System.out.println("Lua chon khong hop le.");
                            break;
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Khong tim thay ma nhan vien, vui long nhap lai.");
            }
        } while (!found);

        GhiVaoFile("data/danhsachNV.txt");
    }

    public NhanVien TimKiemNhanVienTheoMa(String MaNV) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV)) {
                found = true;
                return dsnv[i];
            }
        }
        return null;
    }

    public NhanVien TimKiemNhanVienTheoHoTen(String HoTen) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getHoten().equals(HoTen)) {
                return dsnv[i];
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien");
        return null;
    }

    public NhanVien SuaTheoHoTen(String HoTen_moi, String MaNV_moi) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV_moi)) {
                found = true;
                dsnv[i].setHoten(HoTen_moi);
                return dsnv[i];
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien.");
        return null;
    }

    public NhanVien SuaTheoDiaChi(String DiaChi_moi, String MaNV_moi) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV_moi)) {
                found = true;
                dsnv[i].setDiachi(DiaChi_moi);
                return dsnv[i];
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien.");
        return null;
    }

    public NhanVien SuaTheoSDT(long Sdt_moi, String MaNV_moi) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV_moi)) {
                found = true;
                dsnv[i].setSdt(Sdt_moi);
                return dsnv[i];
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien.");
        return null;
    }

    public void TimkiemNV(Scanner sc) {
        NhanVien nv = null;
        System.out.println("-----TIM KIEM NHAN VIEN-----");
        System.out.println("1.Tim kiem nhan vien theo ma");
        System.out.println("2.Tim kiem nhan vien theo ho va ten");
        System.out.println("-----------------------------");
        System.out.print("Vui long chon: ");
        int c = sc.nextInt();
        sc.nextLine();
        switch (c) {
            case 1:
                System.out.print("Nhap ma nhan vien muon tim kiem: ");
                nv = TimKiemNhanVienTheoMa(sc.nextLine());
                break;
            case 2:
                System.out.print("Nhap ho va ten nhan vien muon tim kiem: ");
                nv = TimKiemNhanVienTheoHoTen(sc.nextLine());
                break;
            default:
                System.out.println("Khong tim kiem nhan vien.");
                break;
        }
        if (nv != null) {
            System.out.println("Thong tin nhan vien tim thay: ");
            nv.Xuat();
        }
    }
}