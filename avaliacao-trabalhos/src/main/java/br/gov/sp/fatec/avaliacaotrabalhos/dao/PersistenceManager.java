package br.gov.sp.fatec.avaliacaotrabalhos.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	/*Padrão de projeto Singleton
	 * é utilizado para restringir a classe a apenas uma instancia no projeto inteiro*/
	private static PersistenceManager manager;
	
	/*avaliacao é o mesmo arquivo configurado no persistence.xml*/
	private EntityManagerFactory factory;
	
	/*para restringir a apenas uma instancia, criar um construtor private dessa classe*/
	private PersistenceManager(){
		
	}

	/*não precisa instanciar a classe para chamar um método estático*/
	public static PersistenceManager getInstance() {
		if(manager == null) {
			/*manager é privado mas dentro da classe é possível acessar*/
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
	//se já existe, retorna
	//se não, cria e retorna
	public EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
}
