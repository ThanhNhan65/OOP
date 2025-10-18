package code.danhsach;

import java.util.*;
import java.io.*;
import code.doituong.*;
import code.danhsach.*;

public class DSCT {
    private Chitiethoadonban[] ds;
    private int n;
    private DSHD dshd;
    private DanhSachSanPham dssp;

    public DSCT() {
        n = 0;
        ds = new Chitiethoadonban[0];
        dssp = new DanhSachSanPham();
        dshd = new DSHD();

    }

    public void setDSHD(DSHD dshd) {
        this.dshd = dshd;
    }

    public void setDSSP(DanhSachSanPham dssp) {
        this.dssp = dssp;
    }

    public Chitiethoadonban getDSCT(int index) {
        return ds[index];
    }

    public int getN() {
        return n;
    }

    // -----------------Đọc file----------------------
    public void ReadFile() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("data/chitiethoadon.txt"));
            String line = input.readLine();
            while (line != null) {
                String[] arr = line.split(",");

                String maHD = arr[0].trim();
                String maSP = arr[1].trim();
                int soluong = Integer.parseInt(arr[2].trim());

                Hoadonban hd = dshd.Timkiem_MaHD(maHD);
                SanPham sp = dssp.TimKiem(maSP);

                Chitiethoadonban ct = new Chitiethoadonban(hd, sp, soluong);
                ds = Arrays.copyOf(ds, n + 1);
                ds[n] = ct;
                n++;

                line = input.readLine();
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // -----------------Ghi file --------------------------
    public void WriteFile() {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("data/chitiethoadon.txt"));
            for (int i = 0; i < n; i++) {
                fw.write(ds[i].getHDB().getMaHDB() + "," + ds[i].getSP().getMa() + "," + ds[i].getSL());
                fw.newLine();
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ----------------Them chi tiet -------------------
    public void Them(Scanner sc) {
        Chitiethoadonban ct = new Chitiethoadonban();
        ct.setDSHD(dshd);
        ct.setDSSP(dssp);
        ct.Nhap(sc);
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = ct;
        n++;
        WriteFile();
    }

    // -----------------Sửa chi tiết----------------
    public void Sua(Scanner sc) {
        System.out.println("Nhap ma hoa don can sua:");
        String MaHD = sc.nextLine();
        Hoadonban hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD = sc.nextLine();
            hd = dshd.Timkiem_MaHD(MaHD);
        }

        Chitiethoadonban[] arr = new Chitiethoadonban[0];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(ds[i].getHDB().getMaHDB())) {
                ds[i].Xuat();
                arr = Arrays.copyOf(arr, count + 1);
                arr[count] = ds[i];
                count++;
            }
        }

        int choice;
        do {
            System.out.println("1. Sua ma san pham va so luong");
            System.out.println("2. Sua so luong san pham");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    Suaspsl(sc, arr, count);
                    break;
                case 2:
                    Suasl(sc, arr, count);
                    break;
                case 3:
                    System.out.println("Thoat menu sua.");
                    break;
            }
        } while (choice != 3);
    }

    public void Suaspsl(Scanner sc, Chitiethoadonban[] arr, int count) {
        System.out.println("Nhap ma san pham can sua:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimKiem(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimKiem(MaSP);
        }

        for (int i = 0; i < count; i++) {
            if (MaSP.equals(arr[i].getSP().getMa())) {
                System.out.println("Nhap ma san pham moi:");
                String newSP = sc.nextLine();
                SanPham newsp = dssp.TimKiem(newSP);
                while (newsp == null) {
                    System.out.println("Nhap lai ma san pham moi:");
                    newSP = sc.nextLine();
                    newsp = dssp.TimKiem(newSP);
                }

                arr[i].setSP(newsp);

                System.out.println("Sua so luong:");
                int newSL = sc.nextInt();
                while (newSL < 0) {
                    System.out.println("Vui long nhap lai! ");
                    newSL = sc.nextInt();
                }
                arr[i].setSL(newSL);
                sc.nextLine();
            }
        }
        WriteFile();
    }

    public void Suasl(Scanner sc, Chitiethoadonban[] arr, int count) {
        System.out.println("Nhap ma san pham can sua:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimKiem(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimKiem(MaSP);
        }

        for (int i = 0; i < count; i++) {
            if (MaSP.equals(arr[i].getSP().getMa())) {
                System.out.println("Nhap so luong moi:");
                int newSL = sc.nextInt();
                arr[i].setSL(newSL);
                sc.nextLine();
            }
        }
        WriteFile();
    }

    // --------------Xoa ------------------
    public void Xoa(Scanner sc) {
        System.out.println("Nhap ma hoa don can xoa:");
        String MaHD = sc.nextLine();
        Hoadonban hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD = sc.nextLine();
            hd = dshd.Timkiem_MaHD(MaHD);
        }

        Chitiethoadonban[] arr = new Chitiethoadonban[0];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(ds[i].getHDB().getMaHDB())) {
                ds[i].Xuat();
                arr = Arrays.copyOf(arr, count + 1);
                arr[count] = ds[i];
                count++;
            }
        }

        int choice;
        do {
            System.out.println("1. Xoa toan bo chi tiet cua hoa don");
            System.out.println("2. Xoa 1 san pham trong hoa don");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    XoaTB(MaHD);
                    break;
                case 2:
                    Xoasphd(sc, arr, count, MaHD);
                    break;
                case 3:
                    System.out.println("Thoat menu xoa.");
                    break;
            }
        } while (choice != 3);
    }

    public void XoaTB(String MaHD) {
        boolean bool = false;
        for (int i = 0; i < n;) {
            if (ds[i].getHDB().getMaHDB().equals(MaHD)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds = Arrays.copyOf(ds, n - 1);
                n--;
                bool = true;
            } else i++;
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void Xoasphd(Scanner sc, Chitiethoadonban[] arr, int count, String MaHD) {
        boolean bool = false;
        System.out.println("Nhap ma san pham can xoa:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimKiem(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimKiem(MaSP);
        }

        for (int i = 0; i < n;) {
            if (ds[i].getHDB().getMaHDB().equals(MaHD) &&
                ds[i].getSP().getMa().equals(MaSP)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                ds = Arrays.copyOf(ds, n - 1);
                n--;
                bool = true;
            } else i++;
        }

        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    //----------------Tìm kiếm---------------------------
    public void TimKiem(Scanner sc) {
        System.out.println("Nhap ma hoa don:");
        String MaHD = sc.nextLine();
        Hoadonban hd = dshd.Timkiem_MaHD(MaHD);
        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(ds[i].getHDB().getMaHDB())) {
                ds[i].Xuat();
            }
        }
    }
}