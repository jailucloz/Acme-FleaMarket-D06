
package acme.features.administrator.customisations;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomisationDisplayService implements AbstractShowService<Administrator, Customisation> {

	@Autowired
	private AdministratorCustomisationRepository repository;


	@Override
	public boolean authorise(final Request<Customisation> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Customisation> request, final Customisation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "spam", "itemCategories", "newsCategories");
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		StringBuilder thresholdPercent = new StringBuilder();
		thresholdPercent.append(numberFormat.format(entity.getThreshold()));
		thresholdPercent.append("%");
		model.setAttribute("thresholdPercentage", thresholdPercent.toString());

	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {
		Customisation result;
		result = this.repository.findManyAll().iterator().next();
		return result;
	}

}
