
package acme.features.buyer.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.roles.Buyer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BuyerItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = ?1")
	Item findOneItemById(int id);

	@Query("select b from Buyer b where b.userAccount.id = ?1")
	Buyer findBuyer(int accountId);

	@Query("select i from Item i where i.finalMode=true")
	Collection<Item> findManyItem();

}
