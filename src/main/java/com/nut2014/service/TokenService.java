package com.nut2014.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nut2014.entity.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {
    //10天
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 1;

    public String getToken(User user) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        String token = "";
        token = JWT.create().withAudience(user.getId() + "").withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(user.getPassWord()));
        return token;
    }


}
