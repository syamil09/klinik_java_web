/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nicho
 */
public class RawatInap {
    private String id_rawat;
    private String id_pasien;
    private String id_kamar;
    private String nama_ruang;
    private String tgl_cekin;
    private String tgl_cekout;
    private String keterangan;

    public String getId_rawat() {
        return id_rawat;
    }

    public void setId_rawat(String id_rawat) {
        this.id_rawat = id_rawat;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getNama_ruang() {
        return nama_ruang;
    }

    public void setNama_ruang(String nama_ruang) {
        this.nama_ruang = nama_ruang;
    }

    public String getTgl_cekin() {
        return tgl_cekin;
    }

    public void setTgl_cekin(String tgl_cekin) {
        this.tgl_cekin = tgl_cekin;
    }

    public String getTgl_cekout() {
        return tgl_cekout;
    }

    public void setTgl_cekout(String tgl_cekout) {
        this.tgl_cekout = tgl_cekout;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
