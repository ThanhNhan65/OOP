package code.danhsach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import code.doituong.*;
import code.kiemtra.InputUtils;

public class DanhSachLoai {
    private Loai[] ds;
    private int n;

    public DanhSachLoai() {
        ds = new Loai[0];
        n = 0;
    }

    public void docFile() {
        int maxid = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/loai.txt"));
            String line = br.readLine();

            while (line != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] a = line.split(",", -1);
                    if (a.length >= 2) {
                        String ma = a[0].trim();
                        int id = Integer.parseInt(ma.substring(1));
                        String ten = a[1].trim();
                        if (!ma.isEmpty() && !TonTaiMa(ma)) {
                            Loai l = new Loai(ma, ten);
                            ds = Arrays.copyOf(ds, n + 1);
                            ds[n++] = l;

                            if (id > maxid)
                                maxid = id;
                        }
                    }
                }
                line = br.readLine();
            }
            br.close();
            Loai.setmanext(maxid + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ghiFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/loai.txt"));
            for (int i = 0; i < n; i++) {
                Loai l = ds[i];
                if (l == null)
                    continue;
                String ma = (l.getMaloai() == null ? "" : l.getMaloai());
                String ten = (l.getTenloai() == null ? "" : l.getTenloai());
                bw.write(ma + "," + ten);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Them(Scanner sc) {
        Loai l = new Loai();
        l.Nhap(sc);
        String ma = l.getMaloai();
        if (ma == null || ma.trim().isEmpty()) {
            System.out.println("Ma khong hop le");
            return;
        }
        if (TonTaiMa(ma)) {
            System.out.println("Ma nay da ton tai");
            return;
        }
        ds = Arrays.copyOf(ds, n + 1);
        ds[n++] = l;
        System.out.println("Da them loai");
        ghiFile();
    }

    private boolean TonTaiMa(String ma) {
        if (ma == null || ma.isEmpty())
            return false;
        for (int i = 0; i < n; i++) {
            Loai l = ds[i];
            if (l != null && ma.equalsIgnoreCase(l.getMaloai()))
                return true;
        }
        return false;
    }

    public void Xem() {
        if (n == 0) {
            System.out.println("Danh sach loai rong");
            return;
        }
        System.out.printf("%-10s %-20s%n", "MaLoai", "TenLoai");
        for (int i = 0; i < n; i++) {
            Loai l = ds[i];
            if (l != null)
                l.Xuat();
        }
    }

    public void Xoa(Scanner sc) {
        int c;
        do {
            System.out.println("1. Xoa loai theo ma");
            System.out.println("2. Xoa toan bo loai");
            System.out.println("0. Thoat");
            c = sc.nextInt();
            sc.nextLine();
            switch (c) {
                case 1: {
                    System.out.print("Nhap ma loai muon xoa: ");
                    String ma = sc.nextLine().trim();
                    if (InputUtils.ThoatNeuEnter(ma))
                        return;
                    boolean ok = XoaTheoMa(ma);
                    System.out.println(ok ? "Da xoa" : "Khong thay ma nay");
                    break;
                }
                case 2: {
                    System.out.println("Hien co " + n + " loai");
                    System.out.println("Nhap OK de xac nhan xoa het:");
                    if ("OK".equalsIgnoreCase(sc.nextLine().trim())) {
                        XoaTatCa();
                        System.out.println("Da xoa tat ca");
                    }
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Khong hop le");
            }
        } while (c != 0);
    }

    private boolean XoaTheoMa(String ma) {
        if (ma == null)
            return false;
        for (int i = 0; i < n; i++) {
            if (ds[i] != null && ma.equalsIgnoreCase(ds[i].getMaloai())) {
                for (int j = i; j < n - 1; j++)
                    ds[j] = ds[j + 1];
                ds[n - 1] = null;
                n--;
                ds = Arrays.copyOf(ds, n);
                ghiFile();
                return true;
            }
        }
        return false;
    }

    private void XoaTatCa() {
        ds = new Loai[0];
        n = 0;
        ghiFile();
    }

    public void Sua(Scanner sc) {
        System.out.print("Nhap ma loai muon sua: ");
        String ma = sc.nextLine().trim();
        if (InputUtils.ThoatNeuEnter(ma))
            return;
        Loai l = TimTheoMa(ma);
        if (l == null) {
            System.out.println("Khong tim thay");
            return;
        }
        int c;
        do {
            System.out.println("Dang sua [" + l.getMaloai() + "]");
            System.out.println("1. Sua ma");
            System.out.println("2. Sua ten");
            System.out.println("3. Sua toan bo");
            System.out.println("0. Thoat");
            c = sc.nextInt();
            sc.nextLine();
            switch (c) {
                case 1:
                    SuaMa(sc, l);
                    break;
                case 2:
                    SuaTen(sc, l);
                    break;
                case 3:
                    SuaToanBo(sc, l);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Khong hop le");
            }
        } while (c != 0);
    }

    private void SuaMa(Scanner sc, Loai l) {
        System.out.println("Ma hien tai: " + l.getMaloai());
        System.out.print("Ma moi: ");
        String m = sc.nextLine().trim();
        if (m.isEmpty()) {
            System.out.println("Khong hop le");
            return;
        }
        if (TonTaiMa(m)) {
            System.out.println("Ma da ton tai");
            return;
        }
        l.setMaloai(m);
        ghiFile();
    }

    private void SuaTen(Scanner sc, Loai l) {
        System.out.println("Ten hien tai: " + l.getTenloai());
        System.out.print("Ten moi: ");
        String t = sc.nextLine().trim();
        if (t.isEmpty()) {
            System.out.println("Khong hop le");
            return;
        }
        l.setTenloai(t);
        ghiFile();
    }

    private void SuaToanBo(Scanner sc, Loai l) {
        System.out.println("Ma hien tai: " + l.getMaloai());
        System.out.print("Ma moi: ");
        String m = sc.nextLine().trim();
        if (!m.isEmpty()) {
            if (TonTaiMa(m))
                System.out.println("Ma da ton tai");
            else
                l.setMaloai(m);
        }

        System.out.println("Ten hien tai: " + l.getTenloai());
        System.out.print("Ten moi: ");
        String t = sc.nextLine().trim();
        if (!t.isEmpty())
            l.setTenloai(t);

        ghiFile();
    }

    public void TimKiem(Scanner sc) {
        int c;
        do {
            System.out.println("1. Tim theo ma");
            System.out.println("2. Tim theo ten (chua chuoi)");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1: {
                    System.out.print("Nhap MA loai: ");
                    String ma = sc.nextLine().trim();
                    Loai l = TimTheoMa(ma);
                    if (l == null) {
                        System.out.println("Khong tim thay!");
                    } else {
                        System.out.printf("%-10s %-20s%n", "MaLoai", "TenLoai");
                        l.Xuat();
                    }
                    break;
                }
                case 2: {
                    System.out.print("Nhap TEN can tim: ");
                    String ten = sc.nextLine().trim();
                    int d = TimTheoTen(ten);
                    if (d == 0)
                        System.out.println("Khong tim thay!");
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Khong hop le!");
            }
        } while (c != 0);
    }

    public Loai TimTheoMa(String ma) {
        if (ma == null)
            return null;
        for (int i = 0; i < n; i++) {
            Loai l = ds[i];
            if (l != null && l.getMaloai().equals(ma))
                return l;
        }
        return null;
    }

    public int TimTheoTen(String ten) {
        if (ten == null)
            return 0;
        String k = ten.toLowerCase();
        int d = 0;
        for (int i = 0; i < n; i++) {
            Loai l = ds[i];
            if (l != null && l.getTenloai() != null && l.getTenloai().toLowerCase().contains(k)) {
                l.Xuat();
                d++;
            }
        }
        return d;
    }

    public int getN() {
        return n;
    }

}