package br.com.devdojo.essentials.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService <T> {

    public T salvar(T entidade);

    public Page<T> listarTodos(Pageable pageable);

    public List<T> listarTodosNotPage();

    public T buscar(Integer id);

    public T atualizar(T entidade, Integer id);

    void deletar(Integer id);
}