package database.dao;
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Comment;
@Repository
public class CommentDao extends DAO<Comment>{

	public CommentDao() {
		super(Comment.class);
	}

	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
