package operations;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import grade.Student;


public class Delete {

    // Eliminar alumno del archivo JSON
    public void deleteJson(String fileName, String matricula) {
        try (Reader reader = new FileReader(fileName)) {
            List<Student> students = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());

            if (students == null) {
                System.out.println("⚠ No hay alumnos registrados.");
                return;
            }

            boolean removed = students.removeIf(s -> s.getMatricula().equalsIgnoreCase(matricula));

            if (removed) {
                try (Writer writer = new FileWriter(fileName)) {
                    new Gson().toJson(students, writer);
                    System.out.println("✅ Alumno eliminado correctamente del JSON.");
                }
            } else {
                System.out.println("⚠ No se encontró la matrícula especificada.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar del JSON: " + e.getMessage());
        }
    }

    // Eliminar alumno del archivo TXT
    public void deleteTxt(String fileName, String matricula) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            boolean removed = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(matricula)) {
                    removed = true;
                    continue;
                }
                lines.add(line);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            }

            if (removed) System.out.println("✅ Alumno eliminado correctamente del TXT.");
            else System.out.println("⚠ No se encontró la matrícula en el TXT.");

        } catch (IOException e) {
            System.out.println("❌ Error al eliminar del TXT: " + e.getMessage());
        }
    }

    // Eliminar alumno del archivo BIN
    public void deleteBin(String fileName, String matricula) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Student> students = (List<Student>) in.readObject();

            boolean removed = students.removeIf(s -> s.getMatricula().equalsIgnoreCase(matricula));

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.writeObject(students);
            }

            if (removed) System.out.println("✅ Alumno eliminado correctamente del BIN.");
            else System.out.println("⚠ No se encontró la matrícula en el BIN.");

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar del BIN: " + e.getMessage());
        }
    }
}
