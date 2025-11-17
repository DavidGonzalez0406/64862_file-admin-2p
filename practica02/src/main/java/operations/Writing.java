package operations;

import grade.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Writing {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // ------------------ Crear TXT ------------------
    public void createTxt(String fileName, List<Student> students) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Student s : students) {
                writer.write(s.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir TXT: " + e.getMessage());
        }
    }

    // ------------------ Crear JSON ------------------
    public void createJson(String fileName, List<Student> students) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(students, writer);

            System.out.println("\n📄 Archivo JSON generado correctamente: " + fileName);
            System.out.println("----- CONTENIDO JSON -----");
            for (Student s : students) {
                System.out.println(s.toJson());
            }
            System.out.println("--------------------------\n");

        } catch (IOException e) {
            System.out.println("Error al escribir JSON: " + e.getMessage());
        }
    }

    // ------------------ Crear BIN ------------------
    public void createBin(String fileName, List<Student> students) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error al escribir BIN: " + e.getMessage());
        }
    }
}
