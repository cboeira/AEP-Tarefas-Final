package tarefas;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tarefa")
@RestController 
public class TarefasController {
	
	@Autowired
	private Tarefas tarefas;
	
//	Mapeamento de metodo HTTP Post, /tarefa, onde recebe no corpo da requisicao, as informacoes para adicionar uma nova tarefa na lista, em formato JSON
	@PostMapping
	public Tarefa adicionarTarefa(@Valid @RequestBody Tarefa tarefa) { 
		 return tarefas.save(tarefa); 
	}
	
//	Mapeamento de metodo GET, /tarefa, onde devolve todas as tarefas já cadastradas
	@GetMapping
	public List<Tarefa> listar() {
			return tarefas.findAll();
	}
	
// 	Mapeamento de metodo GET, /tarefa/ID, onde devolve a tarefa com o ID utilizado na requisicao.
//	Utiliza ResponseEntity para retornar os codigos HTTP, caso não encontre (método findById é Long, caso não encontre, retorna um NULL), retornar um 404
//	Se encontrar, retorna a tarefa e o código 200.
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
		Tarefa tarefa = tarefas.findById(id).orElse(null);
		if (tarefa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tarefa);
	}

//	Mapeamento do metodo PUT, /tarefa/ID, onde é alterada a tarefa com o ID utilizado na requisicao.
//	Utiliza os dados em JSON no corpo da requisicao como o PostMapping, e realiza busca e retorno como o metodo GET por ID.
// 	Para a atualizacao da tarefa, utiliza um bean para realizar a copia da tarefa inteira	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		Tarefa atual = tarefas.findById(id).orElse(null);
		
		if (atual == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(tarefa,  atual, "id");
		atual = tarefas.save(atual);
		
		return ResponseEntity.ok(atual);
	}
	
//	Mapeamento do metodo DELETE, /tarefa/ID, onde é removida a tarefa com o ID utilizado na requisicao.
//	Trabalha da mesma forma que o GET por ID, porém realiza a delecao da tarefa encontrada.	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Tarefa tarefa = tarefas.findById(id).orElse(null);
		if (tarefa == null) {
			return ResponseEntity.notFound().build();
		}
		
		tarefas.delete(tarefa);
		return ResponseEntity.noContent().build();
	}
	 
}