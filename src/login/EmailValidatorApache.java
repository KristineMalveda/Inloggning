package login;

import org.apache.commons.validator.routines.EmailValidator;

public  class EmailValidatorApache {
	
    private static final EmailValidator validator = EmailValidator.getInstance();


    public static boolean isValidUsingValidator(final String email) {
        return validator.isValid(email);
    }
}
