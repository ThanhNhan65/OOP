package code.danhsach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;
import code.doituong.*;

public class DanhSachSanPham{
    private SanPham[] ds = new SanPham[0];
    private int n = 0;

    public void docFile(DanhSachLoai dsl){
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/sanpham.txt"));
            String line = br.readLine();
            while(line != null){
                line = line.trim();
                if(!line.isEmpty()){
                    String[] a = line.split(",", -1);
                    if(a.length == 5){
                        String ma     = a[0].trim();
                        String ten    = a[1].trim();
                        String hang   = a[2].trim();
                        String maLoai = a[3].trim();
                        double gia    = Double.parseDouble(a[4].trim());

                        Loai l = dsl.TimTheoMa(maLoai);
                        if(l != null){
                            SanPham sp = new SanPham();
                            sp.setMa(ma);
                            sp.setTen(ten);
                            sp.setHang(hang);
                            sp.setLoai(l);
                            sp.setGia(gia);

                            ds = java.util.Arrays.copyOf(ds, n + 1);
                            ds[n++] = sp;
                        }
                    }
                }
                line = br.readLine();
            }
            br.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void ghiFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/sanpham.txt"));
            for(int i = 0; i < n; i++){
                SanPham sp = ds[i];
                if(sp == null) continue;
                String maLoai = (sp.getLoai() != null ? sp.getLoai().getMaloai() : "");
                bw.write(sp.getMa() + "," + sp.getTen() + "," + sp.getHang() + "," + maLoai + "," + sp.getGia());
                bw.newLine();
            }
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void Them(Scanner sc, DanhSachLoai dsl){
        SanPham sp = new SanPham();
        sp.Nhap(sc, dsl);
        String ma = sp.getMa();
        if (ma == null || ma.trim().isEmpty()){
            System.out.println("Ma khong hop le");
            return;
        }
        if (TonTaiMa(ma)){
            System.out.println("Ma nay da co ");
            return;
        }
        ds = java.util.Arrays.copyOf(ds, n + 1);
        ds[n++] = sp;
        ghiFile();
    } 
    
    private boolean TonTaiMa(String ma){
        if (ma == null || ma.isEmpty()) return false;
        for (int i = 0; i < n; i++){
            SanPham p = ds[i];
            if (p != null){
                String m = p.getMa();
                if (m != null && m.equalsIgnoreCase(ma))
                return true;
            }
        }
        return false;
    }
    public void Xoa(Scanner sc){
    int c;
    do{
        System.out.println("1. Xoa san pham theo ma");
        System.out.println("2. Xoa toan bo san pham ");
        System.out.println("0. Thoat");
        c = sc.nextInt();
        sc.nextLine();
        switch(c){
            case 1: {
                System.out.println("Nhap ma san pham muon xoa: ");
                String ma = sc.nextLine();
                boolean ok = XoaTheoMa(ma);
                if(ok){
                    System.out.println("Da xoa ma nay");
                } else{
                    System.out.println("Khong thay ma nay");
                }
                break;
            }
            case 2: {
                System.out.println("Hien co" + n + "san pham");
                System.out.println("Ban muon xoa het chu, nhap OK de xac nhan: ");
                if ("OK".equalsIgnoreCase(sc.nextLine().trim())){
                    XoaTatCa();
                }
                break;
            }
            case 0: break;
            default: System.out.println("Khong hop le");
        }
    } while (c != 0);
}

    private boolean XoaTheoMa(String ma){
        for(int i = 0; i < n; i++){
            if(ds[i] != null && ma.equalsIgnoreCase(ds[i].getMa())){
                for(int j = i; j < n - 1; j++){
                    ds[j] = ds[j + 1];
                }
                ds[n - 1] = null;
                n--;
                ds = java.util.Arrays.copyOf(ds, n);
                return true;
            }
        }
        return false;
    }

    private void XoaTatCa(){
        ds = new SanPham[0];
        n = 0;
    }
    public void TimKiem(Scanner sc){
    int c;
    do{
        System.out.println("1. Tim theo ma");
        System.out.println("2. Tim theo ten");
        System.out.println("0. Thoat");
        c = sc.nextInt();
        sc.nextLine();
        switch(c){
            case 1: {
                System.out.print("Nhap ma: ");
                String ma = sc.nextLine().trim();
                SanPham sp = TimTheoMa(ma);
                if(sp != null) sp.Xuat();
                else System.out.println("Khong tim thay");
                break;
            }
            case 2: {
                System.out.print("Nhap ten: ");
                String ten = sc.nextLine().trim();
                int dem = TimTheoTen(ten);
                if(dem == 0) System.out.println("Khong tim thay");
                break;
            }
            case 0: break;
            default: System.out.println("Khong hop le");
        }
    } while (c != 0);
}

    private SanPham TimTheoMa(String ma){
        if(ma == null) return null;
        for(int i = 0; i < n; i++){
            SanPham sp = ds[i];
            if(sp != null && sp.getMa() != null && sp.getMa().equalsIgnoreCase(ma))
                return sp;
        }
        return null;
        }
    private int TimTheoTen(String ten){
        if(ten == null) return 0;
        String k = ten.toLowerCase();
        int d = 0;
        for(int i = 0; i < n; i++){
            SanPham sp = ds[i];
            if(sp != null && sp.getTen() != null && sp.getTen().toLowerCase().contains(k)){
                sp.Xuat();
                d++;
            }
        }
        return d;
    }

    public void Sua(Scanner sc, DanhSachLoai dsl){
        System.out.print("Nhap ma sp muon sua: ");
        SanPham sp = TimTheoMa(sc.nextLine().trim());
            if (sp == null){ 
                System.out.println("Khong tim thay");
                return; 
        }
        int c;
        do{
            System.out.println("Dang Sua [" + sp.getMa() + "]");
            System.out.println("1.Sua ma");
            System.out.println("2.Sua ten");
            System.out.println("3.Sua hang");
            System.out.println("4.Sua loai");
            System.out.println("5.Sua gia");
            System.out.println("6.Sua toan bo");
            System.out.println("0. Thoat");
            c= sc.nextInt();
            sc.nextLine();
            switch(c){
                case 1: SuaMa(sc, sp);
                break;
                case 2: SuaTen(sc, sp);  
                break;
                case 3: SuaHang(sc, sp); 
                break;
                case 4: SuaLoai(sc, dsl, sp); 
                break;
                case 5: SuaGia(sc, sp);  
                break;
                case 6: SuaToanBo(sc, dsl, sp);
                break;
                case 0: break;
                default: System.out.println("Khong hop le");
            }
        } while(c != 0);
}
    private void SuaMa(Scanner sc, SanPham sp){
    System.out.println("Ma hien tai: " + sp.getMa());
    System.out.print("Ma moi: ");
    String maMoi = sc.nextLine().trim();
    if (maMoi.isEmpty()){ 
        System.out.println("Khong hop le"); 
        return; 
    }
    for (int i = 0; i < n; i++){
        SanPham p = ds[i];
        if (p != null && p != sp && p.getMa() != null && p.getMa().equalsIgnoreCase(maMoi)){
            System.out.println("Ma da ton tai"); 
            return;
        }
    }
    sp.setMa(maMoi);
    ghiFile();
}

private void SuaTen(Scanner sc, SanPham sp){
    System.out.println("Ten hien tai: " + sp.getTen());
    System.out.print("Ten moi: ");
    String ten = sc.nextLine().trim();
    if (ten.isEmpty()){ 
        System.out.println("Khong hop le"); 
        return; 
    }
    sp.setTen(ten);
    ghiFile();
}

private void SuaHang(Scanner sc, SanPham sp){
    System.out.println("Hang hien tai: " + sp.getHang());
    System.out.print("Hang moi: ");
    String hang = sc.nextLine().trim();
    if (hang.isEmpty()){ 
        System.out.println("Khong hop le"); 
        return; 
    }
    sp.setHang(hang);
    ghiFile();
}

private void SuaLoai(Scanner sc, DanhSachLoai dsl, SanPham sp){
    String loaiHienTai = (sp.getLoai() != null ? sp.getLoai().getMaloai() : "null");
    System.out.println("Ma loai hien tai: " + loaiHienTai);
    System.out.print("Ma loai moi: ");
    Loai l = dsl.TimTheoMa(sc.nextLine().trim());
    if (l == null){ 
        System.out.println("Khong hop le"); 
        return; 
    }
    sp.setLoai(l);
    ghiFile();
}

private void SuaGia(Scanner sc, SanPham sp){
    System.out.println("Gia hien tai: " + sp.getGia());
    System.out.print("Nhap gia moi: ");
    try{
        double gia = Double.parseDouble(sc.nextLine().trim());
        if (gia < 0){ 
            System.out.println("Gia bi am, khong duoc"); 
            return; 
        }
        sp.setGia(gia);
        ghiFile();
    }catch(Exception e){
        System.out.println("Khong hop le"); 
    }
}

private void SuaToanBo(Scanner sc, DanhSachLoai dsl, SanPham sp){
    String s;
    System.out.println("Ma hien tai: " + sp.getMa());
    System.out.print("Ma moi: ");
    s = sc.nextLine().trim();
    if(!s.isEmpty()){
        boolean trung = false;
        for(int i = 0; i < n; i++){
            SanPham p = ds[i];
            if(p != null && p != sp && s.equalsIgnoreCase(p.getMa())){ 
                trung = true; 
                break; 
            }
        }
        if(!trung) sp.setMa(s);
    }

    System.out.println("Ten hien tai: " + sp.getTen());
    System.out.print("Ten moi: ");
    s = sc.nextLine().trim();
    if(!s.isEmpty()) sp.setTen(s);

    System.out.println("Hang hien tai: " + sp.getHang());
    System.out.print("Hang moi: ");
    s = sc.nextLine().trim();
    if(!s.isEmpty()) sp.setHang(s);

    String loaiHienTai = (sp.getLoai() != null ? sp.getLoai().getMaloai() : "null");
    System.out.println("Ma loai hien tai: " + loaiHienTai);
    System.out.print("Ma loai moi: ");
    s = sc.nextLine().trim();
    if(!s.isEmpty()){
        Loai l = dsl.TimTheoMa(s);
        if(l != null) sp.setLoai(l);
    }

    System.out.println("Gia hien tai: " + sp.getGia());
    System.out.print("Gia moi: ");
    s = sc.nextLine().trim();
    if(!s.isEmpty()){
        try{
            double gia = Double.parseDouble(s);
            if(gia >= 0) sp.setGia(gia);
        }catch(Exception ignored){}
    }
    ghiFile();
}

    public void locTheoHang(Scanner sc){
        System.out.print("Nhap ten hang: ");
        String h = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            if (sp == null) continue;
            String hh = (sp.getHang() == null) ? "" : sp.getHang().trim();
            if (hh.equalsIgnoreCase(h)) 
            sp.Xuat();
        }
    }

    public void locTheoLoai(Scanner sc){
        System.out.print("Nhap ma loai: ");
        String ma = sc.nextLine().trim();
        for (int i = 0; i < n; i++) {
            SanPham sp = ds[i];
            if (sp == null) continue;
            Loai l = sp.getLoai();
            String ml = (l == null) ? "" : l.getMaloai(); 
            if (!ml.isEmpty() && ml.trim().equalsIgnoreCase(ma)) sp.Xuat();
        }
    }

    public void locTheoGia(Scanner sc){
        System.out.print("Nhap gia thap nhat: ");
        String s1 = sc.nextLine().trim();
        System.out.print("Nhap gia cao nhat: ");
        String s2 = sc.nextLine().trim();
        try{
            double min = Double.parseDouble(s1);
            double max = Double.parseDouble(s2);
            for (int i = 0; i < n; i++) {
                SanPham sp = ds[i];
                if (sp == null) continue;
                double g = sp.getGia();
                if (g >= min && g <= max) sp.Xuat();
            }
        }catch(Exception ignored){}
    }

}
