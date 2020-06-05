package br.gov.sp.fatec.avaliacaotrabalhos.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	/*Padr�o de projeto Singleton
	 * � utilizado para restringir a classe a apenas uma instancia no projeto inteiro*/
	private static PersistenceManager manager;
	
	/*avaliacao � o mesmo arquivo configurado no persistence.xml*/
	private EntityManagerFactory factory;
	
	/*para restringir a apenas uma instancia, criar um construtor private dessa classe*/
	private PersistenceManager(){
		
	}

	/*n�o precisa instanciar a classe para chamar um m�todo est�tico*/
	public static PersistenceManager getInstance() {
		if(manager == null) {
			/*manager � privado mas dentro da classe � poss�vel acessar*/
			manager = new PersistenceManager();
		}
		return manager;
	}
	public EntityManagerFactory getEntityManagerFactory() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("avaliacao");
		}
			
			return factory;
	}
	//se j� existe, retorna
	//se n�o, cria e retorna
	public EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
}
