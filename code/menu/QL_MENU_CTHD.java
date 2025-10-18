package code.menu;

import code.danhsach.*;
import java.util.Scanner;

public class QL_MENU_CTHD{
    DanhSachChitietHoaDon ds1= new DanhSachChitietHoaDon();
    public void menu_cthd(Scanner sc){
        int choice;
        do{
            System.out.println("===========QUAN LY CHI TIET HOA DON============");
            System.out.println("1.Them chi tiet hoa don.");
            System.out.println("2.Sua chi tiet hoa don.");
            System.out.println("3.Xoa chi tiet hoa don.");
            System.out.println("4.Tim kiem chi tiet hoa don.");
            System.out.println("5.Thoat.");
            System.out.println("Vui long chon:");
            choice= sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: ds1.Them(sc);
                        break;
                case 2: ds1.Sua(sc);
                        break;
                case 3: ds1.Xoa(sc);
                        break;
                case 4: ds1.TimKiem(sc);
                        break;
                case 5: System.out.println("Thoat chuong trinh!");
                        break;
                default: System.out.println("Vui long chon lai!");
                         break;
            }
        }while(choice !=5);
    }
}