package hn.uth.app.modelos;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String identidad;
    private String nombre;
    private String email;
    private String telefono;
    private List<Pelicula> peliculasFavoritas;

    public Cliente(String identidad, String nombre, String email, String telefono) {
        this.identidad = identidad;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.peliculasFavoritas = new ArrayList<>();
    }

    public String getIdentidad() {
        return identidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Pelicula> getPeliculasFavoritas() {
        return peliculasFavoritas;
    }

    public void agregarPeliculaFavorita(Pelicula pelicula) {
        peliculasFavoritas.add(pelicula);
    }

    @Override
    public String toString() {
        return nombre + " (" + identidad + ")";
    }
}
