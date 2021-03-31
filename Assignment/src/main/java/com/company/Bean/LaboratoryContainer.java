package com.company.Bean;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName Aggregate
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 22:42
 * @Version 1.0
 */
public class LaboratoryContainer implements Container {

    public List<Laboratory> list;

    @Override
    public Iterator getIterator() {
        return new Laboratory(list);
    }

}
