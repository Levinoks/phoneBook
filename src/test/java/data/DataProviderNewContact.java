package data;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderNewContact {
    @DataProvider
    public Iterator<Object[]> negativeTests(){
        List<Object[]> list=new ArrayList<>();
        return list.iterator();
    }
}
