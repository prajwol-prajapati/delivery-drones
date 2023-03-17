package com.musala.deliverydrones.medication;

@Getter
@Setter
@Entity
public class Medication {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double weight;

    @Column
    private String code;

    @Column
    private String image;
}
