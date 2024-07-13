package main.java.conversor.model;

import main.java.conversor.api.CurrencyApi;
import main.java.conversor.util.InputValidator;
import main.java.conversor.service.Menu;

import java.util.List;
import java.util.Scanner;

public class Callback extends Menu {
    private final Scanner userInput = new Scanner(System.in);
    private final InputValidator validator = new InputValidator();
    private final CurrencyApi currencyApi = new CurrencyApi();
    private int option = 0;

    public void handleCurrencyConversion() {
        while (this.option == 1) {
            Conversor currencyPairConversion = new Conversor();
            List<Country> countries = this.getInitalCountryList();
            this.showCountries(countries);
            validator.setUpperLimitIndex(countries.size());
            validator.setLowerLimitIndex(1);
            System.out.println("Selecciona la moneda base (Por ej. 1): ");
            validator.setBaseCurrencyIndex(userInput.nextInt());
            int baseIndex = validator.checkInputWithinRange(userInput);
            Country userCountryBase = countries.get(baseIndex - 1);
            currencyPairConversion.setBaseCode(userCountryBase.getCode());
            System.out.println("Selecciona la moneda a la cual deseas cambiar el monto: ");
            validator.setTargetCurrencyIndex(userInput.nextInt());
            int targetIndex = validator.checkInputWithinRange(userInput);
            targetIndex = validator.ensureDifferentBaseTargetIndex(userInput);
            Country userCountryTarget = countries.get(targetIndex - 1);
            currencyPairConversion.setTargetCode(userCountryTarget.getCode());
            System.out.printf("Ingresa el monto que deseas convertir de '%s' a '%s': %n", currencyPairConversion.getBaseCode(), currencyPairConversion.getTargetCode());
            double userAmount = userInput.nextDouble();
            currencyPairConversion.setAmount(userAmount);
            currencyApi.convertCurrency(currencyPairConversion);
            System.out.println(currencyPairConversion);
            continueWithOption("¿Deseas realizar otra conversión de moneda?", this.option);
        }
    }

    public void displayCountries() {
        while (this.option == 2) {
            List<Country> countries = this.getInitalCountryList();
            this.showCountries(countries);
            returnToMenu();
        }
    }

    public void addCountry() {
        while (this.option == 3) {
            List<Country> localCountries = this.getInitalCountryList();
            List<Country> apiCountries = currencyApi.getCountries();
            this.showCountries(apiCountries);
            System.out.println("Selecciona el número del país que desees añadir (Por ej. 15): ");
            int userIndex = userInput.nextInt();
            Country userCountry = apiCountries.get(userIndex - 1);
            userCountry = validator.ensureUniqueCountryInList(userCountry, localCountries, apiCountries, userInput);
            System.out.println("Se ha añadido tu país a la lista...");
            this.setInitalCountryList(userCountry);
            this.showCountries(localCountries);
            continueWithOption("¿Deseas añadir otro país?", this.option);
        }
    }

    public void removeCountry() {
        while (this.option == 4) {
            List<Country> countries = this.getInitalCountryList();
            if (validator.checkSufficientCountries(countries)) {
                validator.setUpperLimitIndex(countries.size());
                validator.setLowerLimitIndex(1);
                this.showCountries(countries);
                System.out.println("Selecciona el pais que deseas quitar de la lista (por ej. 5): ");
                validator.setBaseCurrencyIndex(userInput.nextInt());
                int index = validator.checkInputWithinRange(userInput);
                index = index - 1;
                String countryCode = countries.get(index).getCode();
                countries = this.removeCountryFormList(index);
                System.out.printf("Se ha eliminado %s de la lista...\n", countryCode);
                continueWithOption("¿Deseas remover otro país de la lista?", this.option);
            } else {
                returnToMenu();
            }
        }
    }

    private void continueWithOption(String message, int currentOption) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Ingresa '1' para continuar o '2' para regresar al menú principal: ");
        validator.setUserChoice(userInput.next());
        setOption(validator.confirmUserChoice(userInput, currentOption));
    }

    private void returnToMenu() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Ingresa cualquier número o letra para regresar al menú...");
        userInput.next();
        setOption(0);
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}