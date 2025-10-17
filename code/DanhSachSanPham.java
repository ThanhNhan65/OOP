package NhacCu;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class DanhSachSanPham{
    private ArrayList<SanPham> ds= new ArrayList<>();
    public void them(Scanner sc, DanhSachLoai dsl){
        SanPham sp= new SanPham();
        sp.nhap(sc, dsl);
        ds.add(sp);
        System.out.println("Ok roi");
    }
    public void xem(){
        System.out.printf("%-10s %-20s %-12s %-12s %10s %6s%n","Ma", "Ten", "Hang", "Loai", "Gia", "SL");
        for(SanPham sp: ds) sp.xuat();
    }
    public void xoa(Scanner sc){
        System.out.print("Nhap ma de xoa: ");
        String ma= sc.nextLine().trim();
        int vt=-1;
        for(int i=0; i<ds.size(); i++){
            SanPham sp= ds.get(i);
            if (sp.getMa()!= null && sp.getMa().equalsIgnoreCase(ma)){
                vt=i;
                break;
            }
        }
        if (vt>=0){
            ds.remove(vt);
            System.out.print("Xoa roi");
        }
        else {
            System.out.println("D co de xoa");
        }
    }
    public SanPham timkiem(String ma){
        if (ma== null) return null;
        String k= ma.trim();  
        for (int i=0; i<ds.size();i++){
            SanPham sp= ds.get(i);
            if(sp.getMa()!= null && sp.getMa().equalsIgnoreCase(k)){
                return sp;
            }
        } return null;
    }
    public void sua(Scanner sc, DanhSachLoai dsl){
        System.out.print("Nhap ma can sua: ");
        String ma= sc.nextLine().trim();
        SanPham sp= timkiem(ma);
        if (sp!= null){
            System.out.println("Nhap lai tt");
            sp.nhap(sc,dsl);
            System.out.println("ok roi");
        } else {
            System.out.println("Kh thay ma");
        }

    }
    public void locTheoHang(Scanner sc){
        System.out.print("Nhap ten hang: ");
        String h= sc.nextLine().trim();
        for(int i=0; i<ds.size(); i++){
            SanPham sp= ds.get(i);
            String hh= sp.getHang()== null? "": sp.getHang().trim();
            if(hh.equalsIgnoreCase(h)) sp.xuat();
        }
    }

    public void locTheoLoai(Scanner sc){
        System.out.print("Nhap MA loai: ");
        String ma= sc.nextLine().trim();
        for(int i=0; i<ds.size(); i++){
            SanPham sp= ds.get(i);
            Loai l= sp.getLoai();
            String ml= l== null? "": l.getMaloai();
            if(ml!= null && ml.trim().equalsIgnoreCase(ma)) sp.xuat();
        }
    }

    public void locTheoGia(Scanner sc){
        System.out.print("Nhap gia thap nhat: ");
        double min= Double.parseDouble(sc.nextLine().trim());
        System.out.print("Nhap gia cao nhat: ");
        double max= Double.parseDouble(sc.nextLine().trim());
        for(int i=0; i<ds.size(); i++){
            SanPham sp= ds.get(i);
            double g= sp.getGia();
            if(g>=min && g<=max) sp.xuat();
        }
    }

    public void docFile(DanhSachLoai dsl){
        ds.clear();
        try{
            File f= new File("sanpham.txt");
            if(!f.exists()){
                PrintWriter w= new PrintWriter("sanpham.txt");
                w.close();
            }
            Scanner sc= new Scanner(f);
            while(sc.hasNextLine()){
                String line= sc.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] p= line.split(",", -1);
                if(p.length!=6) continue;
                Loai l= dsl.timkiem(p[3]);
                if(l== null) l= new Loai(p[3], "Khong ro");
                SanPham sp= new SanPham(p[0], p[1], p[2], l, Double.parseDouble(p[4]), Integer.parseInt(p[5]));
                ds.add(sp);
            }
            sc.close();
        }catch(Exception e){
            System.out.println("Khong doc duoc file!");
        }
    }
    public void ghiFile(){
        try{
            PrintWriter w= new PrintWriter("sanpham.txt");
            for(int i=0; i<ds.size(); i++){
                SanPham sp= ds.get(i);
                String ml= sp.getLoai()== null? "": sp.getLoai().getMaloai();
                w.println(sp.getMa()+","+sp.getTen()+","+sp.getHang()+","+ml+","+sp.getGia()+","+sp.getSoluong());
            }
            w.close();
        }catch(Exception e){
            System.out.println("Loi ghi file!");
        }
    }
}
