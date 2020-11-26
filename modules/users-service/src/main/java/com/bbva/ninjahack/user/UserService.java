package com.bbva.ninjahack.user;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Created by E052826 on 21/11/2020.
 */
@ApplicationScoped
@Transactional(REQUIRED)
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Transactional(SUPPORTS)
    public List<Person> findAll() {
        List<Person> Users = Person.listAll();
        LOGGER.info("Useres: " + Users.toString());
        return Users;
    }

    @Transactional(SUPPORTS)
    public Person login(@Valid Person user) {
    	LOGGER.debug("se busca por: " + user.email);
    	Person result = Person.find("email", user.email).firstResult();
    		   	
    	if (null != result) {
    		// Si existe el usuario se comprueba la password. Si no coinciden el objeto se pone a null
    		if (!user.password.equals(result.password)) {
    			result = null;
    		} else {
    			// Se elimina la password del resultado para que no salga hacia afuera
    			result.password = null;
    		}
    	}
        return result;
    }

    public Person persistUser(@Valid Person user) {
        Person.persist(user);
        return user;
    }

}
