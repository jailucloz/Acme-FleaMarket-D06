
package acme.features.supplier.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.RequestItem;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierRequestRepository extends AbstractRepository {

	//@Query("select sh.item from Sheet sh where sh.item.supplier.id =?1")
	//Collection<RequestItem> findRequestItems(int requestId);

	@Query("select r from RequestItem r where r.id =?1")
	RequestItem findOneRequestById(int requestId);

	@Query("select r from RequestItem r where r.supplier.id = ?1")
	Collection<RequestItem> findManyBySupplierId(int supplierId);

	@Query("Select r from RequestItem r where r.supplier.id = ?1 order by r.item.ticker, r.item.creationMoment")
	Collection<RequestItem> findManyRequestBySupplierOrderBy(int employerId);

}
