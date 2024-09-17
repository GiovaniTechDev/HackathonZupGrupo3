package com.grupo3.app.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

// @formatter:off
public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML,
			  dialectPrefix,
			  null,
			  false,
			  "classforerror",
			  true,
			  1000,
			  true
	 	);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
							 IElementTagStructureHandler structureHandler) {

		boolean temErro = FieldUtils.hasErrors(context, attributeValue);
		if (temErro) {
			String classesExistentes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classesExistentes + " is-invalid");
		}
	}

}
