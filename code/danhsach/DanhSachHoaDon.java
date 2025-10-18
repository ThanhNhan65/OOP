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
        try{
            BufferedReader input = new BufferedReader(new FileReader("data/hoadonban.txt"));
            String line = input.readLine();
                while(line != null){
                    String[] arr = line.split(",");
                    String maHD = arr[0].trim();
                    String maKH = arr[1].trim();
                    String maNV = arr[2].trim();
                    Date ngayGD = df.parse(arr[3].trim());


                    KhachHang kh = dskh.Timkiem_MaKH(maKH); 
                    NhanVien nv = dsnv.Timkiem_MaNV(maNV);
                    
                    HoaDon hd = new HoaDon();
                    
                    hd.setKh(kh);
                    hd.setNv(nv);
                    hd.setNgayGD(ngayGD);
                    dshd = Arrays.copyOf(dshd, n+ 1);
                    dshd[n]= hd;
                    n++;

                    line = input.readLine();
            }
        }catch(Exception ex){
                    ex.printStackTrace();
                }
    }

    public void WriteFile(){
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try{
                BufferedWriter fw = new BufferedWriter(new FileWriter("data/hoadonban.txt"));
                for(int i=0; i<n ;i++){
                    fw.write(dshd[i].getMaHDB() + ","+dshd[i].getKh().getMaKH() + ","+dshd[i].getNv().getMaNV());
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
    
}