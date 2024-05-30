package model;

public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private Mazo[] mazos;
    private int cantidadMazos;


    public Usuario(String nombreUsuario, String contrasenia, Mazo[] mazos, int cantidadMazos) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mazos = mazos;
        this.cantidadMazos = 0;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Mazo[] getMazos() {
        return mazos;
    }

    public void setMazos(Mazo[] mazos) {
        this.mazos = mazos;
    }

    public int getCantidadMazos() {
        return cantidadMazos;
    }

    public void setCantidadMazos(int cantidadMazos) {
        this.cantidadMazos = cantidadMazos;
    }
}
