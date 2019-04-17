package tarefas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarefa {
		
//		ID é o identificador da tarefa, que necessita ser única para não ter problemas ao adicionar na base em memória (é utilizado H2)
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;	

		public enum Status {
			NAO_INICIADA,
			EM_PROGRESSO,
			TERMINADA,
			BLOQUEADA;
		}
		
		String descricao;
		String dataCriacao;
		Status situacao;
		String responsavel;
		float horasEstimadas;

	    public Long getId() {
	        return id;
	    }
	 
	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    public String getDescricao() {
	        return descricao;
	    }
	     
	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }
	    
	    public String getDataCriacao() {
	        return dataCriacao;
	    }
	     
	    public void setDataCriacao(String dataCriacao) {
	        this.dataCriacao = dataCriacao;
	    }
	    
	    public String getResponsavel() {
	        return responsavel;
	    }
	     
	    public void setResponsavel(String responsavel) {
	        this.responsavel = responsavel;
	    }
	    
	    public float getHorasEstimadas() {
	        return horasEstimadas;
	    }
	 
	    public void setHorasEstimadas(float horasEstimadas) {
	        this.horasEstimadas = horasEstimadas;
	    }
}