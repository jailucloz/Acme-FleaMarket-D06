
package acme.features.supplier.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.requests.RequestItem;
import acme.entities.roles.Supplier;
import acme.entities.sheets.Sheet;
import acme.features.supplier.request.SupplierRequestRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SupplierItemDeleteService implements AbstractDeleteService<Supplier, Item> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	SupplierItemRepository		repository;

	@Autowired
	SupplierRequestRepository	supplierRequestRepository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "supplier");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "creationMoment", "descriptionText", "additionalInformation", "finalMode");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// un item se puede borrar siempre y cuando no se haya solicitado

		Collection<RequestItem> requests;
		requests = this.repository.findRequestByItemId(entity.getId());

		if (requests != null && !requests.isEmpty() && entity.getFinalMode() != false) {
			errors.state(request, false, "ticker", "errors.item.hasRequestItems", "An item can be deleted if it is not involved in any requests");
		}

		// validacion: tiene que ser draft mode para poder ser eliminado, osea finalMode = false

		if (requests == null && requests.isEmpty() && entity.getFinalMode() != false) {
			errors.state(request, false, "finalMode", "errors.item.finalMode", "An item can be deleted as long as it is in draft mode");
		}

	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		// hay que borrar tambien los requestItems y los Sheet, porque están relacionados con los items

		Collection<RequestItem> requests = this.repository.findRequestByItemId(entity.getId());
		this.supplierRequestRepository.deleteAll(requests);
		Collection<Sheet> sheets = this.repository.findSheetByItemId(entity.getId());
		this.repository.deleteAll(sheets);

		this.repository.delete(entity);
	}

}
