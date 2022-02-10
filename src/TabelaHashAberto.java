public class TabelaHashAberto{

	/**
	 * Tabela hash com o método da multiplicacao (A=1/3), com endereçamento aberto e rehash 
	 * quadratico (rh(k, i)=(3i2+2i+h(k))%m. 
	 */
	
	//Atributos
	private User[] tabela = new User[10];
	private int quant;


	/**
	 * calcula o hash da chave inteira id, utilizando o método da divisão
	 * @param id chave identificadora do objeto
	 * @return o índice mapeado
	 */
	public int hash(int id) {
		return (int)(((id*((float)1/3))%1)*tabela.length);
	}
	
	/**
	 * calcula o hash em caso de conflito de indice na tabela
	 * @param indice do elemento em conflito e numero de tentativas
	 * @return novo índice mapeado
	 */
	public int rehash(int id, int i){
		return (int) ((3*(Math.pow(i, 2))+(2*i) + hash(id))%tabela.length);
	}
	
	/**
	 * método para averiguar se há espaços vazios na tabela (a tabela pode conter um atributo para contar a quantidade de elementos)
	 * @return verdadeiro se não houver espaços onde possam ser inseridos novos elementos
	 */
	public boolean isCheia() {
		if(tabela.length == quant)
			return true;
		return false;
	}

	/**
	 * adiciona o usuário passado por parâmetro da tabela hash
	 * @param u usuário que será salvo na tabela
	 * @throws Exception lança uma exceção quando não houver espaços disponíveis
	 */
	public void adicionar(User u) throws Exception {
		if(isCheia()){
			throw new Exception("Tabela Cheia.");
		}else{
			int ind = hash(u.getId());
			if(tabela[ind] == null){
				tabela[ind] = u;
			}else{
				int tentativas = 0;
				do {
					tentativas++;
					ind = rehash(u.getId(), tentativas);
				}while(tabela[ind] != null);
				tabela[ind] = u;
			}
			quant++;
		}	
	}

	/**
	 * o método retorna o usuário com id igual ao recebido como parâmetro de entrada
	 * @param id id do usuário que se deseja recuperar
	 * @return retorna o objeto usuário com o id igual ao recebido como parâmetro
	 * @throws Exception lança uma exceção caso o usuário não seja encontrada
	 */
	public User recuperar(int id) throws Exception {
		int ind = hash(id);
		int indTeste = ind;
		User elemento = tabela[ind];
		if(elemento == null){
			throw new Exception("Elemento não encontrado.");
		}
		if(elemento.getId() == id){
			return elemento;
		}else {
			int tentativas = 0;
			do {
				tentativas++;
				ind = rehash(elemento.getId(), tentativas);
				elemento = tabela[ind];
				if(elemento == null){
					throw new Exception("Elemento não encontrado.");
				}
			}while(ind != indTeste && elemento.getId() !=id);
			if(ind == indTeste){
				throw new Exception("Elemento não encontrado.");
			}
			return elemento;
		}
	}

	/**
	 * Exibe todos os elementos
	 */
	public void print() {
		for(int i = 0; i < tabela.length; i++){
			if(tabela[i] == null){
				System.out.println("["+i+"] - vazio");
			}else{
				System.out.println("["+i+"] - "+tabela[i].toString());
			}
		}	
	}

	/**
	 * função que dobra o tamanho da tabela hash e realoca todos os elementos existentes em seus novos índices
	 */
	public void crescer() {
		User[] auxiliar = tabela;
		tabela = new User[tabela.length*2];
		quant = 0;
		for(int i = 0; i < auxiliar.length; i++){
			if(auxiliar[i] != null)
				try{
					this.adicionar(auxiliar[i]);
				}catch(Exception e){}
		}
	}

	/**
	 * Retorna quantidade de elementos do Hash
	 */
	public int qtd() {
		return quant;
	}

}
