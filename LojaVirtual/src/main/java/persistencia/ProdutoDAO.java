package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.Produto;

	public class ProdutoDAO implements Serializable{


	private static final long serialVersionUID = 1L;

	public static void inserir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(produto);
		t.commit();
		sessao.close();
	}

	public static void alterar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(produto);
		t.commit();
		sessao.close();
	}

	public static void excluir(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(produto);
		t.commit();
		sessao.close();
	}
	
	public static List<Produto> listagem(String filtro){
		Session sessao= HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		if(filtro != null && !filtro.isEmpty()) {
			consulta =sessao.createQuery("from Produto where pro_nome like :parametro order by pro_nome");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		else {
			consulta = sessao.createQuery("from Produto order by pro_nome");
		}
		List lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	// Busca produto pelo seu ID
	public static Produto buscar(Integer id){
		Session sessao= HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("id", id));
		Produto prod = (Produto) criteria.uniqueResult();
		sessao.close();
		
		return prod;
	}

}
