
package acme.features.buyer.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestItem;
import acme.entities.roles.Buyer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class BuyerRequestShowService implements AbstractShowService<Buyer, RequestItem> {

	@Autowired
	BuyerRequestRepository repository;


	@Override
	public boolean authorise(final Request<RequestItem> request) {
		assert request != null;

		boolean result;
		int requestId;
		RequestItem requestItem;
		Buyer buyer;
		Principal principal;

		requestId = request.getModel().getInteger("id");
		requestItem = this.repository.findOneRequestById(requestId);
		buyer = requestItem.getBuyer();
		principal = request.getPrincipal();
		result = buyer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<RequestItem> request, final RequestItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "quantity", "notes", "status");
	}

	@Override
	public RequestItem findOne(final Request<RequestItem> request) {
		assert request != null;

		RequestItem result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRequestById(id);

		return result;
	}

}
