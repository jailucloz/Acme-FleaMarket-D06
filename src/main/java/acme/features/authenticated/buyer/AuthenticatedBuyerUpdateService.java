
package acme.features.authenticated.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Buyer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedBuyerUpdateService implements AbstractUpdateService<Authenticated, Buyer> {

	@Autowired
	private AuthenticatedBuyerRepository repository;


	@Override
	public boolean authorise(final Request<Buyer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Buyer> request, final Buyer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "email", "phoneNumber", "deliveryAddres", "creditCard");
	}

	@Override
	public Buyer findOne(final Request<Buyer> request) {
		assert request != null;

		Buyer result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneBuyerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Buyer> request, final Buyer entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Buyer> request, final Response<Buyer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
