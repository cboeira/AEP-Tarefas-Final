package tarefas;

import org.springframework.data.jpa.repository.JpaRepository;

//lista de tarefas, utilizando JPA Repository, para funcoes de insercao de tarefas, delecao e procura

public interface Tarefas extends JpaRepository<Tarefa, Long> {



}