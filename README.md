# Blog

## 技术栈

- java 17
- springboot 3.1.0
- mybatis-plus 3.5.5
- postgerSQL 16

## 初始化项目

- pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.fanqie</groupId>
    <artifactId>blog</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>blog</name>
    <description>blog</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>17</java.version>
        <mybatis-plus.version></mybatis-plus.version>
        <springdoc.version>2.3.0</springdoc.version>
        <fastjson.version>1.2.80</fastjson.version>
        <jjwt.version>0.9.2</jjwt.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <optional>true</optional>
        </dependency>
        <!--redis依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!--fastjson依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson.version}</version>
        </dependency>
        <!--jwt依赖-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.5</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.30</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

- application.yml

```yaml
spring:
  application:
    name: blog
  datasource:
    username: fanqie
    password: 1125
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://192.168.31.216:5432/blog
  sql:
    init:
      mode: always
  data:
    redis:
      host: 192.168.31.216
      port: 6379
mybatis-plus:
  mapper-locations: classpath*:/mapper/**Mapper.xml
  configuration:
    map-underscore-to-camel-case: true
```

- MybatisPlusConfig.java

```java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@Configuration
public class MybatisPlusConfig {

    /**
     * 配置SqlSessionFactory
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        sessionFactory.setGlobalConfig(globalConfig);
        
        // 配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);
        
        // 设置XML文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
        
        // 设置别名包
        sessionFactory.setTypeAliasesPackage("com.fanqie.blog.entity");
        
        return sessionFactory.getObject();
    }

    /**
     * 配置分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }
} 
```

- RedisConfig.java

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * Redis配置类
 */
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
```

- FastJsonRedisSerializer.java

```java
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.util.Assert;
import java.nio.charset.Charset;

/**
 * Redis使用FastJson序列化
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T>
{

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static
    {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz)
    {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException
    {
        if (t == null)
        {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }


    protected JavaType getJavaType(Class<?> clazz)
    {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
```

- Result.java

```java
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.io.Serializable;
/**
 *   接口返回数据格式
 */
@Data
public class Result<T> implements Serializable {
	public boolean isSuccess() {
		return success;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "";

	/**
	 * 返回代码
	 */
	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public Result() {
	}

    /**
     * 兼容VUE3版token失效不跳转登录页面
     * @param code
     * @param message
     */
	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Result<T> success(String message) {
		this.message = message;
		this.code = 200;
		this.success = true;
		return this;
	}

	public static<T> Result<T> ok() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		return r;
	}

	public static<T> Result<T> ok(String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		//Result OK(String msg)方法会造成兼容性问题 issues/I4IP3D
		r.setResult((T) msg);
		r.setMessage(msg);
		return r;
	}

	public static<T> Result<T> ok(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		return r;
	}

	/**
	 * 此方法是为了兼容升级所创建
	 *
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static<T> Result<T> OK(String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		//Result OK(String msg)方法会造成兼容性问题 issues/I4IP3D
		r.setResult((T) msg);
		return r;
	}

	public static<T> Result<T> OK(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK(String msg, T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> error(String msg, T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(false);
		r.setCode(500);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> error(String msg) {
		return error(500, msg);
	}
	
	public static<T> Result<T> error(int code, String msg) {
		Result<T> r = new Result<T>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

	public Result<T> error500(String message) {
		this.message = message;
		this.code = 500;
		this.success = false;
		return this;
	}

	/**
	 * 无权限访问返回结果
	 */
	public static<T> Result<T> noauth(String msg) {
		return error(501, msg);
	}

	@JsonIgnore
	private String onlTable;

}
```

- RedisCache.java

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache
{
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     * 
     * @param key
     * @param hkey
     */
    public void delCacheMapValue(final String key, final String hkey)
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}
```

- JwtUtils.java

```java
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
```

## SpringSecurity配置

- 实现UserDetails

```java
@Data
public class LoginUser implements UserDetails {

    /**
     * @Fields user : 存储用户的完整信息，通常是从数据库中查询得到的 TbUser 实体类。
     * 这个对象包含了用户的基本信息，例如 ID、用户名、密码等。
     */
    private TbUser user;

    /**
     * @return Collection<? extends GrantedAuthority>
     * @Description 获取用户的权限集合。
     * GrantedAuthority 是 Spring Security 中代表用户所拥有的权限的接口，通常是角色（Role）。
     * 在这个实现中，直接返回一个空的 List，意味着当前用户没有任何权限。
     * 在实际应用中，你需要根据用户的角色从数据库或其他来源加载权限信息，并将其封装成 GrantedAuthority 对象返回。
     * 例如，你可以创建一个实现了 GrantedAuthority 接口的类来表示用户的角色，并在这里返回包含这些角色对象的集合。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @return String
     * @Description 获取用户的密码。
     * 这个方法应该返回用户的加密后的密码，该密码通常存储在数据库中。
     * 在这个实现中，直接返回一个空字符串，这在实际应用中是不正确的。
     * 你需要从 `user` 对象中获取用户的密码，并确保返回的是加密后的形式。
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return String
     * @Description 获取用户的用户名。
     * 这个方法应该返回用户的唯一标识符，通常是登录时使用的用户名。
     * 在这个实现中，直接返回一个空字符串，这在实际应用中是不正确的。
     * 你需要从 `user` 对象中获取用户的用户名。
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return boolean
     * @Description 判断用户的账户是否已过期。
     * 如果返回 `true`，则表示账户未过期，用户可以正常登录。
     * 如果返回 `false`，则表示账户已过期，用户无法登录。
     * 在这个实现中，直接返回 `false`，意味着账户总是被视为已过期。
     * 在实际应用中，你需要根据用户的账户状态（例如，是否设置了过期日期）来判断。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户的账户是否被锁定。
     * 如果返回 `true`，则表示账户未被锁定，用户可以正常登录。
     * 如果返回 `false`，则表示账户已被锁定，用户无法登录。
     * 账户可能由于多次登录失败等原因而被锁定。
     * 在这个实现中，直接返回 `false`，意味着账户总是被视为已锁定。
     * 在实际应用中，你需要根据用户的账户状态（例如，是否设置了锁定标志）来判断。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户的凭证（密码）是否已过期。
     * 如果返回 `true`，则表示凭证未过期，用户可以正常登录。
     * 如果返回 `false`，则表示凭证已过期，用户可能需要重置密码。
     * 在这个实现中，直接返回 `false`，意味着凭证总是被视为已过期。
     * 在实际应用中，你可能需要根据密码的更新时间等来判断。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户是否已启用。
     * 如果返回 `true`，则表示用户已启用，可以正常登录。
     * 如果返回 `false`，则表示用户已被禁用，无法登录。
     * 用户可能由于某些原因（例如，管理员禁用）而被禁用。
     * 在这个实现中，直接返回 `false`，意味着用户总是被视为未启用。
     * 在实际应用中，你需要根据 `user` 对象中的状态字段来判断用户是否已启用。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

```

- UserDetailsServiceImpl.java

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username = "admin";
        //查询用户
        TbUser user = tbUserMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("账号不存在");
        }
        return new LoginUser(user);
    }

    public UserDetails loadUserById(String userId) {
        TbUser user = tbUserMapper.selectById(Integer.valueOf(userId));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(user);
    }

}
```

- JwtAuthenticationFilter.java `token`认证过滤器

```java
@Component // 标记为 Spring 组件，使其能够被 Spring 容器管理
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired // 自动注入 UserDetailsServiceImpl 实例，用于加载用户详细信息
    private UserDetailsServiceImpl userDetailsService;

    @Override // 重写 OncePerRequestFilter 中的 doFilterInternal 方法，该方法会在每个请求处理过程中执行一次
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 从 HTTP 请求中获取 JWT 字符串
        String jwt = getJwtFromRequest(request);

        // 判断 JWT 字符串是否有效（不为空且不是空白字符）
        if (StringUtils.hasText(jwt)) {
            try {
                // 使用 JwtUtil 工具类解析 JWT 字符串，获取 Claims 对象（包含了 JWT 的声明信息）
                Claims claims = JwtUtil.parseJWT(jwt);
                // 判断 Claims 对象是否存在且 Token 未过期
                if (!Objects.isNull(claims) && !isTokenExpired(claims)) {
                    // 从 Claims 中获取用户标识，这里假设 subject 存储的是用户 ID
                    String userId = claims.getSubject();
                    // 使用从 JWT 中提取的用户 ID 加载用户的详细信息
                    UserDetails userDetails = userDetailsService.loadUserById(userId);
                    // 创建一个 UsernamePasswordAuthenticationToken 对象，用于 Spring Security 的认证
                    // 参数分别为：userDetails（用户信息）、null（密码，因为 JWT 认证不需要再次验证密码）、userDetails.getAuthorities()（用户拥有的权限）
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    // 设置认证信息的详细信息，例如请求信息
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将创建的认证对象设置到 Spring Security 的安全上下文（SecurityContextHolder）中
                    // 这表示当前用户已通过认证，后续的请求可以直接访问受保护的资源
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // 如果 JWT 解析或验证过程中发生异常，打印错误信息，表示 JWT 验证失败
                System.err.println("JWT 验证失败: " + e.getMessage());
                // 注意：这里没有设置认证失败的状态，请求会继续传递给后续的过滤器，
                // 如果后续的权限控制需要认证，则会因为 SecurityContextHolder 中没有有效的认证信息而拒绝访问。
            }
        }

        // 将请求传递给过滤器链中的下一个过滤器
        filterChain.doFilter(request, response);
    }

    // 从 HttpServletRequest 中提取 JWT 字符串的方法
    private String getJwtFromRequest(HttpServletRequest request) {
        // 从请求头中获取名为 "Authorization" 的 Header 值
        String bearerToken = request.getHeader("Authorization");
        // 判断 Authorization Header 是否有效且以 "Bearer " 开头（这是 JWT 的标准格式）
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 如果符合 Bearer 格式，则截取 "Bearer " 之后的 Token 字符串（"Bearer " 的长度是 7）
            return bearerToken.substring(7);
        }
        // 如果 Authorization Header 不符合 Bearer 格式，则返回整个 bearerToken (可能为空或包含其他认证信息)
        // 注意：这里的返回逻辑可能需要根据实际需求调整，如果只接受 Bearer Token，可以返回 null。
        return bearerToken;
    }

    // 判断 JWT 是否已过期的方法
    private boolean isTokenExpired(Claims claims) {
        // 从 Claims 中获取过期时间（Expiration Time）
        Date expirationDate = claims.getExpiration();
        // 判断过期时间是否在当前时间之前，如果在之前则表示已过期
        return expirationDate.before(new Date());
    }
}
```

- SecurityConfig

```java
import com.fanqie.blog.controller.filter.JwtAuthenticationFilter;
import com.fanqie.blog.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 标记为配置类，用于定义 Spring Bean
@EnableWebSecurity // 启用 Spring Security 的 Web 安全功能
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * @Bean
     * @Description 定义密码编码器 Bean。
     * Spring Security 推荐使用密码编码器对用户密码进行加密存储，而不是明文。
     * BCryptPasswordEncoder 是一种常用的强密码编码算法。
     * 将其声明为 Bean 后，可以在其他需要使用密码编码的地方进行依赖注入。
     * @return PasswordEncoder 密码编码器实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * @Bean
     * @Description 配置 Spring Security 的安全过滤器链。
     * SecurityFilterChain 定义了哪些请求应该被拦截，以及如何进行认证和授权。
     *
     * @param http HttpSecurity 构建器，用于配置 HTTP 请求的安全规则
     * @return SecurityFilterChain 安全过滤器链实例
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .disable()
                        //.loginPage("/auth/login") // 指定你的自定义登录页面
                        //.loginProcessingUrl("/auth/login") // 处理登录请求的 URL (与 form action 相同)
                        //.defaultSuccessUrl("/", true) // 登录成功后重定向的 URL
                        //.failureUrl("/auth/login?error") // 登录失败后重定向的 URL
                )
                .authenticationProvider(authenticationProvider()) // 注册 DaoAuthenticationProvider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 将 JWT 验证过滤器添加到 UsernamePasswordAuthenticationFilter 之前;
        return http.build();
    }

    /**
     * @Bean
     * @Description 配置认证管理器 Bean。
     * AuthenticationManager 是 Spring Security 中负责认证的核心组件。
     * 通常情况下，你需要配置一个 AuthenticationProvider 来告诉 Spring Security 如何进行用户认证（例如，从数据库查询用户信息并验证密码）。
     * 通过 AuthenticationConfiguration 可以获取默认的 AuthenticationManager。
     *
     * @param authenticationConfiguration 认证配置对象
     * @return AuthenticationManager 认证管理器实例
     * @throws Exception 获取过程中可能抛出的异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 你可能还需要配置一个 UserDetailsService Bean 来告诉 Spring Security 如何加载用户信息。
    // 例如：
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new CustomUserDetailsService(); // 你的自定义 UserDetailsService 实现
    // }
}
```

### 登录认证

