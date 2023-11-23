package dto;

import dto.NewContactDto;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AllContactsDTO {
    NewContactDto[] contacts;
}
