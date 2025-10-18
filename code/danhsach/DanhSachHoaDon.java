package code.danhsach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

import code.doituong.*;
import code.danhsach.*;

public class DanhSachHoaDon{
    private HoaDon[] dshd;
    private int n;

    private DanhSachChitietHoaDon dsct;
    private DanhSachSanPham dssp;   
    private DanhSachKhachHang dskh;  
    private DanhSachNhanVien dsnv;  
    public DanhSachHoaDon(){
        n=0;
        dshd= new HoaDon[0];
    }
    public int getN() {
        return n;
    }

    public void ReadFile(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        int Maxid=0;
        try{
            BufferedReader input = new BufferedReader(new FileReader("data/hoadonban.txt"));
            String line = input.readLine();
                while(line != null){
                    String[] arr = line.split(",");
                    String maHD = arr[0].trim();
                    int id = Integer.parseInt(maHD.replaceAll("[^0-9]", ""));
                    if(id>Maxid)
                        Maxid=id;
                    String maKH = arr[1].trim();
                    String maNV = arr[2].trim();
                    Date ngayGD = df.parse(arr[3].trim());


                    KhachHang kh = dskh.Timkiem_MaKH(maKH); 
                    NhanVien nv = dsnv.TimKiemNhanVienTheoMa(maNV);
                    
                    HoaDon hd = new HoaDon();
                    
                    hd.setKh(kh);
                    hd.setNv(nv);
                    hd.setNgayGD(ngayGD);
                    dshd = Arrays.copyOf(dshd, n+ 1);
                    dshd[n]= hd;
                    n++;

                    line = input.readLine();
            }
            HoaDon.setMaHDNext(Maxid + 1);
        }catch(Exception ex){
                    ex.printStackTrace();
                }
    }

    public void WriteFile(){
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try{
                BufferedWriter fw = new BufferedWriter(new FileWriter("data/hoadonban.txt"));
                for(int i=0; i<n ;i++){
                    fw.write(dshd[i].getMaHDB() + ","+dshd[i].getKh().getMaKH() + ","+dshd[i].getNv().getMaNV()+ ","+ df.format(dshd[i].getNgayGD()));
                    fw.newLine();   
                }
                fw.close();
            }catch (Exception e) {
                    System.out.println(e);
                }
    }
        public void Them(Scanner sc){
            dshd = Arrays.copyOf(dshd, n+1);
            dshd[n]= new HoaDon();
            dshd[n].Nhap(sc);
            dshd[n].getdsct();
            n++;
            WriteFile();
        }
    public HoaDon Timkiem_MaHD(String MaHD){
        boolean found=false;
        HoaDon hd= new HoaDon();
        for(int i=0; i<n; i++){
            if(dshd[i].getMaHDB().equals(MaHD)){
                hd=dshd[i];
                found=true;
                break;
            }   
        }
        if(found)
            return hd;
        else
            return null;
    }
    public void Timkiem_MaKH(String MaKH){
        for(int i=0; i<n; i++){
            if(dshd[i].getKh().getMaKH().equals(MaKH)){
                dshd[i].Xuat();
            }   
        }
    }
    public void Timkiem_MaNV(String MaNV){
        for(int i=0; i<n; i++){
            if(dshd[i].getNv().getMaNV().equals(MaNV)){
                dshd[i].Xuat();
            }   
        }
    }

    public void Xoa_MaHD(String MaHD){
        boolean bool=false;
        for(int i=0 ; i<n ;){
            if(dshd[i].getMaHDB().equals(MaHD)){
                for(int j= i; j < n-1; j++){
                    dshd[j]=dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
                bool = true;
                dsct.XoaTB(MaHD);
            }else 
                i++;   
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }
    public void Xoa_MaKH(String MaKH){
        boolean bool=false;
        for(int i=0 ; i<n ;){
            if(dshd[i].getKh().getMaKH().equals(MaKH)){
                HoaDon hd = new HoaDon();
                hd= dshd[i];
                for(int j= i; j < n-1; j++){
                    dshd[j]=dshd[j+1];
                }
                dshd = Arrays.copyOf(dshd, n-1);
                n--;
                bool = true;
                dsct.XoaTB(hd.getMaHDB());;
            }else 
                i++;   
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void Sua_NgayGD(Scanner sc,Date NgayGD){
        boolean bool=false;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for(int i=0 ; i<n ;i++){
            if(df.format(dshd[i].getNgayGD()).equals(df.format(NgayGD))){
                String newngay = sc.nextLine();
                try{
                    dshd[i].setNgayGD(df.parse(newngay));
                    }
                    catch(Exception ex){
                        System.out.println("Loi dinh dang ngay. Su dung dd/MM/yyyy\nDat mac dinh la ngay hien tai.");
                        dshd[i].setNgayGD(new Date());
                    }
                bool = true;        
            }
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void Sua_MaKH(Scanner sc, String MaKH  ){
        boolean bool=false;
        for(int i=0 ; i<n ;i++){
            if(dshd[i].getKh().getMaKH().equals(MaKH)){
                String newma = sc.nextLine();
                KhachHang kh= dskh.Timkiem_MaKH(newma);
                while(kh==null){
                    System.out.println("Khong tim thay khach hang: " + newma);
                    System.out.println("Vui long nhap lai! ");
                    newma = sc.nextLine();
                    kh= dskh.Timkiem_MaKH(newma);
                }
                dshd[i].setKh(kh);
                bool = true;        
            }
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }
    public void Sua_MaNV(Scanner sc, String MaNV  ){
        boolean bool=false;
        for(int i=0 ; i<n ;i++){
            if(dshd[i].getNv().getMaNV().equals(MaNV)){
                String newma = sc.nextLine();
                NhanVien nv= dsnv.TimKiemNhanVienTheoMa(newma);
                while(nv==null){
                    System.out.println("Khong tim thay khach hang: " + newma);
                    System.out.println("Vui long nhap lai! ");
                    newma = sc.nextLine();
                    nv= dsnv.TimKiemNhanVienTheoMa(newma);
                }
                dshd[i].setNv(nv);
                bool = true;        
            }
        }
        System.out.println(bool ? "Xoa thanh cong!" : "That bai!");
        WriteFile();
    }

    public void Showlist(){
        for(int i=0 ; i<n ;i++)
            dshd[i].Xuat();
    }
}

