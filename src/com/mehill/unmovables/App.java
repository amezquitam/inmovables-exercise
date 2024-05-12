package com.mehill.unmovables;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.mehill.unmovables.entity.AbstractUnmovable;
import com.mehill.unmovables.entity.Apartment;
import com.mehill.unmovables.entity.Client;
import com.mehill.unmovables.entity.House;

public class App {
    // Helpers
    private Boolean shouldExit;

    // Logic Business
    private UnmovableManager unmovableManager;
    private UnmovablePercentageReport unmovablePercentageReport;

    public App() {
        shouldExit = false;
        unmovableManager = new UnmovableManager();
        unmovablePercentageReport = new UnmovablePercentageReport(unmovableManager);
        loadDefaults();
    }

    public void run() {
        while (!shouldExit) {
            prompt();
        }
    }

    private void prompt() {
        double percentageOfHousesMoreThanTwoRooms = unmovablePercentageReport.percentageOfHousesMoreThanTwoRooms()
                * 100.0;
        double percentageOfUnrentedUnmovables = unmovablePercentageReport.percentageOfUnrentedUnmovables() * 100.0;

        String promptMessage = new StringBuilder()
                .append("-- Menu --\n")
                .append("1. Agregar inmueble\n")
                .append("2. Valor de alquiler por código\n")
                .append("3. Alquilar inmueble\n")
                .append("4. Listar inmuebles por cliente\n\n")
                .append("-- Datos rápidos --\n")
                .append(String.format("Las casas con más de dos cuartos representa el %.2f%%\n",
                        percentageOfHousesMoreThanTwoRooms))
                .append(String.format("Los inmuebles sin rentar representan el %.2f%%\n",
                        percentageOfUnrentedUnmovables))
                .append("-- Por favor, elija una opción escribiendo el número de la acción")
                .toString();

        String response = JOptionPane.showInputDialog(promptMessage);

        if (response == null) {
            shouldExit = true;
            return;
        }

        try {
            Integer option = Integer.parseInt(response);

            if (option < 1 || option > 4) {
                throw new IndexOutOfBoundsException();
            }

            if (option == 1)
                addUnmovable();
            if (option == 2)
                rentValueByCode();
            if (option == 3)
                rentUnmovable();
            if (option == 4)
                listUnmovableByClient();

        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número");
        } catch (IndexOutOfBoundsException err) {
            JOptionPane.showMessageDialog(null, "La entrada debe estar entre las opciones");
        }
    }

    private void addUnmovable() {
        String[] options = new String[] { "Casa", "Apartamento" };
        int option = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de inmueble",
                "Tipo de inmueble",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                0);

        if (option == JOptionPane.CLOSED_OPTION) {
            return;
        }

        Integer code = getInteger("Ingrese el código del inmueble (solo números)", 0, 999999);
        Integer stratum = getInteger("Ingrese el estrato del inmueble (1-6)", 1, 6);

        if (option == 0) {
            Integer rooms = getInteger("Ingrese el numero de habitaciones de la casa (2 o más)", 1, 150);

            House house = new House(null, code, stratum, rooms);

            unmovableManager.add(house);
        }
        if (option == 1) {
            Integer floor = getInteger("Ingrese el pise en el que se encuentra el apartamento (1-11)", 1, 11);

            Apartment apartment = new Apartment(null, code, stratum, floor);

            unmovableManager.add(apartment);
        }
    }

    private void rentValueByCode() {
        Integer code = getInteger("Ingrese el codigo del inmueble", 0, 999999);

        // backend code in front uwu
        AbstractUnmovable unmovable = unmovableManager.getByCode(code);

        if (unmovable == null) {
            JOptionPane.showMessageDialog(null, "Parece que no hay ningun inmueble registrado con ese código");
            return;
        }

        JOptionPane.showMessageDialog(null,
                String.format("El costo de la renta de este inmueble es de %.2f", unmovable.getRentCost()));
    }

    private void rentUnmovable() {
        Integer code = getInteger("Ingrese el codigo del inmueble", 0, 999999);

        // backend code in front uwu
        AbstractUnmovable unmovable = unmovableManager.getByCode(code);

        if (unmovable == null) {
            JOptionPane.showMessageDialog(null, "Parece que no hay ningun inmueble registrado con ese código");
            return;
        }

        String fullname = JOptionPane.showInputDialog("Ingrese el nombre completo del dueño");
        if (fullname == null)
            return;

        String identity = JOptionPane.showInputDialog("Ingrese el número de identificación del dueño");
        if (identity == null)
            return;

        Client client = new Client(fullname, identity);

        unmovable.setClient(client);

        JOptionPane.showMessageDialog(null, "Fué registrado con éxito");
    }

    private void listUnmovableByClient() {

        Map<String, List<AbstractUnmovable>> unmovablesByClient = unmovableManager.stream()
                .filter(u -> u.getClient() != null)
                .collect(Collectors.groupingBy(u -> u.getClient().getId()));

        StringBuilder response = new StringBuilder();
        
        for (var entry : unmovablesByClient.entrySet()) {
            String clientId = entry.getKey();
            List<AbstractUnmovable> unmovables = entry.getValue();
            String clientName = unmovables.get(0).getClient().getFullName();

            response.append(String.format("Inmuebles registrados con el nombre de %s (%s)\n", clientName, clientId));

            for (var unmovable : unmovables) {
                response.append(" - ").append(unmovable.toString()).append("\n");
            }
            response.append("\n");
        }

        JOptionPane.showMessageDialog(null, response);
    }

    private void loadDefaults() {
        Client raul = new Client("Raul Gonzales", "123456789");
        Client paul = new Client("Paul Gonzales", "987654321");
        Client saul = new Client("Saul Gonzales", "456123789");

        List<AbstractUnmovable> defaultUnmovables = List.of(
                new House(null, 7901, 3, 2),
                new House(saul, 7902, 4, 4),
                new House(paul, 7903, 5, 3),
                new House(saul, 7904, 6, 10),
                new Apartment(null, 5201, 4, 2),
                new Apartment(raul, 5401, 3, 4),
                new Apartment(null, 5601, 5, 6),
                new Apartment(paul, 5801, 2, 8),
                new Apartment(paul, 71001, 3, 10),
                new Apartment(saul, 71101, 6, 11));

        unmovableManager.addAll(defaultUnmovables);
    }

    private static Integer getInteger(String message, Integer min, Integer max) {
        String response = JOptionPane.showInputDialog(message);

        try {

            Integer digit = Integer.parseInt(response);

            if (digit < min || digit > max) {
                throw new IndexOutOfBoundsException();
            }

            return digit;

        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(null, "Parece que la entrada es invalida");
            return getInteger(message, min, max);
        } catch (IndexOutOfBoundsException err) {
            JOptionPane.showMessageDialog(null,
                    String.format("Parece que la entrada está fuera de los limites (%d,%d)", min, max));
            return getInteger(message, min, max);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
