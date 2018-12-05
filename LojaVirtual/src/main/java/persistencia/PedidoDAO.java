package persistencia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.PedidoCabecalho;
import beans.Pessoa;
import utils.Utilitarios;

public class PedidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void salvar(PedidoCabecalho pedido) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(pedido);
		t.commit();
		sessao.close();
	}

	public static void mudaSituacao(PedidoCabecalho pedido) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(pedido);
		t.commit();
		sessao.close();
	}
	
	@SuppressWarnings("unchecked")
	public static Set<PedidoCabecalho> listagem() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(PedidoCabecalho.class);
		criteria.add(Restrictions.eq("cliente", Utilitarios.getUserLogado()));
		criteria.addOrder(Order.desc("id"));		
		Set<PedidoCabecalho> pedidos = new HashSet<>(criteria.list());
		sessao.close();
		return pedidos;
	}

	@SuppressWarnings("unchecked")
	public static Set<PedidoCabecalho> listagem(Pessoa cliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(PedidoCabecalho.class);
		criteria.add(Restrictions.eq("cliente", cliente));
		criteria.addOrder(Order.desc("id"));		
		Set<PedidoCabecalho> pedidos = new HashSet<>(criteria.list());
		sessao.close();
		return pedidos;
	}

}
