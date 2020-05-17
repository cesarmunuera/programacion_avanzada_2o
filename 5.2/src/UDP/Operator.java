package UDP;

import java.time.LocalDate;
import java.time.Period;

public class Operator {

    public static String operation(String numbers) {
        String[] stringNumbers = numbers.trim().split(",");
        if (stringNumbers.length != 3) {
            throw new ArithmeticException("Operation expression must be <año>,<mes>,<dia>, IN THAT ORDER");
        }
        int año = Integer.parseInt(stringNumbers[0]);
        int mes = Integer.parseInt(stringNumbers[1]);
        int dia = Integer.parseInt(stringNumbers[2]);

        LocalDate fecha = LocalDate.of(año, mes, dia);
        LocalDate fechaHoy = LocalDate.now();
        Period respuestaP = Period.between(fecha, fechaHoy);
        int resultado = respuestaP.getYears();
        System.out.println("De la clase operator" + resultado);
        String respuesta = Integer.toString(resultado);

        return respuesta;
    }

}
