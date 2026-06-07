package com.vocallabs.outreach.util;

public class EmailUtil {

    public static String createEmail(
            String name,
            String company) {

        return """
                Hi %s,

                We help companies automate
                customer outreach.

                Would love to connect.

                Regards,
                Chandrakanta
                """.formatted(name);
    }
}