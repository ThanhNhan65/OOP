package NhacCu;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class DanhSachLoai{
    private ArrayList<Loai> ds= new ArrayList<>();
    public void them(Scanner sc){
        Loai l= new Loai();
        l.nhap(sc);
        ds.add(l);
        System.out.println("Ok roi");
    }
    public void xem(){
        System.out.printf("%-10s %-20s%n","MaLoai", "TenLoai");
        for(Loai l: ds) l.xuat();
    }
    public void xoa(Scanner sc){
        System.out.print("Nhap ma loai de xoa: ");
        String ma= sc.nextLine().trim();
        int vt=-1;
        for(int i=0; i<ds.size(); i++){
            Loai l= ds.get(i);
            if(l.getMaloai()!= null && l.getMaloai().equalsIgnoreCase(ma)){
                vt=i;
                break;
            }
        }
        if(vt>=0){
            ds.remove(vt);
            System.out.println("Xoa roi");
        } else {
            System.out.println("D co de xoa");
        }
    }
    public Loai timkiem(String ma){
        if(ma== null) return null;
        String k= ma.trim();
        for(int i=0; i<ds.size(); i++){
            Loai l= ds.get(i);
            if(l.getMaloai()!= null && l.getMaloai().equalsIgnoreCase(k)){
                return l;
            }
        }
        return null;
    }
    public void sua(Scanner sc){
        System.out.print("Nhap ma loai can sua: ");
        String ma= sc.nextLine().trim();
        Loai l= timkiem(ma);
        if(l!= null){
            System.out.println("Nhap lai tt");
            l.nhap(sc);
            System.out.println("ok roi");
        } else {
            System.out.println("Kh thay ma");
        }
    }
    public void docFile(){
        ds.clear();
        try{
            File f= new File("loai.txt");
            if(!f.exists()){
                PrintWriter w= new PrintWriter("loai.txt");
                w.close();
            }
            Scanner sc= new Scanner(f);
            while(sc.hasNextLine()){
                String line= sc.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] p= line.split(",", -1);
                if(p.length!=2) continue;
                Loai l= new Loai(p[0], p[1]);
                ds.add(l);
            }
            sc.close();
        }catch(Exception e){
            System.out.println("Khong doc duoc file!");
        }
    }
    public void ghiFile(){
        try{
            PrintWriter w= new PrintWriter("loai.txt");
            for(int i=0; i<ds.size(); i++){
                Loai l= ds.get(i);
                String ma= l.getMaloai()==null? "": l.getMaloai();
                String ten= l.getTenloai()==null? "": l.getTenloai();
                w.println(ma+","+ten);
            }
            w.close();
        }catch(Exception e){
            System.out.println("Loi ghi file!");
        }
    }
}

