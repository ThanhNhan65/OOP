import java.util.Scanner;

public abstract class ConNguoi {
    public String Hoten;
    public String Diachi;
    public long Sdt;
    public String Matkhau;
    public ConNguoi() {
        String Hoten = "";
        String Diachi = "";
        long Sdt = 0;
        String Matkhau = "";
    }
    public ConNguoi(String Hoten, String Diachi, Long Sdt) {
        this.Hoten = Hoten;
        this.Diachi = Diachi;
        this.Sdt = Sdt;
    }

    public String getHoten() {
        return Hoten;
    }

    public String getDiachi(String Diachi) {
        return Diachi;
    }

    public Long getSdt(long Sdt) {
        return Sdt;
    }

    public void Nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ho va ten: ");
        Hoten = sc.nextLine();
        System.out.print("Nhap dia chi: ");
        Diachi = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        Sdt = sc.nextInt();
    }
    public void Xuat() {
        System.out.println("Ho va ten: " + Hoten);
        System.out.println("Dia chi: " + Diachi);
        System.out.println("So dien thoai: " + Sdt );
    }
}
