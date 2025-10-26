package code.menu;

import java.util.Scanner;
import code.danhsach.*;

public class QLKho {
    
    public void menu() {
        Scanner sc = new Scanner(System.in);
        DanhSachKho dskho = new DanhSachKho();
        DanhSachSanPham dssp = new DanhSachSanPham();
        DanhSachLoai dsl= new DanhSachLoai();
        DanhSachChitietHoaDon dsct= new DanhSachChitietHoaDon();
        DanhSachHoaDon dshd= new DanhSachHoaDon();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        
        dskh.DocTuFile("data/danhsachKH.txt");
        dsnv.DocTuFile("data/danhsachNV.txt");
        dsl.docFile();
        dssp.docFile(dsl);
        dshd.ReadFile(dskh, dsnv);
        dsct.ReadFile(dshd, dssp);
        dskho.docFile(dssp,dsct);
        int chon;
        do {
            System.out.println("\n===== MENU QUAN LY KHO =====");
            System.out.println("1. Them san pham vao kho");
            System.out.println("2. Sua dau vao cua san pham");
            System.out.println("3. Xoa ");
            System.out.println("4. Tim kiem");
            System.out.println("5. Hien thi danh sach");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon = sc.nextInt(); 
            sc.nextLine();

            switch (chon) {
                case 1:
                    dskho.Them(sc,dssp,dsct);
                    break;
                case 2:
                    dskho.Sua(sc,dssp);
                    break;
                case 3:
                    dskho.Xoa(sc,dssp);
                    break;
                case 4:
                    dskho.TimKiem(sc, dssp);
                    break;
                case 5:
                    dskho.HienThi();
                    break;
                case 0:
                    System.out.println("Thoat quan ly kho");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }
}