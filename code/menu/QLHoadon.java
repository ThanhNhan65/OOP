package code.menu;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import code.danhsach.*;
import code.doituong.*;

public class QLHoadon{
        public void menu(){
            Scanner sc = new Scanner(System.in);
            DanhSachKhachHang dskh = new DanhSachKhachHang();
            DanhSachNhanVien dsnv = new DanhSachNhanVien();
            DanhSachChitietHoaDon dsct = new DanhSachChitietHoaDon();
            DanhSachHoaDon dshd = new DanhSachHoaDon();
            DanhSachSanPham dssp= new DanhSachSanPham();
            DanhSachLoai dsl= new DanhSachLoai();
    
            dskh.DocTuFile("data/danhsachKH.txt");
            dsnv.DocTuFile("data/danhsachNV.txt");
            dsl.docFile();
            dssp.docFile(dsl);
            dshd.ReadFile(dskh, dsnv);
            dsct.ReadFile(dshd, dssp);
            
    
            int choice;
            do {
                System.out.println("\n===== QUAN LY HOA DON =====");
                System.out.println("1. Them hoa don");
                System.out.println("2. Sua hoa don");
                System.out.println("3. Xoa hoa don");
                System.out.println("4. Tim kiem hoa don");
                System.out.println("5. Hien thi danh sach hoa don");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = sc.nextInt();
    
                switch (choice) {
                    case 1:
                        dshd.Them(sc, dskh, dsnv, dssp, dsct);
                        break;
                    case 2:
                        dshd.Sua(sc, dsnv, dskh);
                        break;
                    case 3:
                        dshd.Xoa(sc, dshd, dskh);
                        break;
                    case 4:
                        dshd.Timkiem(sc,dskh,dsnv);
                        break;
                    case 5:
                        dshd.Showlist();
                        break;
                    case 0:
                        System.out.println("Thoat quan ly hoa don.");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le! Vui long chon lai.");
                }
            } while (choice != 0);
        }
    }

