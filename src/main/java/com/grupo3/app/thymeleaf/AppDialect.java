package com.grupo3.app.thymeleaf;

import com.grupo3.app.thymeleaf.processor.*;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppDialect extends AbstractProcessorDialect {

    public AppDialect() {
        super("RecruTechPCD Dialeto", "recrutechpcd", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processadores = new HashSet<>();
        processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
        processadores.add(new AlertMessageElementTagProcessor(dialectPrefix));
        processadores.add(new FieldErrorsElementTagProcessor(dialectPrefix));
        processadores.add(new OrderElementTagProcessor(dialectPrefix));
        processadores.add(new PaginationElementTagProcessor(dialectPrefix));
        return processadores;
    }

}
