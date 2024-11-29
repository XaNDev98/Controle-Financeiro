package com.fatec.controle_financeiro.domain.Categoria;

import com.fatec.controle_financeiro.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para buscar categoria por descrição
    Optional<Categoria> findByDescricao(String descricao);
}
