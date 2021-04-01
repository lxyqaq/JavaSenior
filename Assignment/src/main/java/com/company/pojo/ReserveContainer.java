package com.company.pojo;

import java.util.List;

/**
 * @ClassName ReserveContainer
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/3/31 23:15
 * @Version 1.0
 */
public class ReserveContainer implements Container {

    public List<Reserve> list;

    @Override
    public Iterator getIterator() {
        return new Reserve(list);
    }

}
