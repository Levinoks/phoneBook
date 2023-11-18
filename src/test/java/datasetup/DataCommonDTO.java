package datasetup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class DataCommonDTO {
    UserDtoLombok user;
    NewContactDto [] contacts;


}
