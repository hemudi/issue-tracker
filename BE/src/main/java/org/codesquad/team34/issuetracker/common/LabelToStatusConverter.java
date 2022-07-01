package org.codesquad.team34.issuetracker.common;

import org.springframework.core.convert.converter.Converter;

public class LabelToStatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        return Status.fromLabel(source);
    }
}
