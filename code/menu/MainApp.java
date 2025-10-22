package code.menu;

import java.util.Scanner;
import code.danhsach.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DanhSachLoai dsl = new DanhSachLoai();
        DanhSachSanPham dssp = new DanhSachSanPham();

        dsl.docFile();
        dssp.docFile(dsl);

        while (true) {
            System.out.println("==== MENU CHINH ====");
            System.out.println("1. Quan ly LOAI");
            System.out.println("2. Quan ly SAN PHAM");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 0) break;

            switch (ch) {
                case 1: { 
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
                    break;
                }
                case 2: {
                    while (true) {
                        System.out.println("---- MENU SAN PHAM ----");
                        System.out.println("1. Them san pham");
                        System.out.println("2. Tim kiem san pham");
                        System.out.println("3. Sua san pham");
                        System.out.println("4. Xoa san pham");
                        System.out.println("5. Loc san pham (Hang/Loai/Gia)");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon: ");
                        int c2 = sc.nextInt(); sc.nextLine();
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
                                    System.out.println("---- LOC SAN PHAM ----");
                                    System.out.println("1. Loc theo hang");
                                    System.out.println("2. Loc theo loai");
                                    System.out.println("3. Loc theo gia [min, max]");
                                    System.out.println("0. Quay lai");
                                    System.out.print("Chon: ");
                                    int c3 = sc.nextInt(); sc.nextLine();
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
                    break;
                }
                default:
                    System.out.println("Khong hop le");
            }
        }
        sc.close();
    }
}
