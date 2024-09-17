package com.grupo3.app.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

// @formatter:off
public class AlertMessageElementTagProcessor extends AbstractElementTagProcessor {

	public AlertMessageElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML,
			  dialectPrefix,
			  "alert",
			  true,
			  null,
			  false,
			  1000
	 	);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
		IModelFactory modelFactory = context.getModelFactory();
		IModel model = modelFactory.createModel();
        model.add(modelFactory.createStandaloneElementTag("th:block",
                                                          "th:replace",
                                                          "~{fragments/alerts :: alert(${type}, ${message}, ${bi})}"));

//        model.add(modelFactory.createStandaloneElementTag("th:block",
//                                                          "th:replace",
//                                                          "~{fragments/alerts :: success(${mensagem})}"));

//        model.add(modelFactory.createStandaloneElementTag("th:block",
//                                                          "th:replace",
//                                                          "~{fragments/alerts :: fielderrors-object('${credencial.*}')}"));

        structureHandler.replaceWith(model, true);
    }

}
