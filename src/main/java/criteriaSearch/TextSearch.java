package criteriaSearch;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextSearch<Result> {

	private List<String>annotatedFields;
	private SessionFactory sessionFactory;
	private Class<?> clazz;
	
	public TextSearch(SessionFactory sessionFactory, Class<?> clazz){
		this.sessionFactory = sessionFactory;
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	public List<Result> search(String query){
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		Transaction transaction = fullTextSession.beginTransaction();
		List<Result> list = null;
		QueryBuilder b = fullTextSession.getSearchFactory()
		    .buildQueryBuilder().forEntity(clazz).get();

		org.apache.lucene.search.Query luceneQuery =
		    b.keyword()
		    .onFields("name","description")
		    .matching(query.split(" "))
		    .createQuery();

		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		list = fullTextQuery.list();
		transaction.commit();
		return list;
	}

	public List<String> getAnnotatedFields() {
		return annotatedFields;
	}

	public void setAnnotatedFields(List<String> annotatedFields) {
		this.annotatedFields = annotatedFields;
	}
}
