package code.menu;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import code.danhsach.*;

public class menuhoadon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        DanhSachChitietHoaDon dsct = new DanhSachChitietHoaDon();
        DanhSachHoaDon dshd = new DanhSachHoaDon();
        DanhSachSanPham dssp= new DanhSachSanPham();
        DanhSachLoai dsl= new DanhSachLoai();

        dskh.DocTuFile("data/danhsachKH.txt");
        dsnv.DocTuFile("data/danhsachNV.txt");
        dssp.docFile(dsl);
        dsct.ReadFile(dshd, dssp);
        dshd.ReadFile(dskh, dsnv);

        int choice;
        do {
            System.out.println("\n===== QUAN LY HOA DON =====");
            System.out.println("1. Them hoa don");
            System.out.println("2. Sua hoa don");
            System.out.println("3. Xoa hoa don");
            System.out.println("4. Tim kiem hoa don");
            System.out.println("5. Hien thi danh sach hoa don");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dshd.Them(sc);
                    break;
                case 2:
                    dshd.Sua(sc, dsnv, dskh, dsct);
                    break;
                case 3:
                    dshd.Xoa(sc, dshd, dskh, dsct);
                    break;
                case 4:
                    dshd.Timkiem(sc, dshd, dskh, dsnv);
                    break;
                case 5:
                    dshd.Showlist();
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
