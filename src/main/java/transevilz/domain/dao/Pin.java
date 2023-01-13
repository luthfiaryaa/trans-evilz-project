//package transevilz.domain.dao;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Table(schema = "public", name = "pin")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Pin {
//
//    @Column(name = "PIN", length = 20)
//    private String name;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinTable(  name = "pin",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "pin_id"))
//    @Id
//    private Long id;
//}
