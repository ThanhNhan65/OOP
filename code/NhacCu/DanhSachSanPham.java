package NhacCu;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class DanhSachSanPham {
    private static final int MAX = 1000;
    private SanPham[] ds = new SanPham[MAX];
    private int n = 0;

    public void them(Scanner sc, DanhSachLoai dsl){
        if (n >= MAX) {
            System.out.println("Danh sach day, khong the them!");
            return;
        }
        SanPham sp = new SanPham();
        sp.Nhap(sc, dsl);
        ds[n++] = sp;
        System.out.println("Ok roi");
    }

    public void xem(){
        System.out.printf("%-10s %-20s %-12s %-12s %10s%n","Ma", "Ten", "Hang", "Loai", "Gia");
        for (int i = 0; i < n; i++) ds[i].Xuat();
    }

    public void xoa(Scanner sc){
        System.out.print("Nhap ma de xoa: ");
        String ma = sc.nextLine().trim();
        int vt = -1;
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            if (sp.getMa() != null && sp.getMa().equalsIgnoreCase(ma)) { vt = i; break; }
        }
        if (vt >= 0) {
            for (int i = vt; i < n - 1; i++) ds[i] = ds[i + 1];
            ds[n - 1] = null;
            n--;
            System.out.print("Xoa roi");
        } else {
            System.out.println("D co de xoa");
        }
    }

    public SanPham timkiem(String ma){
        if (ma == null) return null;
        String k = ma.trim();
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            if (sp.getMa() != null && sp.getMa().equalsIgnoreCase(k)) return sp;
        }
        return null;
    }

    public void sua(Scanner sc, DanhSachLoai dsl){
        System.out.print("Nhap ma can sua: ");
        String ma = sc.nextLine().trim();
        SanPham sp = timkiem(ma);
        if (sp != null){
            System.out.println("Nhap lai tt");
            sp.Nhap(sc, dsl);
            System.out.println("ok roi");
        } else {
            System.out.println("Kh thay ma");
        }
    }

    public void locTheoHang(Scanner sc){
        System.out.print("Nhap ten hang: ");
        String h = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            String hh = sp.getHang() == null ? "" : sp.getHang().trim();
            if (hh.equalsIgnoreCase(h)) sp.Xuat();
        }
    }

    public void locTheoLoai(Scanner sc){
        System.out.print("Nhap MA loai: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            Loai l = sp.getLoai();
            String ml = (l == null) ? "" : l.getMaloai();
            if (ml != null && ml.trim().equalsIgnoreCase(ma)) sp.Xuat();
        }
    }

    public void locTheoGia(Scanner sc){
        System.out.print("Nhap gia thap nhat: ");
        double min = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Nhap gia cao nhat: ");
        double max = Double.parseDouble(sc.nextLine().trim());
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            double g = sp.getGia();
            if (g >= min && g <= max) sp.Xuat();
        }
    }

    private static final String FILE_NAME = "sanpham.txt";

public void docFile(DanhSachLoai dsl){
    n = 0;
    try{
        File f = new File(FILE_NAME);
        if (!f.exists()){
            System.out.println("Chua co file " + FILE_NAME + " -> danh sach rong.");
            return;
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()){
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split(",", -1);
            if (p.length != 5) continue;
            if (n >= MAX) {
                System.out.println("Vuot MAX, bo qua: " + line);
                continue;
            }
            Loai l = dsl.timkiem(p[3]);
            if (l == null) l = new Loai(p[3], "Khong ro");
            SanPham sp = new SanPham(p[0], p[1], p[2], l, Double.parseDouble(p[4]));
            ds[n++] = sp;
        }
        sc.close();
    } catch(Exception e){
        System.out.println("Khong doc duoc file!");
    }
}

public void ghiFile(){
    try{
        PrintWriter w = new PrintWriter(FILE_NAME);
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            String ml = (sp.getLoai() == null) ? "" : sp.getLoai().getMaloai();
            w.println(sp.getMa()+","+sp.getTen()+","+sp.getHang()+","+ml+","+sp.getGia());
        }
        w.close();
    } catch(Exception e){
        System.out.println("Loi ghi file!");
    }
}
}
