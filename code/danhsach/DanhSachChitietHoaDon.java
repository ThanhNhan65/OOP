package code.danhsach;

import java.util.*;
import java.io.*;
import code.doituong.*;

public class DanhSachChitietHoaDon {
    private ChiTietHoaDon[] dsct;
    private int n;


    public DanhSachChitietHoaDon() {
        n = 0;
        dsct = new ChiTietHoaDon[0];
    }

    public ChiTietHoaDon getDSCT(int index) {
        return dsct[index];
    }

    public int getN() {
        return n;
    }


    // -----------------Đọc file----------------------
    public void ReadFile(DanhSachHoaDon dshd, DanhSachSanPham dssp) {
    try {
        BufferedReader input = new BufferedReader(new FileReader("data/chitiethoadon.txt"));
        String line;
        while ((line = input.readLine()) != null) {
            String[] arr = line.split(",");
            String maHD = arr[0].trim();
            String maSP = arr[1].trim();
            int soluong = Integer.parseInt(arr[2].trim());

            ChiTietHoaDon ct = new ChiTietHoaDon(dshd, dssp);
            HoaDon hd = dshd.Timkiem_MaHD(maHD);
            SanPham sp = dssp.TimTheoMa(maSP);

            ct.setHDB(hd);
            ct.setSP(sp);
            ct.setSL(soluong);

            dsct = Arrays.copyOf(dsct, n + 1);
            dsct[n++] = ct;
        }
        input.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    public void WriteFile() {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("data/chitiethoadon.txt"));
            for (int i = 0; i < n; i++) {
                fw.write(dsct[i].getHDB().getMaHDB() + "," + dsct[i].getSP().getMa() + "," + dsct[i].getSL());
                fw.newLine();
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Them(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        ChiTietHoaDon ct = new ChiTietHoaDon(dshd, dssp);
        ct.Nhap(sc);
        dsct = Arrays.copyOf(dsct, n + 1);
        dsct[n] = ct;
        n++;
        WriteFile();
    }
    public void ThemChiTiet(ChiTietHoaDon ct) {
    if(ct != null) {
        dsct = Arrays.copyOf(dsct, n + 1);
        dsct[n++] = ct;
    }
}

    public void Sua(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma hoa don can sua:");
        String MaHD = sc.nextLine();
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD = sc.nextLine();
            hd = dshd.Timkiem_MaHD(MaHD);
        }

        ChiTietHoaDon[] arr = new ChiTietHoaDon[0];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(dsct[i].getHDB().getMaHDB())) {
                dsct[i].Xuat();
                arr = Arrays.copyOf(arr, count + 1);
                arr[count] = dsct[i];
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
                    Suaspsl(sc, arr, count, dshd, dssp);;
                    break;
                case 2:
                    Suasl(sc, arr, count, dshd, dssp);;
                    break;
                case 3:
                    System.out.println("Thoat menu sua.");
                    break;
            }
        } while (choice != 3);
    }

    public void Suaspsl(Scanner sc, ChiTietHoaDon[] arr, int count,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma san pham can sua:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimTheoMa(MaSP);
        }

        for (int i = 0; i < count; i++) {
            if (MaSP.equals(arr[i].getSP().getMa())) {
                System.out.println("Nhap ma san pham moi:");
                String newSP = sc.nextLine();
                SanPham newsp = dssp.TimTheoMa(newSP);
                while (newsp == null) {
                    System.out.println("Nhap lai ma san pham moi:");
                    newSP = sc.nextLine();
                    newsp = dssp.TimTheoMa(newSP);
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

    public void Suasl(Scanner sc, ChiTietHoaDon[] arr, int count,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma san pham can sua:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimTheoMa(MaSP);
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

    public void Xoa(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma hoa don can xoa:");
        String MaHD = sc.nextLine();
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD = sc.nextLine();
            hd = dshd.Timkiem_MaHD(MaHD);
        }

        ChiTietHoaDon[] arr = new ChiTietHoaDon[0];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(dsct[i].getHDB().getMaHDB())) {
                dsct[i].Xuat();
                arr = Arrays.copyOf(arr, count + 1);
                arr[count] = dsct[i];
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
                    Xoasphd(sc, arr, count, MaHD, dshd, dssp);
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
            if (dsct[i].getHDB().getMaHDB().equals(MaHD)) {
                for (int j = i; j < n - 1; j++) {
                    dsct[j] = dsct[j + 1];
                }
                dsct = Arrays.copyOf(dsct, n - 1);
                n--;
                bool = true;
            } else i++;
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void Xoasphd(Scanner sc, ChiTietHoaDon[] arr, int count, String MaHD,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        boolean bool = false;
        System.out.println("Nhap ma san pham can xoa:");
        String MaSP = sc.nextLine();
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP = sc.nextLine();
            sp = dssp.TimTheoMa(MaSP);
        }

        for (int i = 0; i < n;) {
            if (dsct[i].getHDB().getMaHDB().equals(MaHD) &&
                dsct[i].getSP().getMa().equals(MaSP)) {
                for (int j = i; j < n - 1; j++) {
                    dsct[j] = dsct[j + 1];
                }
                dsct = Arrays.copyOf(dsct, n - 1);
                n--;
                bool = true;
            } else i++;
        }

        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void TimKiem(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma hoa don:");
        String MaHD = sc.nextLine();
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(dsct[i].getHDB().getMaHDB())) {
                dsct[i].Xuat();
            }
        }
    }
    public void Hienthidanhsach(){
        for(int i=0 ; i < n ;i++){
            dsct[i].Xuat();
        }
    }
}