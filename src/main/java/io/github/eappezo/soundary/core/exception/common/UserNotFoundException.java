package io.github.eappezo.soundary.core.exception.common;

import io.github.eappezo.soundary.core.exception.APIException;
import io.github.eappezo.soundary.core.exception.CommonErrorCode;

public class UserNotFoundException extends APIException {
    public UserNotFoundException() {
        super(CommonErrorCode.USER_NOT_FOUND);
    }
}
