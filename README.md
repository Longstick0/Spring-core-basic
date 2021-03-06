# Spring-core-basic
------------

## "스프링 컨테이너"
### 'ApplicationContext'를 스프링 컨테이너라고 함.
+ 기존에는 개발자가 'AppConfig'를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.  

  
+ 스프링 컨테이너는 '@Configuration'이 붙은 'AppConfig'를 설정 정보로 사용한다. 여기서 '@Bean'이라는 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.


+ 스프링 빈은 '@Bean'이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.  


+ 이전에는 개발자가 필요한 객체를 'AppConfig'를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)을 찾아야 한다. 스프링 빈은 'applicationContext.getBean' 메서드릴 이용해서 찾을 수 있다.


+ 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경했다.
------------

## "스프링 빈 조회 (상속관계)"
+ 부모 타입으로 조히하면, 자식 타입도 모두 조회된다.
+ 그래서 모든 자바 객체의 최고 부모인 'Object' 타입으로 조회하면, 모든 스프링 빈을 조회한다.
------------

## "싱글톤 패턴과 문제점"
+ ###싱글톤 패턴이란? 어플리케이션이 시작될 때 어떤 클래스가 최초 한번만 메모리를 할당하고(static) 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴이다.


###그러나 싱글톤 패턴에는 문제점이 존재 
  + 싱글톤 패턴 자체를 구현하는 코드가 많이 들어감(귀찮음)
  + 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP(의존성 역전 원칙)위반
    + 의존성 역전이란? 상위 모듈은 하위 모듈에 의존해서는 안된다.
    + 즉, 의존 관계를 맺을 때 변화하기 쉬운 것에 의존하지 말고, 변화하지 않는 것에 의존해야 한다.
  + 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
    + OCP원칙이란? 개방 폐쇄 원칙이라고 하며, 확장에는 열려있고, 변경에는 닫혀 있어야한다. 
  + 테스트하기가 어렵다.
  + 내부 속성을 변경하거나 초기화 하기 어렵다.
  
### 하지만 Spring은 이러한 단점을 모두 보완하고 싱글톤 패턴의 장점만 뽑아서 쓸 수 있다.

------------
## "싱글톤 방식의 주의점"
+ 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계하면 안됨.
+ 무상태(stateless)로 설계해야한다!
  + 특정 클라이언트에 의존적인 필드가 있으면 안된다.
  + 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
  + 가급적 읽기만 가능해야 한다.
  + 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
+ 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있다.
------------
## "스프링 Bean과 @Configuration과 싱글톤"

```java
MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
```
###위 세 줄의 코드를 실행해본다고 가정하자.
```java
@Bean
public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
}

@Bean
public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
}

@Bean
public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
}
```
+ 모두 memberRepository를 호출하는 메서드고 new를 통해 새로운 memberRepository를 생성하고 있다.
+ 하지만 이는 @Configuration 어노테이션을 통해 싱글톤 원칙이 지켜지게 된다.
+ 아래는 각기 다르게 생성될 거 같았던 memberRepository가 모두 같은 것을 확인할 수 있다.
```java
memberRepository = FEB2022.core2.member.MemoryMemberRepository@52d645b1
memberRepository1 = FEB2022.core2.member.MemoryMemberRepository@52d645b1
memberRepository2 = FEB2022.core2.member.MemoryMemberRepository@52d645b1
```