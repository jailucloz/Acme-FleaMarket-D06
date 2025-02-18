
package acme.entities.items;

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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Supplier;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(indexes = @Index(columnList = "ticker"))

public class Item extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{supplier.item.ticker.pattern}")
	private String				ticker;

	@NotBlank
	private String				title;

	@NotBlank
	private String				itemCategory;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	@Size(min = 1, max = 256)
	private String				descriptionText;

	@NotNull
	@Valid
	private Money				price;

	@URL
	private String				additionalInformation;

	@URL
	private String				photo;

	@NotNull
	private Boolean				finalMode;

	// Relationship

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Supplier			supplier;

}
