package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//final 키워드가 붙은 필드들을 가지고 생성자를 직접 만들어줌. -> 키워드를 추가할때 매우 편함.
@RequiredArgsConstructor
@MainDiscountPolicy
public class OrderServiceImpl implements  OrderService{

    //생성자 주입을 쓰면 final키워드를 사용해서 안정성이 올라감.
    //final 키워드를 쓰면 생성자 코드 누락을 방지할수 있음.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 구체 클래스에 대한 의존관계를 없애고 인터페이스에만 의존함. -> nullpointer 에러가뜸

    //@Autowired 생성자가 1개이면 autowired 생략이 가능하다.
  //  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //    this.memberRepository = memberRepository;
      //  this.discountPolicy = discountPolicy;
    //}

    //Order 구현체에서 할인 정책까지 정하는중 -> 구체 클래스에 대해서 의존적임. -->> 한 구현체는 하나의 책임만을 가져야함.
  //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  //  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // -->> 다른 대상이 여기에 DiscountPolicy에 구현객체를 넣어줘야함.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
