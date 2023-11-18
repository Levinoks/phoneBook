package datasetup;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FillData data=new FillData();
        List<DataCommonDTO> list=data.fillData();
        System.out.println(list.get(0).getUser().getEmail());
        System.out.println(list.get(0).getUser().getPassword());
        System.out.println(list.get(0).getContacts()[0].getName());
        System.out.println(list.get(0).getContacts()[1].getPhone());
        System.out.println(list.get(0).getContacts()[2].getAddress());
        System.out.println(list.get(0).getContacts()[2].getLastName());
    }
}
