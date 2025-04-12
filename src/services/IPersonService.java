package services;

import models.Persona;

import java.util.Queue;
import java.util.Scanner;

public interface IPersonService {

    //Queue<String> cola = new LinkedList<>();

    void obtenerMenoresDeEdad(Queue<Persona> db);
    Queue<Persona> eliminarPersonaPorCedula(Queue<Persona> db, Scanner sc);
    void obtenerTodo(Queue<Persona> db);
    Queue<Persona> crearPersona(Queue<Persona> db, Scanner sc);
    Queue<Persona> modificarPersona(Queue<Persona> db, Scanner sc);
}
