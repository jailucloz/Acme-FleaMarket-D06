
package acme.features.administrator.suggestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.suggestions.Suggestion;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSuggestionsUpdateService implements AbstractUpdateService<Administrator, Suggestion> {

	// Internal state ---------------------------------------------------------------------------------

	@Autowired
	AdministratorSuggestionsRepository repository;


	// AbstractCreateService<Authenticated, Suggestion> interface --------------------------------------

	@Override
	public boolean authorise(final Request<Suggestion> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Suggestion> request, final Suggestion entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Suggestion> request, final Suggestion entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "email");
	}

	@Override
	public Suggestion findOne(final Request<Suggestion> request) {
		assert request != null;

		Suggestion result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Suggestion> request, final Suggestion entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Suggestion> request, final Suggestion entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
