package com.cangu.app.util;

import com.cangu.app.constans.CommonConstants;
import com.cangu.app.persistence.entity.SessionUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author ZhengFeiFei
 * @version V1.0
 */
public class JwtUtils {

    /**
     * 生成token
     * @param sessionUser
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(SessionUser sessionUser, int expire) throws Exception {
        String token = Jwts.builder()
                .setSubject(sessionUser.getName())
                .claim(CommonConstants.USER_ID, sessionUser.getId())
                .claim(CommonConstants.USER_NAME, sessionUser.getName())
                .claim(CommonConstants.USER_TYPE, sessionUser.getType())
                .claim(CommonConstants.USER_ACCOUNT, sessionUser.getAccount())
                .claim(CommonConstants.VALID_TIME_MILLONS,new Date(System.currentTimeMillis()+expire/2))
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256, CommonConstants.JWT_PUBLIC_KEY)
                .compact();
        return token;
    }

    public static String generateToken(SessionUser sessionUser, int expire, String jwtKey) throws Exception {
        String token = Jwts.builder()
                .setSubject(sessionUser.getName())
                .claim(CommonConstants.USER_ID, sessionUser.getId())
                .claim(CommonConstants.USER_NAME, sessionUser.getName())
                .claim(CommonConstants.USER_TYPE, sessionUser.getType())
                .claim(CommonConstants.USER_ACCOUNT, sessionUser.getAccount())
                .claim(CommonConstants.VALID_TIME_MILLONS,new Date(System.currentTimeMillis()+expire/2))
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();
        return token;
    }


    /**
     * 解密token
     * @param token
     * @return
     * @throws Exception
     */
    public static SessionUser getInfoFromToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(CommonConstants.JWT_PUBLIC_KEY).parseClaimsJws(token)
                .getBody();
        return new SessionUser(Long.valueOf(claims.get(CommonConstants.USER_ID).toString()), claims.get(CommonConstants.USER_NAME).toString(), claims.get(CommonConstants.USER_ACCOUNT).toString(), Integer.valueOf(claims.get(CommonConstants.USER_TYPE).toString()), Long.valueOf(claims.get(CommonConstants.VALID_TIME_MILLONS).toString()));
    }

    public static SessionUser getInfoFromToken(String token, String jwtKey) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtKey).parseClaimsJws(token)
                .getBody();
        return new SessionUser(Long.valueOf(claims.get(CommonConstants.USER_ID).toString()), claims.get(CommonConstants.USER_NAME).toString(), claims.get(CommonConstants.USER_ACCOUNT).toString(), Integer.valueOf(claims.get(CommonConstants.USER_TYPE).toString()), Long.valueOf(claims.get(CommonConstants.VALID_TIME_MILLONS).toString()));
    }
}
