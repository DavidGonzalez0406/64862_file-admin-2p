package operations;

import grade.Student;
import grade.Carrera;
import grade.Estado;
import grade.Genero;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Reading {

    // ---------- Leer TXT ----------
    public List<Student> readTxt(String filePath) {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("El archivo TXT no existe todavía.");
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Formato del Student.toString()
                // Matricula: x, Nombre: x, Apellido: x, Carrera: x, Semestre: x, Promedio: x, Estado: x, Genero: x, Correo: x
                String[] data = line.split(", ");
                if (data.length == 9) {
                    try {
                        String matricula = data[0].split(": ")[1];
                        String nombre = data[1].split(": ")[1];
                        String apellido = data[2].split(": ")[1];
                        Carrera carrera = Carrera.valueOf(data[3].split(": ")[1]);
                        int semestre = Integer.parseInt(data[4].split(": ")[1]);
                        double promedio = Double.parseDouble(data[5].split(": ")[1]);
                        Estado estado = Estado.valueOf(data[6].split(": ")[1]);
                        Genero genero = Genero.valueOf(data[7].split(": ")[1]);
                        String correo = data[8].split(": ")[1];

                        students.add(new Student(
                            matricula, nombre, apellido, carrera,
                            semestre, promedio, estado, genero, correo
                        ));
                    } catch (Exception e) {
                        System.out.println("Error al procesar línea: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo TXT: " + e.getMessage());
        }
        return students;
    }

    // ---------- Leer JSON ----------
    public List<Student> readJson(String jsonFile) {
        List<Student> students = new ArrayList<>();
        File file = new File(jsonFile);

        if (!file.exists()) {
            System.out.println("El archivo JSON no existe todavía.");
            return students;
        }

        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Student>>() {}.getType();
            students = gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
        return students;
    }

    // ---------- Leer BIN ----------
    public List<Student> readBin(String filePath) {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("El archivo BIN no existe todavía.");
            return students;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error leyendo BIN: " + e.getMessage());
        }
        return students;
    }
}
