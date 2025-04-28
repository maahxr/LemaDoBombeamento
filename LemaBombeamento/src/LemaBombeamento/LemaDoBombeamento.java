package LemaBombeamento;

import java.util.ArrayList;
import java.util.List;

public class LemaDoBombeamento {

    // Essa função aqui serve pra ver se uma palavra é palíndromo (lê igual de frente pra trás e de trás pra frente).
    public static boolean linguagem(String w) {
        int n = w.length();
        for (int i = 0; i < n / 2; i++) {
            // Compara o caractere do começo com o do fim
            if (w.charAt(i) != w.charAt(n - 1 - i)) {
                return false; // Se achou diferente, não é palíndromo
            }
        }
        return true; // Se passou por tudo sem problema, é palíndromo
    }

    // Essa função divide a palavra em 3 partes: x, y e z
    // Seguindo as regras do lema do bombeamento: |xy| <= p e |y| > 0
    public static List<String[]> dividirCadeia(String w, int p) {
        List<String[]> divisoes = new ArrayList<>();
        int n = w.length();

        // Vamos testar todas as formas possíveis de cortar a palavra em x, y, z
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String x = w.substring(0, i);
                String y = w.substring(i, j);
                String z = w.substring(j);

                // Só adiciona se |xy| <= p e |y| > 0
                if (x.length() + y.length() <= p && y.length() > 0) {
                    divisoes.add(new String[]{x, y, z});
                }
            }
        }
        return divisoes; // Retorna todas as divisões possíveis
    }

    // Verifica se dá pra bombear (repetir o y) e ainda ficar dentro da linguagem
    public static void verificarBombeamento(int p, String w) {
        List<String[]> divisoes = dividirCadeia(w, p);

        System.out.println("Divisões possíveis de w = xyz com |xy| <= " + p + " e |y| > 0:");
        for (String[] divisao : divisoes) {
            String x = divisao[0];
            String y = divisao[1];
            String z = divisao[2];
            System.out.println("x = \"" + x + "\", y = \"" + y + "\", z = \"" + z + "\"");
        }
        System.out.println();

        //  Testa bombear o y de 0 a 9 repetições
        for (String[] divisao : divisoes) {
            String x = divisao[0];
            String y = divisao[1];
            String z = divisao[2];

            for (int i = 0; i < 10; i++) {
                String novaCadeia = x + y.repeat(i) + z;
                // Se a nova cadeia não for mais palíndromo, já mostra que a linguagem não é regular
                if (!linguagem(novaCadeia)) {
                    System.out.println("A linguagem não é regular. Quebra encontrada com divisão:");
                    System.out.println("x = \"" + x + "\", y = \"" + y + "\", z = \"" + z + "\" e i = " + i);
                    System.out.println("Nova cadeia gerada: " + novaCadeia);
                    return; // Para o IF se não for regular.
                }
            }
        }
        // Se passou por todas sem dar erro, a linguagem é regular 
        System.out.println("A linguagem é regular para todas as divisões possíveis.\n");
    }

    // Função para organizar os testes
    public static void executarTestes() {
        // Lista de cadeias que vamos testar
        String[] cadeias = {
            "abcd",     // Não é palíndromo
            "aa"        // Palíndromo
        };

        int p = 3; // Valor de p (constante de bombeamento)

        for (String w : cadeias) {
            System.out.println("Testando a cadeia: " + w);
            verificarBombeamento(p, w);
            System.out.println("-----------------------------------\n");
        }
    }

    // Rodar a função de bombeamento.
    public static void main(String[] args) {
        executarTestes();
    }
}