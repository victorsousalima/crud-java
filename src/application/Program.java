package application;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entites.Usuario;
import model.funcoes.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
        int opc;

        do {
            Menu.printMenu();
            System.out.print("Escolha uma opção: ");
            opc = leia.nextInt();
            leia.nextLine();

            if (opc == 1) {
                System.out.print("Nome do usuário:");
                String nome = leia.nextLine();


                System.out.print("Email do usuário: ");
                String email = leia.nextLine();

                System.out.print("Data de nascimento do usuário: ");
                LocalDate date = LocalDate.parse(leia.next(), fmt);
                leia.nextLine();

                System.out.print("Telefone do usuário: ");
                String telefone = leia.nextLine();
                System.out.println();

                Usuario usuario = new Usuario(nome, email, date, telefone);
                usuarioDao.insert(usuario);
            }
            else if (opc == 2) {
                Menu.printConsultarUsuario();
                System.out.print("Escolha uma opção: ");
                int opcConsulta = leia.nextInt();

                if (opcConsulta == 1) {
                    System.out.print("Id do usuário que deseja consultar: ");
                    int id = leia.nextInt();

                    Usuario usuario = usuarioDao.findById(id);
                    System.out.println();
                    System.out.println(usuario);
                }
                else if (opcConsulta == 2) {
                    System.out.println();
                    List<Usuario> usuarios = usuarioDao.findAll();
                    System.out.println();

                    usuarios.forEach(System.out::println);
                }
            }
            else if (opc == 3) {
                System.out.print("Id do usuário que irá atualizar: ");
                int id = leia.nextInt();
                leia.nextLine();

                Usuario usuario = usuarioDao.findById(id);
                if (usuario != null){
                    Menu.printAtualizar();
                    System.out.print("Escolha uma opção: ");
                    int opcUpdate = leia.nextInt();
                    leia.nextLine();

                    if (opcUpdate == 1) {
                        System.out.print("Qual dado deseja atualizar? ");
                        String dado = leia.next();
                        leia.nextLine();

                        if ("Nome".equalsIgnoreCase(dado)) {
                            System.out.print("Novo Nome: ");
                            String nomeNovo = leia.nextLine();
                            usuario.setNome(nomeNovo);
                            usuarioDao.update(usuario);
                        }
                        else if ("Email".equalsIgnoreCase(dado)) {
                            System.out.print("Novo Email: ");
                            String emailNovo = leia.nextLine();
                            usuario.setEmail(emailNovo);
                            usuarioDao.update(usuario);
                        }
                        else if ("data_nascimento".equalsIgnoreCase(dado)) {
                            System.out.print("Nova Data: ");
                            String dataNova = leia.nextLine();
                            usuario.setData_nascimento(LocalDate.parse(dataNova, fmt));
                            usuarioDao.update(usuario);
                        }
                        else if ("telefone".equalsIgnoreCase(dado)) {
                            System.out.print("Novo Telefone: ");
                            String telefoneNovo = leia.nextLine();
                            usuario.setTelefone(telefoneNovo);
                            usuarioDao.update(usuario);
                        }
                    }
                    else if (opcUpdate == 2) {
                        System.out.print("Novo Nome:");
                        String nome = leia.nextLine();

                        System.out.print("Novo Email: ");
                        String email = leia.nextLine();

                        System.out.print("Nova Data de nascimento: ");
                        LocalDate date = LocalDate.parse(leia.next(), fmt);
                        leia.nextLine();

                        System.out.print("Novo Telefone: ");
                        String telefone = leia.nextLine();

                        System.out.println();

                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setData_nascimento(date);
                        usuario.setTelefone(telefone);

                        usuarioDao.update(usuario);
                    }
                }
            }
            else if (opc == 4) {
                System.out.print("Id do usuário que deseja deletar: ");
                int id = leia.nextInt();
                System.out.println();

                usuarioDao.deleteById(id);
            }

        } while (opc != 0);

        DB.closeConnection();

        leia.close();
    }
}
