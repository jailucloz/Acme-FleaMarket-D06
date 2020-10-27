
package acme.features.supplier.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierItemShowService implements AbstractShowService<Supplier, Item> {

	@Autowired
	SupplierItemRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Item item;
		Supplier supplier;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(applicationId);
		supplier = item.getSupplier();
		principal = request.getPrincipal();
		result = supplier.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "itemCategory", "creationMoment", "finalMode", "descriptionText", "price", "additionalInformation", "photo");
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

}
