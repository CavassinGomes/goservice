package com.soulcode.goserviceapp.repository;

import com.soulcode.goserviceapp.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT * FROM enderecos WHERE id = ?", nativeQuery = true)
    Endereco findEnderecoById(Long id);

    @Modifying
    @Query(value = "UPDATE enderecos e SET e.logradouro = ?, e.numero = ?, e.complemento = ?, e.cidade = ?, e.uf = ? WHERE e.id = ?", nativeQuery = true)
    void updateEndById(String logradouro, Integer numero, String complemento, String cidade, String uf, Long id);
}