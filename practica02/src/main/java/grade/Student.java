package grade;

import java.io.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Student implements Serializable {
    private String matricula;
    private String nombre;
    private String apellido;
    private Carrera carrera;
    private int semestre;
    private double promedio;
    private Estado estado;
    private Genero genero;
    private String correo;

    //Metodo getters y setters
    public Student(String matricula, String nombre, String apellido, Carrera carrera,
                   int semestre, double promedio, Estado estado, Genero genero, String correo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.semestre = semestre;
        this.promedio = promedio;
        this.estado = estado;
        this.genero = genero;
        this.correo = correo;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }

    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public boolean coincide(int filtro, String valor) {
        valor = valor.toLowerCase();
        return switch (filtro) {
            case 1 -> nombre.toLowerCase().contains(valor);
            case 2 -> apellido.toLowerCase().contains(valor);
            case 3 -> carrera.name().toLowerCase().contains(valor);
            case 4 -> String.valueOf(semestre).equals(valor);
            case 5 -> String.valueOf(promedio).equals(valor);
            case 6 -> estado.name().toLowerCase().contains(valor);
            case 7 -> genero.name().toLowerCase().contains(valor);
            case 8 -> correo.toLowerCase().contains(valor);
            default -> false;
        };
    }

     //Metodo toString
    @Override
    public String toString() {
        return "Matricula: " + matricula +
               ", Nombre: " + nombre +
               ", Apellido: " + apellido +
               ", Carrera: " + carrera +
               ", Semestre: " + semestre +
               ", Promedio: " + promedio +
               ", Estado: " + estado +
               ", Genero: " + genero +
               ", Correo: " + correo;
    }

    // Metodo toJson
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
