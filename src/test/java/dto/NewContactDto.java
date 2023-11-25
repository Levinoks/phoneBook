package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NewContactDto {


String address;
String description;
String email;
String id; //always 0 need for API
String lastName;
String name;
String phone;

}

