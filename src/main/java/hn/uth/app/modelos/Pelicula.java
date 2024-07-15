package hn.uth.app.modelos;

public class Pelicula {
    private String titulo;
    private int anio;
    private String resumen;
    private String tipo;

    public Pelicula(String titulo, int anio, String resumen, String tipo) {
        this.titulo = titulo;
        this.anio = anio;
        this.resumen = resumen;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getResumen() {
        return resumen;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return titulo + " (" + anio + ")";
    }
}
