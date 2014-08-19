import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void deveRetornarASomaDeNenhumNumero() {
        int soma = stringCalculator.add("");
        assertEquals(0, soma);
    }

    @Test
    public void deveRetornarASomaDeUmNumero() {
        int soma = stringCalculator.add("1");
        assertEquals(1, soma);

        soma = stringCalculator.add("999");
        assertEquals(999, soma);
    }

    @Test
    public void deveRetornarASomaDeDoisNumeros() {
        int soma = stringCalculator.add("1,2");
        assertEquals(3, soma);

        soma = stringCalculator.add("16,27");
        assertEquals(43, soma);
    }

    @Test
    public void deveSomarNumeroIlimitadoDeNumeros() {
        int soma = stringCalculator.add("1,2,0,10");
        assertEquals(13, soma);
    }

    @Test
    public void devePermitirNovaLinhaComoDelimitador() {
        int soma = stringCalculator.add("1\n2,3");
        assertEquals(6, soma);

        soma = stringCalculator.add("1,\n");
        assertEquals(1, soma);
    }

    @Test
    public void devePermitirDelimitadorComoParametro() {
        int soma = stringCalculator.add("//;\n1;2");
        assertEquals(3, soma);
    }

    @Test
    public void numerosNegativosNaoSaoPermitidos() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negatives not allowed: -3");
        stringCalculator.add("//;\n1;2;-3");
    }

    @Test
    public void mostraNegativosNaMensagem() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negatives not allowed: -3 -9 -1");
        stringCalculator.add("//;\n1;2;-3;-9;-1");
    }

    @Test
    public void ignoraNumerosMaiorQue1000() {
        int soma = stringCalculator.add("2,1001");
        assertEquals(2, soma);
    }

    @Test
    public void delimitadoresPodemTerQualquerTamanho() {
        int soma = stringCalculator.add("//[***]\n1***2***3");
        assertEquals(6, soma);
    }

    @Test
    public void podemHaverMaisDelimitadores() {
        int soma = stringCalculator.add("//[***][;]\n1***2***3;4;5");
        assertEquals(15, soma);
    }

}
