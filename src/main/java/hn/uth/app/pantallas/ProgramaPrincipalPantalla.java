package hn.uth.app.pantallas;

import hn.uth.app.modelos.Cliente;
import hn.uth.app.modelos.Pelicula;
import hn.uth.app.constantes.Constantes;
import hn.uth.app.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramaPrincipalPantalla {
    private List<Pelicula> peliculas = new ArrayList<>();
    private Map<String, Cliente> clientes = new HashMap<>();

    public void iniciarPrograma() {
        boolean continuar = true;
        while (continuar) {
            printMenuPrincipal();
            int opcion = Util.scanInt("¿Qué acción desea realizar (1-5)? R// ");
            switch (opcion) {
                case 1:
                    agregarPelicula();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    verPeliculasFavoritasPorCliente();
                    break;
                case 4:
                    verPeliculasMasPreferidas();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    Util.print("Opción no válida, por favor intente de nuevo.");
            }
        }
        Util.print("Programa finalizado..... adios :)");
    }

    private void printMenuPrincipal() {Util.print("");
        Util.print("");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("|||                         MENU NEFLI                     |||");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("1 – Agregar Película al Catálogo");
        Util.print("2 – Agregar Cliente");
        Util.print("3 – Ver Películas Favoritas por Cliente");
        Util.print("4 – Ver 5 Películas Favoritas Más Preferidas");
        Util.print("5 – Salir");
    }

    private void agregarPelicula() {
        Util.print("");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("|||             Agregar Película al Catálogo               |||");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("");
        String titulo = Util.scanString("Título: ");
        int anio = Util.scanInt("Año: ");
        Util.print("");
        String resumen = Util.scanString("Resumen: ");
        Util.print("");
        String tipo = Util.scanString("Tipo: ");
        Util.print("");
        peliculas.add(new Pelicula(titulo, anio, resumen, tipo));
        Util.print("");
        Util.print("Película agregada con éxito.");
    }

    private void agregarCliente() {
        Util.print("");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("|||                    Agregar Cliente                     |||");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("");
        Util.print("");
        String identidad = Util.scanString("Identidad: ");
        Util.print("");
        String nombre = Util.scanString("Nombre: ");
        Util.print("");
        String email = Util.scanString("Email: ");
        Util.print("");
        String telefono = Util.scanString("Teléfono: ");
        Util.print("");
        Cliente cliente = new Cliente(identidad, nombre, email, telefono);
        Util.print("");
        clientes.put(identidad, cliente);
        Util.print("");
        agregarPeliculasFavoritas(cliente);
        Util.print("");
        Util.print("Cliente agregado con éxito.");
    }

    private void agregarPeliculasFavoritas(Cliente cliente) {
        boolean continuar = true;
        while (continuar) {
            Util.print("");
            Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            Util.print("|||             Agregar Películas Favoritas                |||");
            Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            for (int i = 0; i < peliculas.size(); i++) {
                Util.print((i + 1) + " - " + peliculas.get(i).toString());
            }
            Util.print("");
            String entrada = Util.scanString("Seleccione la película favorita (0 para terminar): ");
            if (entrada.isEmpty()) {
                continuar = false;
            } else {
                int opcion;
                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    Util.print("");
                    Util.print("Opción no válida, por favor intente de nuevo.");
                    continue;
                }
                if (opcion == 0) {
                    continuar = false;
                } else if (opcion > 0 && opcion <= peliculas.size()) {
                    cliente.agregarPeliculaFavorita(peliculas.get(opcion - 1));
                    Util.print("");
                    Util.print("Película agregada a favoritos.");
                } else {
                    Util.print("Opción no válida, por favor intente de nuevo.");
                }
            }
        }
    }

    private void verPeliculasFavoritasPorCliente() {
        Util.print("");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("|||         Ver Películas Favoritas por Cliente            |||");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("");
        String identidad = Util.scanString("Identidad del Cliente: ");
        Cliente cliente = clientes.get(identidad);
        if (cliente != null) {
            Util.print("");
            Util.print(cliente.getNombre() + " - Películas Favoritas:");
            for (Pelicula pelicula : cliente.getPeliculasFavoritas()) {
                Util.print("");
                Util.print(pelicula.toString());
            }
        } else {
            Util.print("");
            Util.print("Cliente no encontrado.");
        }
    }

    private void verPeliculasMasPreferidas() {
        Util.print("");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Util.print("|||       Ver 5 Películas Favoritas Más Preferidas         |||");
        Util.print("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        Map<Pelicula, Integer> conteoPeliculas = new HashMap<>();
        for (Cliente cliente : clientes.values()) {
            for (Pelicula pelicula : cliente.getPeliculasFavoritas()) {
                conteoPeliculas.put(pelicula, conteoPeliculas.getOrDefault(pelicula, 0) + 1);
            }
        }
        conteoPeliculas.entrySet().stream()
                .sorted(Map.Entry.<Pelicula, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> Util.print(entry.getKey() + " - " + entry.getValue() + " votos"));
    }
}
