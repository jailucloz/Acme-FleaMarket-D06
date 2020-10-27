
package acme.features.buyer.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.requests.RequestItem;
import acme.entities.roles.Buyer;
import acme.entities.roles.Supplier;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BuyerRequestRepository extends AbstractRepository {

	//@Query("select sh.item from Sheet sh where sh.item.supplier.id =?1")
	//Collection<RequestItem> findRequestItems(int requestId);

	@Query("select r from RequestItem r where r.id =?1")
	RequestItem findOneRequestById(int requestId);

	@Query("select r from RequestItem r where r.buyer.id = ?1")
	Collection<RequestItem> findManyByBuyerId(int buyerId);

	@Query("select i from Item i where i.id = ?1")
	Item findOneItemById(int id);

	@Query("select b from Buyer b where b.id = ?1")
	Buyer findOneBuyerById(int buyerId);

	@Query("select s from Supplier s where s.id = ?1")
	Supplier findOneSupplierById(int supplierId);

	@Query("select r from RequestItem r where r.ticker = ?1")
	RequestItem findRequestItemByTicker(String ticker);
}
