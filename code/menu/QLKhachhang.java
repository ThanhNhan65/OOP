package code.menu;

import code.danhsach.*;
import code.doituong.KhachHang;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;

public class QLKhachhang {
    public void menu() {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        dskh.DocTuFile("data/danhsachKH.txt");
<<<<<<< HEAD
        dskh.HienThiDS();
=======
        dskh.GhiVaoFile("data/danhsachKH.txt");
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("-----DANH SACH CAC CHUC NANG-----");
            System.out.println("1.Them khach hang.");
            System.out.println("2.Xoa khach hang.");
            System.out.println("3.Sua thong tin khach hang.");
            System.out.println("4.Tim kiem thong tin khach hang.");
            System.out.println("5.Hien danh sach.");
            System.out.println("0.Thoat chuong trinh.");
            System.out.println("----------------------------------");
            System.out.print("Moi chon chuc nang: ");
            chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    System.out.println("Them khach hang vao danh sach");
                    dskh.Them();
                    break;
                case 2:
                    System.out.print("Chon ma khach hang muon xoa: ");
                    dskh.Xoa(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Chon ma nhan vien ban muon sua");
                    dskh.SuaKH();
                    break;
                case 4:
                    dskh.TimkiemKH();
                    break;
                case 5:
                    dskh.HienThiDS();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Khong co chuc nang nay, cho cap nhat.");
            }
<<<<<<< HEAD
            System.out.println("Co muon tiep tuc khong ?(y/n)");
            op = sc.nextLine();
        } while (op.equalsIgnoreCase("y"));
=======
        } while (chon != 0);
>>>>>>> 359e97c70970aab8dd1a523ebc2aaef748d5a81c
    }
}