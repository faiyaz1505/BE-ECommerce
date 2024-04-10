package com.lti.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne
    @NonNull
    private User user;

    @OneToOne
    @org.springframework.lang.NonNull
    private Product product;

}
