package main.java.conversor.util;

import main.java.conversor.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputValidator {
    private int baseCurrencyIndex;
    private int targetCurrencyIndex;
    private int upperLimitIndex;
    private int lowerLimitIndex;
    private String userChoice;

    public int checkInputWithinRange(Scanner input) {
        while (this.baseCurrencyIndex > this.upperLimitIndex | this.baseCurrencyIndex < this.lowerLimitIndex) {
            System.out.println("Opcion invalida ");
            int userInput = input.nextInt();
            setBaseCurrencyIndex(userInput);
        }
        return getBaseCurrencyIndex();
    }

    public int ensureDifferentBaseTargetIndex(Scanner input) {
        while (this.baseCurrencyIndex == this.targetCurrencyIndex) {
            System.out.println("Ambas monedas tienen la misma moneda base. Ingresa una moneda diferente: ");
            System.out.println("Ingresa otra moneda: ");
            int userInput = input.nextInt();
            setTargetCurrencyIndex(userInput);
        }
        return this.targetCurrencyIndex;
    }

    public int confirmUserChoice(Scanner input, int optionContext) {
        int userChoice = 0;
        while (userChoice != 1 && userChoice != 2) {
            System.out.println("Opción inválida. Ingresa '1' para continuar o '2' para regresar al menú principal: ");
            userChoice = input.nextInt();
        }
        if (userChoice == 1) {
            return optionContext;
        } else {
            return 0;
        }
    }

    public Country ensureUniqueCountryInList(Country country, List<Country> countryList, List<Country> compareList, Scanner input) {
        Country finalCountry = country;
        String countryCode = country.getCode();
        List<String> localCountryCodes = new ArrayList<>();
        for (Country currentCountry : countryList) {
            localCountryCodes.add(currentCountry.getCode());
        }
        while (localCountryCodes.contains(countryCode)) {
            System.out.println("Country already in list. Try a new one: ");
            int userInput = input.nextInt();
            finalCountry = compareList.get(userInput - 1);
            countryCode = finalCountry.getCode();
        }
        return finalCountry;
    }

    public boolean checkSufficientCountries(List<Country> countries) {
        if (countries.size() == 2) {
            System.out.println("Cannot remove more countries. At least two are needed for conversions.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Sets the base currency index.
     * @param index The new base currency index.
     */
    public void setBaseCurrencyIndex(int index) {
        this.baseCurrencyIndex = index;
    }

    /**
     * Sets the upper limit index.
     * @param upperLimitIndex The new upper limit index.
     */
    public void setUpperLimitIndex(int upperLimitIndex) {
        this.upperLimitIndex = upperLimitIndex;
    }

    /**
     * Sets the lower limit index.
     * @param lowerLimitIndex The new lower limit index.
     */
    public void setLowerLimitIndex(int lowerLimitIndex) {
        this.lowerLimitIndex = lowerLimitIndex;
    }

    /**
     * Sets the target currency index.
     * @param targetCurrencyIndex The new target currency index.
     */
    public void setTargetCurrencyIndex(int targetCurrencyIndex) {
        this.targetCurrencyIndex = targetCurrencyIndex;
    }

    /**
     * Sets the user choice.
     * @param userChoice The new user choice.
     */
    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    /**
     * Gets the base currency index.
     * @return The base currency index.
     */
    public int getBaseCurrencyIndex() {
        return baseCurrencyIndex;
    }
}