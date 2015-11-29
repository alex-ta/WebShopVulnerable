package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import database.dao.*;
import database.extension.DatabaseException;
import database.mapper.SpringUser;

@Service
public class UserService implements UserDetailsService {
 
    @Autowired
    private UserDao userDao;
    @Autowired
    private SpringUser springUser;

    @Override
    public SpringUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
			springUser.setUser(userDao.select(username));
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return springUser;
    }
    
}