package GDGoC.team_24.global.exception.Handler;

import GDGoC.team_24.global.code.BaseErrorCode;
import GDGoC.team_24.global.exception.GeneralException;

public class S3Handler extends GeneralException {
    public S3Handler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
