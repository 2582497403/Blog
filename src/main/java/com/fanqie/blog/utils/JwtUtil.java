package com.fanqie.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类 (使用 JJWT 0.12.3)
 */
public class JwtUtil {

    // 有效期为 1 小时
    public static final Long JWT_TTL = 60 * 60 * 1000L;
    // 存储生成的安全密钥 (Base64 编码)
    // 注意：在生产环境中，请使用更安全的方式管理密钥，例如环境变量、配置文件或专门的密钥管理服务
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final String JWT_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成 JWT
     *
     * @param subject token 中要存放的数据（json 格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成 JWT
     *
     * @param subject   token 中要存放的数据（json 格式）
     * @param ttlMillis token 超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 创建 JWT
     *
     * @param id        token 的唯一 ID
     * @param subject   token 中要存放的数据（json 格式）
     * @param ttlMillis token 超时时间
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              // 唯一的 ID
                .setSubject(subject)   // 主题  可以是 JSON 数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(secretKey, signatureAlgorithm) // 使用 HS256 对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    public static void main(String[] args) {
        // 示例生成 JWT
        String subject = "{\"userId\": 123, \"username\": \"testUser\"}";
        String generatedToken = createJWT(subject);
        System.out.println("生成的 JWT: " + generatedToken);

        // 立即解析刚刚生成的 JWT
        try {
            Claims claims = parseJWT(generatedToken);
            System.out.println("解析生成的 JWT 成功，Claims: " + claims);
        } catch (Exception e) {
            System.err.println("解析刚刚生成的 JWT 失败: " + e.getMessage());
        }

        // 你提供的示例 JWT
        String providedToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNzIwMjRhZDMzZjU0NWZmODhmZGU5ZjRiYzBmZWYxMCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTc0NDg4NDE3NSwiZXhwIjoxNzQ0ODg3Nzc1fQ.lbLAL4PSCow7aoWmihcsES6COU3f8SkhqVk_MPZkawo";
        try {
            Claims claimsProvided = parseJWT(providedToken);
            System.out.println("解析提供的 JWT 成功，Claims: " + claimsProvided);
        } catch (Exception e) {
            System.err.println("解析提供的 JWT 失败: " + e.getMessage());
        }
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] decodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    /**
     * 解析 JWT
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build() // 确保包含 build() 方法
                .parseClaimsJws(jwt)
                .getBody();
    }
}