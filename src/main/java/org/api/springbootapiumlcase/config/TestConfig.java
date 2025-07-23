package org.api.springbootapiumlcase.config;

import org.api.springbootapiumlcase.domain.*;
import org.api.springbootapiumlcase.domain.enums.CustomerType;
import org.api.springbootapiumlcase.domain.enums.StatePayment;
import org.api.springbootapiumlcase.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public TestConfig(CategoryRepository categoryRepository, ProductRepository productRepository, StateRepository stateRepository, CityRepository cityRepository, CustomerRepository customerRepository, AddressRepository addressRepository, OrderRepository orderRepository, PaymentRepository paymentRepository, OrderItemRepository orderItemRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${test.user.password.customer1}")
    private String customer1Password;

    @Value("${test.user.password.customer2}")
    private String customer2Password;

    @Override
    public void run(String... args) {

        Category category1 = Category.builder().name("Electronics").build();
        Category category2 = Category.builder().name("Books").build();
        Category category3 = Category.builder().name("Computers").build();

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));

        Product product1 = Product.builder().name("Laptop Lenovo").price(3000.0).build();
        Product product2 = Product.builder().name("Printer").price(1200.0).build();
        Product product3 = Product.builder().name("The Hobbit").price(45.0).build();
        Product product4 = Product.builder().name("9600 XT 16GB").price(3000.0).build();

        product1.getCategoryList().add(category3);
        product2.getCategoryList().add(category1);
        product3.getCategoryList().add(category2);
        product4.getCategoryList().add(category1);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));

        State state1 = State.builder()
                .name("Minas Gerais")
                .build();
        State state2 = State.builder()
                .name("São Paulo")
                .build();

        City city1 = City.builder()
                .name("Uberlândia")
                .state(state1)
                .build();

        City city2 = City.builder()
                .name("São Paulo")
                .state(state2)
                .build();

        City city3 = City.builder()
                .name("Belo Horizonte")
                .state(state1)
                .build();

        state1.getCities().addAll(Arrays.asList(city3, city1));
        state2.getCities().add(city2);

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city3, city2, city1));

        Customer customer1 = Customer.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .identificationNumber("12345678900")
                .password(passwordEncoder.encode(customer1Password))
                .customerType(CustomerType.INDIVIDUAL)
                .build();

        // ...

        Customer customer2 = Customer.builder()
                .name("ACME Corp")
                .email("contact@acme.com")
                .identificationNumber("12345678000199")
                .customerType(CustomerType.LEGAL_ENTITY)
                .password(passwordEncoder.encode(customer2Password))
                .build();

        customer2.getPhones().add("11998877665");

        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        Address address1 = Address.builder()
                .street("Main Street")
                .number("123")
                .complement("Apt 4B")
                .neighborhood("Downtown")
                .zipCode("12345-678")
                .customer(customer1)
                .state(state1)
                .city(city1)
                .build();

        Address address2 = Address.builder()
                .street("Secondary Street")
                .number("456")
                .neighborhood("Uptown")
                .zipCode("87654-321")
                .customer(customer1)
                .state(state2)
                .city(city2)
                .build();

        Address address3 = Address.builder()
                .street("Business Avenue")
                .number("789")
                .neighborhood("Business District")
                .zipCode("11223-344")
                .customer(customer2)
                .state(state2)
                .city(city2)
                .build();

        addressRepository.saveAll(Arrays.asList(address1, address2, address3));

        customer1 = customerRepository.findAll().getFirst();
        address1 = addressRepository.findAll().getFirst();


        Order order1 = Order.builder()
                .customer(customer1)
                .deliverAddress(address1)
                .instant(LocalDateTime.now())
                .build();

        Payment payment1 = CreditPayment.builder()
                .order(order1)
                .statePayment(StatePayment.PENDING)
                .numberOfInstallments(3)
                .build();
        order1.setPayment(payment1);

        Customer customer3 = Customer.builder()
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .identificationNumber("09876543211")
                .customerType(CustomerType.INDIVIDUAL)
                .build();

        customer3.getPhones().add("11988776655");

        customerRepository.save(customer3);

        Address address4 = Address.builder()
                .street("Third Avenue")
                .number("101")
                .neighborhood("Westside")
                .zipCode("54321-876")
                .customer(customer3)
                .state(state1)
                .city(city3)
                .build();

        addressRepository.save(address4);

        Order order2 = Order.builder()
                .customer(customer3)
                .deliverAddress(address4)
                .instant(LocalDateTime.now())
                .build();

        Payment payment2 = PixPayment.builder()
                .order(order2)
                .statePayment(StatePayment.PAYD)
                .expireDate(LocalDate.now().plusDays(1))
                .datePayment(LocalDate.now())
                .build();
        order2.setPayment(payment2);

        orderRepository.saveAll(Arrays.asList(order2, order1));
        paymentRepository.saveAll(Arrays.asList(payment2, payment1));

        OrderItem orderItem1 = OrderItem.builder()
                .order(order1)
                .product(product1)
                .discount(0.0)
                .quantity(1)
                .price(product1.getPrice())
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .order(order1)
                .product(product3)
                .discount(0.0)
                .quantity(2)
                .price(product3.getPrice())
                .build();

        OrderItem orderItem3 = OrderItem.builder()
                .order(order2)
                .product(product2)
                .discount(100.0)
                .quantity(4)
                .price(product2.getPrice())
                .build();

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
    }
}
