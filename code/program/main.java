package code.program;

import code.menu.*;
import java.util.Scanner;

public class main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        QLKhachhang ql1 = new QLKhachhang();
        QLNhanvien ql2 = new QLNhanvien();
        QLSanpham ql3 = new QLSanpham();
        QLLoai ql4= new QLLoai();
        QLHoadon ql5 = new QLHoadon();
        QLChitiethoadon ql6 = new QLChitiethoadon();
        QLKho ql7 = new QLKho();

        int choice;
            do {System.out.println("\n===== QUAN LY BAN NHAC CU =====");
                System.out.println("1. Quan ly khach hang");
                System.out.println("2. Quan ly nhan vien");
                System.out.println("3. Quan ly san pham");
                System.out.println("4. Quan ly loai");
                System.out.println("5. Quan ly hoa don");
                System.out.println("6. Quan ly chi tiet hoa don");
                System.out.println("7. Quan ly Kho");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = sc.nextInt();
                sc.nextLine();
    
                switch (choice) {
                    case 1:
                        ql1.menu();
                        break;
                    case 2:
                        ql2.menu();
                        break;
                    case 3:
                        ql3.menu();
                        break;
                    case 4:
                        ql4.menu();
                        break;
                    case 5:
                        ql5.menu();
                        break;
                    case 6:
                        ql6.menu();
                        break;
                    case 7:
                        ql7.menu();
                        break;    
                    case 0:
                        System.out.println("Thoat chuong trinh.");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le! Vui long chon lai.");
                }
            } while (choice != 0);
    
    }
}