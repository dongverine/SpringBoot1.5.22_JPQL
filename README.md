# QueryDSL 사용시 Q class가 읽히지 않을 때 
[https://sung-studynote.tistory.com/11](https://sung-studynote.tistory.com/11)

```
폴더 우클릭 -> BuildPath -> Configure Build Path ->Source탭의 Add Folder를 클릭해서 경로를 추가해준다.

=> /build/generated/sources/annotationProcessor/java/main
```


# STS로 초기에 불러왔을때 스프링등의 라이브러리가 안불러오질 경우
```
프로젝트 마우스Right 클릭 > Configure > Add Gradle Nature
```

# 외부파일 연동 
```
## WebMvcConfig.java
@Slf4j
@Configuration
//@EnableWebMvc//application.properties를 쓰지 않는다?
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /*
        SpringBoot1.5 에서는 extends WebMvcConfigurerAdapter를 한다. 2.0에서는 없어진 추상클래스
        SpringBoot2.x 에서는 implements WebMvcConfigurer 한다.
    */
    @Value("${resources.location_1}")
    private String resourcesLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("resourcesLocation : {}",resourcesLocation);
        registry.addResourceHandler("/exp/**")
                .addResourceLocations("file:"+resourcesLocation);

    }
```

```
## application.properties
resources.location_1=///c:/workspace/
```