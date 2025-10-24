package code.doituong;

import java.util.Date;
import java.util.Scanner;
import code.giaodien.INhapXuat;

public class Kho implements INhapXuat {
    private String maKho;
    private SanPham sp;
    private int soLuongTon;
    private int soLuongToiThieu;
    private int soLuongToiDa;
    private Date ngayNhapCuoi;
    
    
    public Kho() {
        this.maKho = "";
        this.sp = null;
        this.soLuongTon = 0;
        this.soLuongToiThieu = 0;
        this.soLuongToiDa = 0;
        this.ngayNhapCuoi = null;
    }
    

    public Kho(String maKho, SanPham sp, int soLuongTon, int soLuongToiThieu, 
               int soLuongToiDa, Date ngayNhapCuoi) {
        this.maKho = maKho;
        this.sp = sp;
        this.soLuongTon = soLuongTon;
        this.soLuongToiThieu = soLuongToiThieu;
        this.soLuongToiDa = soLuongToiDa;
        this.ngayNhapCuoi = ngayNhapCuoi;
    }
    
   
    public String getMaKho() { return maKho; }
    public SanPham getSp() { return sp; }
    public int getSoLuongTon() { return soLuongTon; }
    public int getSoLuongToiThieu() { return soLuongToiThieu; }
    public int getSoLuongToiDa() { return soLuongToiDa; }
    public Date getNgayNhapCuoi() { return ngayNhapCuoi; }
    

    public void setMaKho(String maKho) { this.maKho = maKho; }
    public void setSp(SanPham sp) { this.sp = sp; }
    public void setSoLuongTon(int sl) { this.soLuongTon = sl; }
    public void setSoLuongToiThieu(int soLuongToiThieu) { this.soLuongToiThieu = soLuongToiThieu; }
    public void setSoLuongToiDa(int soLuongToiDa) { this.soLuongToiDa = soLuongToiDa; }
    public void setNgayNhapCuoi(Date ngayNhapCuoi) { this.ngayNhapCuoi = ngayNhapCuoi; }
    
    
    public void NhapKho(int soLuong) {
        if (soLuong > 0) {
            if (this.soLuongTon + soLuong <= this.soLuongToiDa) {
                this.soLuongTon += soLuong;
                this.ngayNhapCuoi = new Date();
                System.out.println("Nhập kho thành công " + soLuong + " sản phẩm.");
            } else {
                System.out.println("Không thể nhập! Vượt quá số lượng tối đa trong kho.");
                System.out.println("Số lượng có thể nhập thêm: " + (soLuongToiDa - soLuongTon));
            }
        } else {
            System.out.println("Số lượng nhập phải lớn hơn 0.");
        }
    }
    
    
    public boolean XuatKho(int soLuong) {
        if (soLuong <= 0) {
            System.out.println("Số lượng xuất phải lớn hơn 0.");
            return false;
        }
        
        if (soLuong > this.soLuongTon) {
            System.out.println("Không đủ hàng trong kho để xuất.");
            System.out.println("Số lượng tồn hiện tại: " + this.soLuongTon);
            return false;
        }
        
        this.soLuongTon -= soLuong;
        System.out.println("Xuất kho thành công " + soLuong + " sản phẩm.");
        
      
        if (KiemTraTonKho()) {
            System.out.println(" CẢNH BÁO: Tồn kho đang thấp hơn mức tối thiểu!");
        }
        
        return true;
    }
    

    public boolean KiemTraTonKho() {
        return this.soLuongTon <= this.soLuongToiThieu;
    }
    
 
    @Override
    public void Nhap(Scanner sc) {
        System.out.println("=== NHẬP THÔNG TIN KHO ===");
        
        System.out.print("Nhập mã kho: ");
        this.maKho = sc.nextLine();
        
        System.out.println("\n--- Nhập thông tin sản phẩm ---");
        this.sp = new SanPham();
        this.sp.Nhap(sc);
        
        System.out.print("Nhập số lượng tồn: ");
        this.soLuongTon = Integer.parseInt(sc.nextLine());
        
        System.out.print("Nhập số lượng tối thiểu: ");
        this.soLuongToiThieu = Integer.parseInt(sc.nextLine());
        
        System.out.print("Nhập số lượng tối đa: ");
        this.soLuongToiDa = Integer.parseInt(sc.nextLine());
        
        this.ngayNhapCuoi = new Date();
        
        System.out.println("Đã nhập thông tin kho thành công!");
    }
    
    @Override
    public void Xuat() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        THÔNG TIN KHO                   ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Mã kho: %-30s ║\n", maKho);
        System.out.println("╠════════════════════════════════════════╣");
        
        if (sp != null) {
            System.out.println("║ Thông tin sản phẩm:                    ║");
            System.out.printf("║   - Mã SP: %-28s ║\n", sp.getMa());
            System.out.printf("║   - Tên SP: %-27s ║\n", sp.getTen());
            System.out.printf("║   - Hãng: %-29s ║\n", sp.getHang());
            System.out.printf("║   - Giá: %-30.0f ║\n", sp.getGia());
        } else {
            System.out.println("║ Chưa có thông tin sản phẩm             ║");
        }
        
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Số lượng tồn: %-24d ║\n", soLuongTon);
        System.out.printf("║ Số lượng tối thiểu: %-18d ║\n", soLuongToiThieu);
        System.out.printf("║ Số lượng tối đa: %-21d ║\n", soLuongToiDa);
        
        if (ngayNhapCuoi != null) {
            System.out.printf("║ Ngày nhập cuối: %-22s ║\n", 
                String.format("%1$td/%1$tm/%1$tY", ngayNhapCuoi));
        } else {
            System.out.println("║ Ngày nhập cuối: Chưa có                ║");
        }
        
        if (KiemTraTonKho()) {
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  CẢNH BÁO: TỒN KHO THẤP!            ║");
        }
        
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    @Override
    public String toString() {
        return String.format("Kho[%s] - SP: %s - Tồn: %d/%d", 
            maKho, 
            (sp != null ? sp.getTen() : "N/A"), 
            soLuongTon, 
            soLuongToiDa);
    }
}
