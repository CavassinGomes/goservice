package com.soulcode.goserviceapp.domain;

import com.soulcode.goserviceapp.domain.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cliente extends Usuario{
    private String telefone;
    @Column(length = 14)
    private String cpf;
    private LocalDate dataNascimento;

    public Cliente() {
        super();
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(Long id, String nome, String email, String senha, Perfil perfil, boolean habilitado, String telefone, String cpf, LocalDate dataNascimento) {
        super(id, nome, email, senha, perfil, habilitado);
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Cliente(Long id, String nome, String email, String senha, Perfil perfil, boolean habilitado) {
        super(id, nome, email, senha, perfil, habilitado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(telefone, cliente.telefone) &&
                Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(dataNascimento, cliente.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone, cpf, dataNascimento);
    }
}
