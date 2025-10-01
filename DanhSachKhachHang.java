import java.util.Scanner;
import java.util.Arrays;

public class DanhSachKhachHang {
    KhachHang[] dskh;
    public int n;
    public DanhSachKhachHang() {
        n = 0;
        dskh = new KhachHang[0];
    }

    public DanhSachKhachHang(int n, KhachHang[] dskh) {
        this.n = n;
        this.dskh = dskh;
    }

    public void Nhap() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong khach hang: ");
        n = sc.nextInt();
        dskh = new KhachHang[n];
        for(int i = 0; i < n; i++) {
            dskh[i] = new KhachHang();
            dskh[i].Nhap();
        }
    }

    public void Xuat() {
        System.out.println("Danh sach khach hang la: ");
        for(int i = 0; i < n; i++) {
            dskh[i].Xuat();
        }
    }

    public void Them() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vi tri can xoa: ");
        int k = sc.nextInt();
        if(k < 0 || k > n) {
            System.out.println("Vi tri khong hop le");
            return;
        }

        dskh = Arrays.copyOf(dskh, n + 1);
        for(int i = n; i > k; i--) {
           dskh[i] = dskh[i - 1];
        }
        dskh[k] = new KhachHang();
        dskh[k].Nhap();
        n++;
    }

    public void Xoa(int MaKH) {
        boolean found = false;
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH() == MaKH) {
                for(int j = i; j < n - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                dskh[n - 1] = null;
                n--;
                System.out.println("Da xoa nhan vien nay");
                found = true;
                break;
            }
        }
        if(!found) System.out.println("Khong tim thay nhan vien ");
    }

    public void Sua(int MaKH) {
        boolean found = false;
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH() == MaKH) {
               dskh[i].Nhap();
            }
            found = true;
        }
        if(!found) System.out.println("Khong tim thay khach hang");
        System.out.println("Da sua thong tin khach hang ");
    }


}