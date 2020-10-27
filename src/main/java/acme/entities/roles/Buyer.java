
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Buyer extends UserRole {

	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@NotBlank
	@Email
	private String				email;

	@NotNull
	@Pattern(regexp = "([+(\\d]{1})(([\\d+() -.]){5,16})([+(\\d]{1})", message = "{default.buyer.error.phone-number-pattern}")
	private String				phoneNumber;

	@NotBlank
	private String				deliveryAddres;

	@NotBlank
	@CreditCardNumber
	private String				creditCard;

}
