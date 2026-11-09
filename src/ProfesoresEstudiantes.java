import java.util.ArrayList;
import java.util.Scanner;

public class ProfesoresEstudiantes {

    // Variables generales del sistema
    private final Scanner entrada;
    private final ArrayList<String> listaProfesores;
    private final ArrayList<ArrayList<String>> listaGeneralEstudiantes;
    private boolean estudiantesRegistrados;

    // Constructor
    public ProfesoresEstudiantes() {

        entrada = new Scanner(System.in);

        listaProfesores = new ArrayList<>();

        listaGeneralEstudiantes = new ArrayList<>();

        estudiantesRegistrados = false;
    }

    //  iniciar
    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarProfesores();
                    break;

                case 2:
                    registrarEstudiantes();
                    break;

                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;

                case 4:
                    consultaProfesorPorNombre();
                    break;

                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;

                case 6:
                    consultarEstudiante();
                    break;

                case 7:
                    System.out.println("Gracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println();

        } while (opcion != 7);
    }

    // Menú principal
    private void mostrarMenu() {

        System.out.println("==============================================");
        System.out.println(" SISTEMA DE PROFESORES Y ESTUDIANTES");
        System.out.println("==============================================");

        System.out.println("1. Registrar profesores");

        System.out.println("2. Registrar estudiantes");

        System.out.println("3. Consultar todos los profesores y estudiantes");

        System.out.println("4. Consultar un profesor y sus estudiantes");

        System.out.println("5. Consultar cantidad de estudiantes por profesor");

        System.out.println("6. Consultar director de grupo de un estudiante");

        System.out.println("7. Salir");

        System.out.println("==============================================");
    }

    //  para leer números
    private int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String valor = entrada.nextLine().trim();

            try {

                int numero = Integer.parseInt(valor);

                if (numero < 0) {

                    System.out.println("Ingrese un número mayor o igual a cero.");

                } else {

                    return numero;
                }

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    //  leer nombres
    private String leerNombre(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String nombre = entrada.nextLine().trim();

            if (!nombre.isEmpty()) {

                return nombre;

            } else {

                System.out.println("El nombre no puede estar vacío.");
            }
        }
    }

    // OPCIÓN 1: Registrar profesores
    public void registrarProfesores() {

        int cantidad = leerEntero("¿Cuántos profesores desea registrar?: ");

        if (cantidad == 0) {

            System.out.println("No se registraron profesores.");

            return;
        }

        int registrados = 0;

        while (registrados < cantidad) {

            String nombre = leerNombre(
                    "Nombre del profesor " + (registrados + 1) + ": "
            );

            if (listaProfesores.contains(nombre)) {

                System.out.println("Ese profesor ya está registrado.");

            } else {

                listaProfesores.add(nombre);

                listaGeneralEstudiantes.clear();

                estudiantesRegistrados = false;

                registrados++;

                System.out.println("Profesor registrado correctamente.");
            }
        }

        System.out.println("Total de profesores: " + listaProfesores.size());
    }

    // OPCIÓN 2: Registrar estudiantes
    public void registrarEstudiantes() {

        if (listaProfesores.isEmpty()) {

            System.out.println("Primero debe registrar profesores.");

            return;
        }

        listaGeneralEstudiantes.clear();

        estudiantesRegistrados = false;

        for (String profesor : listaProfesores) {

            ArrayList<String> listaEstudiantes = new ArrayList<>();

            System.out.println("\nRegistro de estudiantes para: " + profesor);

            int cantidad = leerEntero(
                    "¿Cuántos estudiantes desea registrar para " + profesor + "?: "
            );

            int registrados = 0;

            while (registrados < cantidad) {

                String nombre = leerNombre(
                        "Nombre del estudiante " + (registrados + 1) + ": "
                );

                if (listaEstudiantes.contains(nombre)) {

                    System.out.println("Ese estudiante ya está registrado en este grupo.");

                } else {

                    listaEstudiantes.add(nombre);

                    registrados++;

                    System.out.println("Estudiante registrado correctamente.");
                }
            }

            listaGeneralEstudiantes.add(listaEstudiantes);
        }

        estudiantesRegistrados = true;

        System.out.println("\nRegistro de estudiantes finalizado correctamente.");
    }

    // Validar que existan profesores y estudiantes
    private boolean hayProfesoresYEstudiantes() {

        if (listaProfesores.isEmpty()) {

            System.out.println("Primero debe registrar profesores (opción 1).");

            return false;
        }

        if (!estudiantesRegistrados) {

            System.out.println("Primero debe registrar estudiantes (opción 2).");

            return false;
        }

        return true;
    }

    // OPCIÓN 3: Consultar todos los profesores y estudiantes
    public void consultarListaProfesoresYEstudiantes() {

        if (!hayProfesoresYEstudiantes()) {

            return;
        }

        System.out.println("\n========== LISTADO GENERAL ==========");

        for (int i = 0; i < listaProfesores.size(); i++) {

            System.out.println("\nProfesor: " + listaProfesores.get(i));

            ArrayList<String> estudiantes =
                    listaGeneralEstudiantes.get(i);

            if (estudiantes.isEmpty()) {

                System.out.println("No tiene estudiantes registrados.");

            } else {

                System.out.println("Estudiantes: " + String.join(", ", estudiantes));
            }
        }
    }

    // OPCIÓN 4: Consultar un profesor
    public void consultaProfesorPorNombre() {

        if (!hayProfesoresYEstudiantes()) {

            return;
        }

        String nombre = leerNombre(
                "Ingrese el nombre del profesor que desea consultar: "
        );

        if (listaProfesores.contains(nombre)) {

            int posicion = listaProfesores.indexOf(nombre);

            ArrayList<String> estudiantes =
                    listaGeneralEstudiantes.get(posicion);

            System.out.println("\nProfesor: " + listaProfesores.get(posicion));

            if (estudiantes.isEmpty()) {

                System.out.println("Este profesor no tiene estudiantes.");

            } else {

                System.out.println("Estudiantes: " + String.join(", ", estudiantes));
            }

        } else {

            System.out.println("El profesor " + nombre + " no está registrado.");
        }
    }

    // OPCIÓN 5: Consultar cantidad de estudiantes
    public void consultaCantidadEstudiantesPorProfesor() {

        if (!hayProfesoresYEstudiantes()) {

            return;
        }

        String nombre = leerNombre("Ingrese el nombre del profesor: ");

        if (listaProfesores.contains(nombre)) {

            int posicion = listaProfesores.indexOf(nombre);

            int cantidad = listaGeneralEstudiantes.get(posicion).size();

            System.out.println(
                    "El profesor " + nombre + " tiene "
                            + cantidad + " estudiante(s)."
            );

        } else {

            System.out.println("El profesor no se encuentra registrado.");
        }
    }

    // OPCIÓN 6: Consultar director de grupo
    public void consultarEstudiante() {

        if (!hayProfesoresYEstudiantes()) {

            return;
        }

        String nombre = leerNombre(
                "Ingrese el nombre del estudiante que desea consultar: "
        );

        boolean encontrado = false;

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

            ArrayList<String> estudiantes =
                    listaGeneralEstudiantes.get(i);

            if (estudiantes.contains(nombre)) {

                System.out.println(
                        "El estudiante " + nombre
                                + " pertenece al grupo del profesor "
                                + listaProfesores.get(i)
                );

                encontrado = true;
            }
        }

        if (!encontrado) {

            System.out.println("El estudiante no se encuentra registrado.");
        }
    }
}