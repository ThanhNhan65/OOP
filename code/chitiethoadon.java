package code;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

/* Chi tiết hóa đơn bán */
class Chitiethoaodonban implements INhapXuat{
    private Hoadonban hd;
    private Sanpham sp;
    int soluong;

    // ====Hàm constructor=====
    public Chitiethoaodonban(){
        hd= new Hoadonban();
        sp= new Sanpham();
    }
    public Chitiethoaodonban (Hoadonban hd, Sanpham sp, int soluong){
            this.hd= hd;
            this.sp= sp;
            this.soluong=soluong;
    }
    // getter và setter

    // -----------Hoa don ban------------
    public void setHDB(Hoadonban hd){
        this.hd=hd;
    }
    public Hoadonban getHDB(){
        return hd;
    }
    // ----------San Pham-----------------
    public void setSP(Sanpham sp){
        this.sp=sp;
    }
    public Sanpham getSP(){
        return sp;
    }
    //---------So luong------------------
    public void setSL(int soluong){
        this.soluong=soluong;
    }
    public int getSL(){
        return soluong;
    }
    
    //Phuong thuc
    public double Tinhtien(){
        return soluong * sp.getDongia();
    }

    public void Nhap(Scanner sc){
        System.out.println("Vui long nhap ma hoa don. ");
        String MaHD= sc.nextLine();
        this.hd= dshd.Timkiem(MaHD);
        while(hd==null){
            System.out.println("Khong tim thay hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            String MaHD= sc.nextLine();
            this.hd= dshd.Timkiem(MaHD);
        }
        System.out.println("Vui long nhap ma san pham. ");
        String MaSP= sc.nextLine();
        this.sp= dssp.Timkiem(MaSP);
        while(sp==null){
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            String MaSP= sc.nextLine();
            this.sp= dssp.Timkiem(MaSP);
        }
        System.out.println("Vui long nhap so luong. ");
        this.soluong= sc.nextInt();
        while(soluong < 0){
            System.out.println("Vui long nhap lai! ");
            this.soluong= sc.nextInt();
        }
        sc.nextLine();
        
    }
    public void Xuat(){
        System.out.println("Hoa đơn: " + hd.getMaHD() +
                           " | Ma san pham: " + sp.getMaSP() +
                           " | San pham: " + sp.getTenSP()+
                           " | So luong: " + soluong +
                           " | Gia tien: " + Tinhtien());
    }
}

/*Lớp danh sách chi tiết */
class DSCT{
    protected Chitiethoaodonban[] ds;
    public  int n;

    public DSCT(){
        n=0;
        ds=new Chitiethoaodonban[0];
    }
    public getDSCT(int index){
            return ds[index];
    }
    public int getN() {
        return n;
    }


        // -----------------Đọc file----------------------
    public void ReadFile(){
        try{
            BufferedReader input = new BufferedReader(new FileReader("data\\chitiethoadon.txt"));
            String line = input.readLine();
            while(line!= null){
                String[] arr = line.split(",");

                String maHD = arr[0].trim();
                String maSP = arr[1].trim();
                int soluong = Integer.parseInt(arr[2].trim());

                Hoadonban hd= dshd.TimKiem(maHD);
                Sanpham sp = dssp.TimKiem(maSP);

                Chitiethoaodonban ct = new Chitiethoaodonban(hd, sp, soluong);
                
                ds = Arrays.copyOf(ds,n+1);
                ds[n]=ct;
                n++;

                line = input.readLine();
            }
            input.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
        // -----------------Ghi file --------------------------
    public void WriteFile(){
        try{
            FileWriter fw = new FileWriter("data\\chitiethoadon.txt");
            for(int i=0; i<n ;i++){
                fw.write(ds[i].hd.getMaHD() + ","+ds[i].sp.getMaSP() + ","+ds[i].getSL());
                fw.newLine();
            }
            fw.close();
        }catch (Exception e) {
                System.out.println(e);
            }
    }

        // ----------------Them chi tiet -------------------
    public void Them(Scanner sc){
        ds = Arrays.copyOf(ds, n+1);
        ds[n] = new Chitiethoaodonban();
        ds[n].Nhap(sc);
        n++;
        WriteFile();
    }

        // -----------------Sửa chi tiết----------------
    public void Sua(Scanner sc){
        System.out.println("Nhap ma hoa don can sua:");
        String MaHD= sc.nextLine();
        Hoadonban hd = dshd.TimKiem(MaHD);
        while( hd ==null){
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD= sc.nextLine();
            hd = dshd.TimKiem(MaHD);
        }

        Chitiethoaodonban[] arr=  new Chitiethoaodonban[0];
        int count=0;
        for(int i=0; i<n; i++){
            if(MaHD.equals(ds[i].getHDB().getMaHD())){
                ds[i].Xuat();
                arr= Arrays.copyOf(arr, count +1);
                arr[count]=ds[i];
                count++;
            }
        }
        int choice;
        do{
            System.out.println("1.Ma san pham va so luong:");
            System.out.println("2.So luong cua san pham:");
            System.out.println("Vui lòng chọn:");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: Suaspsl(sc, arr, count);
                        break;
                case 2: Suasl(sc, arr, count);
                        break;
                case 3: System.out.println("Thoat man hình");
                        break;

            }
        }while(choice !=4);
    }
    public void Suaspsl(Scanner sc, Chitiethoaodonban[] arr, int count){
        System.out.println("Nhap ma san pham can sua:");
        String MaSP= sc.nextLine();
        Sanpham sp= dssp.TimKiem(MaSP);
        while(sp==null){
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP= sc.nextLine();
            sp= dssp.TimKiem(MaSP);
        }
        for(int i=0; i< count; i++){
            if(MaSP.equals(arr[i].getSP().getMaSP())){
                System.out.println("Sua ma sp");
                String newSP= sc.nextLine();
                Sanpham newsp= dssp.TimKiem(newSP); 
                while(newsp == null){
                    System.out.println("Nhap lai ma san pham can sua");
                    newSP= sc.nextLine();
                    newSP= dssp.TimKiem(newSP); 
                }
                
                arr[i].setSP(newSP);

                System.out.println("Sua so luong");
                int newSL= sc.nextInt();
                while(newSL < 0){
                    System.out.println("Vui long nhap lai! ");
                    newSL= sc.nextInt();
                }
                arr[i].setSoluong(newSL);
                sc.nextLine();
            }
            WriteFile();
        }
    }
    public void Suasl(Scanner sc, Chitiethoaodonban[] arr, int count){
        System.out.println("Nhap ma san pham can sua:");
        String MaSP= sc.nextLine();
        SanPham sp= dssp.TimKiem(MaSP);
        while(sp==null){
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP= sc.nextLine();
            sp= dssp.TimKiem(MaSP);
        }
        for(int i=0; i< count; i++){
            if(MaSP.equals(arr[i].getSP().getMaSP())){
                System.out.println("Sua so luong cua san pham ");
                int newSL= sc.nextInt();
                arr[i].setSL(newSL);
                sc.nextLine();
            }
        }
        WriteFile();
    }
        // --------------Xoa ------------------
    public void Xoa(Scanner sc){
        System.out.println("Nhap ma hoa don can xoa:");
        String MaHD= sc.nextLine();
        Hoadonban hd = dshd.TimKiem(MaHD);
        while( hd ==null){
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD= sc.nextLine();
            hd = dshd.TimKiem(MaHD);
        }

        Chitiethoaodonban[] arr=  new Chitiethoaodonban[0];
        int count=0;
        for(int i=0; i<n; i++){
            if(MaHD.equals(ds[i].getHDB().getMaHD())){
                ds[i].Xuat();
                arr= Arrays.copyOf(arr, count +1);
                arr[count]=ds[i];
                count++;
            }
        }
        int choice;
        do{
            System.out.println("1.Toan bo:");
            System.out.println("2.Xoa san pham:");
            System.out.println("3. Thoat:");
            System.out.println("Vui lòng chọn:");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: XoaTB(sc, MaHD);
                        break;
                case 2: Xoasphd(sc, arr, count);
                        break;
                case 3: System.out.println("Thoat man hình");
                        break
            }

        }while(choice!=4);
    }
    public void XoaTB(Scanner sc, String MaHD ){
        boolean bool=false;        
        for(int i=0; i< n; ){
                if(ds[i].getHDB().getMaHD().equals(MaHD)){
                    for(int j=i; j<n-1;j++){
                        ds[j]=ds[j+1];
                }
                    ds = Arrays.copyOf(ds, n-1);
                    n--;
                    bool=true;
                }
                else
                    i++;
            }
        if(bool)
                System.out.println("Xoa thanh cong");
            else
                System.out.println("That bai!");
        WriteFile();
    }

    public void Xoasphd(Scanner sc, Chitiethoaodonban[] arr, int count){
        boolean bool=false;
        System.out.println("Nhap ma san pham can xoa:");
        String MaSP= sc.nextLine();
        Sanpham sp= dssp.Timkiem(MaSP);
        while(sp==null){
            System.out.println("Khong tim thay san pham: " + MaSP);
            System.out.println("Vui long nhap lai! ");
            MaSP= sc.nextLine();
            sp= dssp.TimKiem(MaSP);
        }
        for(int i=0; i< count; i++){
            if(MaSP.equals(arr[i].getSP().getMaSP())){
                System.out.println("Sua ma sp");
                String MaSP= sc.nextLine();
                Sanpham sp= dssp.TimKiem(MaSP); 
                while(sp == null){
                    System.out.println("Nhap lai ma san pham can xoa");
                    MaSP= sc.nextLine();
                    sp= dssp.TimKiem(MaSP); 
                }
                
                for(int i=0; i< n;){
                    if(ds[i].getHDB().getMaHD().equals(MaHD) && ds[i].getSP().getMaSP().equals(MaSP)){
                        for(int j=i; j< n-1;j++){
                            ds[j]=ds[j+1];
                        }
                        bool=true;
                        ds = Arrays.copyOf(ds, n-1);
                        n--;
                    }
                    else
                    i++;
                }
            }

            if(bool)
                System.out.println("Xoa thanh cong");
            else
                System.out.println("That bai!");
            WriteFile();
        }       
    }
        //----------------Tìm kiếm---------------------------
    public void TimKiem(Scanner sc){
        System.out.println("Nhap ma hoa don:");
        String MaHD= sc.nextLine();
        Hoadonban hd = dshd.TimKiem(MaHD);
        while( hd ==null){
            System.out.println("Khong tim thay chi tiet hoa don: " + MaHD);
            System.out.println("Vui long nhap lai! ");
            MaHD= sc.nextLine();
            hd = dshd.TimKiem(MaHD);
        }
        for(int i=0; i<n; i++){
            if(MaHD.equals(ds[i].getHDB().getMaHD())){
                ds[i].Xuat();
            }
        }
    }
}

public class QL_MENU_CTHD{
    DSCT ds1= new DSCT();
    public void menu_cthd(Scanner sc){
        int choice;
        do{
            System.out.println("===========QUAN LY CHI TIET HOA DON============");
            System.out.println("1.Them chi tiet hoa don.");
            System.out.println("2.Sua chi tiet hoa don.");
            System.out.println("3.Xoa chi tiet hoa don.");
            System.out.println("4.Tim kiem chi tiet hoa don.");
            System.out.println("5.Thoat.");
            System.out.println("Vui long chon:");
            choice= sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1: ds1.Them(sc);
                        break;
                case 2: ds1.Sua(sc);
                        break;
                case 3: ds1.Xoa(sc);
                        break;
                case 4: ds1.TimKiem(sc);
                        break;
                case 5: System.out.println("Thoat chuong trinh!");
                        break;
                default: System.out.println("Vui long chon lai!");
                         break;
            }
        }while(choice !=5);
    }
}

