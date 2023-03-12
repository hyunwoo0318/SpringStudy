package Lim.practiceJwt.jwt;

import Lim.practiceJwt.Customer;
import Lim.practiceJwt.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByLoginId(username)
                .map(c -> createCustomerDetail(c))
                .orElseThrow(() -> new RuntimeException("해당하는 user을 찾을수 없습니다."));

    }

    private UserDetails createCustomerDetail(Customer customer){
        return User.builder()
                .username(customer.getLoginId())
                .password(passwordEncoder.encode(customer.getPassword()))
                .roles(customer.getRoles().toString())
                .build();
    }
}
