import services.PersonService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PersonService p = new PersonService();

        p.menu(sc); //VERIFICAR ELIMINAR SI NO SE ENCUENTRA  // CEDULA REPETIDA EN CREACION // minimo actual en crear
    }
}