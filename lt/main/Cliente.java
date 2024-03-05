public class Cliente {
    private String nombre;
    private String apellido;
    private String correo;
    private boolean isAdmin;

    // Constructor
    public Cliente(String nombre, String apellido, String correo, boolean isAdmin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.isAdmin = isAdmin;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
}