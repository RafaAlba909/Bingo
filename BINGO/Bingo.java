package BINGO;

import java.util.Random;
import java.util.Scanner;

public class Bingo {

    private static int[] num90 = new int[91];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Jugar al Bingo");
            System.out.println("2. Salir");

            System.out.println("Introduzca opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    jugarBingo();
                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }

        } while (opcion != 2);
    }

    private static void jugarBingo() {
        int suma;
        Scanner sc = new Scanner(System.in);

        do {
            int[][] matrizBingo = generarCarton();
            suma = calcularSumaCarton(matrizBingo);

            do {
                int numeroGenerado = generarNumeroAleatorio();
                buscarNumero(matrizBingo, numeroGenerado);
                System.out.println("----------"+numeroGenerado+"----------");
                imprimirCarton(matrizBingo);

                suma = calcularSumaCarton(matrizBingo);
                System.out.println("Enter para el siguiente número");
                sc.nextLine();

            } while (suma != 0);

            System.out.println("Bingo!");

            System.out.print("¿Quieres jugar de nuevo? (1: VOLVER A JUGAR,\n 2: VOLVER AL INICIO): ");
            int opcionJuegoNuevo = sc.nextInt();

            if (opcionJuegoNuevo != 1) {
                System.out.println("Saliendo...");
                break;
            }

        } while (true);
    }

    public static int calcularSumaCarton(int[][] matrizBingo) {
        int suma = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                suma += matrizBingo[i][j];
            }
        }
        return suma;
    }

   public static void imprimirCarton(int[][] matrizBingo) {
        System.out.println("--------------------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrizBingo[i][j] + " ");
            }
            System.out.println();
            System.out.println("--------------------------");
        }
    }

    public static int generarNumeroAleatorio() {
        int numeroGenerado;
        do {
            numeroGenerado = obtenerNumeroAleatorio(1, 90);
        } while (num90[numeroGenerado] == 1);
        return numeroGenerado;
    }


   public static void buscarNumero(int[][] matrizBingo, int numero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrizBingo[i][j] == numero) {
                    matrizBingo[i][j] = 0;
                    num90[numero] = 1;
                    return;
                }
            }
        }
    }


    public static int[][] generarCarton() {
        for (int i = 1; i <= 90; i++) {
            num90[i] = 0;
        }

        int[][] matrizBingo = new int[3][9];
        boolean encontrado;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    matrizBingo[j][i] = obtenerNumeroAleatorio(i * 10 + 1, i * 10 + 10);
                    encontrado = false;

                    for (int k = j - 1; k >= 0; k--) {
                        if (matrizBingo[j][i] == matrizBingo[k][i]) {
                            encontrado = true;
                            break;
                        }
                    }
                } while (encontrado);
            }
        }

        return matrizBingo;
    }
    private static int obtenerNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}


