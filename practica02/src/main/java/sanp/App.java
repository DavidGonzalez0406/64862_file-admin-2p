package sanp;

import grade.Student;
import grade.Carrera;
import grade.Estado;
import grade.Genero;
import operations.Writing;
import operations.Reading;
import operations.Delete;

import java.util.*;
import java.io.*;

public class App {
    private static final String TXT_FILE = "alumnos.txt";
    private static final String JSON_FILE = "alumnos.json";
    private static final String BIN_FILE = "alumnos.dat";

    private static List<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final Writing writer = new Writing();
    private static final Reading reader = new Reading();
    private static final Delete deleter = new Delete(); 

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Mostrar alumnos");
            System.out.println("2. Agregar alumno");
            System.out.println("3. Actualizar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Buscar alumnos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String op = sc.nextLine();

            switch (op) {
                case "1" -> mostrarMenu();
                case "2" -> agregarAlumno();
                case "3" -> actualizarAlumno();
                case "4" -> eliminarMenu();
                case "5" -> buscarMenu();
                case "6" -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    // -------------------- MENU MOSTRAR --------------------
    private static void mostrarMenu() {
        System.out.println("\n1. Mostrar alumnos (TXT)");
        System.out.println("2. Mostrar alumnos (JSON)");
        System.out.println("3. Mostrar alumnos (Binario)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "1" -> students = reader.readTxt(TXT_FILE);
            case "2" -> students = reader.readJson(JSON_FILE);
            case "3" -> students = reader.readBin(BIN_FILE);
            default -> {
                System.out.println("Opción inválida");
                return;
            }
        }

        if (students.isEmpty()) System.out.println("No hay alumnos registrados.");
        else students.forEach(System.out::println);
    }

    // -------------------- AGREGAR --------------------
    private static void agregarAlumno() {
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        // Mostrar valores reales del enum Carrera
        System.out.println("Carreras disponibles:");
        for (Carrera c : Carrera.values()) {
            System.out.println("- " + c);
        }
        System.out.print("Carrera: ");
        Carrera carrera = Carrera.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Semestre: ");
        int semestre = Integer.parseInt(sc.nextLine());

        System.out.print("Promedio: ");
        double promedio = Double.parseDouble(sc.nextLine());

        System.out.println("Estados disponibles:");
        for (Estado e : Estado.values()) {
            System.out.println("- " + e);
        }
        System.out.print("Estado: ");
        Estado estado = Estado.valueOf(sc.nextLine().toUpperCase());

        System.out.println("Géneros disponibles:");
        for (Genero g : Genero.values()) {
            System.out.println("- " + g);
        }
        System.out.print("Género: ");
        Genero genero = Genero.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        Student s = new Student(matricula, nombre, apellido, carrera, semestre, promedio, estado, genero, correo);
        students.add(s);

        writer.createTxt(TXT_FILE, students);
        writer.createJson(JSON_FILE, students);
        writer.createBin(BIN_FILE, students);

        System.out.println("✅ Alumno agregado correctamente.");
    }

    // -------------------- ACTUALIZAR --------------------
    private static void actualizarAlumno() {
        System.out.print("Ingrese matrícula del alumno a actualizar: ");
        String matricula = sc.nextLine();

        for (Student s : students) {
            if (s.getMatricula().equals(matricula)) {
                System.out.print("Nombre (" + s.getNombre() + "): ");
                String nombre = sc.nextLine();
                if (!nombre.isEmpty()) s.setNombre(nombre);

                System.out.print("Apellido (" + s.getApellido() + "): ");
                String apellido = sc.nextLine();
                if (!apellido.isEmpty()) s.setApellido(apellido);

                System.out.println("Carreras disponibles:");
                for (Carrera c : Carrera.values()) {
                    System.out.println("- " + c);
                }
                System.out.print("Carrera (" + s.getCarrera() + "): ");
                String carrera = sc.nextLine();
                if (!carrera.isEmpty()) s.setCarrera(Carrera.valueOf(carrera.toUpperCase()));

                System.out.print("Semestre (" + s.getSemestre() + "): ");
                String sem = sc.nextLine();
                if (!sem.isEmpty()) s.setSemestre(Integer.parseInt(sem));

                System.out.print("Promedio (" + s.getPromedio() + "): ");
                String prom = sc.nextLine();
                if (!prom.isEmpty()) s.setPromedio(Double.parseDouble(prom));

                System.out.println("Estados disponibles:");
                for (Estado e : Estado.values()) {
                    System.out.println("- " + e);
                }
                System.out.print("Estado (" + s.getEstado() + "): ");
                String estado = sc.nextLine();
                if (!estado.isEmpty()) s.setEstado(Estado.valueOf(estado.toUpperCase()));

                System.out.println("Géneros disponibles:");
                for (Genero g : Genero.values()) {
                    System.out.println("- " + g);
                }
                System.out.print("Género (" + s.getGenero() + "): ");
                String genero = sc.nextLine();
                if (!genero.isEmpty()) s.setGenero(Genero.valueOf(genero.toUpperCase()));

                System.out.print("Correo (" + s.getCorreo() + "): ");
                String correo = sc.nextLine();
                if (!correo.isEmpty()) s.setCorreo(correo);

                writer.createTxt(TXT_FILE, students);
                writer.createJson(JSON_FILE, students);
                writer.createBin(BIN_FILE, students);
                System.out.println("✅ Alumno actualizado.");
                return;
            }
        }
        System.out.println("⚠ No se encontró alumno con esa matrícula.");
    }

    // -------------------- ELIMINAR --------------------
    private static void eliminarMenu() {
        System.out.println("\n--- ELIMINAR ALUMNO ---");
        System.out.println("1. Eliminar de TXT");
        System.out.println("2. Eliminar de JSON");
        System.out.println("3. Eliminar de BINARIO");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        System.out.print("Ingrese la matrícula del alumno a eliminar: ");
        String matricula = sc.nextLine();

        switch (op) {
            case "1" -> deleter.deleteTxt(TXT_FILE, matricula);
            case "2" -> deleter.deleteJson(JSON_FILE, matricula);
            case "3" -> deleter.deleteBin(BIN_FILE, matricula);
            default -> System.out.println("Opción inválida.");
        }
    }

    // -------------------- BUSCAR --------------------
    private static void buscarMenu() {
        System.out.println("1. Usar alumnos (TXT)");
        System.out.println("2. Usar alumnos (JSON)");
        System.out.println("3. Usar alumnos (Binario)");
        System.out.print("Seleccione: ");
        String op = sc.nextLine();

        switch (op) {
            case "1" -> students = reader.readTxt(TXT_FILE);
            case "2" -> students = reader.readJson(JSON_FILE);
            case "3" -> students = reader.readBin(BIN_FILE);
            default -> {
                System.out.println("Opción inválida");
                return;
            }
        }

        System.out.println("\nBuscar por:");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Carrera");
        System.out.println("4. Semestre");
        System.out.println("5. Promedio");
        System.out.println("6. Estado");
        System.out.println("7. Género");
        System.out.println("8. Correo");
        System.out.print("Seleccione: ");
        int filtro = Integer.parseInt(sc.nextLine());

        System.out.print("Valor a buscar: ");
        String valor = sc.nextLine().toLowerCase();

        students.stream()
                .filter(s -> s.coincide(filtro, valor))
                .forEach(System.out::println);
    }
}
