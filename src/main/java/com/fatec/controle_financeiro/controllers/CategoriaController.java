package com.fatec.controle_financeiro.controllers;

import com.fatec.controle_financeiro.entities.Categoria;
import com.fatec.controle_financeiro.domain.Categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")  // Mapeia a URL /categorias
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método GET para listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    // Método POST para criar uma nova categoria
    @PostMapping
    public ResponseEntity<?> criarCategoria(@RequestBody Categoria categoria) {
        
        // Verificar se já existe uma categoria com a mesma descrição
        Optional<Categoria> categoriaExistente = categoriaRepository.findByDescricao(categoria.getDescricao());
        
        if (categoriaExistente.isPresent()) {
            return ResponseEntity.status(400).body("Erro: A descrição da categoria já está em uso.");
        }
        
        // Salvar a nova categoria
        Categoria novaCategoria = categoriaRepository.save(categoria);
        
        return ResponseEntity.ok(novaCategoria);  // Retorna a categoria criada com o ID
    }
}
