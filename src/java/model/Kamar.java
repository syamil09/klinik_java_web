/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Kamar {
    private String idKamar;
    private String namaRuang;
    private String noKamar;
    private String kelas;
    private Double hargaPerhari;
    private String desKamar;
    private Integer kapasitas;
    private Integer terisi;
    private String status;

    public String getIdKamar() {
        return idKamar;
    }

    public void setIdKamar(String idKamar) {
        this.idKamar = idKamar;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Double getHargaPerhari() {
        return hargaPerhari;
    }

    public void setHargaPerhari(Double hargaPerhari) {
        this.hargaPerhari = hargaPerhari;
    }

    public String getDesKamar() {
        return desKamar;
    }

    public void setDesKamar(String desKamar) {
        this.desKamar = desKamar;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Integer getTerisi() {
        return terisi;
    }

    public void setTerisi(Integer terisi) {
        this.terisi = terisi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
