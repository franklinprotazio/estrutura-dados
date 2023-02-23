package one.digitalinnovation;

public class ListaDuplamenteEncadeada<T> {
	
	private NoDuplo<T> primeiroNo;
	private NoDuplo<T> ultimoNo;
	
	private int tamanhoLista = 0;
	
	public ListaDuplamenteEncadeada() {
		this.primeiroNo = null;
		this.ultimoNo = null;
		this.tamanhoLista = 0;
	}
	
	public T get(int index) {
		return this.getNo(index).getConteudo();
	}
	
	public void add(T elemento) {
		NoDuplo<T> novoNO = new NoDuplo<>(elemento);
		novoNO.setNoProximo(null);
		novoNO.setNoPrevio(ultimoNo);
		if(primeiroNo == null) {
			primeiroNo = novoNO;
		}
		if(ultimoNo != null) {
			ultimoNo.setNoProximo(novoNO);
		}
		ultimoNo = novoNO;
		tamanhoLista++;
	}
	
	public void add(int index, T elemento) {
		NoDuplo<T> noAuxiliar = getNo(index);
		NoDuplo<T> novoNo = new NoDuplo<>(elemento);
		novoNo.setNoProximo(noAuxiliar);
				
		if(novoNo.getNoProximo() != null) {
			novoNo.setNoPrevio(noAuxiliar.getNoPrevio());
			novoNo.getNoProximo().setNoPrevio(novoNo);
		} else {
			novoNo.setNoPrevio(ultimoNo);
			ultimoNo = novoNo;
		}
		
		if(index == 0) {
			primeiroNo = novoNo;
		}else {
			novoNo.getNoPrevio().setNoProximo(novoNo);
		}
		tamanhoLista++;	
	}
	
	public void remove(int index) {
		if(index == 0) {
			primeiroNo = primeiroNo.getNoProximo();
			if(primeiroNo != null) {
				primeiroNo.setNoPrevio(null);
			}
		}else {
			NoDuplo<T> noAuxiliar = getNo(index);
			noAuxiliar.getNoPrevio().setNoProximo(noAuxiliar.getNoProximo());
			if(noAuxiliar != ultimoNo) {
				noAuxiliar.getNoProximo().setNoPrevio(noAuxiliar.getNoPrevio());
			}else {
				ultimoNo = noAuxiliar;
			}
		}	
		this.tamanhoLista--;	
	}
	
	private NoDuplo<T> getNo(int index) {
		NoDuplo<T> noAuxilar = primeiroNo;
		for(int i = 0 ; (i < index) && (noAuxilar != null) ; i++) {
			noAuxilar = noAuxilar.getNoProximo();
		}
		return noAuxilar;
	}
	
	public int size() {
		return this.tamanhoLista;
	}

	@Override
	public String toString() {
		String strRetorno = "";
		
		NoDuplo<T> noAuxiliar = primeiroNo;
		for(int i = 0; i < size(); i++) {
			strRetorno += "[No[conteudo=" + noAuxiliar.getConteudo() + "}]--->";
			noAuxiliar = noAuxiliar.getNoProximo();
		}
		
		strRetorno += "null";
		return strRetorno;
	}
	
	

}
