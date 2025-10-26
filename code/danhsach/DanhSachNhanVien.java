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
import code.kiemtra.InputUtils;

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
        for (int i = 0; i < n; i++) {
            dsnv[i].Xuat();
        }
    }

    public void Them(Scanner sc) {
        System.out.println("Chon loai nhan vien ban muon them(1.FullTime or 2.PartTime, Enter de thoat)");
        String input = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(input)) return;
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Lua chon khong hop le");
            return;
        }
        NhanVien nv = null;
        switch (choice) {
            case 1:
                nv = new NhanVienFullTime();
                break;
            case 2:
                nv = new NhanVienPartTime();
                break;
            default:
                System.out.println("Lua chon khong hop le");
                return;
        }
        nv.Nhap(sc);
        // Check if user pressed Enter to exit at any input
        if (nv.getHoten() == null || nv.getHoten().trim().isEmpty()
            || nv.getDiachi() == null || nv.getDiachi().trim().isEmpty()
            || nv.getSdt() == null || nv.getSdt().trim().isEmpty()
            || nv.getMaNV() == null || nv.getMaNV().trim().isEmpty()) {
            System.out.println("Da huy them nhan vien (thieu thong tin hoac nhan Enter de thoat)");
            return;
        }
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = nv;
        n++;
        GhiVaoFile("data/danhsachNV.txt");
    }

    public void GhiVaoFile(String File) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(File, false));
            for (int i = 0; i < n; i++) {
                NhanVien nv = dsnv[i];
                int work = 0;
                if (nv instanceof NhanVienFullTime) {
                    work = ((NhanVienFullTime) nv).getngay();
                } else if (nv instanceof NhanVienPartTime) {
                    work = ((NhanVienPartTime) nv).getgio();
                }
                writer.write(nv.getHoten() + "," + nv.getDiachi() + "," + nv.getSdt() + "," + nv.getMaNV() + ","
                        + work + "," + nv.getLoai());
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
                String MaNV = chuoi[3].trim();
                int work = Integer.parseInt(chuoi[4].trim());
                String loai = chuoi[5];

                NhanVien nv;
                if ("FullTime".equals(loai)) {
                    nv = new NhanVienFullTime(HoTen, Diachi, Sdt, MaNV, work);
                } else {
                    nv = new NhanVienPartTime(HoTen, Diachi, Sdt, MaNV, work);
                }
                int so = Integer.parseInt(MaNV.substring(2));
                if (so > NhanVien.dem) {
                    NhanVien.dem = so;
                }
                dsnv = Arrays.copyOf(dsnv, n + 1);
                dsnv[n] = nv;
                n++;

                line = input.readLine();
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Xoa(String MaNV) {
        if (InputUtils.ThoatNeuEnter(MaNV))
            return;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV)) {
                for (int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n - 1);
                n--;
                found = true;
                System.out.println("Da xoa nhan vien nay");
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
        boolean found;
        do {
            found = false;
            System.out.print("Nhap ma nhan vien (Enter de thoat): ");
            MaNV = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaNV))
                return;
            for (int i = 0; i < n; i++) {
                if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV)) {
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
                            String Sdt_moi = sc.nextLine();
                            while (Sdt_moi.length() != 10) {
                                System.out.print("Nhap lai so dien thoai: ");
                                Sdt_moi = sc.nextLine();
                            }
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
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV)) {
                return dsnv[i];

            }
        }
        return null;
    }

    public NhanVien TimKiemNhanVienTheoHoTen(String HoTen) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getHoten() != null && dsnv[i].getHoten().equals(HoTen)) {
                return dsnv[i];
            }
        }
        return null;
    }

    public NhanVien SuaTheoHoTen(String HoTen_moi, String MaNV_moi) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV_moi)) {
                dsnv[i].setHoten(HoTen_moi);
                return dsnv[i];
            }
        }
        return null;
    }

    public NhanVien SuaTheoDiaChi(String DiaChi_moi, String MaNV_moi) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV_moi)) {
                dsnv[i].setDiachi(DiaChi_moi);
                return dsnv[i];
            }
        }
        return null;
    }

    public NhanVien SuaTheoSDT(String Sdt_moi, String MaNV_moi) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i] != null && dsnv[i].getMaNV() != null && dsnv[i].getMaNV().equals(MaNV_moi)) {
                dsnv[i].setSdt(Sdt_moi);
                return dsnv[i];
            }
        }
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
                String input = sc.nextLine();
                if (InputUtils.ThoatNeuEnter(input))
                    return;
                nv = TimKiemNhanVienTheoMa(input);
                if (nv == null)
                    System.out.println("Khong tim thay nhan vien");
                else
                    System.out.println("Tim thay nhan vien");
                break;
            case 2:
                System.out.print("Nhap ho va ten nhan vien muon tim kiem: ");
                String input1 = sc.nextLine();
                if (InputUtils.ThoatNeuEnter(input1))
                    return;
                nv = TimKiemNhanVienTheoMa(input1);
                if (nv == null)
                    System.out.println("Khong tim thay nhan vien");
                else
                    System.out.println("Tim thay nhan vien");
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