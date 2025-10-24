package code.danhsach;

import java.util.Scanner;
import code.doituong.Kho;


public class DanhSachKho {
    private Kho[] dsKho;     
    private int soLuong;     

    public DanhSachKho() {
        dsKho = new Kho[0]; 
        soLuong = 0;
    }

   
    public void themKho(Kho k) {
        if (soLuong >= dsKho.length) {
            System.out.println(" Không thể thêm — danh sách kho đã đầy!");
            return;
        }
        dsKho[soLuong++] = k;
        System.out.println(" Đã thêm kho mới vào danh sách!");
    }

    
    public void nhapDanhSachKho(Scanner sc) {
        System.out.print("Nhập số lượng kho cần thêm: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            if (soLuong >= dsKho.length) {
                System.out.println("⚠ Danh sách đã đầy, dừng nhập!");
                break;
            }
            System.out.println("\n--- Nhập kho thứ " + (soLuong + 1) + " ---");
            Kho k = new Kho();
            k.Nhap(sc);
            dsKho[soLuong++] = k;
        }
    }

   
    public void xuatDanhSachKho() {
        if (soLuong == 0) {
            System.out.println("⚠ Danh sách kho trống!");
            return;
        }

        System.out.println("\n===== DANH SÁCH KHO HÀNG =====");
        for (int i = 0; i < soLuong; i++) {
            dsKho[i].Xuat();
        }
    }

  
    public Kho timKhoTheoMa(String maKho) {
        for (int i = 0; i < soLuong; i++) {
            if (dsKho[i].getMaKho().equalsIgnoreCase(maKho)) {
                return dsKho[i];
            }
        }
        return null;
    }

 
    public boolean xoaKhoTheoMa(String maKho) {
        for (int i = 0; i < soLuong; i++) {
            if (dsKho[i].getMaKho().equalsIgnoreCase(maKho)) {
             
                for (int j = i; j < soLuong - 1; j++) {
                    dsKho[j] = dsKho[j + 1];
                }
                dsKho[--soLuong] = null;
                System.out.println("🗑 Đã xóa kho có mã: " + maKho);
                return true;
            }
        }
        System.out.println(" Không tìm thấy kho có mã: " + maKho);
        return false;
    }

    
    public void hienThiKhoCanNhapHang() {
        System.out.println("\n=== DANH SÁCH KHO CẦN NHẬP THÊM HÀNG ===");
        boolean co = false;

        for (int i = 0; i < soLuong; i++) {
            if (dsKho[i].KiemTraTonKho()) {
                dsKho[i].Xuat();
                co = true;
            }
        }

        if (!co) {
            System.out.println(" Tất cả kho đều đủ hàng, không cần nhập thêm!");
        }
    }

    public int getSoLuong() {
        return soLuong;
    }

   
    public Kho[] getDsKho() {
        return dsKho;
    }
}
