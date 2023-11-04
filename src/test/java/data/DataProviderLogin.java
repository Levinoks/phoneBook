package data;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataProviderLogin {
    @DataProvider
    public Iterator<Object[]> positiveDataLogin() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{


                UserDtoLombok.builder()
                        .email("qwer1@hh.e")
                        .password("User#12345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwer1@hh.e")
                        .password("User#12345")
                        .build()
        });

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> negativePasswordDataLogin() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{


                UserDtoLombok.builder()
                        .email("qwer1@hh.e")
                        .password("User012345")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("qwer1@hh.e")
                        .password("user#12345")
                        .build()
        });

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginCSV() {
        List<Object[]> list = new ArrayList<>();
        String path = "src//test//resources//dataLogin.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while(line != null){
                String[] split=line.split(",");
                list.add(new Object[]{
                        UserDtoLombok.builder()
                                .email(split[0])
                                .password(split[1])
                                .build()
                });
                line= reader.readLine();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();

    }

}
