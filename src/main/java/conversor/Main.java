package main.java.conversor;

import main.java.conversor.model.Callback;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Callback app = new Callback();
        String options = app.getMenuItems();

        System.out.println("Aplicación de conversión moneda:");

        try (Scanner userInput = new Scanner(System.in)) {
            while (app.getOption() != 7) {
                System.out.println(options);
                System.out.println("Elige una opción valida: ");
                app.setOption(userInput.nextInt());

                switch (app.getOption()) {
                    case 1 -> app.handleCurrencyConversion();
                    case 2 -> app.displayCountries();
                    case 3 -> app.addCountry();
                    case 4 -> app.removeCountry();
                    case 5 -> app.appFinalMessage();
                    default -> System.out.println("Opcion invalida, intenta de nuevo");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingresa un número válido.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}