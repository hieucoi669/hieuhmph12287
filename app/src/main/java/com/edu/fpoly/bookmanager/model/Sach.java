package com.edu.fpoly.bookmanager.model;

public class Sach {
    private String masach;
    private String tensach;
    private String tentheloai;
    private String tacgia;
    private String nxb;
    private String giabia;
    private String soluong;

    public Sach(String masach, String tensach, String tentheloai, String tacgia, String nxb, String giabia, String soluong) {
        this.masach = masach;
        this.tensach = tensach;
        this.tentheloai = tentheloai;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public Sach() {
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
