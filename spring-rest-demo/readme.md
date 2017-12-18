# Spring Boot

## 1. main中的Annotations

[@SpringBootApplication](), which is a combination of the following more specific spring annotations:

- [@Configuration](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html) : Any class annotated with `@Configuration` annotation is bootstrapped by Spring and is also considered as a source of other bean definitions.

- [@EnableAutoConfiguration](https://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/api/org/springframework/boot/autoconfigure/EnableAutoConfiguration.html) : This annotation tells Spring to automatically configure your application based on the dependencies that you have added in the `pom.xml` file.

  For example, If `spring-data-jpa` is in the classpath, then it automatically tries to configure a `DataSource` by reading the database properties from `application.properties` file.

- [@ComponentScan](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html) : It tells Spring to scan and bootstrap other components defined in the current package (i.e. main class suggested to put under the root package) and all the sub-packages.

## 2. controller中的Annotations

### 2.1 RESTFul

[@RestController](), is a combination of following annotations

- [@Controller]()
- [@ResponseBody]()

[@RequestMapping("/url")]()

@GetMapping("/url/{param}")

- @PathVariable() 

@PostMapping("/url")

- @Valid, 会与Model中的一些Annotation关联去验证有效性，类如：@NotBlank。如果验证失败会返回 `400 BadRequest`
- @RequestBoday

@PutMapping("/url/{id}"), 用来update某个数据项

@DeleteMapping("/url/{id}")



## 3. model中的Annotations

```java
@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    // Getters and Setters ... (Omitted for brevity)
}
```

- [@Entity]()
- [@Table]()
- [@Id]()
- [@Generatedvalue]()
- [@NotBlank]()
- [@Column](), By default, a field named `createdAt` is mapped to a column named `created_at` in the database table. i.e. all camel cases are replaced with underscores.
- [@Temporal](), annotation is used with `java.util.Date` and `java.util.Calendar` classes. It converts the date and time values from Java Object to compatible database type and vice versa.
- [@JsonIgnoreProperties](), annotation is used on `class level`类级别. This annotation is used because we don’t want the clients of the rest api to supply the `createdAt` and `updatedAt` values.
  - allowGetters, 是value中被忽略的属性的getter方法有效，比如返回查询时会用到。
- [@JsonIgnore](), 是属性级别的

### Auditing

为了使日期属性自动填充，我们需要开启审计监听。（TODO：还有那些Annotation是可以被监听的）

- **@CreateDate**
- **@LastNodifiedDate**
- **@EntityListeners(AuditingEntityListener.class)**, 
- **@EnableJpaAuditing**, set this annotation in main class

## 4. 配置文件

- **application.yml**

```properties
###
#   Database Settings
###
spring:
  datasource:
    url: jdbc:h2:file:~/demo
    platform: h2
    username: sa
    password:
    driverClassName: org.h2.Driver
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        use_sql_comments: true
        format_sql: true

###
#   H2 Settings
###
  h2:
    console:
      enabled: true
      path: /h2
      settings:
        trace: false
        web-allow-others: false
```

- **application.properties**

```properties
###
#   Database Settings
###
spring.datasource.url=jdbc:h2:mem:example-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.platform=h2
spring.datasource.username = sa
spring.datasource.password =
spring.datasource.driverClassName = org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

###
#   H2 Settings
###
spring.h2.console.enabled=true
spring.h2.console.path=/console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false

###
#   Hibernate Settings
###
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.show_sql=false
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.format_sql=false
```



## 5. **Test**

POST: localhost:8080/api/notes/

```json
{"title": "My First Note", "content": "Spring Boot is awesome"}
```

