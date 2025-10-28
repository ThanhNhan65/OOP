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
        dskh.GhiVaoFile("data/danhsachKH.txt");
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
                    dskh.Them();
                    break;
                case 2:
                    dskh.Xoa();
                    break;
                case 3:
                    dskh.SuaKH();
                    break;
                case 4:
                    dskh.TimkiemKH();
                    break;
                case 5:
                    dskh.HienThiDS();
                    break;
                case 0:
                    System.out.println("Thoat quan ly khach hang.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
            }
        } while (chon != 0);
    }
}