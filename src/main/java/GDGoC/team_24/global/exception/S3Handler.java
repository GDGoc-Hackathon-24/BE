package GDGoC.team_24.global.exception;

import GDGoC.team_24.global.code.BaseErrorCode;

public class S3Handler extends GeneralException {
    public S3Handler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
