
package acme.features.supplier.request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.requests.RequestItem;
import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/request-item/")
public class SupplierRequestController extends AbstractController<Supplier, RequestItem> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierRequestListMineService	listMineService;

	@Autowired
	private SupplierRequestListOrderService	listOrderService;

	@Autowired
	private SupplierRequestShowService		showService;

	@Autowired
	private SupplierRequestAcceptService	acceptService;

	@Autowired
	private SupplierRequestRejectService	rejectService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ORDER, BasicCommand.LIST, this.listOrderService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);
		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.UPDATE, this.rejectService);

	}

}
