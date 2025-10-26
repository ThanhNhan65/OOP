package code.danhsach;

import java.util.*;
import java.io.*;
import code.doituong.*;
import code.kiemtra.*;;

public class DanhSachChitietHoaDon {
    private ChiTietHoaDon[] dsct;
    private int n;


    public DanhSachChitietHoaDon() {
        n = 0;
        dsct = new ChiTietHoaDon[0];
    }

    public ChiTietHoaDon getct(int index) {
        return dsct[index];
    }

    public int getN() {
        return n;
    }

    public void ReadFile(DanhSachHoaDon dshd, DanhSachSanPham dssp) {
    try {
        BufferedReader input = new BufferedReader(new FileReader("data/chitiethoadon.txt"));
        String line;
        while ((line = input.readLine()) != null) {
            String[] arr = line.split(",");
            String maHD = arr[0].trim();
            String maSP = arr[1].trim();
            int soluong = Integer.parseInt(arr[2].trim());

            ChiTietHoaDon ct = new ChiTietHoaDon(dshd,dssp); 
            HoaDon hd = dshd.Timkiem_MaHD(maHD); 
            SanPham sp = dssp.TimTheoMa(maSP);

            ct.setHD(hd);
            ct.setSP(sp);
            ct.setSL(soluong); 

            if (hd != null && sp != null) {
                dsct = Arrays.copyOf(dsct, n + 1);
                dsct[n++] = ct;

                if (hd.getdsct() != null) {
                    hd.getdsct().ThemChiTiet(ct);
                }
            } 
        }
        input.close();
    }catch(Exception ex){
        ex.printStackTrace();
    }
}

    public void WriteFile() {
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter("data/chitiethoadon.txt"));
            for(int i = 0; i < n; i++){
                if (dsct[i] != null && dsct[i].getHD() != null && dsct[i].getSP() != null) {
                    fw.write(dsct[i].getHD().getMaHD() + "," + dsct[i].getSP().getMa() + "," + dsct[i].getSL());
                    fw.newLine();
                }
            }
            fw.close();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Them(Scanner sc, DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        ChiTietHoaDon ct = new ChiTietHoaDon(dshd, dssp);
        ct.Nhap(sc);
        dsct = Arrays.copyOf(dsct, n + 1); 
        dsct[n] = ct;
        n++;
        
        if(ct.getHD() != null){
            ct.getHD().getdsct().ThemChiTiet(ct);
        }

        WriteFile();
    }

    public void ThemChiTiet(ChiTietHoaDon ct) {
        if(ct != null) {
            dsct = Arrays.copyOf(dsct, n + 1);
            dsct[n] = ct;
            n++;
        }
    }

    public void Sua(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp){
        System.out.println("Nhap ma hoa don can sua (nhan Enter de thoat):");
        String MaHD = sc.nextLine().trim();
        if (InputUtils.ThoatNeuEnter(MaHD)) 
            return;
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null){
            System.out.println("Khong tim thay hoa don: " + MaHD);
            System.out.println("Vui long nhap lai (Enter de thoat)! ");
            MaHD = sc.nextLine().trim();
            if (InputUtils.ThoatNeuEnter(MaHD)) 
                return;
            hd = dshd.Timkiem_MaHD(MaHD);
        }
        hd.Xuat();
        int choice;
        do{
            System.out.println("1. Sua ma san pham va so luong");
            System.out.println("2. Sua so luong san pham");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    Suaspsl(sc, hd.getdsct(), dssp);
                    break;
                case 2:
                    Suasl(sc, hd.getdsct(), dssp);
                    break;
                case 0:
                    System.out.println("Thoat menu sua.");
                    break;
            }
        } while (choice != 0);
    }

    private void Suaspsl(Scanner sc, DanhSachChitietHoaDon dsct, DanhSachSanPham dssp) {
        System.out.println("Nhap ma san pham can sua (Enter de thoat):");
        String MaSP = sc.nextLine().trim();
        if (InputUtils.ThoatNeuEnter(MaSP)) 
            return;
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai (Enter de thoat)! ");
            MaSP = sc.nextLine().trim();
            if (InputUtils.ThoatNeuEnter(MaSP)) 
                return;
            sp = dssp.TimTheoMa(MaSP);
        }

        for (int i = 0; i < dsct.getN(); i++) {
            ChiTietHoaDon ct = dsct.getct(i);
            if (MaSP.equals(ct.getSP().getMa())) {
                System.out.println("Nhap ma san pham moi (Enter de thoat):");
                String newSP = sc.nextLine().trim();
                if (InputUtils.ThoatNeuEnter(newSP)) 
                    return;
                SanPham newsp = dssp.TimTheoMa(newSP);
                while (newsp == null) {
                    System.out.println("Nhap lai ma san pham moi (Enter de thoat):");
                    newSP = sc.nextLine().trim();
                    if (InputUtils.ThoatNeuEnter(newSP)) 
                        return;
                    newsp = dssp.TimTheoMa(newSP);
                }

                ct.setSP(newsp);

                System.out.println("Sua so luong:");
                int newSL = sc.nextInt();
                while (newSL < 0) {
                    System.out.println("Vui long nhap lai! ");
                    newSL = sc.nextInt();
                }
                ct.setSL(newSL);
                sc.nextLine();
                break;
            }
        }
         WriteFile();
         
    }

    private void Suasl(Scanner sc, DanhSachChitietHoaDon dsct, DanhSachSanPham dssp) {
        System.out.println("Nhap ma san pham can sua (Enter de thoat):");
        String MaSP = sc.nextLine().trim();
        if (InputUtils.ThoatNeuEnter(MaSP))
            return;
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai (Enter de thoat)! ");
            MaSP = sc.nextLine().trim();
            if (InputUtils.ThoatNeuEnter(MaSP)) 
                return;
            sp = dssp.TimTheoMa(MaSP);
        }

        for (int i = 0; i < dsct.getN(); i++) {
            ChiTietHoaDon ct = dsct.getct(i);
            if (MaSP.equals(ct.getSP().getMa())) {
                System.out.println("Nhap so luong moi:");
                int newSL = sc.nextInt();
                ct.setSL(newSL);
                sc.nextLine();
                break;
            }
        }
            WriteFile();
          
    }

    public void Xoa(Scanner sc,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        System.out.println("Nhap ma hoa don can xoa (Enter de thoat):");
        String MaHD = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(MaHD)) 
            return;
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        while (hd == null) {
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai (Enter de thoat)! ");
            MaHD = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaHD)) 
                return;
            hd = dshd.Timkiem_MaHD(MaHD);
        }
        hd.Xuat();

        int choice;
        do {
            System.out.println("1. Xoa toan bo chi tiet cua hoa don");
            System.out.println("2. Xoa 1 san pham trong hoa don");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    XoaTB(MaHD);
                    break;
                case 2:
                    Xoasphd(sc, MaHD, dshd, dssp);
                    break;
                case 0:
                    System.out.println("Thoat menu xoa.");
                    break;
            }
        } while (choice != 0);
    }

    public void XoaTB(String MaHD) {
        boolean bool = false;
        for (int i = 0; i < n;) {
            if (dsct[i].getHD().getMaHD().equals(MaHD)) {
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

    public void Xoasphd(Scanner sc,String MaHD,DanhSachHoaDon dshd, DanhSachSanPham dssp) {
        boolean bool = false;
        System.out.println("Nhap ma san pham can xoa (Enter de thoat):");
        String MaSP = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(MaSP)) 
            return;
        SanPham sp = dssp.TimTheoMa(MaSP);
        while (sp == null) {
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai (Enter de thoat)! ");
            MaSP = sc.nextLine();
            if (InputUtils.ThoatNeuEnter(MaSP)) 
                return;
            sp = dssp.TimTheoMa(MaSP);
        }

        for (int i = 0; i < n;) {
            if (dsct[i].getHD().getMaHD().equals(MaHD) &&
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
        System.out.println("Nhap ma hoa don (Enter de thoat):");
        String MaHD = sc.nextLine();
        if (InputUtils.ThoatNeuEnter(MaHD)) 
            return;
        HoaDon hd = dshd.Timkiem_MaHD(MaHD);
        if (hd == null) {
            System.out.println("Khong tim thay hoa don!");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (MaHD.equals(dsct[i].getHD().getMaHD())) {
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