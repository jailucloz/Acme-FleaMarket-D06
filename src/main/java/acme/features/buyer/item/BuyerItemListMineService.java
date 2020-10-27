
package acme.features.buyer.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Buyer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BuyerItemListMineService implements AbstractListService<Buyer, Item> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	BuyerItemRepository repository;


	// AbstractListService<Buyer, Item> interface ---------------------------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "creationMoment");

	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;

		result = this.repository.findManyItem();

		return result;
	}

}
