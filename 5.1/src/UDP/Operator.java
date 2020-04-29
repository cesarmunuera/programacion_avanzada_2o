package UDP;

public class Operator {

    public static String operation(String numbers) {
        String[] stringNumbers = numbers.trim().split(",");
        if (stringNumbers.length != 2) {
            throw new ArithmeticException("Operation expression must be <op1>,<op2>");
        }
        double op1 = Double.parseDouble(stringNumbers[0]);
        double op2 = Double.parseDouble(stringNumbers[1]);
        double resultado = op1 * op2;
        String respuesta = Double.toString(resultado);

        return respuesta;
    }

}
