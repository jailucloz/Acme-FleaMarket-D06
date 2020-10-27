
package acme.features.supplier.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestItem;
import acme.entities.requests.RequestStatus;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierRequestShowService implements AbstractShowService<Supplier, RequestItem> {

	@Autowired
	SupplierRequestRepository repository;


	@Override
	public boolean authorise(final Request<RequestItem> request) {
		assert request != null;

		boolean result;
		int requestId;
		RequestItem requestItem;
		Supplier supplier;
		Principal principal;

		requestId = request.getModel().getInteger("id");
		requestItem = this.repository.findOneRequestById(requestId);
		supplier = requestItem.getSupplier();
		principal = request.getPrincipal();
		result = supplier.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<RequestItem> request, final RequestItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.getStatus().equals(RequestStatus.PENDING)) {
			model.setAttribute("isPending", true);
		}

		request.unbind(entity, model, "ticker", "creationMoment", "quantity", "notes", "item.ticker", "item.title", "rejectJustification");
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
