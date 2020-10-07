package com.edu.fpoly.bookmanager.model;

public class TheLoai {
    private String maTL,tenTL,moTa,viTri;

    public TheLoai() {
    }

    public TheLoai(String maTL, String tenTL, String moTa, String viTri) {
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}
