
package acme.features.supplier.sheet;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
import acme.entities.sheets.Sheet;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class SupplierSheetListService implements AbstractListService<Supplier, Sheet> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	SupplierSheetRepository repository;


	// AbstractListService<Supplier, Sheet> interface ---------------------------------

	@Override
	public boolean authorise(final Request<Sheet> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Sheet> request, final Sheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "indexer", "description", "photo", "item");
	}

	@Override
	public Collection<Sheet> findMany(final Request<Sheet> request) {
		assert request != null;

		Collection<Sheet> result;

		int itemId;

		itemId = request.getModel().getInteger("id");

		result = this.repository.findManyByItemId(itemId);

		return result;
	}

}
