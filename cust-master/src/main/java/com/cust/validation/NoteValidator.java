package com.cust.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cust.model.Note;

@Component
public class NoteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Note.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Note note = (Note) target;
		ValidationUtils.rejectIfEmpty(errors, "message", "note.message.empty");

	}

}
