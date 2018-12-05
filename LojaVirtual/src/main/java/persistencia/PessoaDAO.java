package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.Fone;
import beans.Pessoa;
import beans.Produto;

public class PessoaDAO implements Serializable{


private static final long serialVersionUID = 1L;

	public static void inserir(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(pessoa);
		t.commit();
		sessao.close();
	}

	public static void alterar(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(pessoa);
		t.commit();
		sessao.close();
	}

	public static void excluir(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static List<Pessoa> listagem(String filtro){
		Session sessao= HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro != null && !filtro.isEmpty()) {
			consulta =sessao.createQuery("from Pessoa where pes_nome like :parametro order by pes_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		else {
			consulta = sessao.createQuery("from Pessoa order by pes_nome");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static Pessoa buscar(String email) {
		Session sessao= HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(Pessoa.class);
		criteria.add(Restrictions.eq("email", email));
		Pessoa pessoa = (Pessoa) criteria.uniqueResult();
		sessao.close();
		
		return pessoa;
	}
	
	// Procedimentos para FONE
	
	public static void excluirFone(Fone fone) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(fone);
		t.commit();
		sessao.close();
	}
	//Novo
	public static List<Fone> listagemFone(Pessoa pessoa){
		Session sessao= HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		consulta = sessao.createQuery("from Fone f where f.pessoa = :pes_id")
				.setParameter("pes_id", pessoa);
		
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	


}
