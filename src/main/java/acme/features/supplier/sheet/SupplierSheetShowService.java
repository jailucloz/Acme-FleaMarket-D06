
package acme.features.supplier.sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
import acme.entities.sheets.Sheet;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierSheetShowService implements AbstractShowService<Supplier, Sheet> {

	// Internal state -------------------------------------------------------------

	@Autowired
	SupplierSheetRepository repository;


	// AbstractShowService<Supplier, Sheet> interface -------------------------------

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

		request.unbind(entity, model, "indexer", "title", "description", "photo");
	}

	@Override
	public Sheet findOne(final Request<Sheet> request) {
		assert request != null;

		Sheet result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneSheetById(id);

		return result;
	}

}
