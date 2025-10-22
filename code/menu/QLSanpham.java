package code.menu;

import java.util.Scanner;
import code.danhsach.*;

public class QLSanpham{
    public void menu() {
        Scanner sc = new Scanner(System.in);

        DanhSachLoai dsl = new DanhSachLoai();
        DanhSachSanPham dssp = new DanhSachSanPham();

        dsl.docFile();
        dssp.docFile(dsl);

        while (true) {
            System.out.println("\n---- MENU SAN PHAM ----");
            System.out.println("1. Them san pham");
            System.out.println("2. Tim kiem san pham");
            System.out.println("3. Sua san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Loc san pham (Hang/Loai/Gia)");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int c2 = sc.nextInt(); 
            sc.nextLine();
            
            if (c2 == 0) break;

            switch (c2) {
                case 1:
                    dssp.Them(sc, dsl);
                    break;
                case 2:
                    dssp.TimKiem(sc);
                    break;
                case 3:
                    dssp.Sua(sc, dsl);
                    break;
                case 4:
                    dssp.Xoa(sc);
                    break;
                case 5: {
                    while (true) {
                        System.out.println("\n---- LOC SAN PHAM ----");
                        System.out.println("1. Loc theo hang");
                        System.out.println("2. Loc theo loai");
                        System.out.println("3. Loc theo gia [min, max]");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon: ");
                        int c3 = sc.nextInt(); 
                        sc.nextLine();
                        if (c3 == 0) break;

                        switch (c3) {
                            case 1: dssp.locTheoHang(sc); break;
                            case 2: dssp.locTheoLoai(sc); break;
                            case 3: dssp.locTheoGia(sc); break;
                            default: System.out.println("Khong hop le");
                        }
                    }
                    break;
                }
                default:
                    System.out.println("Khong hop le");
            }
        }
    }
}