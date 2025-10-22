package code.menu;

import java.util.Scanner;
import code.danhsach.*;

public class QLLoai{
    public void menu() {
        Scanner sc = new Scanner(System.in);

        DanhSachLoai dsl = new DanhSachLoai();
        DanhSachSanPham dssp = new DanhSachSanPham();

        dsl.docFile();
        dssp.docFile(dsl);

        while (true) {
            System.out.println("---- MENU LOAI ----");
            System.out.println("1. Them loai");
            System.out.println("2. Xem danh sach loai");
            System.out.println("3. Tim kiem loai");
            System.out.println("4. Sua loai");
            System.out.println("5. Xoa loai");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int c1 = sc.nextInt(); sc.nextLine();
                if (c1 == 0) break;

                    switch (c1) {
                        case 1: dsl.Them(sc); break;
                        case 2: dsl.Xem(); break;
                        case 3: dsl.TimKiem(sc); break;
                        case 4: dsl.Sua(sc); break;
                        case 5: dsl.Xoa(sc); break;
                        default: System.out.println("Khong hop le");
                    }
        }
    }
}