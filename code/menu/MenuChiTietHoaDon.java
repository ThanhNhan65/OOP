package code.menu;
import java.util.Scanner;

import code.danhsach.*;

public class MenuChiTietHoaDon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DanhSachHoaDon dshd = new DanhSachHoaDon();
        DanhSachSanPham dssp = new DanhSachSanPham();
        DanhSachChitietHoaDon dsct = new DanhSachChitietHoaDon();
        DanhSachLoai dSloai=new DanhSachLoai();

        dshd.ReadFile(new DanhSachKhachHang(), new DanhSachNhanVien());
        dssp.docFile(dSloai);
        dsct.ReadFile(dshd, dssp);

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
            sc.nextLine(); // Đọc bỏ dòng thừa

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
                    System.out.println("Danh sach chi tiet hoa don:");
                    for (int i = 0; i < dsct.getN(); i++) {
                        dsct.getDSCT(i).Xuat();
                    }
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
