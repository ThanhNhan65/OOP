package code;

import java.util.Scanner;
import java.util.Arrays;
import java.io.Reader;
import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

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

    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("nhap n: ");
        n = sc.nextInt();
        dsnv = new NhanVien[n];
        for (int i = 0; i < n; i++) {
            dsnv[i] = new NhanVien();
            dsnv[i].Nhap();
        }
    }

    public void Xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");
        System.out.printf("| %-6s | %-20s | %-20s | %-15s | %-14s | %-12s | %-12s |\n", "Ma", "Ho va ten", "Dia chi",
                "So dien thoai", "Chuc vu", "ChamCong", "Luong");
        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");

        for (int i = 0; i < n; i++) {
            dsnv[i].Xuat();
        }

        System.out.println(
                "+--------+----------------------+----------------------+-----------------+----------------+--------------+--------------+");
    }

    public void Them() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vi tri muon them: ");
        int k = sc.nextInt();
        if (k < 0 || k > n) {
            System.out.println("Vi tri khong hop le");
            return;
        }

        dsnv = Arrays.copyOf(dsnv, n + 1);
        for (int i = n; i > k; i--) {
            dsnv[i] = dsnv[i - 1];
        }
        dsnv[k] = new NhanVien();
        dsnv[k].Nhap();
        n++;
    }

    public void GhiVaoFile(String File) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(File, false));
            for (int i = 0; i < n; i++) {
                NhanVien nv = dsnv[i];
                writer.write(
                        nv.Hoten + "," + nv.Diachi + "," + nv.Sdt + "," + nv.MaNV + "," + nv.Chucvu + "," + nv.ChamCong
                                + "," + nv.Luong);
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
                for (int i = 0; i < chuoi.length; i++) {
                    System.out.println(chuoi[i]);
                }

                String HoTen = chuoi[0];
                String Diachi = chuoi[1];
                long Sdt = Long.parseLong(chuoi[2]);
                String MaNV = chuoi[3];
                String Chucvu = chuoi[4];
                int ChamCong = Integer.parseInt(chuoi[5]);
                long Luong = Long.parseLong(chuoi[6]);
                line = input.readLine();
                NhanVien nv = new NhanVien(HoTen, Diachi, Sdt, Chucvu, ChamCong, MaNV, Luong);
                int so = Integer.parseInt(MaNV.substring(2));
                if (so > NhanVien.dem) {
                    NhanVien.dem = so;
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
                System.out.println("Đã xóa nhân viên này");
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy nhân viên");
        } /*
           * else {
           * NhanVien.dem = 0;
           * for (int i = 0; i < n; i++) {
           * int so = Integer.parseInt(dsnv[i].getMaNV().substring(2));
           * if (so > NhanVien.dem) {
           * NhanVien.dem = so;
           * }
           * }
           * }
           */
    }

    public void TimkiemNV() {
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.println("-----TIM KIEM NHAN VIEN-----");
        System.out.println("1.Tim kiem nhan vien theo ma");
        System.out.println("2.Tim kiem nhan vien theo ho va ten");
        System.out.println("3.Tim kiem nhan vien theo chuc vu");
        System.out.println("-----------------------------");
        System.out.print("chon 1 con so: ");
        int c = sc.nextInt();
        sc.nextLine();
        switch (c) {
            case 1:
                System.out.print("Nhap ma nhan vien muon tim kiem: ");
                int maNV = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    if (dsnv[i].getMaNV().equals(maNV)) {
                        dsnv[i].Xuat();
                        found = true;
                    }
                }
                break;
            case 2:
                System.out.print("Nhap ho va ten nhan vien muon tim kiem: ");
                String hoten = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dsnv[i].getHoten().equals(hoten)) {
                        dsnv[i].Xuat();
                        found = true;
                    }
                }
                break;
            case 3:
                System.out.print("Nhap chuc vu nhan vien muon tim kiem: ");
                String chucvu = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dsnv[i].getChucVu().equals(chucvu)) {
                        dsnv[i].Xuat();
                        found = true;
                    }
                }
                break;
            default:
                System.out.println("Khong tim kiem nhan vien.");
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien.");
    }

    public void Sua_Chi_Tiet(String MaKH) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("-----SUA THONG TIN NHAN VIEN-----");
        System.out.println("1.Sua Ho va ten nhan vien.");
        System.out.println("2.Sua dia chi cua nhan vien.");
        System.out.println("3.Sua Sdt cua nhan vien.");
        System.out.println("4.Sua chuc vu cua nhan vien.");
        System.out.println("5.Sua so lan cham cong cua nhan vien.");
        System.out.println("---------------------------------");
        System.out.print("Chon 1 so di con: ");
        int chon = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaKH)) {
                switch (chon) {
                    case 1:
                        System.out.println("Nhap ho va ten moi: ");
                        String Hoten_moi = sc.nextLine();
                        dsnv[i].setHoten(Hoten_moi);
                        found = true;
                        break;
                    case 2:
                        System.out.println("Nhap dia chi moi cua nhan vien: ");
                        String Diachi_moi = sc.nextLine();
                        dsnv[i].setDiachi(Diachi_moi);
                        found = true;
                        break;
                    case 3:
                        System.out.println("Nhap so dien thoai moi cua nhan vien: ");
                        long Sdt_moi = sc.nextInt();
                        dsnv[i].setSdt(Sdt_moi);
                        found = true;
                        break;
                    case 4:
                        System.out.println("Nhap chuc vu moi cua nhan vien: ");
                        String Chucvu_moi = sc.nextLine();
                        dsnv[i].setChucvu(Chucvu_moi);
                        found = true;
                        break;
                    case 5:
                        System.out.println("Nhap so lan cham cong moi cua nhan vien: ");
                        int ChamCong_moi = sc.nextInt();
                        dsnv[i].setChamCong(ChamCong_moi);
                        break;
                    default:
                        System.out.println("Thoat");
                        return;
                }
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien ");
    }
}
