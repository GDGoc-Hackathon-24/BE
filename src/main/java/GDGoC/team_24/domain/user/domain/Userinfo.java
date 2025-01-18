package GDGoC.team_24.domain.user.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Userinfo {
    @Id
    @GeneratedValue
    private static Long id;

    private static HOUSE house;

    private static Long sun;
    private static Long daughter;
    private static String hubby;
    private static String medicine;

}
