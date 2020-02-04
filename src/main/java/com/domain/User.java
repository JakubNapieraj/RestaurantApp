package com.domain;


import com.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = "password", callSuper = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firtName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Order> orderHistoryList = new HashSet();

    public User(UserDTO dto) {
        this.password = dto.getPassword();
        this.firstName = dto.getFirstName();
        this.email = dto.getEmail();
        this.lastName = dto.getLastName();
        this.city = dto.getCity();
        this.streetNumber = dto.getSteetNumber();
        this.postCode = dto.getPostCode();
    }

}
