package main.java.conversor.service;
import main.java.conversor.model.Country;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Country> initalCountryList = new ArrayList<>(List.of(
            new Country("ARS", "Peso argentino"),
            new Country("BOB", "Boliviano boliviano"),
            new Country("BRL", "Real brasileño"),
            new Country("CLP", "Peso chileno"),
            new Country("COP", "Peso colombiano"),
            new Country("USD", "Dolar estadounidense")
    ));

    private String menuItems = """
            1. Realizar una conversión de moneda: convierte una cantidad de una moneda a otra.
            2. Ver el listado actual de monedas: muestra todas las monedas disponibles para la conversión.
            3. Agregar una moneda al listado: permite añadir una nueva moneda a la lista de monedas disponibles.
            4. Eliminar una moneda del listado: permite eliminar una moneda de la lista de monedas disponibles.
            5. Salir: cierra la aplicación.
            """;

    public List<Country> getInitalCountryList() {
        return initalCountryList;
    }

    public String getMenuItems() {
        return menuItems;
    }

    public void showCountries(List<Country> country) {
        System.out.println("Paises disponibles:");
        for (int i = 0; i < country.size(); i++) {
            System.out.println( (i + 1) + "." + country.get(i));
        }
    }

    public void appFinalMessage() {
        System.out.println("Saliendo....");
    }

    public List<Country> setInitalCountryList(Country country) {
        this.initalCountryList.add(country);
        return this.getInitalCountryList();
    }

    public List<Country> removeCountryFormList(int index) {
        this.initalCountryList.remove(index);
        return this.getInitalCountryList();
    }

}