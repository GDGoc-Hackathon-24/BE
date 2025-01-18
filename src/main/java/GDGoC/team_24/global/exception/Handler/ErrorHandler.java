package GDGoC.team_24.global.exception.Handler;

import GDGoC.team_24.global.code.BaseErrorCode;
import GDGoC.team_24.global.exception.GeneralException;

public class ErrorHandler extends GeneralException {
    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
