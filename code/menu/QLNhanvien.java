package code.menu;

import java.util.Scanner;
import code.danhsach.*;

public class QLNhanvien {
    public void menu() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        dsnv.DocTuFile("data/danhsachNV.txt");
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
                    dsnv.Them(sc);
                    dsnv.HienThiDS();
                    break;
                case 2:
                    System.out.print("Chon ma nhan vien muon xoa: ");
                    dsnv.Xoa(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Chon ma nhan vien ban muon sua: ");
                    dsnv.Sua_Chi_Tiet(sc);
                    break;
                case 4:
                    dsnv.TimkiemNV(sc);
                    break;
                case 5:
                    dsnv.HienThiDS();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Khong co chuc nang nay, cho cap nhat.");
            }
            System.out.println("Co muon tiep tuc khong ?(y/n)");
            op = sc.nextLine();
        } while (op.equalsIgnoreCase("y"));
    }
}