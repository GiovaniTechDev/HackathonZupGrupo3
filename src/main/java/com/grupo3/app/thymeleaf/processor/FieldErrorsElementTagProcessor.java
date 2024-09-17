package com.grupo3.app.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

// @formatter:off
public class FieldErrorsElementTagProcessor extends AbstractElementTagProcessor {

	public FieldErrorsElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML,
			  dialectPrefix,
			  "field-errors-object",
			  true,
			  null,
			  false,
			  1000
	 	);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {

		IAttribute fieldObject = tag.getAttribute("fieldObject");
		IModelFactory modelFactory = context.getModelFactory();
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block",
														  "th:replace",
														  String.format("~{fragments/alerts :: fielderrors-object('${%s.*}')}",
																		fieldObject.getValue())));
		structureHandler.replaceWith(model, true);
	}

}
