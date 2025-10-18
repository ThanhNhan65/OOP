package NhacCu;

import java.util.Scanner;

public class MainApp{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DanhSachLoai dsl = new DanhSachLoai();
        DanhSachSanPham dssp = new DanhSachSanPham();

        dsl.docFile();
        dssp.docFile(dsl);

        while(true){
            System.out.println("==== MENU CHINH ====");
            System.out.println("1. Quan ly LOAI");
            System.out.println("2. Quan ly SAN PHAM");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            String s = sc.nextLine().trim();
            int ch = s.isEmpty()? -1 : Integer.parseInt(s);
            if(ch == 0) break;

            switch(ch){
                case 1:
                    while(true){
                        System.out.println("---- MENU LOAI ----");
                        System.out.println("1. Them loai");
                        System.out.println("2. Xem loai");
                        System.out.println("3. Xoa loai");
                        System.out.println("4. Sua loai");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon: ");
                        String s1 = sc.nextLine().trim();
                        int c1 = s1.isEmpty()? -1 : Integer.parseInt(s1);
                        if(c1 == 0) break;

                        switch(c1){
                            case 1: dsl.them(sc); break;
                            case 2: dsl.xem(); break;
                            case 3: dsl.xoa(sc); break;
                            case 4: dsl.sua(sc); break;
                            default: System.out.println("Chon sai");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    while(true){
                        System.out.println("---- MENU SAN PHAM ----");
                        System.out.println("1. Them san pham");
                        System.out.println("2. Xem san pham");
                        System.out.println("3. Xoa san pham");
                        System.out.println("4. Sua san pham");
                        System.out.println("5. Loc theo hang");
                        System.out.println("6. Loc theo MA loai");
                        System.out.println("7. Loc theo gia");
                        System.out.println("8. Tim SP theo ma");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon: ");
                        String s2 = sc.nextLine().trim();
                        int c2 = s2.isEmpty()? -1 : Integer.parseInt(s2);
                        if(c2 == 0) break;

                        switch(c2){
                            case 1: dssp.them(sc, dsl); break;
                            case 2: dssp.xem(); break;
                            case 3: dssp.xoa(sc); break;
                            case 4: dssp.sua(sc, dsl); break;
                            case 5: dssp.locTheoHang(sc); break;
                            case 6: dssp.locTheoLoai(sc); break;
                            case 7: dssp.locTheoGia(sc); break;
                            case 8:
                                System.out.print("Nhap ma sp can tim: ");
                                String ma = sc.nextLine().trim();
                                SanPham sp = dssp.timkiem(ma);
                                if (sp == null) {
                                    System.out.println("Kh thay ma");
                                } else {
                                    System.out.printf("%-10s %-20s %-12s %-12s %10s%n",
                                            "Ma","Ten","Hang","Loai","Gia");
                                    sp.Xuat();
                                }
                                break;
                            default:
                                System.out.println("Chon sai");
                        }
                        System.out.println();
                    }
                    break;

                default:
                    System.out.println("Chon sai");
            }
            System.out.println();
        }

        dsl.ghiFile();
        dssp.ghiFile();
        sc.close();
        System.out.println("Bye");
    }
}
