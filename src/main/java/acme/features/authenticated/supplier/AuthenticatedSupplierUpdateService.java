
package acme.features.authenticated.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
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
public class AuthenticatedSupplierUpdateService implements AbstractUpdateService<Authenticated, Supplier> {

	@Autowired
	private AuthenticatedSupplierRepository repository;


	@Override
	public boolean authorise(final Request<Supplier> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Supplier> request, final Supplier entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Supplier> request, final Supplier entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "homePage", "itemCategory", "description");
	}

	@Override
	public Supplier findOne(final Request<Supplier> request) {
		assert request != null;

		Supplier result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneSupplierByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Supplier> request, final Supplier entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Supplier> request, final Supplier entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Supplier> request, final Response<Supplier> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
