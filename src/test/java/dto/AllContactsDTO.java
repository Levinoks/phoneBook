package dto;

import datasetup.NewContactDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AllContactsDTO {
    NewContactDto[] contacts;
}
