
package acme.features.supplier.sheet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Supplier;
import acme.entities.sheets.Sheet;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/sheet/")
public class SupplierSheetController extends AbstractController<Supplier, Sheet> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierSheetListService	listService;

	@Autowired
	private SupplierSheetShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);

	}

}
