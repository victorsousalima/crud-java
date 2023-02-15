package model.funcoes;

import java.io.IOException;

public class Menu {

    public static void printMenu() {
        System.out.println("------------------------");
        System.out.println("1 - Inserir Usuário");
        System.out.println("2 - Consultar Usuário");
        System.out.println("3 - Atualizar Usuário");
        System.out.println("4 - Deletar Usuário");
        System.out.println("0 - Sair");
        System.out.println();
    }

    public static void printConsultarUsuario() {
        System.out.println("------------------------");
        System.out.println("1 - Consultar Usuário Por ID");
        System.out.println("2 - Consultar Todos os Usuários");
    }

    public static void printAtualizar() {
        System.out.println("------------------------");
        System.out.println("1 - Atualizar Somente um Dado");
        System.out.println("2 - Atualizar Todos os Dados");
    }
}
