package code;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        dsnv.DocTuFile("danhsachNV.txt");
        dsnv.Xuat();
        dsnv.GhiVaoFile("danhsachNV.txt");
        Scanner sc = new Scanner(System.in);
        String op;
        do {
            System.out.println("-----DANH SACH CAC CHUC NANG-----");
            System.out.println("1.Them nhan vien.");
            System.out.println("2.Xoa nhan vien.");
            System.out.println("3.Sua thong tin nhan vien.");
            System.out.println("4.Tim kiem thong tin nhan vien.");
            System.out.println("5.Hien danh sach.");
            System.out.println("0.Thoat chuong trinh.");
            System.out.println("----------------------------------");
            System.out.print("Moi chon chuc nang: ");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    System.out.println("Them nhan vien vao danh sach");
                    dsnv.Them();
                    dsnv.Xuat();
                    dsnv.GhiVaoFile("danhsachNV.txt");
                    break;
                case 2:
                    System.out.print("Chon ma nhan vien muon xoa: ");
                    dsnv.Xoa(sc.nextLine());
                    dsnv.Xuat();
                    dsnv.GhiVaoFile("danhsachNV.txt");
                    break;
                case 3:
                    System.out.println("Chon ma nhan vien ban muon sua");
                    dsnv.Sua_Chi_Tiet(sc.nextLine());
                    dsnv.GhiVaoFile("danhsachNV.txt");
                    break;
                case 4:
                    NhanVien nv = dsnv.TimkiemNV();
                    if (nv != null) {
                        System.out.println("Thong tin nhan vien tim thay:");
                        nv.Xuat();
                    } else {
                        System.out.println("Khong tim thay nhan vien.");
                    }
                    break;
                case 5:
                    dsnv.Xuat();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Khong co chuc nang nay, cho cap nhat.");
            }
            System.out.println("Co muon tiep tuc khong ?(y/n)");
            op = sc.nextLine();
        } while (op.equals("y"));
    }
}
