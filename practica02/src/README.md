# Sistema de Gestión de Estudiantes (Grade)

Este proyecto permite **registrar, eliminar, actualizar y consultar** estudiantes utilizando archivos **JSON** para guardar la información de manera persistente.  
El programa maneja datos como matrícula, nombre, carrera, semestre, promedio, estado, género y correo.

---

## Métodos Implementados

### Clase `Student`
Contiene los atributos del estudiante y los métodos `get`/`set` para cada campo.  
Además, implementa:

- **`toString()`** → Devuelve toda la información del estudiante como texto legible.  
- **`coincide(int filtro, String valor)`** → Permite buscar estudiantes según nombre, apellido, carrera, semestre, promedio, estado, género o correo.  
- Implementa la interfaz `Serializable` para poder ser almacenado fácilmente.

### Clase `StudentManager`
Controla las operaciones sobre la lista de estudiantes:
- **`agregar(Student s)`** → Agrega un nuevo estudiante y actualiza el archivo JSON.  
- **`eliminar(String matricula)`** → Elimina un estudiante por su matrícula (actualiza el archivo).  
- **`buscar(int filtro, String valor)`** → Devuelve los estudiantes que coincidan con el criterio.  
- **`guardarEnArchivo()` / `cargarDesdeArchivo()`** → Guardan y leen todos los datos desde `students.json` automáticamente.

---

## EJEMPLO JSON

El archivo `students.json` guarda los datos con esta estructura:

```json
[
  {
    "matricula": "00000",
    "nombre": "Luis",
    "apellido": "Martinez",
    "carrera": "SISTEMAS",
    "semestre": 5,
    "promedio": 8.7,
    "estado": "ACTIVO",
    "genero": "MASCULINO",
    "correo": "al000000@uacam.com"
  }
]
