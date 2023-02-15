package model.entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Usuario implements Serializable {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer id;
    private String nome;
    private String email;
    private LocalDate data_nascimento;
    private String telefone;

    public Usuario() {}

    public Usuario(String nome, String email, LocalDate data_nascimento, String telefone) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
    }

    public Usuario(Integer id, String nome, String email, LocalDate data_nascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.id +
                ", " + this.nome + ", " + this.email + ", " + fmt.format(this.data_nascimento) + ", " + this.telefone;
    }
}
