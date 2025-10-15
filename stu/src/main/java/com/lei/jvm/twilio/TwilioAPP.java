package com.lei.jvm.twilio;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCreator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan
 */
@Slf4j
public class TwilioAPP {

    public static void main(String[] args) {

    }

    private void verifyCreate() {
        Twilio.init("", "");
        VerificationCreator creator = Verification.creator("VAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "+15017122661", "sms");
        Verification verification = creator.create();
        System.out.println(verification.getStatus());
    }

    private void verifyCheck() {
        Twilio.init("", "");
        VerificationCheck verificationCheck = VerificationCheck.creator("VAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                .setTo("+15017122661")
                .setCode("123456")
                .create();
        System.out.println(verificationCheck.getStatus());
    }


}
