package NhacCu;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class DanhSachLoai {
    private static final int MAX = 1000;
    private Loai[] ds = new Loai[MAX];
    private int n = 0;

    public void them(Scanner sc){
        if (n >= MAX) {
            System.out.println("Danh sach day, khong the them!");
            return;
        }
        Loai l = new Loai();
        l.Nhap(sc);
        ds[n++] = l;
        System.out.println("Ok roi");
    }

    public void xem(){
        System.out.printf("%-10s %-20s%n","MaLoai", "TenLoai");
        for (int i = 0; i < n; i++) ds[i].Xuat();
    }

    public void xoa(Scanner sc){
        System.out.print("Nhap ma loai de xoa: ");
        String ma = sc.nextLine().trim();
        int vt = -1;
        for (int i = 0; i < n; i++) {
            Loai l = ds[i];
            if (l.getMaloai() != null && l.getMaloai().equalsIgnoreCase(ma)) {
                vt = i; break;
            }
        }
        if (vt >= 0){
            for (int i = vt; i < n - 1; i++) ds[i] = ds[i + 1];
            ds[n - 1] = null;
            n--;
            System.out.println("Xoa roi");
        } else {
            System.out.println("D co de xoa");
        }
    }

    public Loai timkiem(String ma){
        if (ma == null) return null;
        String k = ma.trim();
        for (int i = 0; i < n; i++){
            Loai l = ds[i];
            if (l.getMaloai() != null && l.getMaloai().equalsIgnoreCase(k)) return l;
        }
        return null;
    }

    public void sua(Scanner sc){
        System.out.print("Nhap ma loai can sua: ");
        String ma = sc.nextLine().trim();
        Loai l = timkiem(ma);
        if (l != null){
            System.out.println("Nhap lai tt");
            l.Nhap(sc);
            System.out.println("ok roi");
        } else {
            System.out.println("Kh thay ma");
        }
    }

    private static final String FILE_NAME = "loai.txt";

public void docFile(){
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
            if (p.length != 2) continue;
            if (n >= MAX) {
                System.out.println("Vuot MAX, bo qua: " + line);
                continue;
            }
            ds[n++] = new Loai(p[0], p[1]);
        }
        sc.close();
    } catch(Exception e){
        System.out.println("Khong doc duoc file!");
    }
}

public void ghiFile(){
    try{
        PrintWriter w = new PrintWriter(FILE_NAME);
        for (int i = 0; i < n; i++){
            Loai l = ds[i];
            String ma = (l.getMaloai() == null) ? "" : l.getMaloai();
            String ten = (l.getTenloai() == null) ? "" : l.getTenloai();
            w.println(ma + "," + ten);
        }
        w.close();
    } catch(Exception e){
        System.out.println("Loi ghi file!");
    }
}
}
