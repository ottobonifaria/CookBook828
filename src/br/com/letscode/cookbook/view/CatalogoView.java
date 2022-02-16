package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.controller.Catalogo;
import br.com.letscode.cookbook.domain.Receita;

import java.util.Scanner;

public class CatalogoView {
    private Catalogo controller;
    private Receita ative;
    private int currentIndex;

    public CatalogoView(Catalogo controller) {
        this.controller = controller;
        if (controller.getTotal() > 0) {
            currentIndex = 1;
            ative = controller.getReceita(currentIndex);
        } else {
            currentIndex = -1;
            ative = null;
        }
    }
//nao fazer
    public void add() {
        //Capturar o nome da receita.
        //Procura no Catalogo a receita com o mesmo nome.
        //Se encontrar, mostra mensagem.
        //Se NÃO encontrar continua.
        //Capturar dados da nova receita.
        //Cria uma nova receita
        //Passa a receita para o Catalogo adicionar.
        //Torna a nova receita a ativa.
//        view();
    }

    public void find() {
        int total = controller.getTotal();
        //Capturar o nome da receita.
        System.out.println("Entre com o nome da receita");
        Scanner scanner = new Scanner(System.in);
        String receitaProcurada = scanner.nextLine();
        //Procura no Catalogo a receita com o mesmo nome.
        for( int i = 1; i <= total; i++){
            ative = controller.getReceita(i);
            if(receitaProcurada.toUpperCase().equals(ative.getNome().toUpperCase())){
                ative = controller.getReceita(i);
                break;
            }else{
                ative = null;
            }
        }
    }

    public void view() {
        String tela = "";
        do {
            if (ative == null) {
                //Se NÃO estiver com uma receita ativa, mostra mensagem.
                tela = "Nenhuma receita encontrada!";
            } else {
                //Se estiver com uma receita ativa, continua.
                //Monta o layout da tela com os dados da receita.
                tela = ative.toString();
            }
            //Exibe o layout montado.
            System.out.println(tela);
            //Exibe o menu de opções.
        } while (showMenu());
    }

    private boolean showMenu() {
        System.out.println("#".repeat(100));
        System.out.println("  + : Adicionar  ");
        if (controller.getTotal() > 0) {
            System.out.println("  - : Remover  ");
            System.out.println("  P : Próxima  ");
            System.out.println("  A : Anterior  ");
            System.out.println("  L : Localizar  ");
        }
        System.out.println("# # # # # # # # # # # ".concat("#".repeat(78)));
        System.out.println("  X : Sair  ");
        System.out.println("#".repeat(100));

        Scanner scanner = new Scanner(System.in);
        char opcao = scanner.next().toUpperCase().charAt(0);
        switch (opcao) {
            case '+':
                add();
                break;
            case '-':
                if (ative != null) del();
                break;
            case 'P':
                if (ative != null) next();
                break;
            case 'A':
                if (ative != null) previous();
                break;
            case 'L':
                if (controller.getTotal() > 0) {
                    find();
                }
                break;
            case 'X':
                System.out.println("Obrigado!!");
                return false;
            default:
                System.out.println("Opção inválida!!!");
        }
        return true;
    }
    public void next() {
        //Se estiver com uma receita ativa, ativa a próxima receita.
        //Se NÃO estiver com uma receita ativa, ativa a primeira receita.
        if (ative != null) {
            currentIndex++;
        }
        try {
            ative = controller.getReceita(currentIndex);
        } catch (IllegalArgumentException e) {
            ative = null;
        }
        if (ative == null) {
            currentIndex = 1;
            ative = controller.getReceita(currentIndex);
        }
    }

    public void previous() {
        //Se estiver com uma receita ativa, ativa a anterior receita.
        //Se NÃO estiver com uma receita ativa, ativa a última receita.
        if (ative != null) {
            currentIndex--;
        }
       try {
           ative = controller.getReceita(currentIndex);
        } catch (IllegalArgumentException e) {
          ative = null;
        }
        if (ative == null) {
            currentIndex = controller.getTotal();
            ative = controller.getReceita(currentIndex);
        }
    }

    public void del() {
        //Se NÃO estiver com uma receita ativa, mostra mensagem.
        //Se estiver com uma receita ativa, confirma a operação.
        //Se confirmar, solicita ao Catalogo apagar a receita.

        System.out.println("Você deseja realmente APAGAR a receita " + ative.getNome() + "?\nS - Sim   N - Não");
        Scanner scanner = new Scanner(System.in);
        char opcao;
        do {
            opcao = scanner.next().toUpperCase().charAt(0);
            if (opcao == 'S') {
                controller.del(ative.getNome());
                 break;
            } else if (opcao== 'N'){
               break;
            } else {
                System.out.println("Opção inválida!!!");
            }
        } while (true);

    }
    //não fazer
    public void edit() {
        //Se NÃO estiver com uma receita ativa, mostra mensagem.
        //Se estiver com uma receita ativa, abra a tela de edição.
//        view();
    }
}