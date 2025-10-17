import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;

public class MainKH {
    public static void main(String[] args) {
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        dskh.DocTuFile("danhsachKH.txt");
        dskh.Xuat();
        dskh.GhiVaoFile("danhsachKH.txt");
        Scanner sc = new Scanner(System.in);
        String op;
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
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    System.out.println("Them nhan vien vao danh sach");
                    dskh.Them();
                    dskh.Xuat();
                    dskh.GhiVaoFile("danhsachKH.txt");
                    break;
                case 2:
                    System.out.print("Chon ma nhan vien muon xoa: ");
                    dskh.Xoa(sc.nextLine());
                    dskh.Xuat();
                    dskh.GhiVaoFile("danhsachKH.txt");
                    break;
                case 3:
                    System.out.println("Chon ma nhan vien ban muon sua");
                    dskh.SuaKH(sc.nextLine());
                    sc.nextLine();
                    dskh.GhiVaoFile("danhsachKH.txt");
                    break;
                case 4:
                    dskh.TimkiemKH();
                    break;
                case 5:
                    dskh.Xuat();
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
