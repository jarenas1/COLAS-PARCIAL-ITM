package services;

import models.Persona;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PersonService implements IPersonService {

    public void menu(Scanner sc){
        Queue<Persona> cola = new LinkedList<>();
        boolean bandera = true;
        while (bandera){
            System.out.println("SISTEMA DE GESTION CENSO");
            System.out.println("Opciones:");
            System.out.println("1: Ver todos los menores de edad");
            System.out.println("2: Ver todos los datos");
            System.out.println("3: Eliminar persona");
            System.out.println("4: Crear persona");
            System.out.println("5: Modificar persona");
            System.out.println("6: salir");

            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int opt = sc.nextInt();
            sc.nextLine();
            switch (opt){
                case 1:
                   this.obtenerMenoresDeEdad(cola);
                    break;
                case 2:
                    this.obtenerTodo(cola);
                    break;
                case 3:
                   cola = this.eliminarPersonaPorCedula(cola, sc);
                    break;
                case 4:
                    cola = this.crearPersona(cola, sc);
                    break;
                case 5:
                    cola = this.modificarPersona(cola, sc);
                    break;
                case 6:
                    bandera = false;
                    break;
                default:
                    System.out.println("Ingrese un valor valido a modificar");
            }
        }
    }


    @Override
    public void obtenerMenoresDeEdad(Queue<Persona> db) {
        System.out.println("Mostrando personas menores de edad:");
        for(Persona p:db){
            if (p.getEdad() < 18 && p.getEstado() == 1){
                System.out.println("--------------------------------");
                System.out.println(p.toString());
            }
        }
    }

    @Override
    public Queue<Persona> eliminarPersonaPorCedula(Queue<Persona> db, Scanner sc) {
        System.out.println("Eliminando persona");
        System.out.println("Ingrese la cedula de la persona a eliminar");
        String cc = sc.nextLine();
        boolean deleted = false;
        for(Persona p:db){
            if (p.getCedula().equals(cc) && p.getEstado() == 1){
                System.out.println("Eliminando persona con cedula:"+cc);
               //Implementacion de un softDelete para manejar integridad de datos
                p.setEstado(2);
                deleted = true;
            }
            if (deleted){
                System.out.println("Se ha eliminado correctamente la persona, para recuperarla contacte al area de sistemas");
            }else{
                System.out.println("No se pudo encontrar la persona a eliminar en los registros");
            }
        }
        return db;
    }

    @Override
    public void obtenerTodo(Queue<Persona> db) {
        System.out.println("Mostrando todos los registros de la base de datos:");
        for(Persona p:db){
            if (p.getEstado() == 1){
                System.out.println("--------------------------------");
                System.out.println(p.toString());
            }
        }
    }

    @Override
    public Queue<Persona> crearPersona(Queue<Persona> db, Scanner sc) {
        boolean bandera = true;
        System.out.println("Ingrese el salario minimo actual:");
        double minimo = sc.nextDouble();
        sc.nextLine();
        while (bandera){
            System.out.println("Creando persona:");
            System.out.println("Ingrese el nombre de la persona:");
            String nombre = sc.nextLine();
            System.out.println("Ingrese la cedula de la persona:");
            String cedula = sc.nextLine();
            boolean existeCC = this.verificarSiCedulaRegistrada(db, cedula);
            if (existeCC){
                System.out.println("Esta cedula ya esta registrada, verificar");
                break;
            }
            System.out.println("Ingrese la edad de la persona");
            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese 1 si la persona es desplazada, si no, ingrese 2");

            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int desplazado = sc.nextInt();
            while (desplazado < 1 || desplazado > 2) {
                System.out.println("debe ingresa una opcion de 1 a 2");
                desplazado = sc.nextInt();
            }
            sc.nextLine();
            System.out.println("Ingrese el estrado de la persona");
            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int estrato = sc.nextInt();
            sc.nextLine();
            double auxilio = 0;
            //Adicion del auxilio basado en condiciones dadas:
            if (desplazado == 1 && estrato <= 2 && edad < 18){
                auxilio = minimo*0.8;
            }
            System.out.println("Creando ...");
            Persona persona = new Persona(nombre, edad, cedula, auxilio, desplazado == 1, estrato);
            db.add(persona);
            System.out.println("Persona creada");

            System.out.println("Desea añadir otra persona? 1: si, 2: no");
            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int opt = sc.nextInt();
            while (opt < 1 || opt > 2) {
                System.out.println("debe ingresa una opcion de 1 a 2");
                opt = sc.nextInt();
            }
            sc.nextLine();
            if (opt == 2){
                bandera = false;
            }
        }
        return db;
    }

    @Override
    public Queue<Persona> modificarPersona(Queue<Persona> db, Scanner sc) {
        System.out.println("Ingrese el salario minimo actual");
        double minimo = sc.nextDouble();
        sc.nextLine();
        boolean bandera = true;
        while (bandera){
            System.out.println("Modificando persona:");
            System.out.println("Ingrese la cedula de la persona a modificar");
            String cc = sc.nextLine();
            boolean founded = false;

            for(Persona p:db){
                if (p.getCedula().equals(cc)){
                    founded = true;
                    boolean stopper = true;
                    while (stopper){
                        System.out.println("Opciones:");
                        System.out.println("1: Modificar nombre");
                        System.out.println("2: modificar cedula");
                        System.out.println("3: Modificar edad");
                        System.out.println("4: Modificar estado de desplazado");
                        System.out.println("5: Modificar estrado");
                        System.out.println("6: Dejar de modificar la persona");
                        while (!sc.hasNextInt()) {
                            System.out.println("El dato ingresado no es numerico por favor revise o valide");
                            sc.next();
                        }
                        int opt = sc.nextInt();
                        sc.nextLine();
                        switch (opt){
                            case 1:
                                System.out.println("Ingrese el nombre de la persona:");
                                String nombre = sc.nextLine();
                                p.setNombre(nombre);
                                break;
                            case 2:
                                System.out.println("Ingrese la cedula de la persona:");
                                String cedula = sc.nextLine();
                                boolean cedulaRegistrada = verificarSiCedulaRegistrada(db, cedula);
                                if (cedulaRegistrada){
                                    System.out.println("La cedula ya se encuentra registrada en el sistema, verificar");
                                }else{
                                    p.setCedula(cedula);
                                }
                                break;
                            case 3:
                                System.out.println("Ingrese la edad de la persona");
                                while (!sc.hasNextInt()) {
                                    System.out.println("El dato ingresado no es numerico por favor revise o valide");
                                    sc.next();
                                }
                                int edad = sc.nextInt();
                                sc.nextLine();
                                p.setEdad(edad);
                                break;
                            case 4:
                                System.out.println("Ingrese 1 si la persona es desplazada, si no, ingrese 2");
                                while (!sc.hasNextInt()) {
                                    System.out.println("El dato ingresado no es numerico por favor revise o valide");
                                    sc.next();
                                }
                                int desplazado = sc.nextInt();
                                while (desplazado < 1 || desplazado > 2) {
                                    System.out.println("debe ingresa una opcion de 1 a 2");
                                    desplazado = sc.nextInt();
                                }
                                sc.nextLine();
                                p.setEsDesplazado(desplazado == 1);
                                break;
                            case 5:
                                System.out.println("Ingrese el estrato de la persona");
                                while (!sc.hasNextInt()) {
                                    System.out.println("El dato ingresado no es numerico por favor revise o valide");
                                    sc.next();
                                }
                                int estrato = sc.nextInt();
                                sc.nextLine();
                                p.setEstrato(estrato);
                                break;
                            case 6:
                                stopper = false;
                                break;
                            default:
                                System.out.println("Ingrese un valor valido a modificar");
                        }
                        double auxilio = 0;
                        //Adicion del auxilio basado en condiciones dadas:
                        if (p.isEsDesplazado() && p.getEstrato() <= 2 && p.getEdad() < 18){
                            auxilio = minimo*0.8;
                        }
                        p.setAuxilio(auxilio);
                    }
                }
            }
            if (!founded){
                System.out.println("No se encontro la persona con esa cedula");
            }

            System.out.println("Desea añadir modificar otra persona? 1: si, 2: no");
            while (!sc.hasNextInt()) {
                System.out.println("El dato ingresado no es numerico por favor revise o valide");
                sc.next();
            }
            int opt = sc.nextInt();
            while (opt < 1 || opt > 2) {
                System.out.println("debe ingresa una opcion de 1 a 2");
                opt = sc.nextInt();
            }
            sc.nextLine();
            if (opt == 2){
                bandera = false;
            }
        }
        return db;
    }

    boolean verificarSiCedulaRegistrada(Queue<Persona> db, String cedula){
        boolean registrada = false;
        for(Persona p:db){
            if (p.getCedula().equals(cedula)) {
                if (p.getEstado() == 1){
                    registrada = true;
                }
            }
        }
        return registrada;
    }
}
