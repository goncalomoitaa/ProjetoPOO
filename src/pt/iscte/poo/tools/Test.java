package pt.iscte.poo.tools;

import java.util.Objects;

public class Test {
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_GREEN = "\033[0;32m";
  public static final String ANSI_YELLOW = "\033[0;33m";

  /*
    Para ser usado como parâmetro de comparação em casos de incerteza do valor esperado.
    Não faz uma comparação marcada visualmente como acerto ou erro. Exibe uma mensagem de aviso,
    alertando sobre a natureza do uso deste critério de comparação (idealmente associado a aleatoriedade)
   */
  public static final String maybe = "maybe";

  public static void assertNull(Object givenValue) {
    if(givenValue == null) {
      System.out.println(ANSI_GREEN + "Ok: (dado) " + givenValue + " é nulo como esperado;" + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "ERRO: (dado) \"" + givenValue.toString() + "\" NÃO é nulo como esperado;" + ANSI_RESET);
    }
  }

  public static void assertEquals(Object givenValue, Object expectedValue) {
    if(expectedValue == maybe) {
      System.out.println(
        ANSI_YELLOW +
          "Verifique: o parâmetro de comparação dado é `maybe`,\n" +
          "o que quer dizer que o resultado da comparação é incerto,\n" +
          "e provavelemente envolve aleatoriedade (Math.random(), ou similares) nos processos relacionados a esta verificação.\n" +
          "O valor dado, no entanto, é: " +
          givenValue.toString() +
          ANSI_RESET
      );

      return;
    }
    if(Objects.equals(givenValue, expectedValue) || givenValue == expectedValue) {
      System.out.println(ANSI_GREEN + "Ok: (dado) " + givenValue.toString() + " == " + expectedValue + " (esperado);" + ANSI_RESET);
    } else {
      System.out.println(ANSI_RED + "Alguma coisa deu errado, nos foi dado " + givenValue + " enquanto esperávamos " + expectedValue + ";" + ANSI_RESET);
    }
  }
}
