
package acme.features.buyer.request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestItem;
import acme.entities.roles.Buyer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class BuyerRequestListMineService implements AbstractListService<Buyer, RequestItem> {

	@Autowired
	BuyerRequestRepository repository;


	@Override
	public boolean authorise(final Request<RequestItem> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<RequestItem> request, final RequestItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment");
	}

	@Override
	public Collection<RequestItem> findMany(final Request<RequestItem> request) {
		assert request != null;

		Collection<RequestItem> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyByBuyerId(principal.getActiveRoleId());

		return result;
	}

}
