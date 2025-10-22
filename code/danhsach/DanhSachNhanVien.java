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
    public Scanner sc = new Scanner(System.in);

    public DanhSachNhanVien() {
        dsnv = new NhanVien[0];
        n = 0;
    }

    public DanhSachNhanVien(int n, NhanVien[] dsnv) {
        this.n = n;
        this.dsnv = dsnv;
    }

    public void Nhap() {
        System.out.print("nhap n: ");
        n = sc.nextInt();
        dsnv = new NhanVien[n];
        for (int i = 0; i < n; i++) {
            dsnv[i] = new NhanVien();
            dsnv[i].Nhap(sc);
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
        dsnv[k].Nhap(sc);
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
        }
    }

    public void Sua_Chi_Tiet(String MaNV) {
        NhanVien nv = null;
        boolean found = false;
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
                        sc.nextLine();
                        nv = SuaTheoSDT(Sdt_moi, MaNV);
                        if (nv != null)
                            nv.Xuat();
                        break;
                    case 4:
                        System.out.println("Nhap chuc vu moi cua nhan vien: ");
                        String ChucVu_moi = sc.nextLine();
                        nv = SuaTheoChucVu(ChucVu_moi, MaNV);
                        if (nv != null)
                            nv.Xuat();
                        found = true;
                        break;
                    case 5:
                        System.out.println("Nhap so lan cham cong moi cua nhan vien: ");
                        int SoLanChamCong_moi = sc.nextInt();
                        nv = SuaSoLanChamCong(SoLanChamCong_moi, MaNV);
                        if (nv != null)
                            nv.Xuat();
                        break;
                    default:
                        System.out.println("Thoat");
                        return;
                }
                break;
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien ");
    }

    public DanhSachNhanVien TimKiemNhanVienTheoMa(String MaNV) {
        DanhSachNhanVien ketQua = new DanhSachNhanVien();

        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV)) {
                ketQua.dsnv = Arrays.copyOf(ketQua.dsnv, ketQua.n + 1);
                ketQua.dsnv[ketQua.n] = dsnv[i];
                ketQua.n++;
            }
        }
        return ketQua;
    }

    public DanhSachNhanVien TimKiemNhanVienTheoHoTen(String HoTen) {
        DanhSachNhanVien ketQua = new DanhSachNhanVien();

        for (int i = 0; i < n; i++) {
            if (dsnv[i].getHoten().equals(HoTen)) {
                ketQua.dsnv = Arrays.copyOf(ketQua.dsnv, ketQua.n + 1);
                ketQua.dsnv[ketQua.n] = dsnv[i];
                ketQua.n++;
            }
        }
        return ketQua;
    }

    public DanhSachNhanVien TimKiemNhanVienTheoChucVu(String ChucVu) {
        DanhSachNhanVien ketQua = new DanhSachNhanVien();

        for (int i = 0; i < n; i++) {
            if (dsnv[i].getChucVu().equals(ChucVu)) {
                ketQua.dsnv = Arrays.copyOf(ketQua.dsnv, ketQua.n + 1);
                ketQua.dsnv[ketQua.n] = dsnv[i];
                ketQua.n++;
            }
        }
        return ketQua;
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

    public NhanVien SuaSoLanChamCong(int SoLanChamCong_moi, String MaNV_moi) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV_moi)) {
                found = true;
                dsnv[i].setChamCong(SoLanChamCong_moi);
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

    public NhanVien SuaTheoChucVu(String ChucVu_moi, String MaNV_moi) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equals(MaNV_moi)) {
                found = true;
                dsnv[i].setChucvu(ChucVu_moi);
                return dsnv[i];
            }
        }
        if (!found)
            System.out.println("Khong tim thay nhan vien.");
        return null;
    }

    public void TimkiemNV() {
        DanhSachNhanVien dsnv = null;
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
                dsnv = TimKiemNhanVienTheoMa(sc.nextLine());
                break;
            case 2:
                System.out.print("Nhap ho va ten nhan vien muon tim kiem: ");
                dsnv = TimKiemNhanVienTheoHoTen(sc.nextLine());
                break;
            case 3:
                System.out.print("Nhap chuc vu nhan vien muon tim kiem: ");
                dsnv = TimKiemNhanVienTheoChucVu(sc.nextLine());
                break;
            default:
                System.out.println("Khong tim kiem nhan vien.");
                break;
        }
        if (dsnv != null) {
            System.out.println("Thong tin nhan vien tim thay: ");
            dsnv.Xuat();
        }
    }
}
