package id.co.rinaano.saturney2;

class DataFilter {

    private String Nama;
    private String Detail;
    private String Harga;

    DataFilter(String Nama, String Detail, String Harga) {
        this.Nama = Nama;
        this.Detail = Detail;
        this.Harga = Harga;
    }

    String getNama() {
        return Nama;
    }

    public String getDetail() {
        return Detail;
    }

    public String getHarga() {
        return Harga;
    }
}