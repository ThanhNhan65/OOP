package code.kethua;
import java.text.SimpleDateFormat;
import java.util.*;

import code.doituong.*;
import code.giaodien.*;


public abstract class GiaoDich implements INhapXuat{
    private Date ngayGD;
    private NhanVien nv;
    private KhachHang kh;

    public GiaoDich(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // ngayGD= df.parse("");
        nv = new NhanVien();
        kh = new KhachHang();
    }
    public GiaoDich(Date ngayGD, NhanVien nv, KhachHang kh){
        this.ngayGD= ngayGD;
        this.kh=kh;
        this.nv=nv;
    } 
    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }
    public abstract double Thanhtien();
    public abstract void Xuat();
    public abstract void Nhap(Scanner sc);
}
