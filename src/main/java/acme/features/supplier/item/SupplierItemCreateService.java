
package acme.features.supplier.item;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierItemCreateService implements AbstractCreateService<Supplier, Item> {

	@Autowired
	SupplierItemRepository repository;


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

		request.unbind(entity, model, "ticker", "title", "itemCategory", "price", "descriptionText", "additionalInformation", "finalMode", "photo");
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		Item result;
		result = new Item();

		Principal principal;
		int userAccountId;
		Supplier supplier;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		supplier = this.repository.findOneSupplierByUserAccount(userAccountId);

		result.setSupplier(supplier);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isPositive, isEuro;

		Boolean unique = null;
		unique = this.repository.findTickerOfItem(entity.getTicker()) != null;
		errors.state(request, !unique, "ticker", "errors.item.ticker.unique", "The reference must be unique");

		if (!errors.hasErrors("price")) {
			isPositive = entity.getPrice().getAmount() > 0;
			errors.state(request, isPositive, "price", "errors.item.price.money.amount-positive", "The amount must be positive");
		}

		if (!errors.hasErrors("price")) {
			isEuro = entity.getPrice().getCurrency().equals("EUR") || entity.getPrice().getCurrency().equals("€");
			errors.state(request, isEuro, "price", "errors.item.price.money.euro", "The money must be in euro '€' / 'EUR'");
		}

	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		this.repository.save(entity);
	}
}
