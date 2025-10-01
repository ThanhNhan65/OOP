

import java.util.Scanner;
import java.util.Arrays;

public class DanhSachNhanVien {
    public NhanVien[] dsnv;
    public int n;

    public DanhSachNhanVien() {
        dsnv = new NhanVien[0];
        n = 0;
    }

    public DanhSachNhanVien(int n, NhanVien[] dsnv){
        this.n = n;
        this.dsnv = dsnv;
    }

    public void Nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print ("nhap n: ");
        n = sc.nextInt();
        dsnv = new NhanVien[n];
        for(int i = 0; i < n; i++) {
            dsnv[i] = new NhanVien();
            dsnv[i].Nhap();
        }
    }
    public void Xuat() {
        for(int i = 0; i < n; i++) {
            dsnv[i].Xuat();
        }
    }

    public void Them() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vi tri muon them: ");
        int k = sc.nextInt();
        if(k < 0 || k > n) {
            System.out.print("Vi tri khong hop le");
            return;
        }

        dsnv = Arrays.copyOf(dsnv, n + 1);
        for(int i = n; i > k; i--){
            dsnv[i] = dsnv[i - 1];
        }
        dsnv[k] = new NhanVien();
        dsnv[k].Nhap();
        n++;
    }

    public void Xoa(int MaNV) {
        boolean found = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV() == MaNV) {
                for(int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv[n - 1] = null;
                n--;
                System.out.println("Da xoa nhan vien nay");
                found = true;
                break;
            }
        }
        if(!found) System.out.println("Khong tim thay nhan vien ");
    }

    public void Sua(int MaNV) {
        boolean found = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV() == MaNV) {
                dsnv[i].Nhap();
            }
            found = true;
        }
        if(!found) System.out.print("Khong tim thay nhan vien nay");
    }
}
