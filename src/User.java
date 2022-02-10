
public class User {

	/**
	 * Objeto utilizado para testar as funcionalidades da tabela
	 */
	
	private String nome;
	private int id;
	
	public User(int idade, String nome) {
		this.nome = nome;
		this.id = idade;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return nome+" (ID "+id+")";
	}
}
