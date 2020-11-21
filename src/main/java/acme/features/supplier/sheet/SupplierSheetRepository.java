
package acme.features.supplier.sheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.sheets.Sheet;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierSheetRepository extends AbstractRepository {

	@Query("select s from Sheet s where s.id = ?1")
	Sheet findOneSheetById(int id);

	@Query("select s from Sheet s where s.item.id = ?1")
	Collection<Sheet> findManyByItemId(int id);

	@Query("select i from Item i where i.id = ?1")
	Item findItemForThisSheet(int id);

	@Query("select s.item from Sheet s where s.id = ?1")
	Item findItem(int id);

	@Query("select s from Sheet s")
	Collection<Sheet> findManyAll();

}
