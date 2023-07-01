import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculoSalario {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        int numFuncionarios = 5;
        double[] salariosBrutos = new double[numFuncionarios];
        double[] descontoINSS = new double[numFuncionarios];
        double[] descontoIR = new double[numFuncionarios];
        double[] salariosLiquidos = new double[numFuncionarios];

        for (int i = 0; i < numFuncionarios; i++) {
            System.out.print("Informe o salário bruto " + (i+1) + ": ");
            String input = scanner.next();
            salariosBrutos[i] = Double.parseDouble(input.replace(",", "."));

            descontoINSS[i] = calcularDescontoINSS(salariosBrutos[i]);
            double salarioComDescontoINSS = salariosBrutos[i] - descontoINSS[i];
            descontoIR[i] = calcularDescontoIR(salarioComDescontoINSS);
            salariosLiquidos[i] = salarioComDescontoINSS - descontoIR[i];
        }

        System.out.println("Resultados:");
        for (int i = 0; i < numFuncionarios; i++) {
            System.out.println("Funcionário " + (i+1) + ":");
            System.out.println("Salário bruto: " + decimalFormat.format(salariosBrutos[i]));
            System.out.println("Desconto INSS: " + decimalFormat.format(descontoINSS[i]));
            System.out.println("Desconto Imposto de Renda: " + decimalFormat.format(descontoIR[i]));
            System.out.println("Salário líquido: " + decimalFormat.format(salariosLiquidos[i]));
            System.out.println();
        }
    }

    public static double calcularDescontoINSS(double salarioBruto) {
        if (salarioBruto <= 1212.00) {
            return salarioBruto * 0.075;
        } else if (salarioBruto <= 2427.35) {
            return salarioBruto * 0.09;
        } else if (salarioBruto <= 3641.03) {
            return salarioBruto * 0.12;
        } else if (salarioBruto <= 7087.22) {
            return salarioBruto * 0.14;
        } else {
            return 7087.22 * 0.14; //Ultimo valor INSS
        }
    }
    // VALORES - ALIQUOTES 2021
    public static double calcularDescontoIR(double salarioBase) {
        if (salarioBase <= 1903.98) {
            return 0;
        } else if (salarioBase <= 2826.65) {
            return (salarioBase * 0.075) - 142.80;
        } else if (salarioBase <= 3751.05) {
            return (salarioBase * 0.15) - 354.80;
        } else if (salarioBase <= 4664.68) {
            return (salarioBase * 0.225) - 636.13;
        } else {
            return (salarioBase * 0.275) - 869.36;
        }
    }
}