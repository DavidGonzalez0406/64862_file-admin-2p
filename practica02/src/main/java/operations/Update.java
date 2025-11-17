package operations;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import grade.Student;

public class Update {

    // Actualizar alumno en JSON
    public void updateJson(String fileName, Student updatedStudent) {
        try (Reader reader = new FileReader(fileName)) {
            List<Student> students = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());

            boolean found = false;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getMatricula().equals(updatedStudent.getMatricula())) {
                    students.set(i, updatedStudent);
                    found = true;
                    break;
                }
            }

            if (found) {
                try (Writer writer = new FileWriter(fileName)) {
                    new Gson().toJson(students, writer);
                    System.out.println("✅ Alumno actualizado correctamente en JSON.");
                }
            } else {
                System.out.println("⚠ Alumno no encontrado en el archivo JSON.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar JSON: " + e.getMessage());
        }
    }

    // Actualizar alumno en TXT
    public void updateTxt(String fileName, List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("✅ Archivo TXT actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al actualizar TXT: " + e.getMessage());
        }
    }

    // Actualizar alumno en binario
    public void updateBin(String fileName, List<Student> students) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
            System.out.println("✅ Archivo BIN actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al actualizar BIN: " + e.getMessage());
        }
    }

}
