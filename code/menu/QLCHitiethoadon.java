package code.menu;
import java.util.Scanner;

import code.danhsach.*;
import code.doituong.*;

public class QLCHitiethoadon {
    public void menu() {
        Scanner sc = new Scanner(System.in);

        DanhSachHoaDon dshd = new DanhSachHoaDon();
        DanhSachSanPham dssp = new DanhSachSanPham();
        DanhSachChitietHoaDon dsct = new DanhSachChitietHoaDon();
        DanhSachLoai dsl= new DanhSachLoai();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        
        dskh.DocTuFile("data/danhsachKH.txt");
        dsnv.DocTuFile("data/danhsachNV.txt");
        dsl.docFile();
        dssp.docFile(dsl);
        dshd.ReadFile(dskh, dsnv);     
        dsct.ReadFile(dshd, dssp);  
        
        for(int i = 0; i < dshd.getN(); i++) {
            HoaDon hd = dshd.getHoaDon(i);
            if(hd != null && hd.getdsct() != null) {
                for(int j = 0; j < dsct.getN(); j++) {
                    ChiTietHoaDon ct = dsct.getDSCT(j);
                    if(ct != null && ct.getHDB() != null && ct.getHDB().getMaHDB().equals(hd.getMaHDB())) {
                        hd.getdsct().ThemChiTiet(ct);
                    }
                }
            }
        }
       
        int choice;
        do {
            System.out.println("\n===== QUAN LY CHI TIET HOA DON =====");
            System.out.println("1. Them chi tiet hoa don");
            System.out.println("2. Sua chi tiet hoa don");
            System.out.println("3. Xoa chi tiet hoa don");
            System.out.println("4. Tim kiem chi tiet hoa don");
            System.out.println("5. Hien thi tat ca chi tiet hoa don");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dsct.Them(sc, dshd, dssp);
                    break;
                case 2:
                    dsct.Sua(sc, dshd, dssp);
                    break;
                case 3:
                    dsct.Xoa(sc, dshd, dssp);
                    break;
                case 4:
                    dsct.TimKiem(sc, dshd, dssp);
                    break;
                case 5:
                    dsct.Hienthidanhsach();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        } while (choice != 0);

        sc.close();
    }
}
