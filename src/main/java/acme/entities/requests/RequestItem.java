
package acme.entities.requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import acme.entities.items.Item;
import acme.entities.roles.Buyer;
import acme.entities.roles.Supplier;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestItem extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{supplier.request.ticker.pattern}")
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotNull
	@Positive
	private Integer				quantity;

	private String				notes;

	private String				rejectJustification;

	@NotNull
	private RequestStatus		status;

	// Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Item				item;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Supplier			supplier;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Buyer				buyer;

}
