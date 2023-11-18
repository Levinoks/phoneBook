package datasetup;

import manager.BaseHelper;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class FillData  {
    List<DataCommonDTO> list = new ArrayList<>();


    public List<DataCommonDTO> getList() {
        return list;
    }

    public List<DataCommonDTO> fillData() {
        UserDtoLombok user = new UserDtoLombok("qwer1@hh.e", "User#12345");
        NewContactDto contact1 = new NewContactDto("Haifa", "friend", "frnd22@gmail.com", "222", "Brown", "Dan", "1234567890");
        NewContactDto contact2 = new NewContactDto("Haifa", "brother", "brthr22@gmail.com", "722", "Wolf", "Dan", "1234599890");
        NewContactDto contact3 = new NewContactDto("Akko", "sister", "sstr12@gmail.com", "112", "Wolf", "Mary", "1924599890");
        NewContactDto[] contacts = {contact1, contact2, contact3};
        DataCommonDTO data = new DataCommonDTO(user, contacts);
        list.add(data);
        return list;
    }
}
