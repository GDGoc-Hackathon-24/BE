package GDGoC.team_24.global.code;

import GDGoC.team_24.global.code.ErrorReasonDTO;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();
}
