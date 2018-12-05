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
import beans.PedidoItem;
import beans.Pessoa;
import utils.Utilitarios;

public class ItemDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public static Set<PedidoItem> listagem(PedidoCabecalho pedido) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = sessao.createCriteria(PedidoItem.class);
		criteria.add(Restrictions.eq("pedido", pedido));
		criteria.addOrder(Order.desc("id"));		
		Set<PedidoItem> itens = new HashSet<>(criteria.list());
		sessao.close();
		return itens;
	}

}
