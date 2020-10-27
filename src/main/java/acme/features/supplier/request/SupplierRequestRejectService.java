
package acme.features.supplier.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestItem;
import acme.entities.requests.RequestStatus;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierRequestRejectService implements AbstractUpdateService<Supplier, RequestItem> {

	@Autowired
	SupplierRequestRepository repository;


	@Override
	public boolean authorise(final Request<RequestItem> request) {
		assert request != null;

		boolean isPending = true;

		int requestId = request.getModel().getInteger("id");
		RequestStatus status = this.repository.findOneRequestById(requestId).getStatus();

		if (status.equals(RequestStatus.REJECTED) || status.equals(RequestStatus.ACCEPTED)) {
			isPending = false;
		}

		return isPending;
	}

	@Override
	public void bind(final Request<RequestItem> request, final RequestItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status", "supplier", "buyer");
	}

	@Override
	public void unbind(final Request<RequestItem> request, final RequestItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("isPending", true);

		request.unbind(entity, model);
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

	@Override
	public void validate(final Request<RequestItem> request, final RequestItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("rejectJustification")) {
			boolean justification = request.getModel().getString("rejectJustification").equals("");
			errors.state(request, !justification, "rejectJustification", "supplier.request.error.justification");
		}
	}

	@Override
	public void update(final Request<RequestItem> request, final RequestItem entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(RequestStatus.REJECTED);

		this.repository.save(entity);

	}
}
