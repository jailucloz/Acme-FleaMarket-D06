
package acme.features.authenticated.buyer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Buyer;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBuyerRepository extends AbstractRepository {

	@Query("select b from Buyer b where b.userAccount.id = ?1")
	Buyer findOneBuyerByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

}
