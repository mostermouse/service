package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIfs;

public interface APiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
