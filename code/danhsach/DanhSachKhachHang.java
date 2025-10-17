package code;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class DanhSachKhachHang {
    public KhachHang[] dskh;
    public int n;

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
                writer.write(kh.Hoten + "," + kh.Diachi + "," + kh.Sdt + "," + kh.MaKH);
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
                String MaKH = chuoi[3];
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

    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong khach hang: ");
        n = sc.nextInt();
        dskh = new KhachHang[n];
        for (int i = 0; i < n; i++) {
            dskh[i] = new KhachHang();
            dskh[i].Nhap();
        }
    }

    public void Xuat() {
        if (n == 0) {
            System.out.println("Danh sach rong!");
            return;
        }

        System.out
                .println("+--------+-----------------------+----------------------+-----------------+");
        System.out.printf("| %-6s | %-21s | %-20s | %-15s |\n", "Ma", "Ho va ten", "Dia chi", "So dien thoai");
        System.out
                .println("+--------+-----------------------+----------------------+-----------------+");

        for (int i = 0; i < n; i++) {
            dskh[i].Xuat();
        }

        System.out
                .println("+--------+-----------------------+----------------------+-----------------+");
    }

    public void Them() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vi tri can them: ");
        int k = sc.nextInt();
        if (k < 0 || k > n) {
            System.out.println("Vi tri khong hop le");
            return;
        }

        dskh = Arrays.copyOf(dskh, n + 1);
        for (int i = n; i > k; i--) {
            dskh[i] = dskh[i - 1];
        }
        dskh[k] = new KhachHang();
        dskh[k].Nhap();
        n++;
    }

    public void Xoa(String MaKH) {
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
    }

    public void SuaKH(String MaKH) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("-----SUA THONG TIN KHACH HANG-----");
        System.out.println("1.Sua ten khach hang.");
        System.out.println("2.Sua dia chi khach hang.");
        System.out.println("3.Sua so dien thoai khach hang.");
        System.out.println("-----------------------------------");
        System.out.print("Hay chon 1 so");
        int c = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equals(MaKH)) {
                switch (c) {
                    case 1:
                        System.out.print("Sua ten khach hang: ");
                        String ten_moi = sc.nextLine();
                        dskh[i].setHoten(ten_moi);
                        found = true;
                        break;
                    case 2:
                        System.out.print("Sua dia chi khach hang: ");
                        String diachi_moi = sc.nextLine();
                        dskh[i].setDiachi(diachi_moi);
                        found = true;
                        break;
                    case 3:
                        System.out.print("Sua so dien thoai khach hang: ");
                        long sdt_moi = sc.nextLong();
                        dskh[i].setSdt(sdt_moi);
                        found = true;
                        break;
                    default:
                        System.out.println("Thoat chuong trinh.");
                        return;
                }
            }
        }
        if (!found)
            System.out.println("Khong tim thay khach hang");
        if (found)
            System.out.println("Da sua thong tin khach hang ");
    }

    public void TimkiemKH() {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("-----TIM KIEM KHACH HANG-----");
        System.out.println("1.Tim kiem theo ten.");
        System.out.println("2.Tim kiem theo Ma khach hang.");
        System.out.println("-----------------------------");
        System.out.println("Chon 1 so: ");
        int c = sc.nextInt();
        sc.nextLine();
        switch (c) {
            case 1:
                System.out.println("Nhap ho ten khach hang ban muon tim kiem: ");
                String hoten = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dskh[i].getHoten().equals(hoten))
                        dskh[i].Xuat();
                }
                break;
            case 2:
                System.out.println("Nhap ma khach hang ban muon tim kiem: ");
                String maKH = sc.nextLine();
                for (int i = 0; i < n; i++) {
                    if (dskh[i].getMaKH().equals(maKH))
                        dskh[i].Xuat();
                }
                break;
            default:
                System.out.println("Thoat chuong trinh");
                return;

        }
    }
    
}