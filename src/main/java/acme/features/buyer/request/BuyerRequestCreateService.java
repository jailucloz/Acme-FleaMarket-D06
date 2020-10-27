
package acme.features.buyer.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.requests.RequestItem;
import acme.entities.requests.RequestStatus;
import acme.entities.roles.Buyer;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BuyerRequestCreateService implements AbstractCreateService<Buyer, RequestItem> {

	@Autowired
	private BuyerRequestRepository repository;


	@Override
	public boolean authorise(final Request<RequestItem> request) {
		assert request != null;

		//Item item = this.repository.findOneItemById(request.getModel().getInteger("id"));

		//return item == null || item.getFinalMode() == false;
		return true;
	}

	@Override
	public void bind(final Request<RequestItem> request, final RequestItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<RequestItem> request, final RequestItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "quantity", "notes");
		model.setAttribute("itemId", entity.getItem().getId());
	}

	@Override
	public RequestItem instantiate(final Request<RequestItem> request) {

		RequestItem result;
		Principal principal;
		Supplier supplier;

		int accountId, itemId;
		principal = request.getPrincipal();
		accountId = principal.getActiveRoleId();
		itemId = request.getModel().getInteger("id");
		result = new RequestItem();

		Item item = this.repository.findOneItemById(itemId);
		supplier = item.getSupplier();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setStatus(RequestStatus.PENDING);
		result.setBuyer(this.repository.findOneBuyerById(accountId));
		result.setItem(this.repository.findOneItemById(itemId));
		result.setSupplier(supplier);

		return result;
	}

	@Override
	public void validate(final Request<RequestItem> request, final RequestItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isPositive;

		Boolean unique = null;
		unique = this.repository.findRequestItemByTicker(entity.getTicker()) != null;

		errors.state(request, !unique, "ticker", "buyer.request.error.duplicatedTicker");

		if (!errors.hasErrors("price")) {
			isPositive = entity.getQuantity() > 0;
			errors.state(request, isPositive, "quantity", "errors.request.quantity.positive", "The quantity must be positive");
		}

	}

	@Override
	public void create(final Request<RequestItem> request, final RequestItem entity) {

		this.repository.save(entity);
	}

}
