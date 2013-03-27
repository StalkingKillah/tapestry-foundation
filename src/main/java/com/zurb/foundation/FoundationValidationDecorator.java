/**
 * 
 */
package com.zurb.foundation;

import org.apache.tapestry5.BaseValidationDecorator;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.Environment;

/**
 * @author nighthawk
 *
 */
public class FoundationValidationDecorator extends BaseValidationDecorator {
	
	private final Environment environment;
	
	private final MarkupWriter writer;

	/**
	 * @param environment
	 * @param markupWriter
	 */
	public FoundationValidationDecorator(final Environment environment, final MarkupWriter writer) {
		super();
		this.environment = environment;
		this.writer = writer;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.tapestry5.BaseValidationDecorator#insideField(org.apache.tapestry5.Field)
	 */
	@Override
	public void insideField(Field field) {
		if (inError(field)) {
//			getCurrentElement().addClassName("error");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.tapestry5.BaseValidationDecorator#afterField(org.apache.tapestry5.Field)
	 */
	@Override
	public void afterField(Field field) {
		if (inError(field)) {
//			ValidationTracker tracker = getTracker();
//			writer.element("span", "class", "error-class");
//			writer.write(tracker.getError(field));
//			writer.end();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.tapestry5.BaseValidationDecorator#insideLabel(org.apache.tapestry5.Field, org.apache.tapestry5.dom.Element)
	 */
	@Override
	public void insideLabel(Field field, Element labelElement) {
		if (field != null) {
			if (inError(field)) {
//				getCurrentElement().addClassName("error");
			}
			if (field.isRequired()) {
//				labelElement.text("*");
			}
		}
	}
	
	private boolean inError(Field field) {
		ValidationTracker tracker = getTracker();
		return tracker.inError(field);
	}
	
	private ValidationTracker getTracker() {
		return environment.peekRequired(ValidationTracker.class);
	}
	
	private Element getCurrentElement() {
		return writer.getElement();
	}
	
}
