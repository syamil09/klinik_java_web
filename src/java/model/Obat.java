/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author syamil imdad
 */
public class Obat {
//    varchar : String
//    decimal : Double
//    date/timestamp : String
    private String idObat;
    private String namaObat;
    private String satuan;
    private Double stok;
    private Double hargaJual;

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Double getStok() {
        return stok;
    }

    public void setStok(Double stok) {
        this.stok = stok;
    }

    public Double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Double hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    private String waktu;
    private String idUser;
}
