
package acme.features.supplier.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.entities.sheets.Sheet;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierItemUpdateService implements AbstractUpdateService<Supplier, Item> {

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

		request.unbind(entity, model, "ticker", "title", "itemCategory", "creationMoment", "price", "descriptionText", "additionalInformation", "finalMode", "photo");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isPositive, isEuro, uniqueIndexer, isSpam;

		if (entity.getFinalMode()) {

			if (!errors.hasErrors("finalMode")) {
				uniqueIndexer = this.uniqueSheetIndexer(entity.getId());
				errors.state(request, uniqueIndexer, "finalMode", "errors.item.finalMode.unique", "Is finalMode when the indexer is no unique");
			}

			if (!errors.hasErrors("finalMode")) {
				isSpam = this.esSpam(entity.getId());
				errors.state(request, !isSpam, "finalMode", "errors.item.finalMode.spam", "Is finalMode when it’s not considered spam");
			}

		}

		if (!errors.hasErrors("price")) {
			isPositive = entity.getPrice().getAmount() > 0;
			errors.state(request, isPositive, "price", "errors.item.price.positive", "The price must be positive");
		}

		if (!errors.hasErrors("price")) {
			isEuro = entity.getPrice().getCurrency().equals("EUR") || entity.getPrice().getCurrency().equals("€");
			errors.state(request, isEuro, "price", "errors.item.price.euro", "The price must be in euro '€' / 'EUR'");
		}

	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private boolean uniqueSheetIndexer(final Integer itemId) {

		List<Sheet> sheets = (List<Sheet>) this.repository.findSheetByItemId(itemId);
		Boolean res = true;

		for (Sheet s : sheets) {

			for (int i = 0; i < sheets.size(); i++) {
				if (s.getIndexer().equals(sheets.get(i).getIndexer())) {
					res = false;
					break;
				}
			}

		}

		return res;
	}

	private boolean esSpam(final Integer idItem) {
		Boolean result = false;
		Item item = this.repository.findOneItemById(idItem);
		Customisation customisation = this.repository.findCustomisation();
		String[] words = customisation.getSpam().trim().split(",");
		Collection<String> collectionWords = new ArrayList<String>();
		for (String w : words) {
			collectionWords.add(w);
		}

		for (String cw : collectionWords) {
			if (item.getTitle().contains(cw) || item.getAdditionalInformation().contains(cw) || item.getDescriptionText().contains(cw)) {
				result = true;
			}
		}
		return result;
	}

}
